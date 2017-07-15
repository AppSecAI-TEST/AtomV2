package com.tongxun.atongmu.parent.ui.babysign;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.tongxun.atongmu.parent.Base2Activity;
import com.tongxun.atongmu.parent.R;
import com.tongxun.atongmu.parent.adapter.BabySignAdapter;
import com.tongxun.atongmu.parent.adapter.BabySignDetailAdapter;
import com.tongxun.atongmu.parent.model.BabySignInModel;
import com.tongxun.atongmu.parent.model.SignDetailModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;

public class BabySignInActivity extends Base2Activity<IBabySignInContract.View, BabySignPresenter> implements IBabySignInContract.View, BabySignAdapter.IBabySignItemClickListener {

    private static final int REQ_CODE = 1001;
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
    @BindView(R.id.rv_remark_detail)
    RecyclerView rvRemarkDetail;

    private BabySignAdapter mAdapter;

    private BabySignDetailAdapter detailAdapter;


    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    private int firstPosition = 0;
    private int selectPosition = 0;

    private int mMonth;

    private String defaultDatePick="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baby_sign_in);
        setStatusColor(R.color.colorWhite);
        ButterKnife.bind(this);

        tvTitleRight.setText(getString(R.string.ask_for_leave));

        setRecyclerViewUI();
        mMonth = Calendar.getInstance().get(Calendar.MONTH);

        defaultDatePick=dateFormat.format(new Date());
        tvTitleName.setText(format.format(new Date()));
        mPresenter.getSignInRecord(defaultDatePick);

    }


    private void setRecyclerViewUI() {
        rvDateSignIn.setItemAnimator(new DefaultItemAnimator());
        rvDateSignIn.setLayoutManager(new GridLayoutManager(this, 7));
        rvRemarkDetail.setItemAnimator(new DefaultItemAnimator());
        rvRemarkDetail.setLayoutManager(new LinearLayoutManager(this));

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
                Intent intent=new Intent(BabySignInActivity.this,AskForLeaveActivity.class);
                startActivityForResult(intent,REQ_CODE);
                break;
            case R.id.tv_title_name:
                showDatePickDialog();
                break;
        }
    }

    private void showDatePickDialog() {
        Calendar c = Calendar.getInstance();

        try {
            Date date=dateFormat.parse(defaultDatePick);
            c.setTime(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // 直接创建一个DatePickerDialog对话框实例，并将它显示出来
        new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker startDatePicker, int startYear, int startMonthOfYear, int startDayOfMonth) {
                String str=startYear+"-"+(startMonthOfYear+1)+"-"+startDayOfMonth;
                mMonth=startMonthOfYear;
                try {
                    Date date=dateFormat.parse(str);
                    tvTitleName.setText(format.format(date));
                    defaultDatePick=dateFormat.format(date);
                    mPresenter.getSignInRecord(defaultDatePick);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

            }
        },c.get(Calendar.YEAR),c.get(Calendar.MONTH),c.get(Calendar.DATE)).show();

    }

    @Override
    public void setRefreshSignInDate(List<BabySignInModel> list, String signNum) {
        tvSignRecordNum.setText(signNum + getString(R.string.day));
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH, mMonth);
        calendar.set(Calendar.DATE, 1);
        int size = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        if (size < 0) {
            size = 0;
        }

        for (int i = 0; i < size; i++) {
            list.add(0, new BabySignInModel("", "EMPTY"));
        }

        firstPosition = size;
        selectPosition = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getDate().equals(defaultDatePick)) {
                selectPosition = i;
            }
        }

        getSignInRecordDetail(list.get(selectPosition).getDate());


        mAdapter = new BabySignAdapter(this, list, size, selectPosition, this);
        rvDateSignIn.setAdapter(mAdapter);

    }

    private void getSignInRecordDetail(String date) {
        rvRemarkDetail.setVisibility(View.INVISIBLE);
        if(!TextUtils.isEmpty(date)){
            mPresenter.getSignInDetail(date);
        }

    }

    @Override
    public void onError(String mesaage) {
        Toasty.error(this, mesaage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setSignDetailSuccess(List<SignDetailModel> datas, String type) {
        detailAdapter=new BabySignDetailAdapter(this,datas,type);
        rvRemarkDetail.setAdapter(detailAdapter);
        rvRemarkDetail.setVisibility(View.VISIBLE);
    }


    @Override
    public void onItemClick(int position, String date) {
        mAdapter.notifyItemChanged(selectPosition);
        mAdapter.notifyItemChanged(position);
        selectPosition = position;
        getSignInRecordDetail(date);
    }

    @Override
    public void onItemError() {
        Toasty.error(this, getResources().getString(R.string.future), Toast.LENGTH_SHORT).show();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==REQ_CODE){
            if(resultCode==RESULT_OK){
                mPresenter.getSignInRecord(dateFormat.format(new Date()));
            }
        }

    }
}
