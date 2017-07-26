package com.tongxun.atongmu.parent.ui.my.growprofile;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.widget.LinearLayout;

import com.tongxun.atongmu.parent.Base2Activity;
import com.tongxun.atongmu.parent.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GrowProfileActivity extends Base2Activity<IGrowProfileContract.View, GrowProfilePresenter> implements IGrowProfileContract.View {

    @BindView(R.id.ll_no_data)
    LinearLayout llNoData;
    @BindView(R.id.vp_grow_profile)
    ViewPager vpGrowProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grow_profile);
        ButterKnife.bind(this);
        setStatusColor(R.color.colorGrowProfileBg);

        mPresenter.getGrowProfileList();
    }

    @Override
    protected GrowProfilePresenter initPresenter() {
        return new GrowProfilePresenter();
    }
}
