package hise.hznu.istudy.api;

import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceActivity;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import org.json.JSONArray;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;
import hise.hznu.istudy.app.AppConfig;
import hise.hznu.istudy.util.SharePreUtil;

/**
 * Created by PC on 2016/10/9.
 */
public class AsyHttpClient {
        private static final String BASE_URL = "http://dodo.hznu.edu.cn/api/";

        private static AsyncHttpClient client = new AsyncHttpClient();

        public static void get(final String url, RequestParams params, final Handler handler) {
            String authenToken = SharePreUtil.getAuthorToken(AppConfig.getContext(),SharePreUtil.SP_NAME.AUTHOR_TOKEN);
            params.put("authtoken", authenToken);
            client.post(getAbsoluteUrl(url), params, new JsonHttpResponseHandler() {
                @Override
                public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                    super.onFailure(statusCode, headers, responseString, throwable);
                    Log.d(" request Failure","url:"+url +" statusCode:"+statusCode + " responseString: "+responseString);
                }

                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                    super.onSuccess(statusCode, headers, response);
//                 //   ApiResponse response1 = new ApiResponse(JSON.parseObject(com.alibaba.fastjson.JSONObject
//                            .toJSONString(response)));
                    Message message = new Message();
                    message.obj = response;
                    handler.sendMessage(message);
                    Log.i("request success"," url: "+url +" response : " +response.toString() );
                }

                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    super.onSuccess(statusCode, headers, response);
                    Log.i("rsadsad equest success", "url:"+url +" response:" + response);
                   ApiResponse response1 = new ApiResponse(JSON.parseObject(com.alibaba.fastjson.JSONObject.toJSONString(response)));
                    Message message = new Message();
                    message.obj = response;
                    handler.sendMessage(message);
//                   // ApiResponse response1 = new ApiResponse(com.alibaba.fastjson.JSONObject.parseObject(response
//                            .toString()));
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
