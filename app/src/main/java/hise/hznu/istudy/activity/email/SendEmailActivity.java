package hise.hznu.istudy.activity.email;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hise.hznu.istudy.R;
import hise.hznu.istudy.base.BaseActivity;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_email);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.iv_back, R.id.tv_send, R.id.iv_add})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_send:
                break;
            case R.id.iv_add:
                Intent intent = new Intent(this,ContactActivity.class);
                startActivity(intent);
                break;
        }
    }
}
