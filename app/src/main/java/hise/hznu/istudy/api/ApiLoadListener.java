package hise.hznu.istudy.api;


import com.alibaba.fastjson.JSONObject;

/**
 * Created by PC on 2016/7/21.
 */
public interface ApiLoadListener {
    void onSuccess(JSONObject jsonObject, int actionId);
    void onFailure(String errorMsg,int actionId);
    void onStart();
}
