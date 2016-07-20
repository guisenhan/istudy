package hise.hznu.istudy.app;

import android.app.Activity;
import android.os.Process;

import java.util.Stack;

/**
 * Created by PC on 2016/7/20.
 */
public class AppManager {
    private static Stack<Activity> activityStack;
    private static AppManager instance;

    /**
     * 单一实例
     */

    public static AppManager getInstance(){
        if(instance == null){
            instance = new AppManager();
        }
        return  instance;
    }

    public void addActivity(Activity activty){
        if(activityStack == null){
            activityStack = new Stack<Activity>();
        }
        activityStack.add(activty);
    }

    /**
     * 在堆栈中移除Activity,在OnDestroy中调用
     * */
    public void removeActivty(Activity activity){
        if(activityStack!=null){
            activityStack.remove(activity);
        }
    }

    /**
     * 获取当前activity（堆栈中最后一个压入的）
     */
    public Activity currentActivity(){
        return  activityStack.lastElement();
    }

    /**
     * 获取指定的Activity
     */
    public Activity getActivity(Class<?> cls){
        if(activityStack!=null){
            for( Activity activity : activityStack){
                if(activity.getClass().equals(cls)){
                    return activity;
                }
            }
        }
        return null;
    }

    /**
     * 结束当前Activity
     */
    public void finishActivty(){
        finishActivty(activityStack.lastElement());
    }

    /**
     * 结束指定的activity
     * @param activity
     */
    public void finishActivty(Activity activity){
        if(activity != null && !activity.isFinishing()){
            activityStack.remove(activity);
            activity.finish();
            activity = null;
        }
    }

    /**
     * 结束指定类名的Activity
     * @param cls
     */
    public void finishActivity(Class<?> cls){
        for(Activity activity :activityStack){
            if(activity.getClass().equals(cls)){
                finishActivty(activity);
                break;
            }
        }
    }
    /**
     * 结束所有Activity
     */
    public void finishAllActivity(){
        for(int i = 0 ;i< activityStack.size() ; i++ ){
            if(null!= activityStack.get(i)){
                finishActivty(activityStack.get(i));
            }
        }
        activityStack.clear();
    }

    /**
     * 退出应用程序
     */
    public void exitApp(){
        try{
            finishAllActivity();
            android.os.Process.killProcess(Process.myPid());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
