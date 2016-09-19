package hise.hznu.istudy.api_v2.apiutil;

import android.os.Looper;

/**
 * Created by PC on 2016/8/29.
 */
public class ApiUtil {
    public ApiUtil(){

    }
    public static <T> void apiRequest(final ApiContext<T> apiContext){
        if(Thread.currentThread() == Looper.getMainLooper().getThread()){
            apiContext.onApiStarted();

        }else{

        }
    }
}
