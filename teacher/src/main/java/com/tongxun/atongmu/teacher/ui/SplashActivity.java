package com.tongxun.atongmu.teacher.ui;

import android.content.Intent;
import android.os.Bundle;

import com.tongxun.atongmu.teacher.BaseActivity;
import com.tongxun.atongmu.teacher.R;
import com.tongxun.atongmu.teacher.ui.personsign.PersonSignActivity;
import com.tongxun.atongmu.teacher.util.SharePreferenceUtil;

public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        SharePreferenceUtil.getEditor().putString(SharePreferenceUtil.TOKENID,"cdd1da27-a37f-48a8-9f7c-bd87eba036ce").commit();
        Intent intent=new Intent(this, PersonSignActivity.class);
        startActivity(intent);
    }





}
