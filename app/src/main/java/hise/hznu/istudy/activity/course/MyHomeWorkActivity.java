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
import hise.hznu.istudy.R;
import hise.hznu.istudy.activity.adapter.HomeWorkAdapter;
import hise.hznu.istudy.api.ApiResponse;
import hise.hznu.istudy.api.RequestManager;
import hise.hznu.istudy.app.AppConstant;
import hise.hznu.istudy.base.BaseActivity;
import hise.hznu.istudy.model.course.HomeWorkEntity;
import hise.hznu.istudy.util.AppUtils;

public class MyHomeWorkActivity extends BaseActivity implements View.OnClickListener {
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
    @BindView(R.id.lv_homework)
    ListView lvHomework;

    private String courseId;
    private HomeWorkAdapter adapter;
    private List<HomeWorkEntity> _dataList = new ArrayList<HomeWorkEntity>();
    @Override
    protected int initLayout() {
        return R.layout.activity_my_home_work;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        tvBack.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        super.initData();
        adapter = new HomeWorkAdapter(this);
        lvHomework.setAdapter(adapter);
        courseId = getIntent().getExtras().getString("courseId");
        JSONObject params = new JSONObject();
        params.put("courseid",courseId);
        RequestManager.getmInstance().apiPostData(AppConstant.GET_HOMEWORK_ACTION,params,this,AppConstant.POST_HOMEWORK_ACTION);
        lvHomework.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MyHomeWorkActivity.this,TestDetailActivity.class);
                intent.putExtra("testId",_dataList.get(i).getId());
                intent.putExtra("enableClientJudge",_dataList.get(i).isEnableClientJudge()); //是否开启客户端阅卷
                intent.putExtra("keyVisible",_dataList.get(i).isKeyVisible()); //阅卷时参考答案是否可见
                intent.putExtra("viewOneWithAnswerKey",_dataList.get(i).isViewOneWithAnswerKey()); //查卷时参考答案是否可见
                Log.e("time",""+System.currentTimeMillis()+"   "+AppUtils.DateFormat(_dataList.get(i).getDateend()));
                if(System.currentTimeMillis()> AppUtils.DateFormat(_dataList.get(i).getDateend()))
                    intent.putExtra("paperModel",1); //查模式
                else
                    intent.putExtra("paperModel",2); //答题模式
                startActivity(intent);
                //Log.e("testId",);
            }
        });
    }

    @Override
    public void onApiresponseSuccess(ApiResponse response, int actionId) {
        super.onApiresponseSuccess(response, actionId);
        _dataList = response.getListData(HomeWorkEntity.class);
        adapter.UpdateView(_dataList);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_back:
                finish();
                break;
        }
    }

}
