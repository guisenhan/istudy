package hise.hznu.istudy.activity.mine;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.loopj.android.http.Base64;
import com.loopj.android.http.RequestParams;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hise.hznu.istudy.R;
import hise.hznu.istudy.api.ApiResponse;
import hise.hznu.istudy.api.AsyHttpClient;
import hise.hznu.istudy.api.RequestManager;
import hise.hznu.istudy.app.AppConstant;
import hise.hznu.istudy.base.BaseActivity;
import hise.hznu.istudy.model.UpLoadFileEntity;
import hise.hznu.istudy.model.UserInfoEntity;
import hise.hznu.istudy.util.AppUtils;
import hise.hznu.istudy.util.ImageLoaderUtils;
import hise.hznu.istudy.util.MiscUtils;
import hise.hznu.istudy.util.UIUtils;
import hise.hznu.istudy.util.clip.MCrop;
import hise.hznu.istudy.widget.CircleImageView;
import hise.hznu.istudy.widget.SexDialog;
import me.nereo.multi_image_selector.MultiImageSelector;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

public class PersonInfoActivity extends BaseActivity implements EasyPermissions.PermissionCallbacks {

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
    @BindView(R.id.rl_phone)
    RelativeLayout rlPhone;
    @BindView(R.id.rl_email)
    RelativeLayout rlEmail;
    @BindView(R.id.rl_address)
    RelativeLayout rlAddress;
    @BindView(R.id.rl_qq)
    RelativeLayout rlQq;
    @BindView(R.id.rl_zipcode)
    RelativeLayout rlZipcode;

    private ArrayList<String> selectPath;
    private File avatarClipResult;
    private UpLoadFileEntity _uploadResult = new UpLoadFileEntity();
    private UserInfoEntity userInfoEntity = new UserInfoEntity();
    private int seex = 0;
    private SexDialog dialog;
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
    private void initViewss(){
        if(MiscUtils.isNotEmpty(userInfoEntity.getUsername()))
            tvUserAccount.setText(userInfoEntity.getUsername());
        if(MiscUtils.isNotEmpty(userInfoEntity.getAddr()))
            tvAddress.setText(userInfoEntity.getAddr());
        if(MiscUtils.isNotEmpty(userInfoEntity.getEmail()))
            tvEmail.setText(userInfoEntity.getEmail());
        if (MiscUtils.isNotEmpty(userInfoEntity.getGender()))
            tvSex.setText(userInfoEntity.getGender());
        if (MiscUtils.isNotEmpty(userInfoEntity.getName()))
            tvUserName.setText(userInfoEntity.getName());
        if (MiscUtils.isNotEmpty(userInfoEntity.getPhone()))
            tvMobile.setText(userInfoEntity.getPhone());
        if (MiscUtils.isNotEmpty(userInfoEntity.getQq()))
            tvQq.setText(userInfoEntity.getQq());
        if(MiscUtils.isNotEmpty(userInfoEntity.getZipcode()))
            tvZipCode.setText(userInfoEntity.getZipcode());
        if(MiscUtils.isNotEmpty(userInfoEntity.getAvtarurl()))
            ImageLoaderUtils.getImageLoader().displayImage(userInfoEntity.getAvtarurl(),ivUserPhoto);
        if(MiscUtils.isNotEmpty(userInfoEntity.getGender())){
            if(userInfoEntity.getGender().equals("女")){
                seex =0;
            }else if(userInfoEntity.getGender().equals("男")){
                seex =1 ;
            }else{
                seex =2;
            }
        }

    }
    @Override
    public void onApiresponseSuccess(ApiResponse response, int actionId) {
        super.onApiresponseSuccess(response, actionId);
        switch (actionId) {
            case AppConstant.POST_UPLOAD_FILE:
                break;
            case AppConstant.POST_SAVE_PERSON_INFO:
                Log.e("response","" +JSONObject.toJSONString(response));
                if (response.getRetcode() == 0) {
                    if(dialog!= null&&dialog.isShowing()){
                        dialog.dismiss();
                    }
                    MiscUtils.showMessageToast("保存成功");
                    JSONObject params = new JSONObject();
                    RequestManager.getmInstance().apiPostData(AppConstant.GET_USERINFO,params,this,AppConstant.POST_GET_USERINFO);
                }
                break;
            case AppConstant.POST_GET_USERINFO:

                userInfoEntity = response.getInfo(UserInfoEntity.class);
                initViewss();
                break;

        }
    }

