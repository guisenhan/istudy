package hise.hznu.istudy.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.fastjson.JSONObject;
import com.squareup.leakcanary.RefWatcher;



import butterknife.ButterKnife;
import hise.hznu.istudy.api.ApiResponse;
import hise.hznu.istudy.api.RequestManager;
import hise.hznu.istudy.app.AppConstant;
import hise.hznu.istudy.app.IStudyApplication;
import hise.hznu.istudy.util.UIUtils;

/**
 * Created by PC on 2016/7/20.
 */
public class BaseFragment extends Fragment implements RequestManager.ApiRequestListener{
    public View rootView =null;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(initLayout(),container,false);
            // 通过注解绑定控件
            ButterKnife.bind(this, rootView);
            initView(rootView);
            initData();
        }
        ViewGroup parent = (ViewGroup) rootView.getParent();
        if (parent != null) {
            parent.removeView(rootView);
        }
        return rootView;
        // 注册事件总线对象
//        EventBus.getDefault().register(this);
    }

    protected int initLayout() {
        return 0;
    }

    protected void initView(View view){}

    protected void initData(){}


    @Override
    public void onRequest() { }

    @Override
    public void onSuccess(JSONObject response, int actionId) {
        ApiResponse apiResponse = new ApiResponse(response);
        if (apiResponse.getRetcode() == AppConstant.SERVER_ERROR_ERROR) {
            UIUtils.showToast("服务返回出错");
        }else {
            onApiResponseSuccess(apiResponse, actionId);
        }
    }

    protected void onApiResponseSuccess(ApiResponse apiResponse, int actionId) {

    }

    @Override
    public void onFailure(String errorMsg, int actionId) {
        UIUtils.showToast(errorMsg);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        RefWatcher refWatcher = IStudyApplication.getRefWatcher(getActivity());
        if(refWatcher != null){
            refWatcher.watch(this);
        }
    }
}
