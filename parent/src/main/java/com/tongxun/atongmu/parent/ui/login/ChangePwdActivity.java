package com.tongxun.atongmu.parent.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.kaopiz.kprogresshud.KProgressHUD;
import com.tongxun.atongmu.parent.Base2Activity;
import com.tongxun.atongmu.parent.R;
import com.tongxun.atongmu.parent.model.BabyInfoModel;
import com.tongxun.atongmu.parent.model.TokenIdModel;
import com.tongxun.atongmu.parent.util.ActivityControl;
import com.tongxun.atongmu.parent.util.DemoHelper;
import com.tongxun.atongmu.parent.util.SharePreferenceUtil;
import com.zxy.tiny.Tiny;

import org.litepal.crud.DataSupport;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.jpush.android.api.JPushInterface;
import es.dmoral.toasty.Toasty;

public class ChangePwdActivity extends Base2Activity<IChangePwdContract.View, ChangePwdPresenter> implements IChangePwdContract.View {

    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.et_old_pwd)
    EditText etOldPwd;
    @BindView(R.id.et_new_pwd)
    EditText etNewPwd;
    @BindView(R.id.et_new_pwd_confirm)
    EditText etNewPwdConfirm;
    @BindView(R.id.btn_new_pwd)
    Button btnNewPwd;

    private KProgressHUD hud;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pwd);
        setStatusColor(R.color.colorMainYellow);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        hud = KProgressHUD.create(this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel(getResources().getString(R.string.loading))
                .setCancellable(true)
                .setAnimationSpeed(1)
                .setDimAmount(0.5f);
    }

    @Override
    protected ChangePwdPresenter initPresenter() {
        return new ChangePwdPresenter();
    }

    @OnClick(R.id.btn_new_pwd)
    public void onViewClicked() {
        if(TextUtils.isEmpty(etOldPwd.getText().toString()) || TextUtils.isEmpty(etNewPwd.getText().toString()) ){
            Toasty.info(this, getString(R.string.empty_pwd), Toast.LENGTH_SHORT).show();
            return;
        }
        if(etOldPwd.getText().toString().equals(SharePreferenceUtil.getPreferences().getString(SharePreferenceUtil.PASSWORD,""))){
            if(etNewPwd.getText().toString().equals(etNewPwdConfirm.getText().toString())){
                mPresenter.setNewPwd(etNewPwd.getText().toString());
            }else {
                Toasty.error(this, getString(R.string.new_pwd_error), Toast.LENGTH_SHORT).show();
            }
        }else {
            Toasty.error(this, getString(R.string.old_pwd_error), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onError(String message) {
        Toasty.error(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccess() {
        Toasty.error(this, getString(R.string.change_pwd_success), Toast.LENGTH_SHORT).show();
        Tiny.getInstance().clearCompressDirectory();
        JPushInterface.stopPush(ChangePwdActivity.this);
        DemoHelper.getInstance().logout(true,null);
        DataSupport.deleteAll(TokenIdModel.class);
        DataSupport.deleteAll(BabyInfoModel.class);
        Intent intent=new Intent(ChangePwdActivity.this,LoginActivity.class);
        startActivity(intent);
        ActivityControl.finishAll();
    }
}
