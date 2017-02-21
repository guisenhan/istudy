package hise.hznu.istudy.app;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;

import com.squareup.leakcanary.RefWatcher;
import com.umeng.analytics.MobclickAgent;

import java.util.List;

import hise.hznu.istudy.api.RequestManager;

/**
 * Created by PC on 2016/7/20.
 */
public class IStudyApplication extends Application{
    private static float scale;
    private static int widthDp;
    private static int widthPix;
    private static int heightPix;

    private RefWatcher refWatcher;
    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }

    private void init(){
        scale = getResources().getDisplayMetrics().density;
        widthDp = getResources().getDisplayMetrics().widthPixels;
        heightPix = getResources().getDisplayMetrics().heightPixels;
        widthDp = (int)(widthPix/scale);
        RequestManager.init(this);
        MobclickAgent.UMAnalyticsConfig config = new MobclickAgent.UMAnalyticsConfig(this,"58aa488f4ad15644ac002964",
                "hznu");
        MobclickAgent.startWithConfigure(config);

        AppConfig.init(this);
    }

    private boolean isMainProcess(){
        ActivityManager am = (ActivityManager)getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> processInfos  = am.getRunningAppProcesses();
        if(processInfos != null) {
            String mainProcessName = getPackageName();
            int myPid = android.os.Process.myPid();
            for(ActivityManager.RunningAppProcessInfo info : processInfos){
                if(info.pid == myPid && mainProcessName.equals(info.processName)){
                    return true;
                }
            }
        }
            return false;
    }
    /**
     * 内存泄漏检测
    * */
    public static RefWatcher getRefWatcher(Context context){
        IStudyApplication application = (IStudyApplication)context.getApplicationContext();
        return  application.refWatcher;
    }
}
