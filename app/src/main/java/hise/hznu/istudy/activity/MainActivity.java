package hise.hznu.istudy.activity;

import android.os.Bundle;

import com.shizhefei.view.indicator.FixedIndicatorView;
import com.shizhefei.view.indicator.Indicator;
import com.shizhefei.view.indicator.IndicatorViewPager;
import com.shizhefei.view.viewpager.SViewPager;

import butterknife.BindView;
import butterknife.ButterKnife;
import hise.hznu.istudy.R;
import hise.hznu.istudy.activity.fragment.fragmentAdpater.MainFragmentAdapter;
import hise.hznu.istudy.base.BaseActivity;

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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initViews();
    }

    @Override
    public void initData() {
        super.initData();
    }

    @Override
    public void initViews() {
        super.initViews();
        indicator = new IndicatorViewPager(fivMain,svpMain);
        indicator.setAdapter(new MainFragmentAdapter(this,getSupportFragmentManager()));
    }
}
