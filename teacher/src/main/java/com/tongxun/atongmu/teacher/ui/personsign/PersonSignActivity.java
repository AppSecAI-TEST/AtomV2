package com.tongxun.atongmu.teacher.ui.personsign;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.tongxun.atongmu.teacher.Base2Activity;
import com.tongxun.atongmu.teacher.R;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PersonSignActivity extends Base2Activity<IPersonContract.View, PersonSignPresenter> implements IPersonContract.View {


    @BindView(R.id.iv_title_back)
    ImageView ivTitleBack;
    @BindView(R.id.tv_title_name)
    TextView tvTitleName;
    @BindView(R.id.tv_title_right)
    TextView tvTitleRight;

    private SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_sign);
        setStatusColor(R.color.colorWhite);
        ButterKnife.bind(this);
        tvTitleRight.setText(getString(R.string.my_sign_in));
        tvTitleName.setText(dateFormat.format(new Date()));
        mPresenter.getPersonSign();
    }

    @Override
    protected PersonSignPresenter initPresenter() {
        return new PersonSignPresenter();
    }
}
