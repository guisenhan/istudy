package hise.hznu.istudy.activity.course;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.FileAsyncHttpResponseHandler;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cz.msebera.android.httpclient.Header;
import hise.hznu.istudy.R;
import hise.hznu.istudy.app.AppConstant;
import hise.hznu.istudy.base.BaseActivity;
import hise.hznu.istudy.util.AppUtils;
import hise.hznu.istudy.util.ImageLoaderUtils;

public class ImageActivity extends BaseActivity {
    @BindView(R.id.iv_cancel)
    ImageView ivCancel;
    @BindView(R.id.iv_image)
    ImageView ivImage;

    File file1;
    AsyncHttpClient http = new AsyncHttpClient();
    private String url;
    @Override
    protected void initData() {
        super.initData();
    }

    @Override
    protected void initExtras(Bundle extras) {
        super.initExtras(extras);
        url = extras.getString("img");
            ImageLoaderUtils.getImageLoader().loadImage(url, new ImageLoadingListener() {
                @Override
                public void onLoadingStarted(String s, View view) {
                    Log.e("start","start");
                }

                @Override
                public void onLoadingFailed(String s, View view, FailReason failReason) {
                    file1 = new File(AppConstant.FILE_STORED + url);
                    if(file1.exists()){
                        return;
                    }
                    http.get(ImageActivity.this, url, new FileAsyncHttpResponseHandler
                            (ImageActivity.this) {
                        @Override
                        public void onFailure(int statusCode, Header[] headers, Throwable throwable, File file) {

                        }

                        @Override
                        public void onSuccess(int statusCode, Header[] headers, File file) {
                            Log.e("filePath",file.getAbsolutePath());
                            try {
                                FileInputStream  in = new FileInputStream(file);
                                FileOutputStream out = new FileOutputStream(file1);
                                int length = in.available();
                                byte[] by = new byte[length];
                                in.read(by);
                                out.write(by);
                            }catch (Exception  e){

                            }
                            AppUtils.openFile(file1);
                        }
                    });
                    finish();
                }

                @Override
                public void onLoadingComplete(String s, View view, Bitmap bitmap) {
                    Log.e("onLoadingComplete","onLoadingComplete");
                    ivImage.setImageBitmap(bitmap);
                }

                @Override
                public void onLoadingCancelled(String s, View view) {
                    Log.e("onLoadingCancelled","onLoadingCancelled");
                }
            });
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
