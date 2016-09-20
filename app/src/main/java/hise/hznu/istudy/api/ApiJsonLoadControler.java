package hise.hznu.istudy.api;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONObject;

/**
 * Created by PC on 2016/9/20.
 */
public class ApiJsonLoadControler extends ApiAbsLoadControler implements
        Response.Listener<JSONObject>, Response.ErrorListener {
    private ApiLoadListener mOnLoadListener;
    private int mAction = 0;

    public ApiJsonLoadControler(ApiLoadListener requestListener, int actionId) {
        this.mOnLoadListener = requestListener;
        this.mAction = actionId;
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        String errorMsg = null;
        if (error.getMessage() != null) {
            errorMsg = error.getMessage();
        } else {
            try {
                errorMsg = "Server Response Error ("
                        + error.networkResponse.statusCode + ")";
            } catch (Exception e) {
                errorMsg = "Server Response Error";
            }
        }
        this.mOnLoadListener.onFailure(errorMsg,this.mAction);
    }


    @Override
    public void onResponse(org.json.JSONObject jsonObject) {
        String jsonString = jsonObject.toString();
        com.alibaba.fastjson.JSONObject fastJsonObject  = com.alibaba.fastjson.JSONObject.parseObject(jsonString);
        this.mOnLoadListener.onSuccess(fastJsonObject, this.mAction);
    }
}

