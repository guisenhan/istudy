package hise.hznu.istudy.util;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

/**
 * Created by PC on 2016/10/13.
 */
public class FileUtils {
    public static String getRealFilePathFromUri(Context context, Uri uri) {
        if(null == uri) {
            return null;
        } else {
            String scheme = uri.getScheme();
            String data = null;
            if(scheme == null) {
                data = uri.getPath();
            } else if("file".equals(scheme)) {
                data = uri.getPath();
            } else if("content".equals(scheme)) {
                Cursor cursor = context.getContentResolver().query(uri, new String[]{"_data"}, (String)null, (String[])null, (String)null);
                if(null != cursor) {
                    if(cursor.moveToFirst()) {
                        int index = cursor.getColumnIndex("_data");
                        if(index > -1) {
                            data = cursor.getString(index);
                        }
                    }

                    cursor.close();
                }
            }

            return data;
        }
    }
}
