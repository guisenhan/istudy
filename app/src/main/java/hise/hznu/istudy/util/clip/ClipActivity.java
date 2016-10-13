package hise.hznu.istudy.util.clip;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import java.io.File;

import hise.hznu.istudy.R;
import hise.hznu.istudy.app.AppConfig;
import hise.hznu.istudy.base.BaseActivity;
import hise.hznu.istudy.util.MiscUtils;
import hise.hznu.istudy.util.UIUtils;

public class ClipActivity extends BaseActivity {
    public static final String EXTRA_LEFT_BUTTON_TEXT = "__extra_left_photo_text__";
    private ClipImageLayout clipImageLayout;
    private String backText = "返回";
    private TextView tvBack;

    public ClipActivity() {
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.ala_activity_clip_photo);
        this.initView();
        this.updateImage();
        this.updateData();
    }

    private void updateData() {
        String back = this.getIntent().getStringExtra("__extra_left_photo_text__");
        if(MiscUtils.isNotEmpty(back)) {
            this.backText = back;
        }

        this.tvBack.setText(this.backText);
    }

    private void updateImage() {
        Uri uri = this.getIntent().getData();
        if(uri == null) {
            UIUtils.showToast("未找到图片");
            this.finish();
        }

        this.clipImageLayout.setImageResource(new File(uri.getPath()));
    }

    private void initView() {
        this.clipImageLayout = (ClipImageLayout)this.findViewById(R.id.clip_image);
        this.tvBack = (TextView)this.findViewById(R.id.user__tv_back);
        this.tvBack.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent();
                i.putExtra("__extra_left_photo_text__", ClipActivity.this.backText);
                ClipActivity.this.setResult(0, i);
                ClipActivity.this.finish();
            }
        });
        this.findViewById(R.id.user__tv_confirm).setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                ClipActivity.this.doSave();
            }
        });
    }

    private void doSave() {
        final ProgressDialog dialog = UIUtils.showWaiting(this, "裁剪中...");
        AppConfig.execute(new Runnable() {
            public void run() {
                try {
                    File e = PhotoHelper.persist(ClipActivity.this.clipImageLayout.clip());
                    if(e == null) {
                        throw new RuntimeException();
                    }

                    ClipActivity.this.saveDataAndFinish(e);
                } catch (Exception var5) {
                    var5.printStackTrace();
                    UIUtils.showToast("裁剪失败，请重试。");
                } finally {
                    dialog.dismiss();
                }

            }
        });
    }

    private void saveDataAndFinish(File file) {
        Intent data = new Intent();
        data.setData(Uri.fromFile(file));
        this.setResult(-1, data);
        this.finish();
    }

    public String getStatName() {
        return "剪切图片页面";
    }
}
