package hise.hznu.istudy.api;

import com.android.volley.Response;
import com.android.volley.Response.Listener;
import com.android.volley.Response.ErrorListener;
import com.android.volley.VolleyError;
import com.google.gson.JsonObject;

import org.json.JSONObject;

/**
 * Created by PC on 2016/7/21.
 */
public class ApiJsonLoadController  extends ApiAbsLoadController implements Listener<JSONObject>,ErrorListener {
    private ApiLoadListener mOnLoadListener;
    private int mAction = 0 ;

    public ApiJsonLoadController(ApiLoadListener requestListener,int actionId){
        this.mOnLoadListener = requestListener;
        this.mAction = actionId;
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        String errorMsg = null;
        if(error.getMessage()!=null){
            errorMsg = error.getMessage();
        }else{
            try{
                errorMsg = "Server Response Error("+ error.networkResponse.statusCode+")";
            }catch (Exception e){
                errorMsg = "Server Response Error";
            }
        }
        this.mOnLoadListener.onFailure(errorMsg,this.mAction);
    }

    @Override
    public void onResponse(JSONObject response) {
        String jsonString = response.toString();
        this.mOnLoadListener.onSuccess(response,this.mAction);
    }
}
