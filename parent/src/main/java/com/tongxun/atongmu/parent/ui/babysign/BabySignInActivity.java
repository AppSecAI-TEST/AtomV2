package com.tongxun.atongmu.parent.ui.babysign;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.tongxun.atongmu.parent.Base2Activity;
import com.tongxun.atongmu.parent.R;
import com.tongxun.atongmu.parent.adapter.BabySignAdapter;
import com.tongxun.atongmu.parent.model.BabySignInModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;

public class BabySignInActivity extends Base2Activity<IBabySignInContract.View, BabySignPresenter> implements IBabySignInContract.View {

    @BindView(R.id.iv_title_back)
    ImageView ivTitleBack;
    @BindView(R.id.tv_title_name)
    TextView tvTitleName;
    @BindView(R.id.tv_title_right)
    TextView tvTitleRight;
    @BindView(R.id.rv_date_sign_in)
    RecyclerView rvDateSignIn;
    @BindView(R.id.tv_sign_record_num)
    TextView tvSignRecordNum;

    private BabySignAdapter mAdapter;


    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    private int firstPosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baby_sign_in);
        setStatusColor(R.color.colorWhite);
        ButterKnife.bind(this);
        tvTitleName.setText(format.format(new Date()));
        tvTitleRight.setText(getString(R.string.ask_for_leave));

        setRecyclerViewUI();
        mPresenter.getSignInRecord(dateFormat.format(new Date()));
    }


    private void setRecyclerViewUI() {
        rvDateSignIn.setItemAnimator(new DefaultItemAnimator());
        rvDateSignIn.setLayoutManager(new GridLayoutManager(this, 7));

    }

    @Override
    protected BabySignPresenter initPresenter() {
        return new BabySignPresenter();
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @OnClick({R.id.iv_title_back, R.id.tv_title_right, R.id.tv_title_name})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_title_back:
                finish();
                break;
            case R.id.tv_title_right:

                break;
            case R.id.tv_title_name:
                showDatePickDialog();
                break;
        }
    }

    private void showDatePickDialog() {
        Calendar c = Calendar.getInstance();
        // 直接创建一个DatePickerDialog对话框实例，并将它显示出来
        new DatePickerDialog(BabySignInActivity.this,
                // 绑定监听器
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {

                        tvTitleName.setText(year + "-" + (monthOfYear + 1)
                                + "-" + dayOfMonth);
                    }
                }
                // 设置初始日期
                , c.get(Calendar.YEAR), c.get(Calendar.MONTH), c
                .get(Calendar.DAY_OF_MONTH)).show();
    }

    @Override
    public void setRefreshSignInDate(List<BabySignInModel> list, String signNum) {
        tvSignRecordNum.setText(signNum+getString(R.string.day));
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DATE, 1);
        int size = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        if (size < 0) {
            size = 0;
        }

        for (int i = 0; i < size; i++) {
            list.add(0, new BabySignInModel("", "EMPTY"));
        }

        firstPosition = size;

        mAdapter = new BabySignAdapter(this, list, size);
        rvDateSignIn.setAdapter(mAdapter);
    }

    @Override
    public void onError(String mesaage) {
        Toasty.error(this, mesaage, Toast.LENGTH_SHORT).show();
    }
}
