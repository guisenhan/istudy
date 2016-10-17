package hise.hznu.istudy.activity.fragment.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hise.hznu.istudy.R;
import hise.hznu.istudy.activity.adapter.CourseAdapter;
import hise.hznu.istudy.api.ApiResponse;
import hise.hznu.istudy.api.RequestManager;
import hise.hznu.istudy.app.AppConstant;
import hise.hznu.istudy.base.BaseFragment;
import hise.hznu.istudy.model.course.CourseEntity;

/**
 * Created by Guisen Han on 2016/7/25.
 */
public class CourseFragment extends BaseFragment {


    @BindView(R.id.iv_search)
    ImageView ivSearch;
    @BindView(R.id.lv_course)
    ListView lvCourse;
    private List<CourseEntity> _dataList = new ArrayList<CourseEntity>();
    private CourseAdapter adapter;

    @Override
    protected void initData() {
        super.initData();
        JSONObject params = new JSONObject();
        RequestManager.getmInstance().apiPostData(AppConstant.GET_COURSE_ACTION, params, this, AppConstant.POST_GET_COURSE_ACTION);
    }

    @Override
    protected int initLayout() {
        return R.layout.fragment_course;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        adapter = new CourseAdapter(getActivity());
        lvCourse.setAdapter(adapter);
    }

    @Override
    protected void onApiResponseSuccess(ApiResponse apiResponse, int actionId) {
        super.onApiResponseSuccess(apiResponse, actionId);
        _dataList = apiResponse.getListData(CourseEntity.class);
        adapter.UpdateView(_dataList);
    }


}
