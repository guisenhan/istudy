package hise.hznu.istudy.api;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.RetryPolicy;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import hise.hznu.istudy.app.AppConfig;
import hise.hznu.istudy.app.AppConstant;

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
    public static void init(Context context){
        mRequestQueus  = Volley.newRequestQueue(context);
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
    public ApiJsonLoadController apiPostData(String url,HashMap jsonObject,final ApiRequestListener requestListener,int actionId){
       // Log.e("jsonobject"," "+jsonObject.toString());

       // String url1 = AppConstant.SERVER_URL +url;
        /*
        * 在这里根据接口的要求规范进行相应的处理 ，比如添加head 和map的字符串化
        * */
        return this.postJsonRequest(url,jsonObject,null,new ApiListenerHolder(requestListener),false,TIMEOUT_COUNT,RETRY_TIMES,actionId);
    }

    public <T>ApiJsonLoadController postJsonRequest(String url,HashMap params,final Map<String,String > headers,final ApiLoadListener requestListener
            ,boolean shouleCacher,int timeoutCount,int retryTimes,int actionId){
        if(requestListener == null){
            throw  new NullPointerException();
        }
        ApiJsonRequest request;

        final ApiJsonLoadController loadContrller = new ApiJsonLoadController(requestListener,actionId);
        if(params!=null){
            request = new ApiJsonRequest(Request.Method.GET,generateGetUrl(url,params),null,loadContrller,loadContrller);
        }else{
            request =new ApiJsonRequest(Request.Method.GET,url,generatePostParams(params),loadContrller,loadContrller);
        }
        request.setShouldCache(shouleCacher);
        if(headers!=null && !headers.isEmpty()){
            request.setHeaders(headers);
        }
        RetryPolicy retryPolicy = new DefaultRetryPolicy(timeoutCount,retryTimes,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        request.setRetryPolicy(retryPolicy);
        loadContrller.bindRequest(request);

        if(mRequestQueus == null){
            mRequestQueus = Volley.newRequestQueue(AppConfig.getContext());
        }
        requestListener.onStart();
        mRequestQueus.add(request);
        return  loadContrller;
    }
    public String generateGetUrl(String url,HashMap<String,String> params){
        StringBuilder builder = new StringBuilder("?");
        for(HashMap.Entry<String,String> entity:params.entrySet()){
            try {
                builder.append(URLEncoder.encode(entity.getKey(), "UTF-8"));
                builder.append("=");
                builder.append(URLEncoder.encode(entity.getValue(), "UTF-8"));
                builder.append("&");
            }catch (UnsupportedEncodingException e){

            }
        }
        return AppConstant.SERVER_URL + url+builder;
    }
    public JSONObject generatePostParams(HashMap<String,String> params){
        JSONObject jsonObject = new JSONObject();
        try{
            for(HashMap.Entry<String,String> entry:params.entrySet()){
                jsonObject.put(entry.getKey(),entry.getValue());
            }
        }catch (JSONException e){

        }
        return jsonObject;
    }
}
