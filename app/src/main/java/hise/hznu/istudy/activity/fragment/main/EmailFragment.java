package hise.hznu.istudy.activity.fragment.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hise.hznu.istudy.R;
import hise.hznu.istudy.activity.adapter.EmailAdapter;
import hise.hznu.istudy.activity.email.EmailActivity;
import hise.hznu.istudy.api.ApiResponse;
import hise.hznu.istudy.api.RequestManager;
import hise.hznu.istudy.app.AppConstant;
import hise.hznu.istudy.base.BaseFragment;
import hise.hznu.istudy.model.email.EmailEntity;

/**
 * Created by GuisenHan on 2016/7/25.
 */
public class EmailFragment extends BaseFragment {
    @BindView(R.id.lv_email)
    ListView lvEmail;
    @BindView(R.id.iv_choose)
    ImageView ivChoose;
    @BindView(R.id.tv_get_email)
    TextView tvGetEmail;
    @BindView(R.id.tv_send_email)
    TextView tvSendEmail;
    @BindView(R.id.ll_email_type)
    LinearLayout llEmailType;
    @BindView(R.id.icon_add)
    ImageView iconAdd;
    @BindView(R.id.iv_search)
    ImageView ivSearch;

    private List<EmailEntity> _datalist = new ArrayList<EmailEntity>();
    private EmailAdapter adapter;

    @Override
    protected void initData() {
        super.initData();
        adapter = new EmailAdapter(getActivity());
        lvEmail.setAdapter(adapter);
        JSONObject params = new JSONObject();
        params.put("count", "30");
        params.put("page", "0");
        params.put("unreadonly", "2");
        RequestManager.getmInstance()
                .apiPostData(AppConstant.GET_EMAIL_ACTION, params, this, AppConstant.POST_GET_EMAIL_ACTION);
    }

    @Override
    protected int initLayout() {
        return R.layout.fragment_indoor_email;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        lvEmail.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), EmailActivity.class);
                intent.putExtra("email", _datalist.get(i));
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onApiResponseSuccess(ApiResponse apiResponse, int actionId) {
        super.onApiResponseSuccess(apiResponse, actionId);
        if (llEmailType.getVisibility() == View.VISIBLE) {
            llEmailType.setVisibility(View.GONE);
        }
        _datalist.clear();
        _datalist = apiResponse.getListData(EmailEntity.class);
        adapter.UpdateView(_datalist);
    }


    @OnClick({R.id.iv_choose, R.id.icon_add, R.id.tv_get_email, R.id.tv_send_email})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_choose:
                if (llEmailType.getVisibility() == View.VISIBLE) {
                    llEmailType.setVisibility(View.GONE);
                } else {
                    llEmailType.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.icon_add:
                break;
            case R.id.tv_get_email:
                JSONObject params = new JSONObject();
                params.put("count", "30");
                params.put("page", "0");
                params.put("unreadonly", "2");
                RequestManager.getmInstance()
                        .apiPostData(AppConstant.GET_EMAIL_ACTION, params, this, AppConstant.POST_GET_EMAIL_ACTION);
                break;
            case R.id.tv_send_email:
                JSONObject jsonObject = new JSONObject();
                /**
                 * authtoken		：		登录证书
                 count			:		每页记录数量
                 page
                 */
                jsonObject.put("count", "30");
                jsonObject.put("page", "0");
                RequestManager.getmInstance()
                        .apiPostData(AppConstant.QUERY_EMAIL_SEND, jsonObject, this, AppConstant.POST_QUERY_EMAIL_SEND);
                break;
        }
    }
}
