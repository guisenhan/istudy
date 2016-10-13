package hise.hznu.istudy.app;

import android.app.Activity;
import android.app.Application;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.content.LocalBroadcastManager;

import java.lang.ref.WeakReference;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by PC on 2016/7/20.
 * @author  guisenhan
 */
public class AppConfig {
    public static final String TAG = AppConfig.class.getSimpleName();

    private static final String I_STUDY_DATA_DB = "iStudyData.db";
    private static WeakReference<Activity> currentActivity; //当前正在显示的Activity
    private static  boolean debug = true; // 是否在调试模式下
    private static Application application;
    // 系统全局的线程池，Application 启动的时候创建 ，不需要销毁
    private static ExecutorService es;
    private static Handler handler ;//主线程的handler，用于方便post一些事去主线程做
    private static LocalBroadcastManager localBroadcastManager;
    private static  boolean mainProcess;

    public static void init (Application application){
        localBroadcastManager = LocalBroadcastManager.getInstance(application);
        //首先是生成线程池，最多10个，最少1个，闲置1分钟后线程退出
        es = Executors.newFixedThreadPool(10);
        AppConfig.application = application;
        handler = new Handler(Looper.getMainLooper());
    }
    public static LocalBroadcastManager getLocalBroadcastManager(){
        return  localBroadcastManager;
    }
    public static void PostOnUiThread(Runnable task){
        handler.post(task);
    }
    public static void postDelayOnUiThread(Runnable task,long delay){
        handler.postDelayed(task,delay);
    }
    public static void execute(Runnable task){
        es.execute(task);
    }
    public static Application getContext(){
        return application;
    }

    public static void setCurrentActivity(Activity activity) {
        currentActivity = new WeakReference(activity);
    }

    public static Activity getCurrentActivity() {
        return currentActivity != null?(Activity)currentActivity.get():null;
    }
    public static boolean isMainProcess(){
        return  mainProcess;
    }
    public static void setMainProcess(boolean mainProcess){
        AppConfig.mainProcess = mainProcess;
    }
}
