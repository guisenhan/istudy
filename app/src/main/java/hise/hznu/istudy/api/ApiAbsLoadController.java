package hise.hznu.istudy.api;

import com.android.volley.Request;

/**
 * Created by PC on 2016/7/21.
 */
public class ApiAbsLoadController implements ApiLoadController{
    protected Request<?> mRequest;

    public void bindRequest(Request<?> request){
        this.mRequest = request;
    }
    @Override
    public void cancel() {

    }
}
