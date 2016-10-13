package hise.hznu.istudy.util.clip;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.RelativeLayout;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import java.io.File;

import hise.hznu.istudy.app.AppConfig;
import hise.hznu.istudy.util.ImageLoaderUtils;
import hise.hznu.istudy.util.MiscUtils;
import hise.hznu.istudy.util.UIUtils;

public class ClipImageLayout extends RelativeLayout {
    private ClipZoomImageView mZoomImageView;
    private ClipImageBorderView mClipImageView;
    private ProgressDialog progressDialog;
    private int mHorizontalPadding = 20;

    public ClipImageLayout(Context context) {
        super(context);
        this.doInit(context);
    }

    public ClipImageLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.doInit(context);
    }

    private void doInit(Context context) {
        this.mZoomImageView = new ClipZoomImageView(context);
        this.mClipImageView = new ClipImageBorderView(context);
        LayoutParams lp = new LayoutParams(-1, -1);
        this.addView(this.mZoomImageView, lp);
        this.addView(this.mClipImageView, lp);
        this.mHorizontalPadding = (int)TypedValue.applyDimension(1, (float)this.mHorizontalPadding, this.getResources().getDisplayMetrics());
        this.mZoomImageView.setHorizontalPadding(this.mHorizontalPadding);
        this.mClipImageView.setHorizontalPadding(this.mHorizontalPadding);
    }

    public void setImageResource(File file) {
        if(file == null) {
            this.mZoomImageView.setImageBitmap((Bitmap)null);
        } else {
            Activity ac = AppConfig.getCurrentActivity();
            if(ac != null) {
                this.progressDialog = UIUtils.showWaiting(ac, "载入中...");
            }

            ImageLoaderUtils.getImageLoader().displayImage("file://" + file.getAbsolutePath(), this.mZoomImageView, PhotoHelper.defaultBigLoadingOptions, new ImageLoadingListener() {
                public void onLoadingStarted(String imageUri, View view) {
                }

                public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                    UIUtils.showToast("载入失败");
                    ClipImageLayout.this.dismissDialog();
                }

                public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                    ClipImageLayout.this.dismissDialog();
                }

                public void onLoadingCancelled(String imageUri, View view) {
                    ClipImageLayout.this.dismissDialog();
                }
            });
        }

    }

    private void dismissDialog() {
        if(this.progressDialog != null) {
            this.progressDialog.dismiss();
            this.progressDialog = null;
        }

    }

    public void setHorizontalPadding(int mHorizontalPadding) {
        this.mHorizontalPadding = mHorizontalPadding;
    }

    public Bitmap clip() {
        this.mZoomImageView.setHorizontalPadding(MiscUtils.getPxByDip(60));
        return this.mZoomImageView.clip();
    }
}

