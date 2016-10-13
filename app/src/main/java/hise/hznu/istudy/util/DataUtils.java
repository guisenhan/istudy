package hise.hznu.istudy.util;

import android.graphics.drawable.Drawable;
import android.os.Environment;

import java.io.File;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

import hise.hznu.istudy.app.AppConfig;

/**
 * Created by PC on 2016/10/12.
 */
public class DataUtils {
    private static final int HEADER_SIZE = 16;
    private static final String KEY = "_alasoft.com_";
    private static Map<String, WeakReference<Drawable>> imageCaches = new HashMap();

    private DataUtils() {
    }
    public static boolean isExternalStorageMounted() {
        return "mounted".equals(Environment.getExternalStorageState());
    }

    public static String getSDCardAppPath() {
        File file = new File(Environment.getExternalStorageDirectory(), "/Android/data/" + AppConfig.getContext().getPackageName());
        return file.getPath();
    }
}
