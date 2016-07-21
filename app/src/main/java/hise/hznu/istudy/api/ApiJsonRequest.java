package hise.hznu.istudy.api;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by PC on 2016/7/21.
 */
public class ApiJsonRequest extends JsonObjectRequest{

    private Map<String,String> mheaders = new HashMap<String,String>();
    private Response.Listener<JSONObject> mListener;
    public ApiJsonRequest(int method, String url, JSONObject jsonRequest, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
        super(method, url, jsonRequest, listener, errorListener);
        this.mListener = listener;
    }
    public ApiJsonRequest(String url, JSONObject jsonRequest, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
        super(url, jsonRequest, listener, errorListener);
    }

    public void setHeaders(Map<String,String> headers){
        mheaders.putAll(headers);
    }
    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
       mheaders.put("Accept","application/json");
        mheaders.put("Content-type","application/json;charset=UTF-8");
        return mheaders;
    }

    @Override
    protected void deliverResponse(JSONObject response) {
        super.deliverResponse(response);
    }

    @Override
    protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
        return super.parseNetworkResponse(response);
    }
}
