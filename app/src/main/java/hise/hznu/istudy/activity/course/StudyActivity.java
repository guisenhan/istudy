package hise.hznu.istudy.activity.course;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.shizhefei.view.indicator.FixedIndicatorView;
import com.shizhefei.view.indicator.IndicatorViewPager;
import com.shizhefei.view.indicator.slidebar.ColorBar;
import com.shizhefei.view.indicator.transition.OnTransitionTextListener;
import com.shizhefei.view.viewpager.SViewPager;

import butterknife.BindView;
import butterknife.ButterKnife;
import hise.hznu.istudy.R;
import hise.hznu.istudy.activity.fragment.fragmentAdpater.MainFragmentAdapter;
import hise.hznu.istudy.activity.fragment.fragmentAdpater.StudyFragmentAdapter;
import hise.hznu.istudy.api.ApiResponse;
import hise.hznu.istudy.base.BaseActivity;
import hise.hznu.istudy.model.course.CourseEntity;


public class StudyActivity extends BaseActivity implements View.OnClickListener{

    @BindView(R.id.iv_back)
    ImageView tvBack;
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
        fivStudy.setScrollBar(new ColorBar(getApplicationContext(), getResources().getColor(R.color.colorPrimary),5));
        indicator = new IndicatorViewPager(fivStudy,svpStudy);
        indicator.setAdapter(new StudyFragmentAdapter(this,getSupportFragmentManager(),courseEntity));
        indicator.setIndicatorOnTransitionListener(new OnTransitionTextListener(13,13,getResources().getColor(R.color
                .colorPrimary),getResources().getColor(R.color
                .text_important_color)));
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
