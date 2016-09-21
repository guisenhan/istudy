package hise.hznu.istudy.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

import hise.hznu.istudy.*;
import hise.hznu.istudy.app.AppConstant;
import hise.hznu.istudy.base.BaseActivity;
import hise.hznu.istudy.util.MiscuUtil;
import hise.hznu.istudy.util.SharePreUtil;

public class StartActivity extends BaseActivity {

    private Timer timer ;
    private TimerTask timerTask;
    @Override
    protected void initData() {
        super.initData();
        timer= new Timer(true);

        timerTask = new TimerTask() {
            @Override
            public void run() {
                if(!MiscuUtil.isEmpty(SharePreUtil.getAuthorToken(StartActivity.this, SharePreUtil.SP_NAME.AUTHOR_TOKEN))){
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