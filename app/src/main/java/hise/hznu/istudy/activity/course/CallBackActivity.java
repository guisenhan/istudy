package hise.hznu.istudy.activity.course;

import android.Manifest;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.loopj.android.http.Base64;
import com.loopj.android.http.RequestParams;

import jp.wasabeef.richeditor.RichEditor;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hise.hznu.istudy.R;
import hise.hznu.istudy.activity.adapter.CommentBackListAdapter;
import hise.hznu.istudy.api.ApiResponse;
import hise.hznu.istudy.api.AsyHttpClient;
import hise.hznu.istudy.api.RequestManager;
import hise.hznu.istudy.app.AppConstant;
import hise.hznu.istudy.base.BaseActivity;
import hise.hznu.istudy.model.UpLoadFileEntity;
import hise.hznu.istudy.model.course.CommentBackEntity;
import hise.hznu.istudy.util.AppUtils;
import hise.hznu.istudy.util.ImageLoaderUtils;
import hise.hznu.istudy.util.MiscUtils;
import hise.hznu.istudy.util.UIUtils;
import hise.hznu.istudy.util.clip.MCrop;
import hise.hznu.istudy.widget.MyListView;
import me.nereo.multi_image_selector.MultiImageSelector;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

public class CallBackActivity extends BaseActivity implements EasyPermissions.PermissionCallbacks{

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_email_send)
    TextView tvEmailSend;
    @BindView(R.id.action_insert_image)
    ImageButton actionInsertImage;
    @BindView(R.id.re_editor)
    RichEditor reEditor;
    @BindView(R.id.mlv_comment)
    MyListView mlvComment;

    private CommentBackListAdapter adapter;
    private List<CommentBackEntity> _dataList;

    private String tagId;
    boolean isChange = true;
    boolean isBackChange = true;
    public static final int REQUEST_IMAGE = 103;
    protected static final int REQUEST_STORAGE_READ_ACCESS_PERMISSION = 102;
    private ArrayList<String> selectPath = new ArrayList<String>();
    private String courseid;
    @Override
    protected void initData() {
        super.initData();
        adapter = new CommentBackListAdapter(this);
        _dataList = new ArrayList<CommentBackEntity>();

        mlvComment.setAdapter(adapter);
        /**
         * authtoken		：		登录证书
         count			:		每页记录数量
         page			:		第几页
         tag				:		主题id
         */
        JSONObject params = new JSONObject();
        params.put("count", "50");
        params.put("page", "1");
        params.put("tag", tagId);
        RequestManager.getmInstance()
                .apiPostData(AppConstant.FORUM_COMMENT, params, this, AppConstant.POST_FORUM_COMMENT);
    }

    @Override
    protected void initExtras(Bundle extras) {
        super.initExtras(extras);
        tagId = extras.getString("tagId");
        courseid = extras.getString("courseId");
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_call_back;
    }

    @Override
    public void onApiresponseSuccess(ApiResponse response, int actionId) {
        super.onApiresponseSuccess(response, actionId);
        switch (actionId) {
            case AppConstant.POST_FORUM_COMMENT:
                _dataList = response.getListData(CommentBackEntity.class);
                adapter.UpdateView(_dataList);
                break;
            case AppConstant.POST_FORUM_POST:
                if(response.getRetcode()== 0){
                    MiscUtils.showMessageToast("回复成功！");
                    finish();
                }
                break;
        }
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        mlvComment.setAdapter(adapter);
    }


    @OnClick({R.id.iv_back, R.id.action_insert_image,
            R.id.tv_email_send})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;

            case R.id.action_insert_image:
                pickImage();
                //插入图片到富文本框
                break;
            case R.id.tv_email_send:
                commit();
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
            selector.start(CallBackActivity.this, REQUEST_IMAGE);
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
            if (data != null) {
                Uri uri = MCrop.getOutput(data);
                if (uri == null) {
                    UIUtils.showToast("选取失败");
                } else {
                    String path = uri.getPath();
                    //上传图像
                    doUpLoad(path);
                }
            }
        }
    }

    private void doUpLoad(String path) {
        RequestParams params = new RequestParams();
        try {
            params.put("name", new File(path));
            params.put("type","3");
        } catch (IOException e) {

        }
        AsyHttpClient.get("upfile", params, handler);
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            UpLoadFileEntity upLoadFileEntity =(UpLoadFileEntity) msg.obj;
            reEditor.insertImage(upLoadFileEntity.getUploadedurl(), upLoadFileEntity.getUploadedfilename());
        }
    };

    private void commit() {
        JSONObject jsonObject = new JSONObject();
        if (MiscUtils.isEmpty(reEditor.getHtml())) { MiscUtils.showMessageToast("请输入内容"); }
        /**
         * subject	：	主题 //如果是回帖，这里空
         parentid  :   如果是回复某个帖子的， 这里填主题帖的id
         content	:	短信内容 html
         forumtypeid ： 填空即可
         projectid	：	课程id
         */
        jsonObject.put("subject","");
        jsonObject.put("parentid",tagId);
        jsonObject.put("forumtypeid","");
        jsonObject.put("content",reEditor.getHtml());
        jsonObject.put("projectid",courseid);
        JSONObject params = new JSONObject();
        params.put("data",new String(Base64.encode(JSONObject.toJSONString(jsonObject).getBytes(),Base64.DEFAULT)));
        params.put("postype","2");
        RequestManager.getmInstance().apiPostData(AppConstant.FORUM_POST,params,this,AppConstant.POST_FORUM_POST);
    }
}
