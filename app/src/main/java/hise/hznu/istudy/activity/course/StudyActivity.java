package hise.hznu.istudy.activity.course;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


import butterknife.BindView;
import butterknife.ButterKnife;
import hise.hznu.istudy.R;
import hise.hznu.istudy.activity.fragment.fragmentAdpater.MainFragmentAdapter;
import hise.hznu.istudy.activity.fragment.fragmentAdpater.StudyFragmentAdapter;
import hise.hznu.istudy.api.ApiResponse;
import hise.hznu.istudy.base.BaseActivity;
import hise.hznu.istudy.model.course.CourseEntity;
import hise.hznu.istudy.shizhefei.view.indicator.FixedIndicatorView;
import hise.hznu.istudy.shizhefei.view.indicator.IndicatorViewPager;
import hise.hznu.istudy.shizhefei.view.indicator.slidebar.ColorBar;
import hise.hznu.istudy.shizhefei.view.viewpager.SViewPager;

public class StudyActivity extends BaseActivity implements View.OnClickListener{

    @BindView(R.id.tv_back)
    TextView tvBack;
    @BindView(R.id.fiv_study)
    FixedIndicatorView fivStudy;
    @BindView(R.id.svp_study)
    SViewPager svpStudy;

    private IndicatorViewPager indicator;
    private CourseEntity courseEntity;

    @Override
    public void initData() {
        super.initData();
        courseEntity = (CourseEntity)getIntent().getExtras().get("course");
        fivStudy.setScrollBar(new ColorBar(getApplicationContext(), Color.WHITE,5));

        indicator = new IndicatorViewPager(fivStudy,svpStudy);
        indicator.setAdapter(new StudyFragmentAdapter(this,getSupportFragmentManager(),courseEntity));
        tvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_study;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
    }

    @Override
    public void onApiresponseSuccess(ApiResponse response, int actionId) {
        super.onApiresponseSuccess(response, actionId);
    }

    @Override
    public void onFailure(String errorMsg, int actionId) {
        super.onFailure(errorMsg, actionId);
    }

    @Override
    public void onClick(View v) {

    }
}
