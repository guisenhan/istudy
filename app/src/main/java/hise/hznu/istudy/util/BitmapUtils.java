package hise.hznu.istudy.util;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.util.Log;

import java.io.IOException;

/**
 * Created by PC on 2016/10/13.
 */
public class BitmapUtils {
    public static Bitmap zoomBitmap(Bitmap bitmap, int w, int h) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Matrix matrix = new Matrix();
        float scaleWidth = (float)w / (float)width;
        float scaleHeight = (float)h / (float)height;
        matrix.postScale(scaleWidth, scaleHeight);
        Bitmap newBmp = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, false);
        return newBmp;
    }

    public static int getBitmapDegree(String path) {
        short degree = 0;

        try {
            ExifInterface e = new ExifInterface(path);
            int orientation = e.getAttributeInt("Orientation", 1);
            switch(orientation) {
                case 3:
                    degree = 180;
                    break;
                case 6:
                    degree = 90;
                    break;
                case 8:
                    degree = 270;
            }
        } catch (IOException var4) {
            var4.printStackTrace();
        }

        return degree;
    }


    public static Bitmap rotateBitmapByDegree(Bitmap bm, int degree) {
        Bitmap bitmap = null;
        Matrix matrix = new Matrix();
        matrix.postRotate((float)degree);

        try {
            bitmap = Bitmap.createBitmap(bm, 0, 0, bm.getWidth(), bm.getHeight(), matrix, true);
        } catch (OutOfMemoryError var5) {
        }

        if(bitmap == null) {
            bitmap = bm;
        }

        if(bm != bitmap) {
            bm.recycle();
        }

        return bitmap;
    }
}
