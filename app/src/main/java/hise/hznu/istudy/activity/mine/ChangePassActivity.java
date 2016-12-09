package hise.hznu.istudy.activity.mine;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hise.hznu.istudy.R;
import hise.hznu.istudy.api.ApiResponse;
import hise.hznu.istudy.api.RequestManager;
import hise.hznu.istudy.app.AppConstant;
import hise.hznu.istudy.base.BaseActivity;
import hise.hznu.istudy.util.AppUtils;
import hise.hznu.istudy.util.MiscUtils;

public class ChangePassActivity extends BaseActivity {


    @BindView(R.id.tv_back)
    TextView tvBack;
    @BindView(R.id.ed_old_pass)
    EditText edOldPass;
    @BindView(R.id.ed_new_pass)
    EditText edNewPass;
    @BindView(R.id.ed_new_passs)
    EditText edNewPasss;
    @BindView(R.id.tv_save)
    TextView tvSave;

    @Override
    protected void initData() {
        super.initData();
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_change_pass;
    }

    @Override
    public void onApiresponseSuccess(ApiResponse response, int actionId) {
        super.onApiresponseSuccess(response, actionId);
        if(response.getRetcode()==0){
            MiscUtils.showMessageToast("修改成功");
            finish();
        }
    }


    @OnClick({R.id.tv_back, R.id.tv_save})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_back:
                finish();
                break;
            case R.id.tv_save:
                commit();
                break;
        }
    }
    private void commit(){
        if(MiscUtils.isEmpty(edOldPass.getText().toString())){
            MiscUtils.showMessageToast("请输入旧密码");
            return;
        }
        if(MiscUtils.isEmpty(edNewPass.getText().toString())){
            MiscUtils.showMessageToast("请输入新密码");
            return;
        }
        if(MiscUtils.isEmpty(edNewPasss.toString().toString())){
            MiscUtils.showMessageToast("请确认密码");
            return;
        }
        if(!edNewPass.getText().toString().equals(edNewPasss.getText().toString())){
            MiscUtils.showMessageToast("两次输入密码不一致");
            return;
        }
        JSONObject params = new JSONObject();
        params.put("oldpassword",edOldPass.getText().toString());
        params.put("newpassword",edNewPass.getText().toString());
        RequestManager.getmInstance().apiPostData(AppConstant.CHANGE_PASSWORD,params,this,AppConstant
                .POST_CHANGE_PASSWORD);
    }
}
