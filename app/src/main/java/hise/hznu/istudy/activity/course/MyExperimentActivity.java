package hise.hznu.istudy.activity.course;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
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
import hise.hznu.istudy.util.AppUtils;

public class MyExperimentActivity extends BaseActivity {
    @BindView(R.id.tv_back)
    TextView tvBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
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
        lvExperiment.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MyExperimentActivity.this,TestDetailActivity.class);
                intent.putExtra("testId",_dataList.get(i).getId());
                intent.putExtra("title","我的实验");
                intent.putExtra("enableClientJudge",_dataList.get(i).isEnableClientJudge()); //是否开启客户端阅卷
                intent.putExtra("keyVisible",_dataList.get(i).isKeyVisible()); //阅卷时参考答案是否可见
                intent.putExtra("viewOneWithAnswerKey",_dataList.get(i).isViewOneWithAnswerKey()); //查卷时参考答案是否可见
                if(System.currentTimeMillis()> AppUtils.DateFormat(_dataList.get(i).getDateend()))
                    intent.putExtra("paperModel",1); //查模式
                else
                    intent.putExtra("paperModel",2); //答题模式
                startActivity(intent);
            }
        });
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


    @OnClick({R.id.tv_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_back:
                finish();
                break;
        }
    }
}
