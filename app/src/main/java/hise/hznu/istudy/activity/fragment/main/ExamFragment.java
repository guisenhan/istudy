package hise.hznu.istudy.activity.fragment.main;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;

import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import hise.hznu.istudy.R;
import hise.hznu.istudy.activity.adapter.ExamAdapter;
import hise.hznu.istudy.activity.course.TestDetailActivity;
import hise.hznu.istudy.api.ApiResponse;
import hise.hznu.istudy.api.RequestManager;
import hise.hznu.istudy.app.AppConstant;
import hise.hznu.istudy.base.BaseFragment;
import hise.hznu.istudy.model.exam.ExamEntity;
import hise.hznu.istudy.util.AppUtils;
import hise.hznu.istudy.widget.LoadView;

/**
 * Created by GuisenHan on 2016/7/25.
 */
public class ExamFragment extends BaseFragment {
    @BindView(R.id.lv_exam)
    ListView lvExam;
    @BindView(R.id.load_view)
    LoadView loadView;
    private List<ExamEntity> _dataList = new ArrayList<ExamEntity>();
    private ExamAdapter adapter;
    @Override
    protected void initData() {
        super.initData();
        adapter = new ExamAdapter(getActivity());
        lvExam.setAdapter(adapter);
        JSONObject params = new JSONObject();
        RequestManager.getmInstance().apiPostData(AppConstant.GET_EXAM_LIST_ACTION,params,this,AppConstant.POST_EXAM_LIST_ACTION);
        loadView.showLoading();
    }

    @Override
    protected int initLayout() {
        return R.layout.fragment_exam;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onSuccess(JSONObject response, int actionId) {
        super.onSuccess(response, actionId);
    }

    @Override
    protected void onApiResponseSuccess(ApiResponse apiResponse, int actionId) {
        super.onApiResponseSuccess(apiResponse, actionId);
        loadView.clearLoad();
        _dataList = apiResponse.getListData(ExamEntity.class);
        if(_dataList.size()==0){
            loadView.showNoData();
            loadView.setNoDataText("暂无考试信息！");
        }else{
            adapter.UpdateView(_dataList);
        }

    }
}
