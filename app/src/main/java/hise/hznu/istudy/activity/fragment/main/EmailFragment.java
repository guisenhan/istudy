package hise.hznu.istudy.activity.fragment.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;

import com.alibaba.fastjson.JSONObject;

import hise.hznu.istudy.R;
import hise.hznu.istudy.api.ApiResponse;
import hise.hznu.istudy.base.BaseFragment;

/**
 * Created by GuisenHan on 2016/7/25.
 */
public class EmailFragment extends BaseFragment{
    @Override
    protected void initData() {
        super.initData();
    }

    @Override
    protected int initLayout() {
        return R.layout.fragment_indoor_email;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
    }

    @Override
    protected void onApiResponseSuccess(ApiResponse apiResponse, int actionId) {
        super.onApiResponseSuccess(apiResponse, actionId);
    }

    @Override
    public void onFailure(String errorMsg, int actionId) {
        super.onFailure(errorMsg, actionId);
    }

    @Override
    public void onSuccess(JSONObject response, int actionId) {
        super.onSuccess(response, actionId);
    }

}
