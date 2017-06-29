package com.tongxun.atongmu.parent.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.tongxun.atongmu.parent.Base2Activity;
import com.tongxun.atongmu.parent.R;
import com.tongxun.atongmu.parent.ui.MainActivity;
import com.tongxun.atongmu.parent.ui.WebViewActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;

import static com.tongxun.atongmu.parent.R.id.btn_verification_confirm;

public class VerificationActivity extends Base2Activity<IVerificationContract.View, VerificationPresenter> implements IVerificationContract.View,View.OnClickListener {

    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.et_verification_code)
    EditText etVerificationCode;
    @BindView(R.id.tv_verification_time)
    TextView tvVerificationTime;
    @BindView(R.id.tv_no_get_code)
    TextView tvNoGetCode;
    @BindView(btn_verification_confirm)
    Button btnVerificationConfirm;
    @BindView(R.id.tv_agreement)
    TextView tvAgreement;

    private TimeCount time;
    private String phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);
        setStatusColor(R.color.colorMainYellow);
        ButterKnife.bind(this);
        tvAgreement.setOnClickListener(this);
        btnVerificationConfirm.setOnClickListener(this);
        tvNoGetCode.setOnClickListener(this);
        tvVerificationTime.setOnClickListener(this);
        toolbarTitle.setText(getResources().getString(R.string.login));
        time = new TimeCount(60000, 1000);
        Intent intent=getIntent();
        try {
            phone=intent.getStringExtra("phone");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    class TimeCount extends CountDownTimer {
        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            tvVerificationTime.setClickable(false);//防止重复点击
            tvVerificationTime.setText(millisUntilFinished / 1000+"″");
        }

        @Override
        public void onFinish() {
            tvVerificationTime.setText(getResources().getString(R.string.verification_time));
            tvVerificationTime.setClickable(true);
        }
    }

    @Override
    protected VerificationPresenter initPresenter() {
        return new VerificationPresenter();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_agreement:
                String url=getResources().getString(R.string.agreement_url);
                WebViewActivity.startWebViewActivity(VerificationActivity.this,"用户协议",url,false);
                break;
            case btn_verification_confirm:
                mPresenter.checkVerCode(getVerCode());
                break;
            case R.id.tv_no_get_code:

                break;
            case R.id.tv_verification_time:
                mPresenter.getWebVer(phone);
                time.start();
                break;

        }
    }

    @Override
    public String getVerCode() {
        return etVerificationCode.getText().toString();
    }

    @Override
    public void LoginSuccess() {
        Intent intent=new Intent(VerificationActivity.this, MainActivity.class);
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
    public void sendError() {
        Toasty.error(this, getResources().getString(R.string.code_send_error), Toast.LENGTH_SHORT, true).show();
        if(time!=null){
            time.cancel();
        }
        time.onFinish();
    }

    @Override
    protected void onDestroy() {
        try {
            if(time!=null){
                time.cancel();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.onDestroy();
    }
}
