package hise.hznu.istudy.activity.fragment.study;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hise.hznu.istudy.R;
import hise.hznu.istudy.api.ApiResponse;
import hise.hznu.istudy.base.BaseFragment;

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

    @Override
    protected void initData() {
        super.initData();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @OnClick({R.id.ll_study_info, R.id.ll_my_homework, R.id.icon_comment_each, R.id.ll_my_experiment, R.id.ll_practice, R.id.ll_talk_zone})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_study_info:
                break;
            case R.id.ll_my_homework:
                break;
            case R.id.icon_comment_each:
                break;
            case R.id.ll_my_experiment:
                break;
            case R.id.ll_practice:
                break;
            case R.id.ll_talk_zone:
                break;
        }
    }
}
