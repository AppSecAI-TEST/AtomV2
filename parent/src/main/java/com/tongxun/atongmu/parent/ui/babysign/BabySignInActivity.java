package com.tongxun.atongmu.parent.ui.babysign;

import android.os.Bundle;

import com.tongxun.atongmu.parent.Base2Activity;
import com.tongxun.atongmu.parent.R;

public class BabySignInActivity extends Base2Activity<IBabySignInContract.View,BabySignPresenter> implements IBabySignInContract.View {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baby_sign_in);

    }

    @Override
    protected BabySignPresenter initPresenter() {
        return new BabySignPresenter();
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }
}
