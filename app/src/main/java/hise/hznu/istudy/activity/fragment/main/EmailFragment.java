package hise.hznu.istudy.activity.fragment.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
    @BindView(R.id.iv_choose)
    ImageView ivChoose;
    @BindView(R.id.tv_get_email)
    TextView tvGetEmail;
    @BindView(R.id.tv_send_email)
    TextView tvSendEmail;
    @BindView(R.id.ll_email_type)
    LinearLayout llEmailType;

    private List<EmailEntity> _datalist = new ArrayList<EmailEntity>();
    private EmailAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        ButterKnife.bind(this, super.onCreateView(inflater, container, savedInstanceState));
        return super.onCreateView(inflater, container, savedInstanceState);

    }

    @Override
    protected void initData() {
        super.initData();
        adapter = new EmailAdapter(getActivity());
        lvEmail.setAdapter(adapter);
        JSONObject params = new JSONObject();
        params.put("count", "20");
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
    }

    @Override
    protected void onApiResponseSuccess(ApiResponse apiResponse, int actionId) {
        super.onApiResponseSuccess(apiResponse, actionId);
        _datalist = apiResponse.getListData(EmailEntity.class);
        adapter.UpdateView(_datalist);
    }



    @OnClick({R.id.iv_choose, R.id.icon_edit, R.id.tv_get_email, R.id.tv_send_email})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_choose:
                if(llEmailType.getVisibility() ==View.VISIBLE){
                    llEmailType.setVisibility(View.GONE);
                }else{
                    llEmailType.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.icon_edit:
                break;
            case R.id.tv_get_email:
                break;
            case R.id.tv_send_email:
                break;
        }
    }
}
