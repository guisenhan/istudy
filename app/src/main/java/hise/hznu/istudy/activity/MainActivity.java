package hise.hznu.istudy.activity;

import android.os.Bundle;


import com.shizhefei.view.indicator.FixedIndicatorView;
import com.shizhefei.view.indicator.IndicatorViewPager;
import com.shizhefei.view.indicator.transition.OnTransitionTextListener;
import com.shizhefei.view.viewpager.SViewPager;

import butterknife.BindView;
import butterknife.ButterKnife;
import hise.hznu.istudy.R;
import hise.hznu.istudy.activity.fragment.fragmentAdpater.MainFragmentAdapter;
import hise.hznu.istudy.base.BaseActivity;
import hise.hznu.istudy.lib.SlidingMenu;
import hise.hznu.istudy.util.AppUtils;
import hise.hznu.istudy.util.MiscUtils;


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
        indicator.setIndicatorOnTransitionListener(new OnTransitionTextListener(12,
                12,getResources().getColor(R.color.colorPrimary),getResources().getColor(R.color
                .text_important_color)));
    }

}
