package hise.hznu.istudy.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by PC on 2016/10/13.
 */
public class ImageUtils {


    public static Bitmap rotateAndCompressBitmapFromResource(String file, int reqWidth, int reqHeight) {
        Bitmap bitmap;
        try {
            int exception = BitmapUtils.getBitmapDegree(file);
            System.gc();
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(file, options);
            options.inJustDecodeBounds = false;
            int width = options.outWidth;
            int height = options.outHeight;
            int be = 1;
            if(width > height && width > reqWidth) {
                be = width / reqWidth;
            } else if(width < height && height > reqHeight) {
                be = height / reqHeight;
            }

            if(be <= 0) {
                be = 1;
            } else {
                be = be >= 6?6:be;
            }

            options.inSampleSize = be;
            options.inPreferredConfig = Bitmap.Config.ARGB_8888;
            options.inPurgeable = true;
            options.inInputShareable = true;
            bitmap = BitmapFactory.decodeFile(file, options);
            if(exception != 0) {
                bitmap = BitmapUtils.rotateBitmapByDegree(bitmap, exception);
            }
        } catch (Exception var9) {
            bitmap = null;
            System.gc();
        }

        return bitmap;
    }
}
