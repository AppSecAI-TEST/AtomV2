package com.tongxun.atongmu.parent.ui.login;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.tongxun.atongmu.parent.Base2Activity;
import com.tongxun.atongmu.parent.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RegisterActivity extends Base2Activity<IRegisterContract.View, RegisterPresenter> implements IRegisterContract.View {

    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.tv_verification_time)
    TextView tvVerificationTime;
    @BindView(R.id.tv_no_get_code)
    TextView tvNoGetCode;
    @BindView(R.id.btn_register_next)
    Button btnRegisterNext;
    @BindView(R.id.tv_register_goLogin)
    TextView tvRegisterGoLogin;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
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


        toolbarTitle.setText(getResources().getString(R.string.register));
    }

    @Override
    protected RegisterPresenter initPresenter() {
        return new RegisterPresenter();
    }
}
