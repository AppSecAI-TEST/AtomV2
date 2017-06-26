package com.tongxun.atongmu.parent.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.kaopiz.kprogresshud.KProgressHUD;
import com.tongxun.atongmu.parent.R;
import com.tongxun.atongmu.parent.ui.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener,ILoginContract.View {

    @BindView(R.id.et_login_username)
    AppCompatEditText etLoginUsername;
    @BindView(R.id.et_login_password)
    AppCompatEditText etLoginPassword;
    @BindView(R.id.btn_login_confirm)
    Button btnLoginConfirm;
    KProgressHUD hud;
    LoginPresenter loginPersenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        btnLoginConfirm.setOnClickListener(this);
        hud = KProgressHUD.create(this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("Please wait")
                .setCancellable(true)
                .setAnimationSpeed(1)
                .setDimAmount(0.5f);
        loginPersenter=new LoginPresenter(this);

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
    public String getUserName() {
        return etLoginUsername.getText().toString();
    }

    @Override
    public String getPassword() {
        return etLoginPassword.getText().toString();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login_confirm:
                loginPersenter.Login(getUserName(),getPassword());
                break;
        }
    }

    @Override
    public void loginSuccess() {
        Intent intent =new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void loginError(String message) {
        Toasty.error(this, message, Toast.LENGTH_SHORT, true).show();
    }

    @Override
    public void goRegister() {
        Intent intent=new Intent(LoginActivity.this,RegisterActivity.class);
        startActivity(intent);
    }

    @Override
    public void goForgetPwd() {
        Intent intent=new Intent(LoginActivity.this,ForgetPwdActivity.class);
        startActivity(intent);
    }

}
