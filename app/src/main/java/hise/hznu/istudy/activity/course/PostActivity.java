package hise.hznu.istudy.activity.course;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import java.net.URL;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hise.hznu.istudy.R;
import hise.hznu.istudy.api.ApiResponse;
import hise.hznu.istudy.base.BaseActivity;
import hise.hznu.istudy.model.course.CommentCardEntity;
import hise.hznu.istudy.util.ImageLoaderUtils;
import hise.hznu.istudy.util.MiscUtils;

public class PostActivity extends BaseActivity {
    @BindView(R.id.tv_back)
    TextView tvBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_content)
    WebView tvContent;
    @BindView(R.id.iv_comment)
    ImageView ivComment;

    private CommentCardEntity comment = new CommentCardEntity();
    private String courseId;
    @Override
    protected void initData() {
        super.initData();
    }

    @Override
    protected void initExtras(Bundle extras) {
        super.initExtras(extras);
        comment =(CommentCardEntity) extras.get("comment");
        courseId = extras.getString("courseId");
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_post;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        Log.e("content","" + comment.getContent());
      //  tvContent.setText(Html.fromHtml(comment.getContent(),imgGetter,null));
        tvContent.loadDataWithBaseURL(null,comment.getContent(), "text/html", "utf-8", null);
    }
    @Override
    public void onApiresponseSuccess(ApiResponse response, int actionId) {
        super.onApiresponseSuccess(response, actionId);
    }

    @Override
    public void onFailure(String errorMsg, int actionId) {
        super.onFailure(errorMsg, actionId);
    }


    @OnClick({R.id.tv_back, R.id.iv_comment})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_back:
                finish();
                break;
            case R.id.iv_comment:
                Intent intent = new Intent(this,CallBackActivity.class);
                intent.putExtra("tagId",comment.getId());
                intent.putExtra("courseId",courseId);
                startActivity(intent);
                break;
        }
    }
}
