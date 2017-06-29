package com.tongxun.atongmu.parent.ui.login;

import android.os.Bundle;

import com.tongxun.atongmu.parent.Base2Activity;
import com.tongxun.atongmu.parent.R;

public class RegisterActivity extends Base2Activity<IRegisterContract.View,RegisterPresenter> implements IRegisterContract.View {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    @Override
    protected RegisterPresenter initPresenter() {
        return new RegisterPresenter();
    }
}
