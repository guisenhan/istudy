package hise.hznu.istudy.util;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by PC on 2016/7/23.
 */
public class SharePreUtil {
    public static final String SP_BASE_NAME = "hise.hznu.istudy.sp";
    public static final String SP_FILE_NAME = "hise.hznu.istudy.sp.base";
    public static class SP_NAME{
        public static final String AUTHOR_TOKEN="hise.hznu.istudy.base.token";
    }
    public static void saveAuthorToken (Context context,String authorToken){
        put(context,SP_FILE_NAME,SP_NAME.AUTHOR_TOKEN,authorToken);
    }
    public static String getAuthorToken(Context context,String authorToken){
        return context.getSharedPreferences(SP_FILE_NAME,Activity.MODE_PRIVATE).getString(authorToken,"");
    }
    public static void put(Context context ,String name,String key,Object object){
        SharedPreferences sp = context.getSharedPreferences(name, Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        if(object instanceof  Integer ){
            editor.putInt(key,(Integer)object);
        }else if(object instanceof String){
            editor.putString(key,(String)object);
        }else if(object instanceof Boolean){
            editor.putBoolean(key,(Boolean)object);
        }else if(object instanceof Float){
            editor.putFloat(key,(Float) object);
        }else if(object instanceof Long){
            editor.putLong(key,(Long)object);
        }else{
            editor.putString(key,object.toString());
        }
        SharePreferenceCompat.apply(editor);
    }
    public static class SharePreferenceCompat{
        private static final Method sApplyMethod = findApplyMethod();
        private static Method findApplyMethod() {
            try {
                Class clz = SharedPreferences.Editor.class;
                return clz.getMethod("apply");
            } catch (NoSuchMethodException e) {
            }

            return null;
        }
        public static void apply(SharedPreferences.Editor editor) {
            try {
                if (sApplyMethod != null) {
                    sApplyMethod.invoke(editor);
                    return;
                }
            } catch (IllegalArgumentException e) {
            } catch (IllegalAccessException e) {
            } catch (InvocationTargetException e) {
            }
            editor.commit();
        }
    }
}
