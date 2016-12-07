package hise.hznu.istudy.activity.course;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.loopj.android.http.Base64;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hise.hznu.istudy.R;
import hise.hznu.istudy.activity.adapter.AdditionalAdapter;
import hise.hznu.istudy.activity.adapter.CommentRuleAdapter;
import hise.hznu.istudy.api.ApiResponse;
import hise.hznu.istudy.api.RequestManager;
import hise.hznu.istudy.app.AppConstant;
import hise.hznu.istudy.base.BaseActivity;
import hise.hznu.istudy.model.course.CommentPaperEntity;
import hise.hznu.istudy.model.course.CommentResultEntity;
import hise.hznu.istudy.util.MiscUtils;
import hise.hznu.istudy.widget.MyGridView;
import hise.hznu.istudy.widget.MyListView;

public class CommentActivity extends BaseActivity {


    @BindView(R.id.tv_back)
    TextView tvBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.rl_top)
    RelativeLayout rlTop;
    @BindView(R.id.divider)
    View divider;
    @BindView(R.id.tv_comment_title)
    TextView tvCommentTitle;
    @BindView(R.id.tv_student_answer)
    TextView tvStudentAnswer;
    @BindView(R.id.ll_answer)
    LinearLayout llAnswer;
    @BindView(R.id.gv_file)
    MyGridView gvFile;
    @BindView(R.id.lv_comment)
    MyListView lvComment;
    @BindView(R.id.ed_comment)
    EditText edComment;
    @BindView(R.id.tv_auto_commit)
    TextView tvAutoCommit;
    @BindView(R.id.iv_left_arrow)
    ImageView ivLeftArrow;
    @BindView(R.id.iv_save)
    ImageView ivSave;
    @BindView(R.id.iv_right_arrow)
    ImageView ivRightArrow;
    @BindView(R.id.ll_bottom)
    LinearLayout llBottom;

    private String usertestId;
    private List<CommentPaperEntity> _dataList = new ArrayList<CommentPaperEntity>();
    private CommentRuleAdapter adapter;
    private List<CommentResultEntity> resultList;
    private int bProblem = 0;
    private AdditionalAdapter additionalAdapter;
    @Override
    protected void initData() {
        super.initData();
        JSONObject params = new JSONObject();
        params.put("usertestid", usertestId);
        RequestManager.getmInstance().apiPostData(AppConstant.QUERY_USER_TESTER_COMMENT, params, this,
                AppConstant.POST_QUERY_USER_TESTER_COMMENT);
    }

    @Override
    protected void initExtras(Bundle extras) {
        super.initExtras(extras);
        resultList = new ArrayList<CommentResultEntity>();
        usertestId = extras.getString("usertestId");
        tvAutoCommit.setVisibility(View.GONE);
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_comment;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        adapter = new CommentRuleAdapter(this);
        additionalAdapter = new AdditionalAdapter(this);
        gvFile.setAdapter(additionalAdapter);
        lvComment.setAdapter(adapter);
        gvFile.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(CommentActivity.this, ImageActivity.class);
                intent.putExtra("img", _dataList.get(bProblem).getAnswerfiles().get(i).getUrl());
                startActivity(intent);
            }
        });
    }

    @Override
    public void onApiresponseSuccess(ApiResponse response, int actionId) {
        super.onApiresponseSuccess(response, actionId);
        switch (actionId) {
            case AppConstant.POST_SUBMIT_HUPING:
                if (response.getRetcode() == 0) {
                    MiscUtils.showMessageToast(response.getMessage());
                    finish();
                }
                break;
            case AppConstant.POST_QUERY_USER_TESTER_COMMENT:
                _dataList = response.getListData(CommentPaperEntity.class);
                setContent(_dataList.get(bProblem));
                if (_dataList.size() == 1) {
                    tvAutoCommit.setVisibility(View.VISIBLE);
                    llBottom.setVisibility(View.GONE);
                }
                break;
        }

    }

    private void setContent(CommentPaperEntity commentPaperEntity) {
        tvCommentTitle.setText(commentPaperEntity.getContent());
        if (MiscUtils.isNotEmpty(commentPaperEntity.getAnswer())) {
            tvStudentAnswer.setText(commentPaperEntity.getAnswer());
        } else { llAnswer.setVisibility(View.GONE); }
        adapter.UpdateView(commentPaperEntity.getRules());
        additionalAdapter.UpdateView(commentPaperEntity.getAnswerfiles());
    }

    @Override
    public void onFailure(String errorMsg, int actionId) {
        super.onFailure(errorMsg, actionId);
    }

    @OnClick({R.id.tv_back, R.id.iv_left_arrow, R.id.iv_right_arrow, R.id.tv_auto_commit, R.id.iv_save})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_back:
                finish();
                break;
            case R.id.iv_left_arrow:
                if ((bProblem) > 0) {
                    bProblem = bProblem - 1;
                    if (bProblem != _dataList.size()) {
                        tvAutoCommit.setVisibility(View.GONE);
                        llBottom.setVisibility(View.VISIBLE);
                    }
                    setContent(_dataList.get(bProblem));
                } else {
                    MiscUtils.showMessageToast("没有更多了");
                }
                break;
            case R.id.iv_right_arrow:
                if (bProblem + 1 < _dataList.size()) {
                    if (bProblem == _dataList.size()) {
                        llBottom.setVisibility(View.GONE);
                        tvAutoCommit.setVisibility(View.VISIBLE);
                        return;
                    }
                    bProblem = bProblem + 1;
                    setContent(_dataList.get(bProblem));
                } else {
                    MiscUtils.showMessageToast("没有更多了！");
                }
                break;
            case R.id.tv_auto_commit:
                setResult();
                commit();
                break;
            case R.id.iv_save:
                setResult();
                break;
        }
    }

    private void commit() {
        JSONObject result = new JSONObject();
        result.put("usertestid", usertestId);
        result.put("questions", resultList);

        JSONObject params = new JSONObject();
        params.put("data", new String(Base64.encode(JSONObject.toJSONString(result).getBytes(), Base64.DEFAULT)));
        RequestManager.getmInstance()
                .apiPostData(AppConstant.QUERY_SUBMIT_HUPING, params, this, AppConstant.POST_SUBMIT_HUPING);

    }

    private void setResult() {
        int temp = -1;
        CommentResultEntity com = new CommentResultEntity();
        for (int i = 0; i < resultList.size(); i++) {
            if (resultList.get(i).getQuestionid().equals(_dataList.get(bProblem).getId())) { ; }
            {
                com = resultList.get(i);
                temp = i;
                break;
            }
        }
        com.setQuestionid(_dataList.get(bProblem).getId());
        com.setComments(edComment.getText().toString());
        com.setIsauthorvisible("false");
        List<CommentResultEntity.rules> rule1 = new ArrayList<CommentResultEntity.rules>();
        for (int i = 0; i < adapter.get_dataList().size(); i++) {
            CommentResultEntity.rules rules1 = new CommentResultEntity.rules();
            rules1.setRuleid(adapter.get_dataList().get(i).getRuleid());
            if (MiscUtils.isEmpty(adapter.get_dataList().get(i).getScore())) {
                MiscUtils.showMessageToast("请答完题再保存！");
                return;
            }
            rules1.setScore(adapter.get_dataList().get(i).getScore());
            rule1.add(rules1);
        }
        com.setRules(rule1);
        if (temp != -1) {
            resultList.set(temp, com);
        } else {
            resultList.add(com);
        }

    }
}
