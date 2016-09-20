package hise.hznu.istudy.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.Window;


import com.alibaba.fastjson.JSONObject;

import hise.hznu.istudy.api.RequestManager;
import hise.hznu.istudy.app.AppManager;

/**
 * Created by PC on 2016/7/20.
 */
public class BaseActivity extends FragmentActivity implements RequestManager.ApiRequestListener{
    private AppManager appManager ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        appManager = AppManager.getInstance();
        appManager.addActivity(this);
    }
    public void initData(){

    }
    public void initViews(){

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        appManager.removeActivty(this);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onFailure(String errorMsg, int actionId) {

    }

    @Override
    public void onRequest() {

    }

    @Override
    public void onSuccess(JSONObject response, int actionId) {

    }
}
