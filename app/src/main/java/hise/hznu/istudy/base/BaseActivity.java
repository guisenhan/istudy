package hise.hznu.istudy.base;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.Window;


import com.alibaba.fastjson.JSONObject;
import com.pgyersdk.crash.PgyCrashManager;
import com.pgyersdk.feedback.PgyFeedbackShakeManager;

import java.util.Map;

import butterknife.ButterKnife;
import hise.hznu.istudy.api.ApiResponse;
import hise.hznu.istudy.api.RequestManager;
import hise.hznu.istudy.app.AppConstant;
import hise.hznu.istudy.app.AppManager;
import hise.hznu.istudy.util.UIUtils;

/**
 * Created by PC on 2016/7/20.
 */
public class BaseActivity extends FragmentActivity implements RequestManager.ApiRequestListener{
    protected final String TAG = getClass().getSimpleName();
    protected Context mContext;
    protected LayoutInflater mInflater;
    protected SharedPreferences mSharePref;
    protected ConnectivityManager mConnectManager;
    protected Dialog mProgressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //layout加载前的操作
        onBeforeSetContentLayout();
        //初始化layout
        if (initLayout() != 0) {
            setContentView(initLayout());
        }
        // 注册依赖注入对象
        ButterKnife.bind(this);
//      x.view().inject(this);
        // 注册事件总线对象
        if(getIntent() != null && getIntent().getExtras() != null){
            initExtras(getIntent().getExtras());
        }
        PgyCrashManager.register(this);
//      EventBus.getDefault().register(this);
        //添加activity到堆栈中
        AppManager.getInstance().addActivity(this);
        // 初始化视图
        initView(savedInstanceState);
        // 初始化数据
        initData();
    }

    protected void initExtras(Bundle extras){

    }
    protected int initLayout() {
        return 0;
    }

    protected void onBeforeSetContentLayout() {
    }

    protected void initView(Bundle savedInstanceState) {
    }

    protected void initData() {
    }

    @Override
    protected void onPause() {
        super.onPause();
        PgyFeedbackShakeManager.unregister();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // 自定义摇一摇的灵敏度，默认为950，数值越小灵敏度越高。
        PgyFeedbackShakeManager.setShakingThreshold(800);

        // 以对话框的形式弹出
        PgyFeedbackShakeManager.register(this);

        // 以Activity的形式打开，这种情况下必须在AndroidManifest.xml配置FeedbackActivity
        // 打开沉浸式,默认为false
        // FeedbackActivity.setBarImmersive(true);
        PgyFeedbackShakeManager.register(this, false);
    }

    @Override
    protected void onDestroy() {
        // 注销事件总线对象
//        EventBus.getDefault().unregister(this);
        // 关闭时在Activity中删除
        PgyCrashManager.unregister();
        AppManager.getInstance().removeActivty(this);
        super.onDestroy();
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        //noinspection StatementWithEmptyBody
        if (requestCode == 1000 & resultCode == 1001) {

        }
    }

    @Override
    public void onFailure(String errorMsg, int actionId) {

    }

    @Override
    public void onSuccess(JSONObject response, int actionId) {
        ApiResponse response1 = new ApiResponse(response);
        if(response1.getRetcode() != AppConstant.SERVER_ERROR_ERROR){
            onApiresponseSuccess(response1,actionId);
        }
    }
    public void onApiresponseSuccess(ApiResponse response,int actionId){

    };
    @Override
    public void onRequest() { }
}
