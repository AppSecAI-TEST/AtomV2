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

import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;

public class NewPwdActivity extends Base2Activity<INewPwdContract.View,NewPwdPresenter> implements View.OnClickListener,INewPwdContract.View {

    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.et_new_pwd)
    EditText etNewPwd;
    @BindView(R.id.et_new_pwd_confirm)
    EditText etNewPwdConfirm;
    @BindView(R.id.btn_new_pwd)
    Button btnNewPwd;

    KProgressHUD hud;

    private String phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_pwd);
        ButterKnife.bind(this);
        setStatusColor(R.color.colorMainYellow);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Intent intent=getIntent();
        if(intent!=null){
            phone=intent.getStringExtra("phone");
        }

        hud = KProgressHUD.create(this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel(getResources().getString(R.string.loading))
                .setCancellable(true)
                .setAnimationSpeed(1)
                .setDimAmount(0.5f);

        btnNewPwd.setOnClickListener(this);
    }

    @Override
    protected NewPwdPresenter initPresenter() {
        return new NewPwdPresenter();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_new_pwd:
                if (TextUtils.isEmpty(getNewPwd())){
                    Toasty.error(NewPwdActivity.this,getResources().getString(R.string.empty_pwd),Toast.LENGTH_SHORT).show();
                }else {
                    if(getNewPwd().equals(getConfirmPwd())){
                        mPresenter.setNewPwd("15850051246",getNewPwd());
                    }else {
                        Toasty.error(NewPwdActivity.this,getResources().getString(R.string.input_difference),Toast.LENGTH_SHORT).show();
                    }
                }

                break;
        }
    }

    @Override
    public String getNewPwd() {
        return etNewPwd.getText().toString();
    }

    @Override
    public String getConfirmPwd() {
        return etNewPwdConfirm.getText().toString();
    }

    @Override
    public void showProgress() {
        hud.show();
    }

    @Override
    public void hideProgress() {
        hud.dismiss();
    }

    @Override
    public void onResetPwdSuccess() {
        AccountFinishActivity.startAccountFinish(this,"change",phone);
        finish();
    }

    @Override
    public void onResetPwdError(String message) {
        Toasty.error(this,message, Toast.LENGTH_SHORT).show();
    }
}
