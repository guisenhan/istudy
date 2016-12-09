package hise.hznu.istudy.activity.course;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hise.hznu.istudy.R;
import hise.hznu.istudy.activity.adapter.TalkZoneAdapter;
import hise.hznu.istudy.api.ApiResponse;
import hise.hznu.istudy.api.RequestManager;
import hise.hznu.istudy.app.AppConstant;
import hise.hznu.istudy.base.BaseActivity;
import hise.hznu.istudy.model.course.CommentCardEntity;
import hise.hznu.istudy.model.course.CourseEntity;
import hise.hznu.istudy.widget.LoadView;

public class CommentAreaActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_class_name)
    TextView tvClassName;
    @BindView(R.id.tv_comment_name)
    TextView tvCommentName;
    @BindView(R.id.tv_comment_number)
    TextView tvCommentNumber;
    @BindView(R.id.lv_comment)
    ListView lvComment;
    @BindView(R.id.iv_refresh)
    ImageView ivRefresh;
    @BindView(R.id.iv_post_card)
    ImageView ivPostCard;
    @BindView(R.id.load_view)
    LoadView loadView;
    private List<CommentCardEntity> _dataList = new ArrayList<CommentCardEntity>();
    private TalkZoneAdapter adapter;
    private CourseEntity course;
   private JSONObject params = new JSONObject();
    @Override
    protected void initExtras(Bundle extras) {
        super.initExtras(extras);
        course = (CourseEntity) extras.get("course");
    }

    @Override
    protected void initData() {
        super.initData();
        /**
         * authtoken		：		登录证书
         count			:		每页记录数量
         page			:		第几页
         projectid		:	   课程id
         mode			：		1=轻量的（无内容返回），2=完整的
         */
        params.put("projectid", course.getId());
        params.put("count", "50");
        params.put("page", "1");
        params.put("mode", "1");
        RequestManager.getmInstance().apiPostData(AppConstant.GET_FORUM, params, this, AppConstant.POST_GET_FORUM);
        loadView.showLoading();
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_comment_area;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        adapter = new TalkZoneAdapter(this);
        lvComment.setAdapter(adapter);
        tvClassName.setText(course.getPictit());
        tvClassName.setBackgroundColor(
                Color.argb(255, course.getPicbg().get(0), course.getPicbg().get(1), course.getPicbg().get(2)));
        tvCommentName.setText(course.getTitle());
        lvComment.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //在这里
                Intent intent = new Intent(CommentAreaActivity.this, PostActivity.class);
                intent.putExtra("comment", _dataList.get(i));
                intent.putExtra("courseId",course.getId());
                startActivity(intent);
            }
        });
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    public void onApiresponseSuccess(ApiResponse response, int actionId) {
        super.onApiresponseSuccess(response, actionId);
        _dataList.clear();
        loadView.clearLoad();

        _dataList = response.getListData(CommentCardEntity.class);
        if(_dataList.size() ==0){
            loadView.showNoData();
            loadView.setNoDataText("暂无帖子，我这些都是什么同学啊，连帖子都不发！");
        }else{
            adapter.UpdateView(_dataList);
            tvCommentNumber.setText("总共有" + _dataList.size() + "条");
        }
    }



    @OnClick({R.id.iv_refresh, R.id.iv_post_card})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_refresh:
                RequestManager.getmInstance().apiPostData(AppConstant.GET_FORUM, params, this, AppConstant.POST_GET_FORUM);
                break;
            case R.id.iv_post_card:
                Intent intent = new Intent(this,EditPostActivity.class);
                intent.putExtra("courseId",course.getId());
                startActivity(intent);
                break;
        }
    }
}
