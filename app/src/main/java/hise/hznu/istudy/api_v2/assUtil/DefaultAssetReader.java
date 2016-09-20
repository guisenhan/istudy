package hise.hznu.istudy.api_v2.assUtil;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.view.Display;

import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by PC on 2016/8/29.
 */
public class DefaultAssetReader implements AssertReader{
    private Map<String,WeakReference<Drawable>> imageCaches = new HashMap<>();
    private Context context;

    private DefaultAssetReader(Context context){
        this.context = context;
    }
    private Drawable getCachedDrawable(Map<String,WeakReference<Drawable>> map,String key){
        WeakReference wr =(WeakReference) map.get(key);
        if(wr != null){
            Drawable entry = (Drawable) wr.get();
            if(entry != null){
                return entry;
            }
            map.remove(key);
        }
        return  null;
    }
    @Override
    public Drawable getDrawableOfAssets(String var1) {
        return null;
    }
//    private Drawable loadAndCacheIfNeed(String filePath,Map<String,WeakReference<Drawable>> map ){
//       Object dr = this.getCachedDrawable(map,filePath);
//        if(dr!=null){
//            return (Drawable) dr;
//        }else{
//            InputStream is = null ;
//            Object usedWidth;
//            try {
//                DisplayMetrics ex =
//            }catch (Exception var){
//                var.printStackTrace();
//                return (Drawable) dr;
//            }finally {
//
//            }
//        }
//
//        return  null;
//    }
}
