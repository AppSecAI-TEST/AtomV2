package com.tongxun.atongmu.parent.ui.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.tongxun.atongmu.parent.BaseActivity;
import com.tongxun.atongmu.parent.R;
import com.tongxun.atongmu.parent.util.ActivityControl;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AccountFinishActivity extends BaseActivity {

    @BindView(R.id.tv_type)
    TextView tvType;
    @BindView(R.id.tv_account)
    TextView tvAccount;
    @BindView(R.id.btn_go_login)
    Button btnGoLogin;
    private String type;
    private String phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_finish);
        ButterKnife.bind(this);
        Intent intent=getIntent();
        if(intent!=null){
            type=intent.getStringExtra("type");
            phone=intent.getStringExtra("phone");
        }

        if(type.equals("register")){
            tvType.setText(getResources().getString(R.string.register_finish));
        }else {
            tvType.setText(getResources().getString(R.string.pwd_change_finish));
        }
        tvAccount.setText(getResources().getString(R.string.account)+phone);
        btnGoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AccountFinishActivity.this,LoginActivity.class);
                startActivity(intent);
                ActivityControl.finishAll();
            }
        });
    }

    public static void startAccountFinish(Context context,String type,String phone) {
        Intent intent = new Intent(context, AccountFinishActivity.class);
        intent.putExtra("type",type);
        intent.putExtra("phone",phone);
        context.startActivity(intent);
    }
}
