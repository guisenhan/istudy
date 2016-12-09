package hise.hznu.istudy.activity.fragment.main;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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
import hise.hznu.istudy.activity.course.StudyActivity;
import hise.hznu.istudy.api.ApiResponse;
import hise.hznu.istudy.api.RequestManager;
import hise.hznu.istudy.app.AppConstant;
import hise.hznu.istudy.base.BaseFragment;
import hise.hznu.istudy.model.UserInfo;
import hise.hznu.istudy.model.UserInfoEntity;
import hise.hznu.istudy.model.course.CourseEntity;
import hise.hznu.istudy.util.MiscUtils;
import hise.hznu.istudy.widget.CircleRefreshLayout;
import hise.hznu.istudy.widget.LoadView;

/**
 * Created by Guisen Han on 2016/7/25.
 */
public class CourseFragment extends BaseFragment {
    @BindView(R.id.lv_course)
    ListView lvCourse;
    @BindView(R.id.load_view)
    LoadView loadView;
    private List<CourseEntity> _dataList = new ArrayList<CourseEntity>();
    private CourseAdapter adapter;

    @Override
    protected void initData() {
        super.initData();
        JSONObject params = new JSONObject();
        RequestManager.getmInstance().apiPostData(AppConstant.GET_COURSE_ACTION, params, this, AppConstant.POST_GET_COURSE_ACTION);
        RequestManager.getmInstance()
                .apiPostData(AppConstant.GET_USERINFO, params, this, AppConstant.POST_GET_USERINFO);
        loadView.showLoading();
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
        lvCourse.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent study = new Intent(getActivity(), StudyActivity.class);
                study.putExtra("course",_dataList.get(i));
                startActivity(study);
            }
        });

    }
    @Override
    protected void onApiResponseSuccess(ApiResponse apiResponse, int actionId) {
        super.onApiResponseSuccess(apiResponse, actionId);
        switch (actionId){
            case AppConstant.POST_GET_USERINFO:
                UserInfoEntity userInfoEntity;
                userInfoEntity = apiResponse.getInfo(UserInfoEntity.class);
                MiscUtils.setSharedPreferenceValue("userInfo","userInfo",JSONObject.toJSONString(userInfoEntity));
                break;
            case AppConstant.POST_GET_COURSE_ACTION:
                _dataList = apiResponse.getListData(CourseEntity.class);
                loadView.clearLoad();
                if(_dataList.size() == 0 ){
                    loadView.showNoData();
                }else{
                    adapter.UpdateView(_dataList);
                }
                break;
        }

    }
}
