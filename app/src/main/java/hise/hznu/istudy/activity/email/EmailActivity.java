package hise.hznu.istudy.activity.email;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.net.URL;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hise.hznu.istudy.R;
import hise.hznu.istudy.api.ApiResponse;
import hise.hznu.istudy.api.RequestManager;
import hise.hznu.istudy.app.AppConstant;
import hise.hznu.istudy.base.BaseActivity;
import hise.hznu.istudy.model.email.EmailEntity;
import hise.hznu.istudy.model.email.SendEmailEntity;
import hise.hznu.istudy.util.ImageLoaderUtils;
import hise.hznu.istudy.util.MiscUtils;

public class EmailActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.rl_top)
    RelativeLayout rlTop;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_email_sender)
    TextView tvEmailSender;
    @BindView(R.id.tv_date)
    TextView tvDate;
    @BindView(R.id.ll_top)
    LinearLayout llTop;
    @BindView(R.id.divider)
    View divider;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.iv_email_back)
    ImageView ivEmailBack;
    @BindView(R.id.iv_delete)
    ImageView ivDelete;

    private EmailEntity data;
    private SendEmailEntity sendEmailEntity;
    private String msgId;
    private int type;
    @Override
    protected void initData() {
        super.initData();
        if(type==1){
            JSONObject read = new JSONObject();
            msgId = data.getId();
            read.put("msgid",msgId);
            RequestManager.getmInstance().apiPostData(AppConstant.EMAIL_READED,read,this,AppConstant.POST_EMAIL_READED);
        }

    }

    @Override
    protected void initExtras(Bundle extras) {
        super.initExtras(extras);
        data = new EmailEntity();
        sendEmailEntity = new SendEmailEntity();
        type = extras.getInt("type");
        if(type ==1){
            data = (EmailEntity) extras.get("email");
        }else if(type ==2){
            sendEmailEntity = (SendEmailEntity)extras.get("email");
        }
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_email;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        if(type ==1) {
            tvName.setText(data.getSubject());
            tvContent.setText(Html.fromHtml(data.getContent(),imgGetter,null));
            tvEmailSender.setText("发件人：" + data.getSendername());
        }else if(type ==2){
            tvName.setText(sendEmailEntity.getSubject());
            tvContent.setText(Html.fromHtml(sendEmailEntity.getContent(),imgGetter,null));
            StringBuilder sb = new StringBuilder();
            for(int i= 0 ; i < sendEmailEntity.getReceives().size(); i++){
                sb.append(sendEmailEntity.getReceives().get(i).getName());
                if(i!= sendEmailEntity.getReceives().size()-1){
                    sb.append("，");
                }
            }
            tvEmailSender.setText("收件人："+sb.toString());
        }

    }
    Html.ImageGetter imgGetter = new Html.ImageGetter() {
        public Drawable getDrawable(String source) {
            Drawable drawable = null;
            URL url;
            try {
                url = new URL(source);
                drawable =new BitmapDrawable(ImageLoaderUtils.getImageLoader().loadImageSync(url.toString())); // 获取网路图片
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
            drawable.setBounds(0, 0, MiscUtils.getScreenWidth(),
                    (drawable.getIntrinsicHeight() * MiscUtils.getScreenWidth()) / drawable.getIntrinsicWidth());
            return drawable;
        }
    };
    @Override
    public void onApiresponseSuccess(ApiResponse response, int actionId) {
        super.onApiresponseSuccess(response, actionId);
        switch (actionId){
            case AppConstant.POST_DELETE_EMAIL:
                if(response.getRetcode()==0){
                    finish();
                }else{
                    MiscUtils.showMessageToast(response.getMessage());
                }
                break;
        }
    }

    @OnClick({R.id.iv_back, R.id.iv_email_back, R.id.iv_delete})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_email_back:
                Intent intent = new Intent(this,SendEmailActivity.class);
                intent.putExtra("isBack",true);
                intent.putExtra("parentCode",data.getId());
                startActivity(intent);
                break;
            case R.id.iv_delete:
                JSONObject delete = new JSONObject();
                delete.put("msgid",msgId);
                RequestManager.getmInstance().apiPostData(AppConstant.DELETE_EMAIL,delete,this,AppConstant
                        .POST_DELETE_EMAIL);
                break;
        }
    }
}
