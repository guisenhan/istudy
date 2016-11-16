package hise.hznu.istudy.activity.email;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.loopj.android.http.Base64;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hise.hznu.istudy.R;
import hise.hznu.istudy.api.ApiResponse;
import hise.hznu.istudy.api.RequestManager;
import hise.hznu.istudy.app.AppConstant;
import hise.hznu.istudy.base.BaseActivity;
import hise.hznu.istudy.util.MiscUtils;

public class SendEmailActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_left)
    TextView tvLeft;
    @BindView(R.id.iv_add)
    ImageView ivAdd;
    @BindView(R.id.tv_getter)
    TextView tvGetter;
    @BindView(R.id.ed_subject)
    EditText edSubject;
    @BindView(R.id.ed_content)
    EditText edContent;
    @BindView(R.id.tv_send)
    TextView tvSend;

    private String contacter = "";
    private boolean isBack = false;
    private String parentCode;
    @Override
    protected void initData() {
        super.initData();
    }

    @Override
    protected void initExtras(Bundle extras) {
        super.initExtras(extras);
        isBack = extras.getBoolean("isBack",false);
        parentCode = extras.getString("parentCode");
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_send_email;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
    }

    @Override
    public void onApiresponseSuccess(ApiResponse response, int actionId) {
        super.onApiresponseSuccess(response, actionId);
        if(response.getRetcode()==0){
            MiscUtils.showMessageToast(response.getMessage());
            finish();
        }else{
            MiscUtils.showMessageToast(response.getMessage());
        }
    }

    @OnClick({R.id.iv_back, R.id.tv_send, R.id.iv_add})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_send:
                commit();
                break;
            case R.id.iv_add:
                Intent intent = new Intent(this,ContactActivity.class);
                startActivityForResult(intent,101);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==101 && resultCode ==100){
            contacter = data.getStringExtra("chooseId");
            tvGetter.setText(data.getStringExtra("names"));
        }
    }
    private void commit(){
        JSONObject params = new JSONObject();
        if(MiscUtils.isEmpty(edSubject.getText().toString())){
            MiscUtils.showMessageToast("请填写邮件主题");
            return;
        }
        if(MiscUtils.isEmpty(tvGetter.getText().toString())){
            MiscUtils.showMessageToast("请添加收件人");
            return;
        }
        if(MiscUtils.isEmpty(edContent.getText().toString())){
            MiscUtils.showMessageToast("请填写内容");
            return;
        }
        if(isBack){
            params.put("parentcode",parentCode);
        }
        params.put("subject",edSubject.getText().toString());
        params.put("receives",contacter);
        params.put("content",edContent.getText().toString());
        JSONObject temp = new JSONObject();
        temp.put("data",new String(Base64.encode(JSONObject.toJSONString(params).getBytes(),Base64.DEFAULT)));
        RequestManager.getmInstance().apiPostData(AppConstant.MESSAGE_SEND,temp,this,AppConstant.POST_MESSAGE_SEND);
    }
}
