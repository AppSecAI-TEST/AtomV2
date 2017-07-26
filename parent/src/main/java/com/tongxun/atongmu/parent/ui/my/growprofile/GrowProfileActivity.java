package com.tongxun.atongmu.parent.ui.my.growprofile;

import android.os.Bundle;

import com.tongxun.atongmu.parent.Base2Activity;
import com.tongxun.atongmu.parent.R;

public class GrowProfileActivity extends Base2Activity<IGrowProfileContract.View,GrowProfilePresenter> implements IGrowProfileContract.View {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grow_profile);
        setStatusColor(R.color.colorGrowProfileBg);
    }

    @Override
    protected GrowProfilePresenter initPresenter() {
        return new GrowProfilePresenter();
    }
}
