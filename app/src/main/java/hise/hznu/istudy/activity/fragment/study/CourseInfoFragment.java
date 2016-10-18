package hise.hznu.istudy.activity.fragment.study;

import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import hise.hznu.istudy.R;
import hise.hznu.istudy.api.ApiResponse;
import hise.hznu.istudy.base.BaseFragment;
import hise.hznu.istudy.model.course.CourseEntity;

/**
 * Created by PC on 2016/9/21.
 */
public class CourseInfoFragment extends BaseFragment {
    @BindView(R.id.tv_course_title)
    TextView tvCourseTitle;
    @BindView(R.id.tv_course_info)
    TextView tvCourseInfo;

    private CourseEntity courseEntity;
    @Override
    protected void initData() {
        super.initData();
        courseEntity = (CourseEntity)getArguments().getSerializable("course");
        tvCourseInfo.setText(Html.fromHtml(courseEntity.getMemo()));
        tvCourseTitle.setText(courseEntity.getTitle());
    }

    @Override
    protected int initLayout() {
        return R.layout.fragment_course_info;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
    }

    @Override
    protected void onApiResponseSuccess(ApiResponse apiResponse, int actionId) {
        super.onApiResponseSuccess(apiResponse, actionId);
    }

}
