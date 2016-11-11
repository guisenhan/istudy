package hise.hznu.istudy.activity.course;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hise.hznu.istudy.R;
import hise.hznu.istudy.api.ApiResponse;
import hise.hznu.istudy.api.RequestManager;
import hise.hznu.istudy.app.AppConstant;
import hise.hznu.istudy.base.BaseActivity;
import hise.hznu.istudy.widget.MyListView;

public class CommentActivity extends BaseActivity {
    @BindView(R.id.tv_back)
    TextView tvBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_comment_title)
    TextView tvCommentTitle;
    @BindView(R.id.tv_student_answer)
    TextView tvStudentAnswer;
    @BindView(R.id.lv_comment)
    MyListView lvComment;
    @BindView(R.id.tv_auto_commit)
    TextView tvAutoCommit;

    private String usertestId;
    @Override
    protected void initData() {
        super.initData();
        JSONObject params = new JSONObject();
        params.put("usertestid",usertestId);
        RequestManager.getmInstance().apiPostData(AppConstant.QUERY_USER_TESTER_COMMENT,params,this,AppConstant
                .POST_QUERY_USER_TESTER_COMMENT);
    }

    @Override
    protected void initExtras(Bundle extras) {
        super.initExtras(extras);
        usertestId = extras.getString("usertestId");
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_comment;
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


    @OnClick({R.id.tv_back, R.id.tv_auto_commit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_back:
                finish();
                break;
            case R.id.tv_auto_commit:
                break;
        }
    }
}
