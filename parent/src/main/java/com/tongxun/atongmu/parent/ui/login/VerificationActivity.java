package com.tongxun.atongmu.parent.ui.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.tongxun.atongmu.parent.Base2Activity;
import com.tongxun.atongmu.parent.Constants;
import com.tongxun.atongmu.parent.R;
import com.tongxun.atongmu.parent.model.TokenIdModel;
import com.tongxun.atongmu.parent.ui.WebViewActivity;
import com.tongxun.atongmu.parent.ui.home.MainActivity;
import com.tongxun.atongmu.parent.util.SharePreferenceUtil;

import org.litepal.crud.DataSupport;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.jpush.android.api.JPushInterface;
import es.dmoral.toasty.Toasty;

import static com.tongxun.atongmu.parent.R.id.btn_verification_confirm;

public class VerificationActivity extends Base2Activity<IVerificationContract.View, VerificationPresenter> implements IVerificationContract.View, View.OnClickListener {

    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.et_verification_code)
    EditText etVerificationCode;
    @BindView(R.id.tv_verification_time)
    TextView tvVerificationTime;
    @BindView(R.id.tv_no_get_code)
    TextView tvNoGetCode;
    @BindView(R.id.tv_agreement)
    TextView tvAgreement;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.btn_verification_confirm)
    Button btnVerificationConfirm;

    private TimeCount time;
    private String phone;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);
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


        tvAgreement.setOnClickListener(this);
        btnVerificationConfirm.setOnClickListener(this);
        tvNoGetCode.setOnClickListener(this);
        tvVerificationTime.setOnClickListener(this);
        toolbarTitle.setText(getResources().getString(R.string.login));
        time = new TimeCount(60000,1000);
        Intent intent = getIntent();
        try {
            phone = intent.getStringExtra("phone");
            password = intent.getStringExtra("password");
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
            tvVerificationTime.setText(millisUntilFinished / 1000 + "″");
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
        switch (v.getId()) {
            case R.id.tv_agreement:
                String url = getResources().getString(R.string.agreement_url);
                WebViewActivity.startWebViewActivity(VerificationActivity.this,getResources().getString(R.string.agreement),"", Constants.DEFAULTICON,url,"yellow", false);
                break;
            case btn_verification_confirm:
                mPresenter.checkVerCode(getVerCode());
                break;
            case R.id.tv_no_get_code:
                jumpToNoGetCode();
                break;
            case R.id.tv_verification_time:
                mPresenter.getWebVer(phone);
                time.start();
                break;

        }
    }

    private void jumpToNoGetCode() {
        //// TODO: 2017/8/2 收不到验证码的URL
        String url="";
        WebViewActivity.startWebViewActivity(this,"","",Constants.DEFAULTICON,url);
    }

    @Override
    public String getVerCode() {
        return etVerificationCode.getText().toString();
    }

    /**
     * 验证码验证成功
     */
    @Override
    public void LoginSuccess() {
        saveLoginSuccessInfo();
        mPresenter.getUserInfo();
    }

    /**
     * 保存登录成功的信息 用户手机号 tokenID 和记住密码状态
     */
    private void saveLoginSuccessInfo() {
        SharedPreferences preferences= SharePreferenceUtil.getPreferences();
        SharedPreferences.Editor editor=preferences.edit();
        editor.putString(SharePreferenceUtil.USERPHONE,phone);
        editor.putString(SharePreferenceUtil.PASSWORD,password);
        editor.putString(SharePreferenceUtil.TOKENID, DataSupport.findFirst(TokenIdModel.class).getTokenId());
        editor.putBoolean(SharePreferenceUtil.isRemember,true);
        editor.commit();
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
        if (time != null) {
            time.cancel();
        }
        time.onFinish();
    }


    @Override
    public void onBabyInfoSuccess() {
        JPushInterface.resumePush(this);
        Intent intent = new Intent(VerificationActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            moveTaskToBack(true);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    @Override
    protected void onDestroy() {
        try {
            if (time != null) {
                time.cancel();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.onDestroy();
    }
}
