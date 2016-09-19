package hise.hznu.istudy.api_v2.apiutil;

/**
 * Created by PC on 2016/8/29.
 */
public interface ApiContext<T> {
    T request() throws Exception;

    void onApiSuccess(T var1);

    void onApiFailure(Exception var1);

    void onApiStarted();

    void onApiFinished();
}
