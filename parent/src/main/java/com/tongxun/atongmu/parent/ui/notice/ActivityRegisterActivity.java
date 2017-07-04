package com.tongxun.atongmu.parent.ui.notice;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.webkit.WebView;
import android.widget.TextView;

import com.tongxun.atongmu.parent.BaseActivity;
import com.tongxun.atongmu.parent.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 活动报名页面
 */
public class ActivityRegisterActivity extends BaseActivity {

    @BindView(R.id.toolbar_right_text)
    TextView toolbarRightText;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.wv_activity_info)
    WebView wvActivityInfo;

    private String statusId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        ButterKnife.bind(this);
        Intent intent=getIntent();

    }
}
