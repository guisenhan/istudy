package hise.hznu.istudy.activity.fragment.main;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.prefs.AbstractPreferences;

import butterknife.BindView;
import butterknife.ButterKnife;
import hise.hznu.istudy.R;
import hise.hznu.istudy.activity.adapter.EmailAdapter;
import hise.hznu.istudy.activity.adapter.ExamAdapter;
import hise.hznu.istudy.api.ApiResponse;
import hise.hznu.istudy.api.RequestManager;
import hise.hznu.istudy.app.AppConstant;
import hise.hznu.istudy.base.BaseFragment;
import hise.hznu.istudy.model.email.EmailEntity;

/**
 * Created by GuisenHan on 2016/7/25.
 */
public class EmailFragment extends BaseFragment {
    @BindView(R.id.icon_edit)
    ImageView iconEdit;
    @BindView(R.id.lv_email)
    ListView lvEmail;

    private List<EmailEntity> _datalist = new ArrayList<EmailEntity>();
    private EmailAdapter adapter;

    @Override
    protected void initData() {
        super.initData();
        adapter = new EmailAdapter(getActivity());
        lvEmail.setAdapter(adapter);
        JSONObject params = new JSONObject();
        params.put("count","20");
        params.put("page","0");
        params.put("unreadonly","2");
        RequestManager.getmInstance().apiPostData(AppConstant.GET_EMAIL_ACTION,params,this,AppConstant.POST_GET_EMAIL_ACTION);
    }

    @Override
    protected int initLayout() {
        return R.layout.fragment_indoor_email;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);

    }

    @Override
    protected void onApiResponseSuccess(ApiResponse apiResponse, int actionId) {
        super.onApiResponseSuccess(apiResponse, actionId);
        _datalist = apiResponse.getListData(EmailEntity.class);
        adapter.UpdateView(_datalist);
    }


}
