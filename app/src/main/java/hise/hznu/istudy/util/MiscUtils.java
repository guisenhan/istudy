//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package hise.hznu.istudy.util;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.DialogInterface.OnClickListener;
import android.content.SharedPreferences.Editor;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory.Options;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.Build.VERSION;
import android.provider.Settings.System;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.widget.Toast;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.TreeMap;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONObject;

import hise.hznu.istudy.app.AppConfig;

public class MiscUtils {
    public static final String GLOBAL_TAG = "core";
    public static final int MAX_GRPS_RETRY_COUNT = 5;

    public MiscUtils() {
    }

    public static String sayHello(String name) {
        return "hello " + name;
    }

    public static int getResourcesIdentifier(Context context, String fullname) {
        return context.getResources().getIdentifier(context.getPackageName() + ":" + fullname, (String)null, (String)null);
    }

    public static String optString(JSONObject jsonObject, String key) {
        return optString(jsonObject, key, "");
    }

    public static String optString(JSONObject jsonObject, String key, String fallback) {
        return jsonObject.isNull(key)?fallback:jsonObject.optString(key, fallback);
    }

    public static int optInt(JSONObject jsonObject, String key) {
        return optInt(jsonObject, key, 0);
    }

    public static int optInt(JSONObject jsonObject, String key, int fallback) {
        return jsonObject.isNull(key)?fallback:jsonObject.optInt(key, fallback);
    }

