package hise.hznu.istudy.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;

import hise.hznu.istudy.*;
import hise.hznu.istudy.app.AppConfig;
import hise.hznu.istudy.base.BaseActivity;
import hise.hznu.istudy.util.MiscUtils;
import hise.hznu.istudy.util.SharePreUtil;

public class StartActivity extends BaseActivity {

    private Timer timer ;
    private TimerTask timerTask;
    @Override
    protected void initData() {
        super.initData();
        timer= new Timer(true);
        String authenToken = MiscUtils.getSharepreferenceValue("token","tokens","");
        timerTask = new TimerTask() {
            @Override
            public void run() {
                if(!MiscUtils.isEmpty(MiscUtils.getSharepreferenceValue("token","tokens",""))){
                    Intent intent = new Intent(StartActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }else{
                    Intent intent = new Intent(StartActivity.this,LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
                timer.cancel();
            }
        };
        timer.schedule(timerTask,2000);

    }

    @Override
    protected int initLayout() {
        return R.layout.activity_start;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
    }

}
