package hise.hznu.istudy.api;

import android.app.Activity;

import org.json.JSONObject;

import java.lang.ref.WeakReference;

/**
 * Created by PC on 2016/7/21.
 */
public class ApiListenerHolder implements ApiLoadListener{
    private WeakReference<RequestManager.ApiRequestListener> mRequestListenerRef;
    private RequestManager.ApiRequestListener mRequestListener;
    public ApiListenerHolder(RequestManager.ApiRequestListener requestListener){
        if(requestListener instanceof Activity){
            this.mRequestListenerRef = new WeakReference<RequestManager.ApiRequestListener>(requestListener);
        }else{
            this.mRequestListener = requestListener;
        }
    }

    @Override
    public void onFailure(String errorMsg, int actionId) {
        if(mRequestListenerRef!=null){
            RequestManager.ApiRequestListener requestListener = mRequestListenerRef.get();
            if(requestListener!=null){
                requestListener.onFailure(errorMsg,actionId);
                return;
            }
        }
        if(mRequestListener!=null){
            mRequestListener.onFailure(errorMsg,actionId);
        }
    }

    @Override
    public void onSuccess(JSONObject jsonObject, int actionId) {
        if(mRequestListenerRef!=null){
            RequestManager.ApiRequestListener requestListener = mRequestListenerRef.get();
            if(requestListener!=null){
                requestListener.onSuccess(jsonObject,actionId);
                return;
            }
        }
        if(this.mRequestListener!=null){
            this.mRequestListener.onSuccess(jsonObject,actionId);
        }
    }

    @Override
    public void onStart() {
        if(mRequestListenerRef!=null){
            RequestManager.ApiRequestListener requestListener = mRequestListenerRef.get();
            if(requestListener!=null){
                requestListener.onRequest();
                return;
            }
        }
        if(mRequestListener!=null){
            mRequestListener.onRequest();
        }
    }
}
