package com.tongxun.atongmu.parent.ui.my;

import android.os.Bundle;

import com.tongxun.atongmu.parent.Base2Activity;
import com.tongxun.atongmu.parent.R;

public class UseHelpActivity extends Base2Activity<IUserHelpContract.View,UseHelpPresenter> implements IUserHelpContract.View {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_use_help);
    }

    @Override
    protected UseHelpPresenter initPresenter() {
        return new UseHelpPresenter();
    }
}
