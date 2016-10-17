package hise.hznu.istudy.api;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.RetryPolicy;
import com.android.volley.toolbox.Volley;


import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import hise.hznu.istudy.app.AppConfig;
import hise.hznu.istudy.app.AppConstant;
import hise.hznu.istudy.app.IStudyApplication;
import hise.hznu.istudy.util.SharePreUtil;

/**
 * Created by PC on 2016/7/21.
 */
public class RequestManager {
    private static final int TIMEOUT_COUNT = 10*1000 ;//超时时间
    private static final int RETRY_TIMES = 1 ; //重试次数
    private volatile static RequestManager mInstance = null;
    private static RequestQueue mRequestQueus = null;
    private IStudyApplication context = new IStudyApplication();
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

    public ApiJsonLoadControler apiPostData(String url,JSONObject jsonObject,final ApiRequestListener requestListener,int actionId){
       // Log.e("jsonobject"," "+jsonObject.toString());

       // String url1 = AppConstant.SERVER_URL +url;
        /*
        * 在这里根据接口的要求规范进行相应的处理 ，比如添加head 和map的字符串化
        * */
        String authenToken = SharePreUtil.getAuthorToken(AppConfig.getContext(),SharePreUtil.SP_NAME.AUTHOR_TOKEN);
        Log.e("authtoken",""+authenToken);
        jsonObject.put("authtoken", authenToken);
        return this.postJsonRequest(url,JSONObject.toJSONString(jsonObject),jsonObject,null,new ApiListenerHolder(requestListener),false,TIMEOUT_COUNT,RETRY_TIMES,actionId);
    }

    public <T>ApiJsonLoadControler postJsonRequest(String url,String params,JSONObject params1,final Map<String,String > headers,final ApiLoadListener requestListener
            ,boolean shouleCacher,int timeoutCount,int retryTimes,int actionId){
        if(requestListener == null){
            throw  new NullPointerException();
        }
        ApiJsonRequest request;
        final ApiJsonLoadControler loadContrller = new ApiJsonLoadControler(requestListener,actionId);
        if(params!=null){
            request = new ApiJsonRequest(Request.Method.GET,generateGetUrl(url,params1),params,loadContrller,loadContrller);
        }else{
            request =new ApiJsonRequest(Request.Method.GET,url,params,loadContrller,loadContrller);
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


    public String generateGetUrl(String url,JSONObject params){
        StringBuilder builder = new StringBuilder("?");

        for(JSONObject.Entry<String,Object> entity:params.entrySet()){
            try {
                builder.append(URLEncoder.encode(entity.getKey(), "UTF-8"));
                builder.append("=");
                builder.append(URLEncoder.encode((String)entity.getValue(), "UTF-8"));
                builder.append("&");
            }catch (UnsupportedEncodingException e){

            }
        }
        return AppConstant.SERVER_URL + url+builder;
    }
    public String generatePostParams(JSONObject params){
        JSONObject jsonObject = new JSONObject();
        try{
            for(JSONObject.Entry<String,Object> entry:params.entrySet()){
                jsonObject.put(entry.getKey(),(String)entry.getValue());
            }
        }catch (JSONException e){

        }
        return JSONObject.toJSONString(jsonObject);
    }
}
