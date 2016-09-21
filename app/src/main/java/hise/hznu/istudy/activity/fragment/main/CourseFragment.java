package hise.hznu.istudy.activity.fragment.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import com.alibaba.fastjson.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hise.hznu.istudy.R;
import hise.hznu.istudy.api.ApiResponse;
import hise.hznu.istudy.base.BaseFragment;

/**
 * Created by Guisen Han on 2016/7/25.
 */
public class CourseFragment extends BaseFragment {
    @BindView(R.id.iv_search)
    ImageView ivSearch;
    @BindView(R.id.lv_course)
    ListView lvCourse;

    @Override
    protected int initLayout() {
        return R.layout.fragment_course;
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
    public void onSuccess(JSONObject response, int actionId) {
        super.onSuccess(response, actionId);
    }


    @OnClick(R.id.iv_search)
    public void onClick() {

    }
}
