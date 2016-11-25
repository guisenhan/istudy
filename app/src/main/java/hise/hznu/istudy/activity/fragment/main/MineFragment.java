package hise.hznu.istudy.activity.fragment.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.makeramen.roundedimageview.RoundedImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hise.hznu.istudy.R;
import hise.hznu.istudy.activity.LoginActivity;
import hise.hznu.istudy.activity.mine.ChangePassActivity;
import hise.hznu.istudy.activity.mine.PersonInfoActivity;
import hise.hznu.istudy.api.ApiResponse;
import hise.hznu.istudy.api.RequestManager;
import hise.hznu.istudy.app.AppConstant;
import hise.hznu.istudy.app.AppManager;
import hise.hznu.istudy.base.BaseFragment;
import hise.hznu.istudy.model.UserInfoEntity;
import hise.hznu.istudy.util.ImageLoaderUtils;
import hise.hznu.istudy.util.MiscUtils;

/**
 * Created by GuisenHan on 2016/7/25.
 */
public class MineFragment extends BaseFragment {
    @BindView(R.id.riv_user_photo)
    RoundedImageView rivUserPhoto;
    @BindView(R.id.tv_user_name)
    TextView tvUserName;
    @BindView(R.id.tv_safe_setting)
    TextView tvSafeSetting;
    @BindView(R.id.tv_exit)
    TextView tvExit;
    @BindView(R.id.ll_person_info)
    LinearLayout llPersonInfo;
    @BindView(R.id.tv_switch)
    TextView tvSwitch;
    @BindView(R.id.tv_account)
    TextView tvAccount;
    @Override
    protected void initData() {
        super.initData();
        JSONObject params = new JSONObject();
        RequestManager.getmInstance()
                .apiPostData(AppConstant.GET_USERINFO, params, this, AppConstant.POST_GET_USERINFO);
    }

    @Override
    protected int initLayout() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
    }

    @Override
    public void onSuccess(JSONObject response, int actionId) {
        super.onSuccess(response, actionId);
    }

    @Override
    public void onFailure(String errorMsg, int actionId) {
        super.onFailure(errorMsg, actionId);
    }

    @Override
    protected void onApiResponseSuccess(ApiResponse apiResponse, int actionId) {
        super.onApiResponseSuccess(apiResponse, actionId);
        UserInfoEntity userInfoEntity = apiResponse.getInfo(UserInfoEntity.class);
        if (MiscUtils.isNotEmpty(userInfoEntity.getName())) { tvUserName.setText(userInfoEntity.getName()); }
        if (MiscUtils.isNotEmpty(userInfoEntity.getAvtarurl())) {
            ImageLoaderUtils.getImageLoader().displayImage(userInfoEntity.getAvtarurl(), rivUserPhoto);
        }
        if(MiscUtils.isNotEmpty(userInfoEntity.getUsername())){
            tvAccount.setText(userInfoEntity.getUsername());
        }
    }

    @OnClick({ R.id.ll_person_info, R.id.tv_safe_setting, R.id.tv_exit,R.id.tv_switch})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_switch:
                Intent Login = new Intent(getActivity(), LoginActivity.class);
                MiscUtils.setSharedPreferenceValue("token","tokens"," ");
                startActivity(Login);
                getActivity().finish();
                break;
            case R.id.ll_person_info:
                Intent intent = new Intent(getActivity(), PersonInfoActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_safe_setting:
                Intent intent1 = new Intent(getActivity(), ChangePassActivity.class);
                startActivity(intent1);
                break;
            case R.id.tv_exit:
                AppManager.getInstance().finishAllActivity();
                break;
        }
    }


}
