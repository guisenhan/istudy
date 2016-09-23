package hise.hznu.istudy.activity.fragment.study;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import hise.hznu.istudy.R;
import hise.hznu.istudy.activity.adapter.CourseNoticeAdapter;
import hise.hznu.istudy.api.ApiResponse;
import hise.hznu.istudy.api.RequestManager;
import hise.hznu.istudy.app.AppConstant;
import hise.hznu.istudy.base.BaseFragment;
import hise.hznu.istudy.model.course.CourseEntity;
import hise.hznu.istudy.model.course.CourseNoticeEntity;

/**
 * Created by PC on 2016/9/21.
 */
public class CourseMessageFragment extends BaseFragment {
    @BindView(R.id.lv_course_message)
    ListView lvCourseMessage;
    private CourseEntity courseEntity;
    List<CourseNoticeEntity> _dataList = new ArrayList<CourseNoticeEntity>();
    CourseNoticeAdapter adapter;

    @Override
    protected void initData() {
        super.initData();
        courseEntity = (CourseEntity) getArguments().getSerializable("course");
        JSONObject params = new JSONObject();
        params.put("courseid", courseEntity.getId());
        RequestManager.getmInstance().apiPostData(AppConstant.GET_COURSE_NOTICE_ACTION, params, this, AppConstant.POST_GET_CORSE_NOTICE_ACTION);
        adapter = new CourseNoticeAdapter(getActivity());
        lvCourseMessage.setAdapter(adapter);
    }

    @Override
    protected int initLayout() {
        return R.layout.fragment_course_message;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
    }

    @Override
    protected void onApiResponseSuccess(ApiResponse apiResponse, int actionId) {
        super.onApiResponseSuccess(apiResponse, actionId);
        _dataList = apiResponse.getListData(CourseNoticeEntity.class);
        adapter.UpdateView(_dataList);
    }

}
