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
import butterknife.OnClick;
import hise.hznu.istudy.R;
import hise.hznu.istudy.activity.adapter.ExperimentAdapter;
import hise.hznu.istudy.api.ApiResponse;
import hise.hznu.istudy.api.RequestManager;
import hise.hznu.istudy.app.AppConstant;
import hise.hznu.istudy.base.BaseActivity;
import hise.hznu.istudy.model.course.ExprementEntity;

public class MyExperimentActivity extends BaseActivity {
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
    @BindView(R.id.lv_experiment)
    ListView lvExperiment;

    private String courseId;
    private List<ExprementEntity> _dataList = new ArrayList<ExprementEntity>();
    private ExperimentAdapter adapter;
    @Override
    protected void initData() {
        super.initData();
        courseId = getIntent().getExtras().getString("courseId");
        JSONObject params = new JSONObject();
        params.put("courseid",courseId);
        RequestManager.getmInstance().apiPostData(AppConstant.GET_MY_EXPERIMENT,params,this,AppConstant.POST_GET_MY_EXPERIMENT);
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_my_experiment;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        adapter = new ExperimentAdapter(this);
        lvExperiment .setAdapter(adapter);
    }

    @Override
    public void onApiresponseSuccess(ApiResponse response, int actionId) {
        super.onApiresponseSuccess(response, actionId);
        _dataList = response.getListData(ExprementEntity.class);
        adapter.UpdateView(_dataList);
    }

    @Override
    public void onFailure(String errorMsg, int actionId) {
        super.onFailure(errorMsg, actionId);
    }


    @OnClick({R.id.tv_back, R.id.exam_name, R.id.exam_teacher, R.id.exam_start_time, R.id.exam_end_time})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_back:
                finish();
                break;
            case R.id.exam_name:
                break;
            case R.id.exam_teacher:
                break;
            case R.id.exam_start_time:
                break;
            case R.id.exam_end_time:
                break;
        }
    }
}
