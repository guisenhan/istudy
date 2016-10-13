package hise.hznu.istudy.util;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;

import java.io.File;

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
}
