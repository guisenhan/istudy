package hise.hznu.istudy.api;

import android.text.TextUtils;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.RetryPolicy;

import org.json.JSONObject;

import java.util.Map;

/**
 * Created by PC on 2016/7/21.
 */
public class RequestManager {
    private static final int TIMEOUT_COUNT = 10*1000 ;//超时时间
    private static final int RETRY_TIMES = 1 ; //重试次数
    private volatile static RequestManager mInstance = null;
    private static RequestQueue mRequestQueus = null;

    /**
     * Request result callBack
     */

    public interface ApiRequestListener{
        void onRequest();

        void onSuccess(JSONObject response, int actionId);

        void onFailure(String errorMsg,  int actionId);
    }

    /**
     * request Result CallBack
     */
    public interface RequestListener{
        void onRequest();
        void onSuccess(String response, Map<String,String> headers,String url,int actionId);
        void onFailure(String errorMsg,String url,int actionId);
    }
    private RequestManager(){}
    public static RequestManager getmInstance(){
        if(mInstance == null){
            synchronized (RequestManager.class){
                if(mInstance==null)
                    mInstance = new RequestManager();
            }
        }
        return mInstance;
    }
    public ApiJsonLoadController apiPostData(String url,JSONObject jsonObject,final ApiRequestListener requestListener,int actionId){
        /*
        * 在这里根据接口的要求规范进行相应的处理 ，比如添加head 和map的字符串化
        * */
        return this.postJsonRequest(url,jsonObject,null,new ApiListenerHolder(requestListener),false,TIMEOUT_COUNT,RETRY_TIMES,actionId);
    }

    public <T>ApiJsonLoadController postJsonRequest(String url,JSONObject jsonString,final
                                                    Map<String,String > headers,final
                                                    ApiLoadListener requestListener

    ,boolean shouleCacher,int timeoutCount,int retryTimes,int actionId){
        if(requestListener == null){
            throw  new NullPointerException();
        }
        ApiJsonRequest request;
        final ApiJsonLoadController loadContrller = new ApiJsonLoadController(requestListener,actionId);
        if(jsonString==null){
            request = new ApiJsonRequest(Request.Method.POST,url,null,loadContrller,loadContrller);
        }else{
            request =new ApiJsonRequest(Request.Method.POST,url,jsonString,loadContrller,loadContrller);
        }
        request.setShouldCache(shouleCacher);
        if(headers!=null && !headers.isEmpty()){
            request.setHeaders(headers);
        }
        RetryPolicy retryPolicy = new DefaultRetryPolicy(timeoutCount,retryTimes,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        request.setRetryPolicy(retryPolicy);
        loadContrller.bindRequest(request);

        if(mRequestQueus == null){
            throw  new NullPointerException();
        }
        requestListener.onStart();
        mRequestQueus.add(request);
        return  loadContrller;
    }
}
