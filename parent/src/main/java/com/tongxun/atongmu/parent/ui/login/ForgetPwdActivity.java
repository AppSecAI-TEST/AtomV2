package com.tongxun.atongmu.parent.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.tongxun.atongmu.parent.Base2Activity;
import com.tongxun.atongmu.parent.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;

public class ForgetPwdActivity extends Base2Activity<IForgetPwdContract.view, ForgetPwdPresenter> implements IForgetPwdContract.view, View.OnClickListener {

    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.tv_verification_time)
    TextView tvVerificationTime;
    @BindView(R.id.tv_no_get_code)
    TextView tvNoGetCode;
    @BindView(R.id.btn_forget_next)
    Button btnForgetNext;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.et_register_phone)
    EditText etRegisterPhone;
    @BindView(R.id.et_forget_code)
    EditText etForgetCode;

    private TimeCount time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_pwd);
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

        toolbarTitle.setText(getResources().getString(R.string.forget_pwd));

        tvVerificationTime.setOnClickListener(this);
        tvNoGetCode.setOnClickListener(this);
        btnForgetNext.setOnClickListener(this);
        time = new TimeCount(60000, 1000);
    }

    @Override
    protected ForgetPwdPresenter initPresenter() {
        return new ForgetPwdPresenter();
    }

    @Override
    public String getRegisterPhone() {
        return etRegisterPhone.getText().toString();
    }

    @Override
    public void LoginSuccess() {
        Intent intent = new Intent(ForgetPwdActivity.this, NewPwdActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void LoginError(String message) {
        Toasty.error(this, message, Toast.LENGTH_SHORT, true).show();
    }

    @Override
    public void sendSuccess() {
        Toasty.success(this, getResources().getString(R.string.code_send_success), Toast.LENGTH_SHORT, true).show();
    }

    @Override
    public void sendError(String message) {
        Toasty.error(this, message, Toast.LENGTH_SHORT, true).show();
        if (time != null) {
            time.cancel();
        }
        time.onFinish();
    }

    class TimeCount extends CountDownTimer {
        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            tvVerificationTime.setClickable(false);//防止重复点击
            tvVerificationTime.setText(millisUntilFinished / 1000 + "″");
        }

        @Override
        public void onFinish() {
            tvVerificationTime.setText(getResources().getString(R.string.verification_time));
            tvVerificationTime.setClickable(true);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_verification_time:
                if (TextUtils.isEmpty(getRegisterPhone())) {
                    Toasty.error(ForgetPwdActivity.this, getResources().getString(R.string.empty_phone), Toast.LENGTH_SHORT).show();
                    return;
                }
                if (getRegisterPhone().length() != 11) {
                    Toasty.error(ForgetPwdActivity.this, getResources().getString(R.string.error_phone), Toast.LENGTH_SHORT).show();
                    return;
                }
                mPresenter.getWebVer(getRegisterPhone());
                time.start();
                break;
            case R.id.tv_no_get_code:

                break;
            case R.id.btn_forget_next:
                mPresenter.checkVerCode(etForgetCode.getText().toString());
                break;
        }
    }
}
