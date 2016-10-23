package hise.hznu.istudy.util;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import hise.hznu.istudy.util.clip.ClipHeaderActivity;

/**
 * Created by PC on 2016/10/13.
 */
public class AppUtils {
    public final static int REQUEST_CODE_CLIP_PHOTO = 10984;
    public static void startClipAvatarActivity(Activity activity, File photoFile) {
        Intent intent = new Intent(activity, ClipHeaderActivity.class);
        intent.setData(Uri.fromFile(photoFile));
        intent.putExtra("side_length", 200);//裁剪图片宽高
        activity.startActivityForResult(intent, REQUEST_CODE_CLIP_PHOTO);
    }

    /**
     * 服务端获取到的时间格式为yyyyMMddHHmmss
     * 这个方法将其转换为正常的显示格式
     * @param date
     * @return
     */
    public static String dateFormat(String date){
        String result = "" ;
        SimpleDateFormat sim = new SimpleDateFormat("yyyyMMddHHmmss");
        SimpleDateFormat sim1 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try {
            Date date1 = sim.parse(date);
            result = sim1.format(date1);
        }catch (ParseException e){
            e.getMessage();
        }
        return  result;
    }

    public static long DateFormat(String date){
        long result = 0;
        SimpleDateFormat sim = new SimpleDateFormat("yyyyMMddHHmmss");
        try {
            Date date1 = sim.parse(date);
            result = date1.getTime();
        }catch (ParseException e){
            e.getMessage();
        }
        return result;
    }
}