    public static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (Exception var2) {
            var2.printStackTrace();
            return false;
        }
    }

    public static String getSharepreferenceValue(String shareName, String key, String defaultValue) {
        SharedPreferences share = AppConfig.getContext().getSharedPreferences(shareName, 0);
        return share.getString(key, defaultValue);
    }

    public static boolean getSharepreferenceValue(String shareName, String key, boolean defaultValue) {
        SharedPreferences share = AppConfig.getContext().getSharedPreferences(shareName, 0);
        return share.getBoolean(key, defaultValue);
    }

    public static int getSharepreferenceValue(String shareName, String key, int defaultValue) {
        SharedPreferences share = AppConfig.getContext().getSharedPreferences(shareName, 0);
        return share.getInt(key, defaultValue);
    }

    public static float getSharepreferenceValue(String shareName, String key, float defaultValue) {
        SharedPreferences share = AppConfig.getContext().getSharedPreferences(shareName, 0);
        return share.getFloat(key, defaultValue);
    }

    public static long getSharepreferenceValue(String shareName, String key, long defaultValue) {
        SharedPreferences share = AppConfig.getContext().getSharedPreferences(shareName, 0);
        return share.getLong(key, defaultValue);
    }

    public static void setSharedPreferenceValue(String shareName, String key, String value) {
        SharedPreferences share = AppConfig.getContext().getSharedPreferences(shareName, 0);
        Editor editor = share.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public static void setSharedPreferenceValue(String shareName, String key, boolean value) {
        SharedPreferences share = AppConfig.getContext().getSharedPreferences(shareName, 0);
        Editor editor = share.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    public static void setSharedPreferenceValue(String shareName, String key, int value) {
        SharedPreferences share = AppConfig.getContext().getSharedPreferences(shareName, 0);
        Editor editor = share.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    public static void setSharedPreferenceValue(String shareName, String key, float value) {
        SharedPreferences share = AppConfig.getContext().getSharedPreferences(shareName, 0);
        Editor editor = share.edit();
        editor.putFloat(key, value);
        editor.commit();
    }

    public static void setSharedPreferenceValue(String shareName, String key, long value) {
        SharedPreferences share = AppConfig.getContext().getSharedPreferences(shareName, 0);
        Editor editor = share.edit();
        editor.putLong(key, value);
        editor.commit();
    }





    public static boolean isValidCarno(String carno) {
        if(isEmpty(carno)) {
            return false;
        } else {
            try {
                return carno.matches("(WJ|[Α-￥]{1})[a-zA-Z0-9]{6}|(WJ|[Α-￥]{1})[a-zA-Z0-9]{5}[Α-￥]{1}");
            } catch (Exception var2) {
                return false;
            }
        }
    }

    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static void lock(Object lock) {
        try {
            synchronized(lock) {
                lock.wait();
            }
        } catch (Exception var4) {
            var4.printStackTrace();
        }

    }

    public static boolean isCellphone(String str) {
        Pattern pattern = Pattern.compile("1[34589][0-9]{9}");
        Matcher matcher = pattern.matcher(str);
        Pattern patternTow = Pattern.compile("1[0-9]{10}");
        Matcher matcherTow = patternTow.matcher(str);
        return matcherTow.matches() || matcher.matches();
    }





    public static void showMessageToast(final String message) {
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            public void run() {
                Toast.makeText(AppConfig.getContext(), message, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public static void showMessageToastLong(final String message) {
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            public void run() {
                Toast.makeText(AppConfig.getContext(), message, Toast.LENGTH_LONG).show();
            }
        });
    }

    public static void showMessageDialog(final Activity context, final String message) {
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            public void run() {
                if(!context.isFinishing()) {
                    Builder builder = new Builder(context);
                    builder.setTitle("消息");
                    builder.setMessage(message);
                    builder.setPositiveButton("确定", (OnClickListener)null);
                    builder.create().show();
                }
            }
        });
    }

    public static boolean showConfirmDialog(final Activity context, final String title, final String message) {
        Looper looper = Looper.getMainLooper();
        if(Thread.currentThread() == looper.getThread()) {
            throw new IllegalStateException("此方法只能在非主线程上调用！");
        } else {
            Handler handler = new Handler(looper);
            final Boolean[] fuck = new Boolean[1];
            final Object lock = new Object();
            handler.post(new Runnable() {
                public void run() {
                    Builder builder = new Builder(context);
                    builder.setTitle(title);
                    builder.setMessage(message);
                    builder.setCancelable(false);
                    builder.setPositiveButton("确定", new OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            fuck[0] = Boolean.TRUE;
                            Object var3 = lock;
                            synchronized(lock) {
                                lock.notifyAll();
                            }
                        }
                    });
                    builder.setNegativeButton("取消", new OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            fuck[0] = Boolean.FALSE;
                            Object var3 = lock;
                            synchronized(lock) {
                                lock.notifyAll();
                            }
                        }
                    });
                    builder.create().show();
                }
            });

            while(fuck[0] == null) {
                synchronized(lock) {
                    try {
                        lock.wait();
                    } catch (Exception var10) {
                        var10.printStackTrace();
                    }
                }
            }

            return fuck[0].booleanValue();
        }
    }

    public static String getHost(String url) {
        try {
            URL ex = new URL(url);
            return ex.getHost();
        } catch (Exception var2) {
            var2.printStackTrace();
            return "";
        }
    }




    public static void viewInMarket(Context context) {
        try {
            Intent ex = new Intent("android.intent.action.VIEW");
            ex.setData(Uri.parse("market://details?id=" + context.getPackageName()));
            ex.addFlags(268435456);
            context.startActivity(ex);
        } catch (Exception var2) {
            var2.printStackTrace();
            Toast.makeText(context, "当前手机不支持应用市场！", Toast.LENGTH_SHORT).show();
        }

    }

    public static void sendShare(Activity context, String content) {
        Intent it = new Intent("android.intent.action.SEND");
        it.putExtra("android.intent.extra.TEXT", content);
        it.setType("text/plain");
        Intent intent = Intent.createChooser(it, "请选择分享的方式");
        context.startActivity(intent);
    }

    public static void lock(Object lock, long time) {
        try {
            synchronized(lock) {
                lock.wait(time);
            }
        } catch (Exception var6) {
            var6.printStackTrace();
        }

    }

    public static void sleep(long time) {
        try {
            Thread.sleep(time);
        } catch (Exception var3) {
            var3.printStackTrace();
        }

    }

    public static int computeSampleSize(Options options, int minSideLength, int maxNumOfPixels) {
        int initialSize = computeInitialSampleSize(options, minSideLength, maxNumOfPixels);
        int roundedSize;
        if(initialSize <= 8) {
            for(roundedSize = 1; roundedSize < initialSize; roundedSize <<= 1) {
                ;
            }
        } else {
            roundedSize = (initialSize + 7) / 8 * 8;
        }

        return roundedSize;
    }

    private static int computeInitialSampleSize(Options options, int minSideLength, int maxNumOfPixels) {
        double w = (double)options.outWidth;
        double h = (double)options.outHeight;
        int lowerBound = maxNumOfPixels == -1?1:(int)Math.ceil(Math.sqrt(w * h / (double)maxNumOfPixels));
        int upperBound = minSideLength == -1?128:(int)Math.min(Math.floor(w / (double)minSideLength), Math.floor(h / (double)minSideLength));
        return upperBound < lowerBound?lowerBound:(maxNumOfPixels == -1 && minSideLength == -1?1:(minSideLength == -1?lowerBound:upperBound));
    }

    public static String getDaysDesc(int days) {
        return days < 0?"已过期":(days == 0?"今天":(days == 1?"明天":(days == 2?"后天":days + "天")));
    }

    public static ProgressDialog showProgressDialog(Activity context, String title, String message, ProgressDialog pd) {
        if(context.isFinishing()) {
            return null;
        } else {
            if(pd == null) {
                pd = new ProgressDialog(context);
            }

            if(isNotEmpty(title)) {
                pd.setTitle(title);
            }

            if(isNotEmpty(message)) {
                pd.setMessage(message);
            }

            pd.show();
            return pd;
        }
    }

