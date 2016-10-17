package hise.hznu.istudy.activity.fragment.study;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hise.hznu.istudy.R;
import hise.hznu.istudy.activity.course.CommentEachActivity;
import hise.hznu.istudy.activity.course.MyExerciseActivity;
import hise.hznu.istudy.activity.course.MyExperimentActivity;
import hise.hznu.istudy.activity.course.MyHomeWorkActivity;
import hise.hznu.istudy.activity.course.StudyDatumActivity;
import hise.hznu.istudy.api.ApiResponse;
import hise.hznu.istudy.base.BaseFragment;
import hise.hznu.istudy.model.course.CourseEntity;

/**
 * Created by PC on 2016/9/21.
 */
public class MyStudyFragment extends BaseFragment {


    @BindView(R.id.ll_study_info)
    LinearLayout llStudyInfo;
    @BindView(R.id.ll_my_homework)
    LinearLayout llMyHomework;
    @BindView(R.id.icon_comment_each)
    LinearLayout iconCommentEach;
    @BindView(R.id.ll_my_experiment)
    LinearLayout llMyExperiment;
    @BindView(R.id.ll_practice)
    LinearLayout llPractice;
    @BindView(R.id.ll_talk_zone)
    LinearLayout llTalkZone;

    private CourseEntity courseEntity;
    @Override
    protected void initData() {
        super.initData();
        courseEntity =(CourseEntity) getArguments().getSerializable("course");
    }

    @Override
    protected int initLayout() {
        return R.layout.fragment_my_study;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
    }

    @Override
    protected void onApiResponseSuccess(ApiResponse apiResponse, int actionId) {
        super.onApiResponseSuccess(apiResponse, actionId);
    }

    @OnClick({R.id.ll_study_info, R.id.ll_my_homework, R.id.icon_comment_each, R.id.ll_my_experiment, R.id.ll_practice, R.id.ll_talk_zone})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_study_info:
                Intent datum = new Intent(getActivity(), StudyDatumActivity.class);
                datum.putExtra("courseId",courseEntity.getId());
                startActivity(datum);
                break;
            case R.id.ll_my_homework:
                Intent homework = new Intent(getActivity(), MyHomeWorkActivity.class);
                homework.putExtra("courseId",courseEntity.getId());
                startActivity(homework);
                break;
            case R.id.icon_comment_each:
                Intent comment = new Intent(getActivity(), CommentEachActivity.class);
                comment.putExtra("courseId",courseEntity.getId());
                startActivity(comment);
                break;
            case R.id.ll_my_experiment:
                Intent experiment = new Intent(getActivity(),MyExperimentActivity.class);
                experiment.putExtra("courseId",courseEntity.getId());
                startActivity(experiment);
                break;
            case R.id.ll_practice:
                Intent exercise = new Intent(getActivity(),MyExerciseActivity.class);
                exercise.putExtra("courseId",courseEntity.getId());
                startActivity(exercise);
                break;
            case R.id.ll_talk_zone:
                break;
        }
    }
}
