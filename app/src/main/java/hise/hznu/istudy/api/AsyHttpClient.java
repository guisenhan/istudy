package hise.hznu.istudy.api;

import android.preference.PreferenceActivity;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import org.json.JSONArray;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

/**
 * Created by PC on 2016/10/9.
 */
public class AsyHttpClient {
        private static final String BASE_URL = "https://api.twitter.com/1/";

        private static AsyncHttpClient client = new AsyncHttpClient();

        public static void get(final String url, RequestParams params) {
            client.get(getAbsoluteUrl(url), params, new JsonHttpResponseHandler() {
                @Override
                public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                    super.onFailure(statusCode, headers, responseString, throwable);
                    Log.d(" request Failure","url:"+url +" statusCode:"+statusCode + " responseString: "+responseString);
                }

                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                    super.onSuccess(statusCode, headers, response);

                    Log.i("request success"," url: "+url +" response : " +response.toString() );
                }

                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    super.onSuccess(statusCode, headers, response);
                    Log.i("request success", "url:"+url +" response:" + response);
                    ApiResponse response1 = new ApiResponse(com.alibaba.fastjson.JSONObject.parseObject(response.toString()));
                }

                @Override
                public void onSuccess(int statusCode, Header[] headers, String responseString) {
                    super.onSuccess(statusCode, headers, responseString);
                }
            });
        }

        public static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
            client.post(getAbsoluteUrl(url), params, responseHandler);
        }

        private static String getAbsoluteUrl(String relativeUrl) {
            return BASE_URL + relativeUrl;
        }
}
