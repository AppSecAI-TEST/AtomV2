package com.tongxun.atongmu.parent.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.tongxun.atongmu.parent.BaseActivity;
import com.tongxun.atongmu.parent.R;
import com.tongxun.atongmu.parent.ui.home.MainActivity;
import com.tongxun.atongmu.parent.ui.login.LoginActivity;
import com.tongxun.atongmu.parent.util.SharePreferenceUtil;

/**
 * 1.启动页判断是否第一登录
 * 2.判断是否记住密码
 * 3.获取用户信息
 */
public class SplashActivity extends BaseActivity {

    private final long DELAY_MILLISECOND=3000;

    private final int GO_LOGIN=1000;
    private final int GO_GUIDE=1001;
    private final int GO_MAIN=1002;

    private boolean isFirstIn=false;
    private boolean isRemember=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        isFirstIn=SharePreferenceUtil.getPreferences().getBoolean(SharePreferenceUtil.isFirstIn,true);
        isRemember=SharePreferenceUtil.getPreferences().getBoolean(SharePreferenceUtil.isRemember,false);
        if(isFirstIn){
            handler.sendEmptyMessageDelayed(GO_GUIDE,DELAY_MILLISECOND);
        }else {
            if(isRemember){
                getWebBabyInfoList();
               handler.sendEmptyMessageDelayed(GO_MAIN,DELAY_MILLISECOND);
            }else {
                handler.sendEmptyMessageDelayed(GO_LOGIN,DELAY_MILLISECOND);
            }
        }

    }

    private void getWebBabyInfoList() {

    }

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case GO_LOGIN:
                    goLogin();
                    break;
                case GO_GUIDE:
                    goGuide();
                    break;
                case GO_MAIN:
                    goMain();
                    break;
            }
        }
    };

    private void goGuide() {
        Intent intent=new Intent(SplashActivity.this, GuideActivity.class);
        startActivity(intent);
        finish();
    }

    private void goMain() {
        Intent intent=new Intent(SplashActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void goLogin() {
        Intent intent=new Intent(SplashActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
