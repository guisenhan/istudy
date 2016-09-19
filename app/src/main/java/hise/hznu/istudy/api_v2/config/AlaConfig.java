package hise.hznu.istudy.api_v2.config;

import android.app.Activity;
import android.app.Application;
import android.support.v4.content.LocalBroadcastManager;

import java.lang.ref.WeakReference;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import hise.hznu.istudy.api_v2.assUtil.AssertReader;

/**
 * Created by PC on 2016/8/29.
 */
public class AlaConfig {
    private static final String TAG = AlaConfig.class.getSimpleName();
    private static final String ALA_CORE_SHARED_PREFERENCE_DATA = "core.config.sp.date";
    private static WeakReference<Activity> currentActivity;
    private static boolean debug;
    private static LocalBroadcastManager localBroadcastManager; //本地广播相关
    private static AssertReader assertReader;
    private static Application application;
    private static ExecutorService es; //线程池相关
    public AlaConfig(){}
    public static void init(Application application){
        localBroadcastManager = LocalBroadcastManager.getInstance(application);
        es = Executors.newFixedThreadPool(10);
        assertReader = new DefaultaSS
    }
    public static Application getContext() {
        return application;
    }
}
