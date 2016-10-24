package hise.hznu.istudy.activity.mine;

import android.app.FragmentManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
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

public class SetUserInfoActivity extends BaseActivity {
    @BindView(R.id.tv_back)
    TextView tvBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_save)
    TextView tvSave;
    @BindView(R.id.ed_content)
    EditText edContent;

    private String type;
    private String title;

    private JSONObject params = new JSONObject();

    @Override
    protected void initData() {
        super.initData();
    }

    @Override
    protected void initExtras(Bundle extras) {
        super.initExtras(extras);
        title = extras.getString("title");
        type = extras.getString("type");
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_set_user_info;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        tvTitle.setText(title);
    }

    @Override
    public void onApiresponseSuccess(ApiResponse response, int actionId) {
        super.onApiresponseSuccess(response, actionId);
        if(response.getRetcode()==0){
            MiscUtils.showMessageToast("保存成功");
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

    private void commit() {
        if(MiscUtils.isEmpty(edContent.getText().toString()))
            MiscUtils.showMessageToast("请输入要修改的信息");
        JSONObject object = new JSONObject();
        object.put(type,edContent.getText().toString());
        params.put("data",new String(Base64.encode(JSONObject.toJSONString(object).getBytes(),Base64.DEFAULT)));
        RequestManager.getmInstance().apiPostData(AppConstant.SAVE_PERSON_INFO,params,this,AppConstant
                .POST_SAVE_PERSON_INFO);
    }
}
