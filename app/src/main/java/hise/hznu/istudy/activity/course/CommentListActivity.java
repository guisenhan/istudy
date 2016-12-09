package hise.hznu.istudy.activity.course;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
import hise.hznu.istudy.activity.adapter.CommentListAdapter;
import hise.hznu.istudy.api.ApiResponse;
import hise.hznu.istudy.api.RequestManager;
import hise.hznu.istudy.app.AppConstant;
import hise.hznu.istudy.base.BaseActivity;
import hise.hznu.istudy.model.course.CommetListEntity;
import hise.hznu.istudy.widget.LoadView;

public class CommentListActivity extends BaseActivity {

    @BindView(R.id.tv_back)
    TextView tvBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.lv_comment_list)
    ListView lvCommentList;
    @BindView(R.id.load_view)
    LoadView loadView;
    private String hupingId;
    private List<CommetListEntity> _dataList;
    private CommentListAdapter adapter;
    @Override
    protected void initData() {

        super.initData();
        adapter = new CommentListAdapter(this);
        lvCommentList.setAdapter(adapter);
        JSONObject params = new JSONObject();

        params.put("testid",hupingId);
        RequestManager.getmInstance().apiPostData(AppConstant.QUERY_COMMENT_LIST,params,this,AppConstant
                .POST_QUERY_COMMENT_LIST);
        loadView.showLoading();
        _dataList = new ArrayList<CommetListEntity>();
    }

    @Override
    protected void initExtras(Bundle extras) {
        super.initExtras(extras);
        hupingId = extras.getString("hupingId");
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_comment_list;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        lvCommentList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(CommentListActivity.this,CommentActivity.class);
                intent.putExtra("usertestId",_dataList.get(i).getUsertestid());
                startActivity(intent);
            }
        });
    }

    @Override
    public void onApiresponseSuccess(ApiResponse response, int actionId) {
        super.onApiresponseSuccess(response, actionId);
        loadView.clearLoad();
        _dataList = response.getListData(CommetListEntity.class);
        if(_dataList.size() == 0){
            loadView.showNoData();
            loadView.setNoDataText("暂无互评任务！找几个朋友撸一把吧！哈哈哈哈....");
        }else{
            adapter.UpdateView(_dataList);
        }
    }

    @OnClick(R.id.tv_back)
    public void onClick() {
        finish();
    }
}
