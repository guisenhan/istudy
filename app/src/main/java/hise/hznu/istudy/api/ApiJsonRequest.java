package hise.hznu.istudy.api;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lenove on 2016/7/8.
 */
public class ApiJsonRequest extends JsonObjectRequest {

    private Map<String, String> mHeaders = new HashMap<String, String>(1);
    private  Response.Listener<JSONObject> mListener;

    public ApiJsonRequest(int method, String url, String requestBody, Response.Listener<JSONObject> listener,
                          Response.ErrorListener errorListener) {
        super(method, url, requestBody, listener, errorListener);
        this.mListener = listener;
    }

    public ApiJsonRequest(int method, String url, Response.Listener<JSONObject> listener,
                          Response.ErrorListener errorListener) {
        super(method, url, listener, errorListener);
    }

    public ApiJsonRequest(int method, String url, JSONObject jsonRequest, Response.Listener<JSONObject> listener,
                          Response.ErrorListener errorListener) {
        super(method, url, jsonRequest, listener, errorListener);
    }


//    @Override
//    public String getBodyContentType() {
//        return "application/x-www-form-urlencoded; charset=" + getParamsEncoding();
//    }

    public void setHeaders(Map<String, String> header) {
        mHeaders.putAll(header);
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        mHeaders.put("Accept", "application/json");
        mHeaders.put("Content-Type", "application/json; charset=UTF-8");

        return mHeaders;
    }

    @Override
    protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
        return super.parseNetworkResponse(response);
    }

    @Override
    protected void deliverResponse(JSONObject response) {
        this.mListener.onResponse(response);
    }
}
