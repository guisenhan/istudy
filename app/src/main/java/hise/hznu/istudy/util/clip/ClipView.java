package hise.hznu.istudy.util.clip;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.Xfermode;
import android.graphics.Paint.Style;
import android.graphics.PorterDuff.Mode;
import android.util.AttributeSet;
import android.view.View;

import hise.hznu.istudy.util.MiscUtils;

public class ClipView extends View {
    private Paint paint;
    private Paint borderPaint;
    private int clipBorderWidth;
    private static final int LAYER_FLAGS = 31;
    private float radiusWidthRatio;
    int width;
    int height;
    private Xfermode xfermode;

    public ClipView(Context context) {
        this(context, (AttributeSet)null);
    }

    public ClipView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ClipView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.paint = new Paint();
        this.borderPaint = new Paint();
        this.clipBorderWidth = 2;
        this.radiusWidthRatio = 0.22222222F;
        this.paint.setAntiAlias(true);
        this.borderPaint.setStyle(Style.STROKE);
        this.borderPaint.setColor(-1);
        this.borderPaint.setStrokeWidth((float)this.clipBorderWidth);
        this.borderPaint.setAntiAlias(true);
        this.xfermode = new PorterDuffXfermode(Mode.DST_OUT);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.width = this.getWidth();
        this.height = this.getHeight();
        canvas.saveLayer(0.0F, 0.0F, (float)this.width, (float)this.height, (Paint)null, 31);
        canvas.drawColor(Color.parseColor("#a8000000"));
        this.paint.setXfermode(this.xfermode);
//        canvas.drawCircle((float)(this.width / 2), (float)(this.height / 2), (float)this.width * this.radiusWidthRatio, this.paint);
//      //  canvas.drawCircle((float)(this.width / 2), (float)(this.height / 2), (float)this.width * this
//                .radiusWidthRatio + (float)this.clipBorderWidth, this.borderPaint);
        canvas.drawRect((this.width/2) - MiscUtils.dpToPx(80,getResources()), this.height/2 - MiscUtils.dpToPx(80,
                getResources()), this.width/2+MiscUtils.dpToPx(80,getResources()),this.height/2+MiscUtils.dpToPx(80,
                getResources()),
                this.paint);
        canvas.drawRect((this.width/2) - MiscUtils.dpToPx(80,getResources())+2, this.height/2 - MiscUtils.dpToPx(80,
                getResources())+2, this.width/2+MiscUtils.dpToPx(80,getResources())+2,this.height/2+MiscUtils.dpToPx(80,
                getResources())+2,
                this.borderPaint);
        canvas.restore();
    }

    public Rect getClipRect() {
        Rect rect = new Rect();
        rect.left = (int)((float)(this.width / 2) - MiscUtils.dpToPx(80,getResources()));
        rect.right = (int)((float)(this.width / 2) + MiscUtils.dpToPx(80,getResources()));
        rect.top = (int)((float)(this.height / 2) - MiscUtils.dpToPx(80,getResources()));
        rect.bottom = (int)((float)(this.height / 2) + MiscUtils.dpToPx(80,getResources()));
        return rect;
    }
}

