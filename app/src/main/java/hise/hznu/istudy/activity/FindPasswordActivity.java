package hise.hznu.istudy.activity;

import android.graphics.BitmapRegionDecoder;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import hise.hznu.istudy.model.TokenEntity;
import hise.hznu.istudy.util.AppUtils;
import hise.hznu.istudy.util.MiscUtils;

public class FindPasswordActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.ed_email)
    EditText edEmail;
    @BindView(R.id.tv_next)
    TextView tvNext;
    @BindView(R.id.input_email_address)
    LinearLayout inputEmailAddress;
    @BindView(R.id.ed_validate)
    EditText edValidate;
    @BindView(R.id.tv_validata_next)
    TextView tvValidataNext;
    @BindView(R.id.ll_validate)
    LinearLayout llValidate;
    @BindView(R.id.ed_pass1)
    EditText edPass1;
    @BindView(R.id.ed_pass2)
    EditText edPass2;
    @BindView(R.id.tv_sure)
    TextView tvSure;
    @BindView(R.id.ll_password)
    LinearLayout llPassword;

    private String token;
    private int type = 1;
    private String email;
    @Override
    protected void initExtras(Bundle extras) {
        super.initExtras(extras);
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_find_password;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        llPassword.setVisibility(View.GONE);
        llValidate.setVisibility(View.GONE);
        inputEmailAddress.setVisibility(View.GONE);
        setView(type);
    }

    @Override
    public void onApiresponseSuccess(ApiResponse response, int actionId) {
        super.onApiresponseSuccess(response, actionId);
        switch (actionId){
            case AppConstant.POST_GET_VALIDATE_CODE:

                if(response.getRetcode() ==0){
                    type = 2 ;
                    setView(type);
                    email= edEmail.getText().toString();
                }
                MiscUtils.showMessageToast(response.getMessage());
                break;
            case AppConstant.POST_VALIDATE_VERIFY_CODE:
                if(response.getRetcode()==0){
                    type = 3;
                    setView(3);
                    TokenEntity token1 = new TokenEntity();
                    token1 = response.getInfo(TokenEntity.class);
                    token = token1.getToken();
                }
                MiscUtils.showMessageToast(response.getMessage());
                break;
            case AppConstant.POST_SET_PASSWORD:
                if(response.getRetcode()==0){
                    MiscUtils.showMessageToast(response.getMessage());
                    finish();
                }else{
                    MiscUtils.showMessageToast(response.getMessage());
                }
                break;
        }
    }
    private void setView(int type){
        switch (type){
            case 1:
                inputEmailAddress.setVisibility(View.VISIBLE);
                llValidate.setVisibility(View.GONE);
                llPassword.setVisibility(View.GONE);
                tvTitle.setText("输入邮箱");
                break;
            case 2:
                inputEmailAddress.setVisibility(View.GONE);
                llValidate.setVisibility(View.VISIBLE);
                llPassword.setVisibility(View.GONE);
                tvTitle.setText("输入验证码");
                break;
            case 3:
                inputEmailAddress.setVisibility(View.GONE);
                llValidate.setVisibility(View.GONE);
                llPassword.setVisibility(View.VISIBLE);
                tvTitle.setText("填写密码");
                break;
        }
    }
    @OnClick({R.id.iv_back, R.id.tv_next, R.id.tv_validata_next, R.id.tv_sure})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                type = type -1;
                if(type ==0){
                    finish();
                }else{
                    setView(type);
                }
                break;
            case R.id.tv_next:
                getValidate();
                break;
            case R.id.tv_validata_next:
                validateVerifyCode();
                break;
            case R.id.tv_sure:
                setLlPassword();
                break;
        }
    }
    private void getValidate(){
        if(MiscUtils.isEmpty(edEmail.getText().toString())){
            MiscUtils.showMessageToast("请输入邮箱");
             return;
        }
        if(!AppUtils.isEmail(edEmail.getText().toString())){
            MiscUtils.showMessageToast("请输入合法的邮箱");
            return;
        }
        JSONObject params = new JSONObject();
        params.put("email",edEmail.getText().toString());
        RequestManager.getmInstance().apiPostData(AppConstant.GET_VALIDATE_CODE,params,this,AppConstant
                .POST_GET_VALIDATE_CODE);
    }

    private void validateVerifyCode(){
        if(MiscUtils.isEmpty(edValidate.getText().toString())){
            MiscUtils.showMessageToast("请输入验证码");
            return;
        }
        JSONObject params = new JSONObject();
        params.put("email",email);
        params.put("validcode",edValidate.getText().toString());
        RequestManager.getmInstance().apiPostData(AppConstant.VALIDATE_VERIFY_CODE,params,this,AppConstant
                .POST_VALIDATE_VERIFY_CODE);
    }

    private void setLlPassword(){
        if(MiscUtils.isEmpty(edPass1.getText().toString())){
            MiscUtils.showMessageToast("请输入新密码");
            return;
        }
        if(MiscUtils.isEmpty(edPass2.getText().toString())){
            MiscUtils.showMessageToast("请确认新密码");
            return;
        }
        if(!edPass1.getText().toString().equals(edPass2.getText().toString())){
            MiscUtils.showMessageToast("两次输入密码不一致");
            return;
        }
        JSONObject params = new JSONObject();
        params.put("token",token);
        params.put("newpassword",edPass1.getText().toString());
        RequestManager.getmInstance().apiPostData(AppConstant.SETPASSWORD,params,this,AppConstant
                .POST_SET_PASSWORD);
    }

    @Override
    public void onBackPressed() {
        type = type -1;
        if(type ==0){
            finish();
        }else{
            setView(type);
        }
    }
}
