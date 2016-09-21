package hise.hznu.istudy.api;

import com.android.volley.Request;

/**
 * Created by PC on 2016/9/20.
 */
public abstract class ApiAbsLoadControler implements ApiLoadControler {

    protected Request<?> mRequest;

    public void bindRequest(Request<?> request) {
        this.mRequest = request;
    }

    @Override
    public void cancel() {
        if (this.mRequest != null) {
            this.mRequest.cancel();
        }
    }

}
