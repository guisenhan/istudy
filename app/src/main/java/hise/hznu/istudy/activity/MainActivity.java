package hise.hznu.istudy.activity;

import android.os.Bundle;


import butterknife.BindView;
import butterknife.ButterKnife;
import hise.hznu.istudy.R;
import hise.hznu.istudy.activity.fragment.fragmentAdpater.MainFragmentAdapter;
import hise.hznu.istudy.base.BaseActivity;
import hise.hznu.istudy.shizhefei.view.indicator.FixedIndicatorView;
import hise.hznu.istudy.shizhefei.view.indicator.IndicatorViewPager;
import hise.hznu.istudy.shizhefei.view.viewpager.SViewPager;

/**
 *  Create by GuisenHan on 2016/7/25
 */
public class MainActivity extends BaseActivity {

    @BindView(R.id.fiv_main)
    FixedIndicatorView fivMain;
    @BindView(R.id.svp_main)
    SViewPager svpMain;
    private IndicatorViewPager indicator;

    @Override
    protected int initLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initData() {
        super.initData();
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        indicator = new IndicatorViewPager(fivMain,svpMain);
        indicator.setAdapter(new MainFragmentAdapter(this,getSupportFragmentManager()));
    }

}
