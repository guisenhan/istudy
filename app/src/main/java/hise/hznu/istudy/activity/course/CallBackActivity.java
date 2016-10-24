package hise.hznu.istudy.activity.course;

import android.Manifest;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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
import hise.hznu.istudy.util.MiscUtils;
import hise.hznu.istudy.util.UIUtils;
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
    @BindView(R.id.action_undo)
    ImageButton actionUndo;
    @BindView(R.id.action_redo)
    ImageButton actionRedo;
    @BindView(R.id.action_bold)
    ImageButton actionBold;
    @BindView(R.id.action_italic)
    ImageButton actionItalic;
    @BindView(R.id.action_subscript)
    ImageButton actionSubscript;
    @BindView(R.id.action_superscript)
    ImageButton actionSuperscript;
    @BindView(R.id.action_strikethrough)
    ImageButton actionStrikethrough;
    @BindView(R.id.action_underline)
    ImageButton actionUnderline;
    @BindView(R.id.action_heading1)
    ImageButton actionHeading1;
    @BindView(R.id.action_heading2)
    ImageButton actionHeading2;
    @BindView(R.id.action_heading3)
    ImageButton actionHeading3;
    @BindView(R.id.action_heading4)
    ImageButton actionHeading4;
    @BindView(R.id.action_heading5)
    ImageButton actionHeading5;
    @BindView(R.id.action_heading6)
    ImageButton actionHeading6;
    @BindView(R.id.action_txt_color)
    ImageButton actionTxtColor;
    @BindView(R.id.action_bg_color)
    ImageButton actionBgColor;
    @BindView(R.id.action_indent)
    ImageButton actionIndent;
    @BindView(R.id.action_outdent)
    ImageButton actionOutdent;
    @BindView(R.id.action_align_left)
    ImageButton actionAlignLeft;
    @BindView(R.id.action_align_center)
    ImageButton actionAlignCenter;
    @BindView(R.id.action_align_right)
    ImageButton actionAlignRight;
    @BindView(R.id.action_insert_bullets)
    ImageButton actionInsertBullets;
    @BindView(R.id.action_insert_numbers)
    ImageButton actionInsertNumbers;
    @BindView(R.id.action_blockquote)
    ImageButton actionBlockquote;
    @BindView(R.id.action_insert_image)
    ImageButton actionInsertImage;
    @BindView(R.id.action_insert_link)
    ImageButton actionInsertLink;
    @BindView(R.id.action_insert_checkbox)
    ImageButton actionInsertCheckbox;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.iv_back, R.id.action_undo, R.id.action_redo, R.id.action_bold, R.id.action_italic,
            R.id.action_subscript, R.id.action_superscript, R.id.action_strikethrough, R.id.action_underline,
            R.id.action_heading1, R.id.action_heading2, R.id.action_heading3, R.id.action_heading4,
            R.id.action_heading5, R.id.action_heading6, R.id.action_txt_color, R.id.action_bg_color, R.id.action_indent,
            R.id.action_outdent, R.id.action_align_left, R.id.action_align_center, R.id.action_align_right,
            R.id.action_insert_bullets, R.id.action_insert_numbers, R.id.action_blockquote, R.id.action_insert_image,
            R.id.action_insert_link, R.id.action_insert_checkbox,R.id.tv_email_send})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.action_undo:
                reEditor.undo();
                break;
            case R.id.action_redo:
                reEditor.redo();
                break;
            case R.id.action_bold:
                reEditor.setBold();
                break;
            case R.id.action_italic:
                reEditor.setItalic();
                break;
            case R.id.action_subscript:
                reEditor.setSubscript();
                break;
            case R.id.action_superscript:
                reEditor.setSuperscript();
                break;
            case R.id.action_strikethrough:
                reEditor.setStrikeThrough();
                break;
            case R.id.action_underline:
                reEditor.setUnderline();
                break;
            case R.id.action_heading1:
                reEditor.setHeading(1);
                break;
            case R.id.action_heading2:
                reEditor.setHeading(2);
                break;
            case R.id.action_heading3:
                reEditor.setHeading(3);
                break;
            case R.id.action_heading4:
                reEditor.setHeading(4);
                break;
            case R.id.action_heading5:
                reEditor.setHeading(5);
                break;
            case R.id.action_heading6:
                reEditor.setHeading(6);
                break;
            case R.id.action_txt_color:
                reEditor.setTextColor(isChange ? Color.BLACK : Color.RED);
                isChange = !isChange;
                break;
            case R.id.action_bg_color:
                reEditor.setTextBackgroundColor(isBackChange ? Color.TRANSPARENT : Color.YELLOW);
                isBackChange = !isBackChange;
                break;
            case R.id.action_indent:
                reEditor.setIndent();
                break;
            case R.id.action_outdent:
                reEditor.setOutdent();
                break;
            case R.id.action_align_left:
                reEditor.setAlignLeft();
                break;
            case R.id.action_align_center:
                reEditor.setAlignCenter();
                break;
            case R.id.action_align_right:
                reEditor.setAlignRight();
                break;
            case R.id.action_insert_bullets:
                reEditor.setBullets();
                break;
            case R.id.action_insert_numbers:
                reEditor.setNumbers();
                break;
            case R.id.action_blockquote:
                reEditor.setBlockquote();
                break;
            case R.id.action_insert_image:
                pickImage();
                //插入图片到富文本框
                break;
            case R.id.action_insert_link:
                break;
            case R.id.action_insert_checkbox:
                reEditor.insertTodo();
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
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_IMAGE) {
                selectPath = data.getStringArrayListExtra(MultiImageSelector.EXTRA_RESULT);
                if (MiscUtils.isNotEmpty(selectPath)) {
                    AppUtils.startClipAvatarActivity(this, new File(selectPath.get(0)));
                }
            } else if (requestCode == AppUtils.REQUEST_CODE_CLIP_PHOTO) {
                if (data != null) {
                    Uri uri = data.getData();
                    if (uri == null) {
                        UIUtils.showToast("选取失败");
                    } else {
                        doUpLoad();
                    }
                }
            }
        }
    }

    private void doUpLoad() {
        RequestParams params = new RequestParams();
        try {
            params.put("name", new File(selectPath.get(0)));
        } catch (IOException e) {

        }
        AsyHttpClient.get("upfile", params, handler);
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            org.json.JSONObject response = (org.json.JSONObject) msg.obj;
            UpLoadFileEntity upLoadFileEntity = new UpLoadFileEntity();
            upLoadFileEntity = new ApiResponse(JSON.parseObject(response.toString())).getInfo(UpLoadFileEntity.class);
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
