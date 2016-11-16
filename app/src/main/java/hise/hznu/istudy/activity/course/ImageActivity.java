package hise.hznu.istudy.activity.course;

import android.os.Bundle;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hise.hznu.istudy.R;
import hise.hznu.istudy.base.BaseActivity;
import hise.hznu.istudy.util.ImageLoaderUtils;

public class ImageActivity extends BaseActivity {
    @BindView(R.id.iv_cancel)
    ImageView ivCancel;
    @BindView(R.id.iv_image)
    ImageView ivImage;

    @Override
    protected void initData() {
        super.initData();
    }

    @Override
    protected void initExtras(Bundle extras) {
        super.initExtras(extras);
        ImageLoaderUtils.getImageLoader().displayImage(extras.getString("img"),ivImage);
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_image;
    }


    @OnClick(R.id.iv_cancel)
    public void onClick() {
        finish();
    }
}
