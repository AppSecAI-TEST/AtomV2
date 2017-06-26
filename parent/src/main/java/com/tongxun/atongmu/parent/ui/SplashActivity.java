package com.tongxun.atongmu.parent.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;

import com.github.chrisbanes.photoview.PhotoView;
import com.tongxun.atongmu.parent.R;
import com.tongxun.atongmu.parent.ui.login.LoginActivity;
import com.tongxun.atongmu.parent.util.SharePreferenceUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashActivity extends AppCompatActivity {

    @BindView(R.id.photo_splash)
    PhotoView photoSplash;

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
        ButterKnife.bind(this);
        isFirstIn=SharePreferenceUtil.getPreferences().getBoolean(SharePreferenceUtil.isFirstIn,false);
        isRemember=SharePreferenceUtil.getPreferences().getBoolean(SharePreferenceUtil.isRemember,false);
        if(isFirstIn){
            handler.sendEmptyMessageDelayed(GO_GUIDE,DELAY_MILLISECOND);
        }else {
            if(isRemember){
                getWebBabyInfoList();
               // handler.sendEmptyMessageDelayed(GO_MAIN,DELAY_MILLISECOND);
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
