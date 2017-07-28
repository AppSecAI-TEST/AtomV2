package com.tongxun.atongmu.parent.ui.my;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.tongxun.atongmu.parent.BaseActivity;
import com.tongxun.atongmu.parent.Constants;
import com.tongxun.atongmu.parent.R;
import com.tongxun.atongmu.parent.dialog.CommonDialog;
import com.tongxun.atongmu.parent.ui.WebViewActivity;
import com.tongxun.atongmu.parent.util.SystemUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;
import kr.co.namee.permissiongen.PermissionFail;
import kr.co.namee.permissiongen.PermissionGen;
import kr.co.namee.permissiongen.PermissionSuccess;

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
        setStatusColor(R.color.colorWhite);
        ButterKnife.bind(this);

        tvTitleName.setText(getString(R.string.about_us));
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
                Intent intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                Uri content_url = Uri.parse(Constants.WEBSITE);
                intent.setData(content_url);
                startActivity(intent);
                break;
            case R.id.about_provision:
                WebViewActivity.startWebViewActivity(this,"","",Constants.DEFAULTICON,Constants.restShowProvision);
                break;
            case R.id.about_disclaimer:
                WebViewActivity.startWebViewActivity(this,"","",Constants.DEFAULTICON,Constants.restDutyShowContext);
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

    @PermissionSuccess(requestCode = 104)
    public void doPhone() {
        SystemUtil.openSystemPhone(AboutActivity.this, mPhone);
    }

    @PermissionFail(requestCode = 104)
    public void doSomeError() {
        Toasty.error(this, getResources().getString(R.string.get_phone_permission_error), Toast.LENGTH_LONG).show();
    }


}
