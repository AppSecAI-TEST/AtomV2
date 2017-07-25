package com.tongxun.atongmu.parent.ui.my;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.tongxun.atongmu.parent.BaseActivity;
import com.tongxun.atongmu.parent.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SettingActivity extends BaseActivity {

    @BindView(R.id.iv_title_back)
    ImageView ivTitleBack;
    @BindView(R.id.tv_title_name)
    TextView tvTitleName;
    @BindView(R.id.me_setting_news_toggle)
    ToggleButton meSettingNewsToggle;
    @BindView(R.id.me_setting_change_login)
    LinearLayout meSettingChangeLogin;
    @BindView(R.id.me_setting_clean_chat)
    LinearLayout meSettingCleanChat;
    @BindView(R.id.setting_cache_size)
    TextView settingCacheSize;
    @BindView(R.id.me_setting_qinglihuancun)
    LinearLayout meSettingQinglihuancun;
    @BindView(R.id.setting_isnew)
    TextView settingIsnew;
    @BindView(R.id.setting_versionname)
    TextView settingVersionname;
    @BindView(R.id.me_setting_dangqianbanben)
    LinearLayout meSettingDangqianbanben;
    @BindView(R.id.me_setting_guanyuwomen)
    LinearLayout meSettingGuanyuwomen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);
        tvTitleName.setText(getString(R.string.setting));
    }

    @OnClick({R.id.iv_title_back, R.id.me_setting_change_login, R.id.me_setting_clean_chat, R.id.me_setting_qinglihuancun, R.id.me_setting_dangqianbanben, R.id.me_setting_guanyuwomen})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_title_back:
                break;
            case R.id.me_setting_change_login:
                break;
            case R.id.me_setting_clean_chat:
                break;
            case R.id.me_setting_qinglihuancun:
                break;
            case R.id.me_setting_dangqianbanben:
                break;
            case R.id.me_setting_guanyuwomen:
                break;
        }
    }
}