//    public static int getFontSizeOfWidth(String content, int width) {
//        Paint paint = new Paint();
//        int fontSize = getPxByDip(24);
//        paint.setTextSize((float)fontSize);
//        int realWidth = (int)paint.measureText(content);
//        if(realWidth > width) {
//            while(fontSize > 1) {
//                --fontSize;
//                paint.setTextSize((float)fontSize);
//                realWidth = (int)paint.measureText(content);
//                if(realWidth <= width) {
//                    break;
//                }
//            }
//        } else if(realWidth < width) {
//            DisplayMetrics dm = new DisplayMetrics();
//            WindowManager wm = (WindowManager)AppConfig.getContext().getSystemService("window");
//            wm.getDefaultDisplay().getMetrics(dm);
//
//            while(fontSize < dm.widthPixels) {
//                ++fontSize;
//                paint.setTextSize((float)fontSize);
//                realWidth = (int)paint.measureText(content);
//                if(realWidth >= width) {
//                    break;
//                }
//            }
//        }
//
//        return fontSize;
//    }

    public static int getFontSizeOfMaxWidth(String content, int maxWidth, int initFontSize) {
        Paint paint = new Paint();
        int fontSize = initFontSize;
        paint.setTextSize((float)initFontSize);
        int realWidth = (int)paint.measureText(content);
        if(realWidth > maxWidth) {
            while(fontSize > 1) {
                --fontSize;
                paint.setTextSize((float)fontSize);
                realWidth = (int)paint.measureText(content);
                if(realWidth <= maxWidth) {
                    break;
                }
            }
        }

        return fontSize;
    }











    public static int calculateDays(Date from, Date to) {
        long ONE_DAY = 86400000L;
        TimeZone timeZone = TimeZone.getDefault();
        long rawOffset = (long)timeZone.getRawOffset();
        int fromDays = (int)((from.getTime() + rawOffset) / 86400000L);
        int toDays = (int)((to.getTime() + rawOffset) / 86400000L);
        return toDays - fromDays;
    }

    public static String getExtensionOfFile(File file) {
        String name = file.getName();
        int index = name.lastIndexOf(".");
        return index != -1 && index != name.length() - 1?name.substring(index):"";
    }

    public static String addTrail(String input, int maxLength) {
        return isEmpty(input)?input:(input.length() > maxLength?input.substring(0, maxLength - 1) + "...":input);
    }

    public static void goToSite(Context context, String url) {
        Intent it = new Intent("android.intent.action.VIEW", Uri.parse(url));
        context.startActivity(it);
    }

    public static void guard(boolean predicate, String errorMessage) throws Exception {
        if(!predicate) {
            throw new RuntimeException("错误：" + errorMessage);
        }
    }

    public static String getWeekName(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int week = cal.get(Calendar.DAY_OF_WEEK);
        switch(week) {
            case 1:
                return "星期日";
            case 2:
                return "星期一";
            case 3:
                return "星期二";
            case 4:
                return "星期三";
            case 5:
                return "星期四";
            case 6:
                return "星期五";
            case 7:
                return "星期六";
            default:
                return "星期八";
        }
    }

    public static String getErrorMessage(Throwable th, String defaultMessage) {
        String msg = th.getMessage();
        return isEmpty(msg)?defaultMessage:msg;
    }

    public static final String format(Date date, String pattern) {
        if(date == null) {
            return "";
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            return sdf.format(date);
        }
    }

    public static final String formatDate(Date date) {
        return format(date, "yyyy-MM-dd");
    }

    public static Bitmap getBitmapFromDrawable(Drawable drawable, int width, int height) {
        if(drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable)drawable).getBitmap();
        } else {
            if(drawable.getIntrinsicHeight() > 0 && drawable.getIntrinsicWidth() > 0) {
                width = drawable.getIntrinsicWidth();
                height = drawable.getIntrinsicHeight();
            }

            Bitmap bitmap = Bitmap.createBitmap(width, height, Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmap);
            drawable.setBounds(0, 0, width, height);
            drawable.draw(canvas);
            return bitmap;
        }
    }

    public static Drawable getCoveredDrawable(Drawable input, Drawable cover) {
        int width = input.getIntrinsicWidth();
        int height = input.getIntrinsicHeight();
        Bitmap bitmap = Bitmap.createBitmap(width, height, Config.ARGB_8888);
        Canvas cancas = new Canvas(bitmap);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        cancas.drawARGB(0, 0, 0, 0);
        input.setBounds(0, 0, width, height);
        input.draw(cancas);
        Bitmap converBitmap = getBitmapFromDrawable(cover, width, height);
        Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        cancas.drawBitmap(converBitmap, rect, rect, paint);
        return new BitmapDrawable(bitmap);
    }

    public static String safeURLEncode(String s, String encoding) {
        if(s == null) {
            return "";
        } else {
            try {
                return URLEncoder.encode(s, encoding);
            } catch (UnsupportedEncodingException var3) {
                var3.printStackTrace();
                return s;
            }
        }
    }

    public static String safeURLDecode(String s, String encoding) {
        if(s == null) {
            return null;
        } else {
            try {
                return URLDecoder.decode(s, encoding);
            } catch (Exception var3) {
                var3.printStackTrace();
                return s;
            }
        }
    }

    public static <T> T safeGetList(List<T> list, int index) {
        return index >= 0 && index < list.size()?list.get(index):null;
    }

    public static boolean isTheSameDay(Date date1, Date date2) {
        if(date1 != null && date2 != null) {
            TimeZone timeZone = TimeZone.getDefault();
            long rawOffset = (long)timeZone.getRawOffset();
            return (date1.getTime() + rawOffset) / 86400000L == (date2.getTime() + rawOffset) / 86400000L;
        } else {
            return false;
        }
    }

    public static int parseInt(String s, int defaultInt) {
        if(isEmpty(s)) {
            return defaultInt;
        } else {
            try {
                return Integer.parseInt(s);
            } catch (Exception var3) {
                var3.printStackTrace();
                return defaultInt;
            }
        }
    }

    public static boolean parseBoolean(String s, boolean defaultBool) {
        if(isEmpty(s)) {
            return defaultBool;
        } else {
            try {
                return Boolean.parseBoolean(s);
            } catch (Exception var3) {
                var3.printStackTrace();
                return defaultBool;
            }
        }
    }

    public static double parseDouble(String s, double defaultDouble) {
        try {
            return Double.parseDouble(s);
        } catch (Exception var4) {
            var4.printStackTrace();
            return defaultDouble;
        }
    }

    public static Boolean checkKeyWordsByRegular(String keyWord, String regular) {
        Pattern pattern = Pattern.compile(regular);
        Matcher matcher = pattern.matcher(keyWord);
        return Boolean.valueOf(matcher.matches());
    }

    public static int getPxByDip(int dip) {
        DisplayMetrics dm = AppConfig.getContext().getResources().getDisplayMetrics();
        int px = (int)((float)dip * dm.density + 0.5F);
        return px;
    }

    public static int getPxBySp(int sp) {
        DisplayMetrics dm = AppConfig.getContext().getResources().getDisplayMetrics();
        int px = (int)((float)sp * dm.scaledDensity);
        return px;
    }

    public static int getScreenWidth() {
        WindowManager wm = (WindowManager)AppConfig.getContext().getSystemService(Context.WINDOW_SERVICE);
        Point size = new Point();
        wm.getDefaultDisplay().getSize(size);
        return size.x;
    }

    public static int getScreenHeight() {
        WindowManager wm = (WindowManager)AppConfig.getContext().getSystemService(Context.WINDOW_SERVICE);
        Point size = new Point();
        wm.getDefaultDisplay().getSize(size);
        return size.y;
    }

    public static String format(long number, String pattern) {
        DecimalFormat df = new DecimalFormat(pattern);
        return df.format(number);
    }

    public static String format(double number, String pattern) {
        if(Double.isNaN(number) || Double.isInfinite(number)) {
            number = 0.0D;
        }

        DecimalFormat df = new DecimalFormat(pattern);
        return df.format(number);
    }

    public static String format(Number number, String pattern) {
        DecimalFormat df = new DecimalFormat(pattern);
        return df.format(number);
    }

    public static Object invokeMethod(Object obj, String methodName, Object[] methodArgs, Class... args) {
        Class cls = obj.getClass();

        try {
            Method ex = cls.getDeclaredMethod(methodName, args);
            ex.setAccessible(true);
            return ex.invoke(obj, methodArgs);
        } catch (Exception var6) {
            var6.printStackTrace();
            return null;
        }
    }

