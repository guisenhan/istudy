package hise.hznu.istudy.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import hise.hznu.istudy.R;
import hise.hznu.istudy.api.RequestManager;
import hise.hznu.istudy.app.AppConstant;
import hise.hznu.istudy.app.AppManager;
import hise.hznu.istudy.base.BaseActivity;
import hise.hznu.istudy.model.LoginModel;
import hise.hznu.istudy.util.SharePreUtil;
import hise.hznu.istudy.util.UIUtils;

/**
 *  Create by GuisenHan on 2016/7/25
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.ed_username)
    EditText edUsername;
    @BindView(R.id.ed_password)
    EditText edPassword;
    @BindView(R.id.tv_login)
    TextView tvLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        AppManager.getInstance().addActivity(this);
        initViews();
    }

    public void initData() {

    }

    public void initViews() {
        tvLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_login:
                loginAction(edUsername.getText().toString(), edPassword.getText().toString());
                break;
            default:
                break;
        }
    }

    private void loginAction(String username, String password) {
        if (TextUtils.isEmpty(username)) {
            UIUtils.showToast("请输入用户名");
            return;
        }
        if (TextUtils.isEmpty(password)) {
            UIUtils.showToast("请输入密码");
            return;
        }
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("username", username);
        params.put("password", password);
        params.put("clienttype", "1");
        RequestManager.getmInstance().apiPostData(AppConstant.LOGIN_ACTION, params, this, AppConstant.POST_LOGIN_ACTION);
    }

    @Override
    public void onFailure(String errorMsg, int actionId) {

        UIUtils.showToast(errorMsg);
    }

    @Override
    public void onRequest() {
    }

    @Override
    public void onSuccess(JSONObject response, int actionId) {
        Log.e("response", response.toString());
        switch (actionId) {
            case AppConstant.POST_LOGIN_ACTION:
                LoginModel login = new Gson().fromJson(response.toString(), new TypeToken<LoginModel>() {
                }.getType());
                SharePreUtil.saveAuthorToken(this, login.getAuthtoken());
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                AppManager.getInstance().finishActivty();

                break;
        }

    }
}