    @Override
    public void onFailure(String errorMsg, int actionId) {
        super.onFailure(errorMsg, actionId);
    }
    @OnClick({R.id.iv_user_photo, R.id.tv_user_account, R.id.tv_user_name, R.id.tv_sex, R.id.tv_mobile, R.id.tv_email,
            R.id.tv_address, R.id.tv_qq, R.id.tv_zip_code,R.id.rl_phone, R.id.rl_email, R.id.rl_address, R.id.rl_qq,
            R.id.rl_zipcode,R.id.tv_back
    })
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_phone:
                Intent mobile = new Intent(this, SetUserInfoActivity.class);
                mobile.putExtra("type", "phone");
                mobile.putExtra("title", "修改手机号");
                startActivity(mobile);
                break;
            case R.id.rl_email:
                Intent email = new Intent(this, SetUserInfoActivity.class);
                email.putExtra("type", "email");
                email.putExtra("title", "修改邮箱");
                startActivity(email);
                break;
            case R.id.rl_address:
                Intent address = new Intent(this, SetUserInfoActivity.class);
                address.putExtra("type", "addr");
                address.putExtra("title", "修改地址");
                startActivity(address);
                break;
            case R.id.rl_qq:
                Intent qq = new Intent(this, SetUserInfoActivity.class);
                qq.putExtra("type", "qq");
                qq.putExtra("title", "修改QQ");
                startActivity(qq);
                break;
            case R.id.rl_zipcode:
                Intent zipcode = new Intent(this, SetUserInfoActivity.class);
                zipcode.putExtra("type", "zipcode ");
                zipcode.putExtra("title", "修改邮编");
                startActivity(zipcode);
                break;
            case R.id.iv_user_photo:
                pickImage();
                break;
            case R.id.tv_user_name:
                Intent name = new Intent(this, SetUserInfoActivity.class);
                name.putExtra("type", "name");
                name.putExtra("title", "修改姓名");
                startActivity(name);
                break;
            case R.id.tv_sex:

                dialog = new SexDialog(this, seex, this, new SexDialog.OnChooseSex() {
                    @Override
                    public void callBack(int sex) {
                        seex = sex;
                        switch (sex) {
                            case 2:
                                tvSex.setText("未设置性别");
                                break;
                            case 1:
                                tvSex.setText("男");
                                break;
                            case 0:
                                tvSex.setText("女");
                                break;
                        }
                    }
                });
                dialog.show();
                break;
            case R.id.tv_back:
                finish();
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
        if (resultCode == RESULT_OK && requestCode == REQUEST_IMAGE) {
            selectPath = data.getStringArrayListExtra(MultiImageSelector.EXTRA_RESULT);
            if (MiscUtils.isNotEmpty(selectPath)) {
                File outDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
                if (!outDir.exists()) {
                    outDir.mkdirs();
                }
                File outFile = new File(outDir, System.currentTimeMillis() + ".jpg");
                //裁剪后图片的绝对路径
              //  String cameraScalePath = outFile.getAbsolutePath();
                Uri destinationUri = Uri.fromFile(outFile);
                MCrop.of(Uri.fromFile(new File(selectPath.get(0))),destinationUri).withAspectRatio(1,1)
                        .withMaxResultSize
                        (200,200).start(this);
            }
        } else if ( resultCode == RESULT_OK && requestCode == MCrop.REQUEST_CROP) {
            Log.e("path"," exe");
            if (data != null) {
                Uri uri = MCrop.getOutput(data);
                if (uri == null) {
                    UIUtils.showToast("选取失败");
                } else {
                    Log.e("path"," " +uri.getPath());
                    String path = uri.getPath();
                    avatarClipResult = new File(path);
                    ImageLoaderUtils.getImageLoader().displayImage("file://" + path, ivUserPhoto);
                    //上传图像
                    doUpLoad();
                }
            }
        }
    }

    private void doUpLoad() {
        // JSONObject params  = new JSONObject();
        RequestParams params = new RequestParams();
        try {
            params.put("name", avatarClipResult);
            params.put("type","1");
        } catch (IOException e) {

        }
        AsyHttpClient.get("upfile", params, handler);
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            JSONObject params = new JSONObject();
            UpLoadFileEntity upLoadFileEntity =(UpLoadFileEntity) msg.obj;
            params.put("avtarurl", upLoadFileEntity.getUploadedurl());
            JSONObject temp = new JSONObject();
            temp.put("data",new String(Base64.encode(JSONObject.toJSONString(params).getBytes(),Base64.DEFAULT)));
            RequestManager.getmInstance().apiPostData(AppConstant.SAVE_PERSON_INFO, temp, PersonInfoActivity.this,
                    AppConstant.POST_SAVE_PERSON_INFO);
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        JSONObject params = new JSONObject();
        RequestManager.getmInstance().apiPostData(AppConstant.GET_USERINFO,params,this,AppConstant.POST_GET_USERINFO);
    }



}
