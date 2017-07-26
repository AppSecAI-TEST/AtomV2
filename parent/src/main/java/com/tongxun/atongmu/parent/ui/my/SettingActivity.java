package com.tongxun.atongmu.parent.ui.my;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.tongxun.atongmu.parent.BaseActivity;
import com.tongxun.atongmu.parent.R;
import com.tongxun.atongmu.parent.dialog.CommonDialog;
import com.tongxun.atongmu.parent.util.DataCleanManager;
import com.zxy.tiny.Tiny;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.jpush.android.api.JPushInterface;
import es.dmoral.toasty.Toasty;

import static com.tongxun.atongmu.parent.R.id.setting_cache_size;

public class SettingActivity extends BaseActivity implements CompoundButton.OnCheckedChangeListener {

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
    @BindView(setting_cache_size)
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
    @BindView(R.id.me_setting_login_out)
    LinearLayout meSettingLoginOut;

    private CommonDialog commonDialog;

    private PackageManager pm;
    private PackageInfo pi;

    private String cache;//缓存

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        setStatusColor(R.color.colorWhite);
        ButterKnife.bind(this);
        tvTitleName.setText(getString(R.string.setting));
        meSettingNewsToggle.setOnCheckedChangeListener(this);

        pm = getPackageManager();
        pi = null;
        try {
            pi = pm.getPackageInfo(getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        settingVersionname.setText(pi.versionName);

        //计算缓存
        try {
            cache = DataCleanManager.getTotalCacheSize(this);
        } catch (Exception e) {
            e.printStackTrace();
        }

        settingCacheSize.setText(cache);
    }

    @OnClick({R.id.iv_title_back, R.id.me_setting_change_login, R.id.me_setting_clean_chat, R.id.me_setting_qinglihuancun, R.id.me_setting_dangqianbanben, R.id.me_setting_guanyuwomen,R.id.me_setting_login_out})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_title_back:
                finish();
                break;
            case R.id.me_setting_change_login:
                changePwd();
                break;
            case R.id.me_setting_clean_chat:
                cleanIM();
                break;
            case R.id.me_setting_qinglihuancun:
                cleanCache();
                break;
            case R.id.me_setting_dangqianbanben:

                break;
            case R.id.me_setting_guanyuwomen:
                //// TODO: 2017/7/26
                break;
            case R.id.me_setting_login_out:
                loginOut();
                break;
        }
    }

    /**
     * 登出
     */
    private void loginOut() {

    }

    /**
     * 修改登录密码
     */
    private void changePwd() {

    }

    /**
     * 清空聊天记录
     */
    private void cleanIM() {

    }

    /**
     * 清理缓存
     * 清理上传图片缓存
     */
    private void cleanCache() {
        String msg = getString(R.string.confirm_clean_cache);
        commonDialog = new CommonDialog(this, msg, getString(R.string.confirm), getString(R.string.cancel), new CommonDialog.GoCommonDialog() {
            @Override
            public void go() {
                Tiny.getInstance().clearCompressDirectory();
                // 清除缓存
                try {
                    DataCleanManager.clearAllCache(SettingActivity.this);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                commonDialog.dismiss();
                Toasty.success(SettingActivity.this, getString(R.string.clean_cache_finish), Toast.LENGTH_SHORT).show();
                //计算缓存
                try {
                    cache = DataCleanManager.getTotalCacheSize(SettingActivity.this);
                    settingCacheSize.setText(cache);//缓存大小
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void cancel() {
                commonDialog.dismiss();
            }
        });
        commonDialog.show();
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            JPushInterface.resumePush(this);
        } else {
            JPushInterface.stopPush(this);
        }
    }
}
