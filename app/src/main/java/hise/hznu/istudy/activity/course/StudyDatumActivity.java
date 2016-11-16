package hise.hznu.istudy.activity.course;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.FileAsyncHttpResponseHandler;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cz.msebera.android.httpclient.Header;
import hise.hznu.istudy.R;
import hise.hznu.istudy.activity.adapter.CourseDatumAdapter;
import hise.hznu.istudy.api.ApiResponse;
import hise.hznu.istudy.api.AsyHttpClient;
import hise.hznu.istudy.api.RequestManager;
import hise.hznu.istudy.app.AppConfig;
import hise.hznu.istudy.app.AppConstant;
import hise.hznu.istudy.base.BaseActivity;
import hise.hznu.istudy.model.course.CourseDatumEntity;
import hise.hznu.istudy.util.AppUtils;

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
    AsyncHttpClient http = new AsyncHttpClient();
    File file1;
    @Override
    protected void initData() {
        super.initData();
        adapter = new CourseDatumAdapter(this);
        lvDatum.setAdapter(adapter);
        courseId = getIntent().getExtras().getString("courseId");
        JSONObject params = new JSONObject();
        Log.e("courseId",""+courseId);
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
        lvDatum.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view,final int i, long l) {
                 file1 = new File(AppConstant.FILE_STORED + _dataList.get(i).getFilename());
                if(file1.exists()){
                    return;
                }
                http.get(StudyDatumActivity.this, _dataList.get(i).getUrl(), new FileAsyncHttpResponseHandler
                        (StudyDatumActivity.this) {
                    @Override
                    public void onFailure(int statusCode, Header[] headers, Throwable throwable, File file) {

                    }

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, File file) {
                        Log.e("filePath",file.getAbsolutePath());
                        try {
                            FileInputStream  in = new FileInputStream(file);
                            FileOutputStream out = new FileOutputStream(file1);
                            int length = in.available();
                            byte[] by = new byte[length];
                            in.read(by);
                            out.write(by);
                        }catch (Exception  e){

                        }
                        AppUtils.openFile(file1);
                    }
                });
            }
        });

    }

    @Override
    public void onApiresponseSuccess(ApiResponse response, int actionId) {
        super.onApiresponseSuccess(response, actionId);
        Log.e("response",""+JSONObject.toJSONString(response));
        _dataList = response.getListData(CourseDatumEntity.class);
        adapter.UpdateView(_dataList);
        tvTitle.setText("学习资料（共"+_dataList.size()+"个）");
    }



}
