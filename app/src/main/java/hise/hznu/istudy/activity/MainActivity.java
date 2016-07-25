package hise.hznu.istudy.activity;

import android.os.Bundle;

import com.shizhefei.view.indicator.FixedIndicatorView;
import com.shizhefei.view.viewpager.SViewPager;

import butterknife.BindView;
import butterknife.ButterKnife;
import hise.hznu.istudy.R;
import hise.hznu.istudy.base.BaseActivity;

/**
 *  Create by GuisenHan on 2016/7/25
 */
public class MainActivity extends BaseActivity {

    @BindView(R.id.fiv_main)
    FixedIndicatorView fivMain;
    @BindView(R.id.svp_main)
    SViewPager svpMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }
}
