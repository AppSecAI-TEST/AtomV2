package com.tongxun.atongmu.parent.ui.my;

import android.Manifest;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.tongxun.atongmu.parent.BaseActivity;
import com.tongxun.atongmu.parent.Constants;
import com.tongxun.atongmu.parent.R;
import com.tongxun.atongmu.parent.dialog.CommonDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import kr.co.namee.permissiongen.PermissionGen;

public class AboutActivity extends BaseActivity {

    @BindView(R.id.iv_title_back)
    ImageView ivTitleBack;
    @BindView(R.id.tv_title_name)
    TextView tvTitleName;
    @BindView(R.id.me_setting_about_tel2)
    TextView meSettingAboutTel2;
    @BindView(R.id.about_website)
    TextView aboutWebsite;
    @BindView(R.id.about_provision)
    TextView aboutProvision;
    @BindView(R.id.about_disclaimer)
    TextView aboutDisclaimer;

    private CommonDialog commonDialog;
    private String mPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.iv_title_back, R.id.me_setting_about_tel2, R.id.about_website, R.id.about_provision, R.id.about_disclaimer})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_title_back:
                finish();
                break;
            case R.id.me_setting_about_tel2:
                callPhone();
                break;
            case R.id.about_website:

                break;
            case R.id.about_provision:
                break;
            case R.id.about_disclaimer:
                break;
        }
    }

    private void callPhone() {
        mPhone=getString(R.string.atom_tel);
        commonDialog = new CommonDialog(AboutActivity.this, getResources().getString(R.string.is_call_phone) + mPhone+ "?", getResources().getString(R.string.confirm), getResources().getString(R.string.cancel), new CommonDialog.GoCommonDialog() {
            @Override
            public void go() {
                PermissionGen.with(AboutActivity.this)
                        .addRequestCode(Constants.PERMISSION_PHONE_CODE)
                        .permissions(
                                Manifest.permission.CALL_PHONE
                        )
                        .request();
                commonDialog.dismiss();
            }

            @Override
            public void cancel() {
                commonDialog.dismiss();
            }
        });
        commonDialog.show();
    }
}
