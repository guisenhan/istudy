package hise.hznu.istudy.activity.course;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import hise.hznu.istudy.R;
import hise.hznu.istudy.activity.adapter.CommentEachAdapter;
import hise.hznu.istudy.api.ApiResponse;
import hise.hznu.istudy.api.RequestManager;
import hise.hznu.istudy.app.AppConstant;
import hise.hznu.istudy.base.BaseActivity;
import hise.hznu.istudy.model.course.CommentTaskEntity;

public class CommentEachActivity extends BaseActivity {

    @BindView(R.id.tv_back)
    TextView tvBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.exam_name)
    TextView examName;
    @BindView(R.id.exam_teacher)
    TextView examTeacher;
    @BindView(R.id.exam_start_time)
    TextView examStartTime;
    @BindView(R.id.exam_end_time)
    TextView examEndTime;
    @BindView(R.id.lv_comment)
    ListView lvComment;
    private CommentEachAdapter adapter;
    private List<CommentTaskEntity> _dataList = new ArrayList<CommentTaskEntity>();
    String courseId;

    @Override
    protected void initData() {
        super.initData();
        adapter = new CommentEachAdapter(this);
        lvComment.setAdapter(adapter);
        JSONObject params = new JSONObject();
        courseId = getIntent().getExtras().getString("courseId");
        params.put("courseid", courseId);
        RequestManager.getmInstance().apiPostData(AppConstant.GET_COMMENT_EACH, params, this, AppConstant.POST_COMMENT_EACH);
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_comment_each;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        tvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void onApiresponseSuccess(ApiResponse response, int actionId) {
        super.onApiresponseSuccess(response, actionId);
        _dataList = response.getListData(CommentTaskEntity.class);
        adapter.UpdateView(_dataList);
    }

}