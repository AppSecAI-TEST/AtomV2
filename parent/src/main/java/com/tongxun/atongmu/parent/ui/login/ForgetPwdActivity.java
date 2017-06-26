package com.tongxun.atongmu.parent.ui.login;

import android.os.Bundle;

import com.tongxun.atongmu.parent.BaseActivity;
import com.tongxun.atongmu.parent.R;

public class ForgetPwdActivity extends BaseActivity<IForgetPwdContract.view,ForgetPwdPresenter> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_pwd);
    }

    @Override
    protected ForgetPwdPresenter initPresenter() {
        return new ForgetPwdPresenter();
    }
}
