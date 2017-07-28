package com.tongxun.atongmu.parent.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;

import com.tongxun.atongmu.parent.BaseActivity;
import com.tongxun.atongmu.parent.R;
import com.tongxun.atongmu.parent.adapter.ViewPagerAdapter;
import com.tongxun.atongmu.parent.ui.login.LoginActivity;
import com.tongxun.atongmu.parent.util.DepthPageTransformer;
import com.tongxun.atongmu.parent.util.SharePreferenceUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GuideActivity extends BaseActivity implements ViewPagerAdapter.onGuideListener {

    @BindView(R.id.guide_layout_vp)
    ViewPager guideLayoutVp;
    private ViewPagerAdapter vpAdapter;
    private List<View> views;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        ButterKnife.bind(this);

        LayoutInflater inflater=LayoutInflater.from(this);
        views=new ArrayList<View>();
        views.add(inflater.inflate(R.layout.guide_item_one,null));
        views.add(inflater.inflate(R.layout.guide_item_two,null));
        views.add(inflater.inflate(R.layout.guide_item_three,null));

        vpAdapter=new ViewPagerAdapter(views,this);
        guideLayoutVp.setAdapter(vpAdapter);
        guideLayoutVp.setPageTransformer(true, new DepthPageTransformer());
    }

    @Override
    protected void onDestroy() {
        views.clear();
        super.onDestroy();
    }

    @Override
    public void onLogin() {
        SharePreferenceUtil.getPreferences().edit().putBoolean(SharePreferenceUtil.isFirstIn,false).commit();
        Intent intent=new Intent(this,LoginActivity.class);
        startActivity(intent);
        finish();
    }


}
