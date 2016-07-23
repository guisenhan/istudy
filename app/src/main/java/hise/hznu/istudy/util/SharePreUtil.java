package hise.hznu.istudy.util;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by PC on 2016/7/23.
 */
public class SharePreUtil {
    public static final String SP_BASE_NAME = "hise.hznu.istudy.sp";
    public static final String SP_FILE_NAME = "hise.hznu.istudy.sp.base";
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
        editor.commit();
    }
}
