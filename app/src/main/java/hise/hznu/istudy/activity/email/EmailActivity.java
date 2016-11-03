package hise.hznu.istudy.activity.email;

import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hise.hznu.istudy.R;
import hise.hznu.istudy.api.ApiResponse;
import hise.hznu.istudy.base.BaseActivity;
import hise.hznu.istudy.model.email.EmailEntity;

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
    @BindView(R.id.tv_email_geter)
    TextView tvEmailGeter;
    @BindView(R.id.tv_date)
    TextView tvDate;
    @BindView(R.id.tv_hide)
    TextView tvHide;
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
    @Override
    protected void initData() {
        super.initData();
    }

    @Override
    protected void initExtras(Bundle extras) {
        super.initExtras(extras);
        data = new EmailEntity();
        data = (EmailEntity) extras.get("email");
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_email;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        tvName.setText(data.getSubject());
        tvContent.setText(Html.fromHtml(data.getContent()));
        tvEmailGeter.setText(data.getSendername());

    }

    @Override
    public void onApiresponseSuccess(ApiResponse response, int actionId) {
        super.onApiresponseSuccess(response, actionId);
    }

    @OnClick({R.id.iv_back, R.id.iv_email_back, R.id.iv_delete})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_email_back:
                break;
            case R.id.iv_delete:
                break;
        }
    }
}
