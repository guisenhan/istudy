package hise.hznu.istudy.util.clip;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.Bitmap.CompressFormat;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ImageView.ScaleType;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

import hise.hznu.istudy.R;
import hise.hznu.istudy.util.BitmapUtils;
import hise.hznu.istudy.util.FileUtils;
import hise.hznu.istudy.util.ImageUtils;

public class ClipHeaderActivity extends Activity implements OnTouchListener {
    private String TAG = "ClipHeaderActivity";
    private ImageView srcPic;
    private TextView iv_back;
    private ClipView clipview;
    private TextView tv_ok;
    private Matrix matrix = new Matrix();
    private Matrix savedMatrix = new Matrix();
    private static final int NONE = 0;
    private static final int DRAG = 1;
    private static final int ZOOM = 2;
    private int mode = 0;
    private PointF start = new PointF();
    private PointF mid = new PointF();
    private float oldDist = 1.0F;
    private Bitmap bitmap;
    private int side_length;

    public ClipHeaderActivity() {
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.ala_header_activity_clip_photo);
        this.init();
    }

    private void init() {
        this.side_length = this.getIntent().getIntExtra("side_length", 200);
        this.iv_back = (TextView)this.findViewById(R.id.user__tv_back);
        this.srcPic = (ImageView)this.findViewById(R.id.src_pic);
        this.clipview = (ClipView)this.findViewById(R.id.clip_image);
        this.tv_ok = (TextView)this.findViewById(R.id.user__tv_confirm);
        this.srcPic.setOnTouchListener(this);
        ViewTreeObserver observer = this.clipview.getViewTreeObserver();
        observer.addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                ClipHeaderActivity.this.clipview.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                ClipHeaderActivity.this.initSrcPic();
            }
        });
        this.tv_ok.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                ClipHeaderActivity.this.generateUriAndReturn();
            }
        });
        this.iv_back.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                ClipHeaderActivity.this.finish();
            }
        });
    }

    private void initSrcPic() {
        Uri uri = this.getIntent().getData();
        String path = FileUtils.getRealFilePathFromUri(this.getApplicationContext(), uri);
        if(!TextUtils.isEmpty(path)) {
            this.bitmap = ImageUtils.rotateAndCompressBitmapFromResource(path, 480, 800);
            if(this.bitmap != null) {
                float scale;
                if(this.bitmap.getWidth() > this.bitmap.getHeight()) {
                    scale = (float)this.srcPic.getWidth() / (float)this.bitmap.getWidth();
                    Rect midX = this.clipview.getClipRect();
                    float midY = (float)(midX.height() / this.bitmap.getHeight());
                    if(scale < midY) {
                        scale = midY;
                    }
                } else {
                    scale = (float)this.srcPic.getWidth() / 2.0F / (float)this.bitmap.getWidth();
                }

                this.matrix.postScale(scale, scale);
                int midX1 = this.srcPic.getWidth() / 2;
                int midY1 = this.srcPic.getHeight() / 2;
                int imageMidX = (int)((float)this.bitmap.getWidth() * scale / 2.0F);
                int imageMidY = (int)((float)this.bitmap.getHeight() * scale / 2.0F);
                this.matrix.postTranslate((float)(midX1 - imageMidX), (float)(midY1 - imageMidY));
                this.srcPic.setScaleType(ScaleType.MATRIX);
                this.srcPic.setImageMatrix(this.matrix);
                this.srcPic.setImageBitmap(this.bitmap);
            }
        }
    }

    public boolean onTouch(View v, MotionEvent event) {
        ImageView view = (ImageView)v;
        switch(event.getAction() & 255) {
            case 0:
                this.savedMatrix.set(this.matrix);
                this.start.set(event.getX(), event.getY());
                this.mode = 1;
                break;
            case 1:
            case 6:
                this.mode = 0;
                break;
            case 2:
                if(this.mode == 1) {
                    this.matrix.set(this.savedMatrix);
                    this.matrix.postTranslate(event.getX() - this.start.x, event.getY() - this.start.y);
                } else if(this.mode == 2) {
                    float newDist = this.spacing(event);
                    if(newDist > 10.0F) {
                        this.matrix.set(this.savedMatrix);
                        float scale = newDist / this.oldDist;
                        this.matrix.postScale(scale, scale, this.mid.x, this.mid.y);
                    }
                }
            case 3:
            case 4:
            default:
                break;
            case 5:
                this.oldDist = this.spacing(event);
                if(this.oldDist > 10.0F) {
                    this.savedMatrix.set(this.matrix);
                    this.midPoint(this.mid, event);
                    this.mode = 2;
                }
        }

        view.setImageMatrix(this.matrix);
        return true;
    }

    private float spacing(MotionEvent event) {
        float x = event.getX(0) - event.getX(1);
        float y = event.getY(0) - event.getY(1);
        return (float)Math.sqrt((double)(x * x + y * y));
    }

    private void midPoint(PointF point, MotionEvent event) {
        float x = event.getX(0) + event.getX(1);
        float y = event.getY(0) + event.getY(1);
        point.set(x / 2.0F, y / 2.0F);
    }

    private Bitmap getZoomedCropBitmap() {
        this.srcPic.setDrawingCacheEnabled(true);
        this.srcPic.buildDrawingCache();
        Rect rect = this.clipview.getClipRect();
        Bitmap cropBitmap = null;
        Bitmap zoomedCropBitmap = null;

        try {
            cropBitmap = Bitmap.createBitmap(this.srcPic.getDrawingCache(), rect.left, rect.top, rect.width(), rect.height());
            zoomedCropBitmap = BitmapUtils.zoomBitmap(cropBitmap, this.side_length, this.side_length);
        } catch (Exception var5) {
            var5.printStackTrace();
        }

        if(cropBitmap != null) {
            cropBitmap.recycle();
        }

        this.srcPic.destroyDrawingCache();
        return zoomedCropBitmap;
    }

    private void generateUriAndReturn() {
        File file = null;
        Bitmap zoomedCropBitmap = this.getZoomedCropBitmap();
        if(zoomedCropBitmap != null) {
            Uri mSaveUri = Uri.fromFile(new File(this.getCacheDir(), "cropped_" + System.currentTimeMillis() + ".jpg"));

            try {
                file = PhotoHelper.persist(zoomedCropBitmap);
            } catch (Exception var16) {
                var16.printStackTrace();
            }

            if(mSaveUri != null) {
                OutputStream outputStream = null;

                try {
                    outputStream = this.getContentResolver().openOutputStream(mSaveUri);
                    if(outputStream != null) {
                        zoomedCropBitmap.compress(CompressFormat.JPEG, 90, outputStream);
                    }
                } catch (IOException var15) {
                    ;
                } finally {
                    if(outputStream != null) {
                        try {
                            outputStream.close();
                        } catch (IOException var14) {
                            var14.printStackTrace();
                        }
                    }

                }

                this.saveDataAndFinish(file);
            }

        }
    }

    private void saveDataAndFinish(File file) {
        if(file != null) {
            Intent data = new Intent();
            data.setData(Uri.fromFile(file));
            this.setResult(-1, data);
            this.finish();
        }
    }
}