//    public static <T> T getValueOfField(Object obj, String fieldName) {
//        Class cls = obj.getClass();
//
//        try {
//            Field ex = cls.getDeclaredField(fieldName);
//            ex.setAccessible(true);
//            return ex.get(obj);
//        } catch (Exception var4) {
//            var4.printStackTrace();
//            return null;
//        }
//    }

    public static boolean setValueOfField(Object obj, String fieldName, Object value) {
        Class cls = obj.getClass();

        try {
            Field ex = cls.getDeclaredField(fieldName);
            ex.setAccessible(true);
            ex.set(obj, value);
            return true;
        } catch (Exception var5) {
            var5.printStackTrace();
            return false;
        }
    }

    public static Bitmap getRoundedCornerBitmap(Bitmap bitmap) {
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
        int color = -12434878;
        Paint paint = new Paint();
        Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        RectF rectF = new RectF(rect);
        float roundPx = (float)getPxByDip(12);
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(Color.parseColor("-12434878"));
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        return output;
    }

    public static <T> List<T> copy(List<T> list) {
        ArrayList copy = new ArrayList();
        if(isEmpty((Collection)list)) {
            return copy;
        } else {
            Iterator i$ = list.iterator();

            while(i$.hasNext()) {
                Object t = i$.next();
                copy.add(t);
            }

            return copy;
        }
    }

    public static int parseInt(String s) {
        if(isEmpty(s)) {
            return 0;
        } else {
            try {
                return Integer.parseInt(s);
            } catch (Exception var2) {
                return 0;
            }
        }
    }

    public static Date parseDate(String input, String pattern) {
        try {
            SimpleDateFormat ex = new SimpleDateFormat(pattern);
            ex.setLenient(false);
            return ex.parse(input);
        } catch (Exception var3) {
            var3.printStackTrace();
            return new Date();
        }
    }

    public static int parseColor(String color) {
        if(TextUtils.isEmpty(color)) {
            return -16777216;
        } else {
            try {
                if(color.startsWith("#")) {
                    color = color.substring(1);
                }

                return Integer.parseInt(color, 16) | -16777216;
            } catch (NumberFormatException var2) {
                var2.printStackTrace();
                return 0;
            }
        }
    }

    public static String getIMEI() {
        TelephonyManager telephonyManager = (TelephonyManager)AppConfig.getContext().getSystemService("phone");
        String imei = telephonyManager.getDeviceId();
        if(TextUtils.isEmpty(imei)) {
            imei = "123";
        }

        return imei;
    }

    public static String getAllowedLocationPrividers(Context context) {
        String content = System.getString(context.getContentResolver(), "location_providers_allowed");
        return content;
    }

    public static void assertTrue(boolean b, String error) {
        if(!b) {
            throw new RuntimeException(error);
        }
    }

    public static String getNetworkType() {
        String netWorkInfo = "unknown";
        if(isConnectAvailable()) {
            ConnectivityManager manager = (ConnectivityManager)AppConfig.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo info = manager.getActiveNetworkInfo();
            if(info != null) {
                if(info.getType() == 1) {
                    netWorkInfo = "WIFI";
                } else if(info.getType() == 0) {
                    switch(info.getSubtype()) {
                        case 0:
                        case 1:
                        case 2:
                        case 4:
                        case 7:
                            netWorkInfo = "2G";
                            break;
                        case 3:
                        case 5:
                        case 6:
                        case 8:
                        case 9:
                        case 10:
                        case 11:
                        case 12:
                            netWorkInfo = "3G";
                            break;
                        case 13:
                            netWorkInfo = "4G";
                            break;
                        default:
                            netWorkInfo = "2G";
                    }
                }
            }
        }

        return netWorkInfo;
    }

    public static boolean isConnectAvailable() {
        ConnectivityManager cm = (ConnectivityManager)AppConfig.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        if(cm == null) {
            return false;
        } else {
            NetworkInfo info = cm.getActiveNetworkInfo();
            return info != null && info.isAvailable();
        }
    }

    public static boolean isEquals(Object obj1, Object obj2) {
        return obj1 != null?obj1.equals(obj2):(obj2 != null?obj2.equals(obj1):obj1 == obj2);
    }

    public static boolean isNotEquals(Object obj1, Object obj2) {
        return !isEquals(obj1, obj2);
    }

    public static boolean isNotEmpty(String s) {
        return !isEmpty(s);
    }

    public static boolean isEmptyOrLiterallyNull(String s) {
        return isEmpty(s) || "null".equals(s);
    }

    public static boolean isNotEmpty(Collection<?> c) {
        return !isEmpty(c);
    }

    public static boolean isNotEmpty(Map<?, ?> map) {
        return !isEmpty(map);
    }

    public static boolean isEmpty(String s) {
        return s == null || s.trim().length() == 0 || "null".equals(s);
    }

    public static boolean isEmpty(Collection<?> c) {
        return c == null || c.isEmpty();
    }

    public static boolean isEmpty(Map<?, ?> map) {
        return map == null || map.isEmpty();
    }

    public static List<String> splitSql(String content, String split) {
        ArrayList result = new ArrayList();
        if(isEmpty(split)) {
            return result;
        } else {
            String[] ss = content.split(split);
            if(ss == null) {
                return result;
            } else {
                String[] arr$ = ss;
                int len$ = ss.length;

                for(int i$ = 0; i$ < len$; ++i$) {
                    String sql = arr$[i$];
                    if(!isEmpty(sql)) {
                        result.add(sql);
                    }
                }

                return result;
            }
        }
    }

    public static void initWebView(Context context) {
        WebView webView = new WebView(context);
        webView.setVerticalScrollbarOverlay(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setAppCacheEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setDatabaseEnabled(true);
        webView.getSettings().setDatabasePath(AppConfig.getContext().getDir("database", 0).getPath());
        webView.getSettings().setAppCachePath(AppConfig.getContext().getDir("cache", 0).getPath());
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    public static void enableHTML5(WebView webView, boolean netFirst) {
        webView.setVerticalScrollbarOverlay(true);
        int prid = getResourcesIdentifier(AppConfig.getContext(), "string/product");
        String pr = AppConfig.getContext().getResources().getString(prid);
        String userAgent = webView.getSettings().getUserAgentString();
        userAgent = userAgent + "ala" + pr;
        webView.getSettings().setGeolocationEnabled(true);
        webView.getSettings().setUserAgentString(userAgent);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setAppCacheEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setDatabaseEnabled(true);
        webView.getSettings().setDatabasePath(AppConfig.getContext().getDir("database", 0).getPath());
        webView.getSettings().setAppCachePath(AppConfig.getContext().getDir("cache", 0).getPath());
        webView.getSettings().setSupportZoom(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        if(VERSION.SDK_INT >= 16) {
            webView.getSettings().setDisplayZoomControls(false);
        }

        try {
            webView.getSettings().setDomStorageEnabled(true);
        } catch (Exception var6) {
            var6.printStackTrace();
        }

        webView.getSettings().setAppCacheMaxSize(52428800L);
        webView.getSettings().setAllowFileAccess(true);
        if(netFirst) {
            webView.getSettings().setCacheMode(-1);
        } else {
            webView.getSettings().setCacheMode(1);
        }

//        webView.setDownloadListener(new DownloadListener() {
//            public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {
//                MiscuUtil.goToSite(AppConfig.getCurrentActivity(), url);
//            }
//        });
    }

//    public static String getBetweenTime(long min, long max) {
//        int seconds = (int)((max - min) / 1000L);
//        if(seconds < 30) {
//            return "刚刚";
//        } else if(seconds < 60) {
//            return "30秒前";
//        } else if(seconds < 3600) {
//            return seconds / 60 + "分钟前";
//        } else {
//            Calendar minCal = Calendar.getInstance();
//            Calendar maxCal = Calendar.getInstance();
//            minCal.setTimeInMillis(min);
//            maxCal.setTimeInMillis(max);
//            Date minDate = minCal.getTime();
//            return minCal.get(1) == maxCal.get(1) && minCal.get(2) == maxCal.get(2) && minCal.get(5) == maxCal.get(5)?format(minDate, "HH:mm"):(minCal.get(1) == maxCal.get(1)?format(minDate, "MM-dd HH:mm"):format(minDate, "yyyy-MM-dd HH:mm"));
//        }
//    }

    public static int getStateBarHeight() {
        return Resources.getSystem().getDimensionPixelSize(Resources.getSystem().getIdentifier("status_bar_height", "dimen", "android"));
    }

    public static String getFragmentParams(String url, String key) {
        String value = "";

        try {
            Uri ex = Uri.parse(url);
            String fragment = ex.getFragment();
            if(isNotEmpty(fragment) && fragment.contains(key + "=")) {
                String[] splits = fragment.split("&");
                String[] arr$ = splits;
                int len$ = splits.length;

                for(int i$ = 0; i$ < len$; ++i$) {
                    String split = arr$[i$];
                    String[] nameValuePairs = split.split("=");
                    if(nameValuePairs != null && nameValuePairs.length == 2) {
                        String name = nameValuePairs[0];
                        if(name.equals(key)) {
                            value = nameValuePairs[1];
                            break;
                        }
                    }
                }
            }
        } catch (Exception var12) {
            var12.printStackTrace();
        }

        return value;
    }

    public static void hideKeyBoard(View view) {
        InputMethodManager imm = (InputMethodManager)AppConfig.getContext().getSystemService("input_method");
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public static void showKeyBoard(View view) {
        InputMethodManager imm = (InputMethodManager)AppConfig.getContext().getSystemService("input_method");
        imm.showSoftInput(view, 2);
    }

    public static int dpToPx(float dp, Resources resources) {
        float px = TypedValue.applyDimension(1, dp, resources.getDisplayMetrics());
        return (int)px;
    }

    public static int compareVersion(String ver1, String ver2) {
        if(isEmpty(ver1) && isNotEmpty(ver2)) {
            return -1;
        } else if(isNotEmpty(ver1) && isEmpty(ver2)) {
            return 1;
        } else {
            ArrayList ver1ItemList = new ArrayList(Arrays.asList(ver1.split("\\.")));
            ArrayList ver2ItemList = new ArrayList(Arrays.asList(ver2.split("\\.")));
            int sizeGap = Math.abs(ver1ItemList.size() - ver2ItemList.size());
            int maxSize = ver1ItemList.size() > ver2ItemList.size()?ver1ItemList.size():ver2ItemList.size();

            int i;
            for(i = 0; i < sizeGap; ++i) {
                if(ver1ItemList.size() < ver2ItemList.size()) {
                    ver1ItemList.add("0");
                } else if(ver1ItemList.size() > ver2ItemList.size()) {
                    ver2ItemList.add("0");
                }
            }

            for(i = 0; i < maxSize; ++i) {
                try {
                    int e = Integer.parseInt((String)ver1ItemList.get(i));
                    int v2 = Integer.parseInt((String)ver2ItemList.get(i));
                    if(e > v2) {
                        return 1;
                    }

                    if(e < v2) {
                        return -1;
                    }
                } catch (NumberFormatException var9) {
                    Log.w("jin", (String)null, var9);
                }
            }

            return 0;
        }
    }

//    public static long getCallRecordDuration(String phoneNumber, long startTime, long threshold) {
//        Cursor cursor = null;
//
//        long date;
//        try {
//            ContentResolver e = AppConfig.getContext().getContentResolver();
//            cursor = e.query(Calls.CONTENT_URI, new String[]{"date", "duration"}, "type=2 and number=" + phoneNumber, (String[])null, "_id desc");
//            long delta = 9223372036854775807L;
//            long duration = -1L;
//
//            while(cursor.moveToNext()) {
//                date = cursor.getLong(0);
//                long time = cursor.getLong(1);
//                if(Math.abs(startTime - date) < delta) {
//                    delta = Math.abs(startTime - date);
//                    duration = time;
//                }
//            }
//
//            if(delta > threshold) {
//                return -1L;
//            }
//
//            date = duration;
//        } catch (SecurityException var19) {
//            var19.printStackTrace();
//            StatisticsUtils.onEvent(AlaConfig.getContext(), "tel", "打电话权限被拦截");
//            return -1L;
//        } catch (Exception var20) {
//            var20.printStackTrace();
//            return -1L;
//        } finally {
//            DataUtils.close(cursor);
//        }
//
//        return date;
//    }

    public static String replaceBlank(String str) {
        String dest = "";
        if(str != null) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }

        return dest;
    }

    public static String replaceNewLine(String str) {
        return str.replaceAll("[\\t\\n\\r]", "");
    }

    public static Map<String, Object> sortMapByKey(Map<String, Object> map) {
        if(map != null && !map.isEmpty()) {
            TreeMap sortMap = new TreeMap(new MiscUtils.MapKeyComparator());
            sortMap.putAll(map);
            return sortMap;
        } else {
            return null;
        }
    }

    public static class MapKeyComparator implements Comparator<String> {
        public MapKeyComparator() {
        }

        public int compare(String str1, String str2) {
            return str1.compareTo(str2);
        }
    }
}
