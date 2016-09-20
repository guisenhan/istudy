package hise.hznu.istudy.activity.fragment.main;

import android.view.View;

import com.alibaba.fastjson.JSONObject;

import hise.hznu.istudy.R;
import hise.hznu.istudy.base.BaseFragment;

/**
 * Created by GuisenHan on 2016/7/25.
 */
public class ExamFragment extends BaseFragment{
    @Override
    protected void initData() {
        super.initData();
    }

    @Override
    protected int initLayout() {
        return R.layout.fragment_exam;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
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
