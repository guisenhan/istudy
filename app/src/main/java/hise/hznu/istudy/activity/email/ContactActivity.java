package hise.hznu.istudy.activity.email;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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
import hise.hznu.istudy.activity.adapter.ContactorAdapter;
import hise.hznu.istudy.api.ApiResponse;
import hise.hznu.istudy.api.RequestManager;
import hise.hznu.istudy.app.AppConstant;
import hise.hznu.istudy.base.BaseActivity;
import hise.hznu.istudy.model.email.ContacterEntity;

public class ContactActivity extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_sure)
    TextView tvSure;
    @BindView(R.id.lv_contact)
    ListView lvContact;

    private List<ContacterEntity> _dataList;
    private ContactorAdapter adapter;
    @Override
    protected void initData() {
        super.initData();
        JSONObject params = new JSONObject();
        RequestManager.getmInstance()
                .apiPostData(AppConstant.GET_CONTACTOR, params, this, AppConstant.POST_GET_CONTACTOR);
    }

    @Override
    protected void initExtras(Bundle extras) {
        super.initExtras(extras);
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_contact;

    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        _dataList = new ArrayList<ContacterEntity>();
        adapter = new ContactorAdapter(this);
        lvContact.setAdapter(adapter);
    }

    @Override
    public void onApiresponseSuccess(ApiResponse response, int actionId) {
        super.onApiresponseSuccess(response, actionId);
        _dataList = response.getListData(ContacterEntity.class);
        adapter.UpdateView(_dataList);
    }


    @OnClick({R.id.iv_back, R.id.tv_sure})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_sure:
                Intent intent = new Intent();
                intent.putExtra("chooseId",list2Str(adapter.getChooseId()));
                intent.putExtra("names",list2Str(adapter.getNames()));
                setResult(100,intent);
                finish();
                break;
        }
    }
    private String list2Str(List<String> list){
        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i < list.size(); i++){
            sb.append(list.get(i));
            if(i != list.size()-1){
               sb.append(",");
            }
        }
        return sb.toString();
    }
}
