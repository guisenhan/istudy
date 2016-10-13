package hise.hznu.istudy.activity.mine;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hise.hznu.istudy.R;
import hise.hznu.istudy.api.ApiResponse;
import hise.hznu.istudy.base.BaseActivity;
import hise.hznu.istudy.util.AppUtils;
import hise.hznu.istudy.util.MiscUtils;
import hise.hznu.istudy.util.UIUtils;
import hise.hznu.istudy.widget.CircleImageView;
import me.nereo.multi_image_selector.MultiImageSelector;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

public class PersonInfoActivity extends BaseActivity implements EasyPermissions.PermissionCallbacks{

    protected static final int REQUEST_STORAGE_READ_ACCESS_PERMISSION = 102;
    public static final int REQUEST_IMAGE = 103;
    @BindView(R.id.iv_user_photo)
    CircleImageView ivUserPhoto;
    @BindView(R.id.iv_arrow)
    ImageView ivArrow;
    @BindView(R.id.rv_user_photo)
    RelativeLayout rvUserPhoto;
    @BindView(R.id.tv_user_account)
    TextView tvUserAccount;
    @BindView(R.id.tv_user_name)
    TextView tvUserName;
    @BindView(R.id.tv_sex)
    TextView tvSex;
    @BindView(R.id.tv_mobile)
    TextView tvMobile;
    @BindView(R.id.tv_email)
    TextView tvEmail;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.tv_qq)
    TextView tvQq;
    @BindView(R.id.tv_zip_code)
    TextView tvZipCode;

    private ArrayList<String> selectPath;
    private File avatarClipResult;
    @Override
    protected void initData() {
        super.initData();
        selectPath = new ArrayList<String>();
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_person_info;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
    }

    @Override
    public void onApiresponseSuccess(ApiResponse response, int actionId) {
        super.onApiresponseSuccess(response, actionId);
    }

    @Override
    public void onFailure(String errorMsg, int actionId) {
        super.onFailure(errorMsg, actionId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.iv_user_photo, R.id.tv_user_account, R.id.tv_user_name, R.id.tv_sex, R.id.tv_mobile, R.id.tv_email, R.id.tv_address, R.id.tv_qq, R.id.tv_zip_code})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_user_photo:
                pickImage();
                break;
            case R.id.tv_user_account:
                break;
            case R.id.tv_user_name:
                break;
            case R.id.tv_sex:
                break;
            case R.id.tv_mobile:
                break;
            case R.id.tv_email:
                break;
            case R.id.tv_address:
                break;
            case R.id.tv_qq:
                break;
            case R.id.tv_zip_code:
                break;
        }
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        if (requestCode == REQUEST_STORAGE_READ_ACCESS_PERMISSION) {
        }
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @AfterPermissionGranted(REQUEST_STORAGE_READ_ACCESS_PERMISSION)
    private void pickImage() {
        String[] perms = {Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE};
        if (!EasyPermissions.hasPermissions(this, perms)) {
            EasyPermissions.requestPermissions(this, getString(R.string.mis_permission_rationale),
                    REQUEST_STORAGE_READ_ACCESS_PERMISSION, perms);
        } else {
            MultiImageSelector selector = MultiImageSelector.create();
            selector.showCamera(true);
            selector.single();
            selector.origin(selectPath);
            selector.start(PersonInfoActivity.this, REQUEST_IMAGE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
             if (requestCode == REQUEST_IMAGE) {
                selectPath = data.getStringArrayListExtra(MultiImageSelector.EXTRA_RESULT);
                if(MiscUtils.isNotEmpty(selectPath)){
                    AppUtils.startClipAvatarActivity(this, new File(selectPath.get(0)));
                }
            }else if (requestCode == AppUtils.REQUEST_CODE_CLIP_PHOTO) {
                if(data != null) {
                    Uri uri = data.getData();
                    if (uri == null) {
                        UIUtils.showToast("选取失败");
                    } else {
                        String path = uri.getPath();
                        avatarClipResult = new File(path);
                        //上传图像
                       // doUpload(ivUserPhoto);
                    }
                }
            }
        }
    }
}
