package hise.hznu.istudy.activity.fragment.study;

import android.util.Log;
import android.view.View;

import com.alibaba.fastjson.JSONObject;

import hise.hznu.istudy.R;
import hise.hznu.istudy.api.ApiResponse;
import hise.hznu.istudy.api.RequestManager;
import hise.hznu.istudy.app.AppConstant;
import hise.hznu.istudy.base.BaseFragment;
import hise.hznu.istudy.model.course.CourseEntity;

/**
 * Created by PC on 2016/9/21.
 */
public class CourseMessageFragment extends BaseFragment{
    private CourseEntity courseEntity;
    @Override
    protected void initData() {
        super.initData();
        courseEntity = (CourseEntity) getArguments().getSerializable("course");
        JSONObject params = new JSONObject();
        params.put("courseid",courseEntity.getId());
        RequestManager.getmInstance().apiPostData(AppConstant.GET_COURSE_NOTICE_ACTION,params,this,AppConstant.POST_GET_CORSE_NOTICE_ACTION);
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
    public void onSuccess(JSONObject response, int actionId) {
        super.onSuccess(response, actionId);
        Log.e("courseNotice",""+response);
    }

    @Override
    protected void onApiResponseSuccess(ApiResponse apiResponse, int actionId) {
        super.onApiResponseSuccess(apiResponse, actionId);
    }
}
