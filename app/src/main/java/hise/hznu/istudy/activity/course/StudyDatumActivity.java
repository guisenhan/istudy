package hise.hznu.istudy.activity.course;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import hise.hznu.istudy.R;
import hise.hznu.istudy.activity.adapter.CourseDatumAdapter;
import hise.hznu.istudy.api.ApiResponse;
import hise.hznu.istudy.api.RequestManager;
import hise.hznu.istudy.app.AppConstant;
import hise.hznu.istudy.base.BaseActivity;
import hise.hznu.istudy.model.course.CourseDatumEntity;

public class StudyDatumActivity extends BaseActivity {

    @BindView(R.id.tv_back)
    TextView tvBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.lv_datum)
    ListView lvDatum;

    private String courseId;
    private List<CourseDatumEntity> _dataList = new ArrayList<CourseDatumEntity>();
    private CourseDatumAdapter adapter;
    @Override
    protected void initData() {
        super.initData();
        adapter = new CourseDatumAdapter(this);
        lvDatum.setAdapter(adapter);
        courseId = getIntent().getExtras().getString("courseId");
        JSONObject params = new JSONObject();
        params.put("courseid",courseId);
        RequestManager.getmInstance().apiPostData(AppConstant.GET_STUDY_DATUM_ACTION,params,this,AppConstant.POST_GET_STUDY_DATUM);
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_study_datum;
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

//    @Override
//    public void onSuccess(JSONObject response, int actionId) {
//        super.onSuccess(response, actionId);
//        Log.e("datumResponse",""+response);
//    }

    @Override
    public void onApiresponseSuccess(ApiResponse response, int actionId) {
        super.onApiresponseSuccess(response, actionId);
        _dataList = response.getListData(CourseDatumEntity.class);
        adapter.UpdateView(_dataList);
        tvTitle.setText("学习资料（共"+_dataList.size()+"个）");
    }

}
