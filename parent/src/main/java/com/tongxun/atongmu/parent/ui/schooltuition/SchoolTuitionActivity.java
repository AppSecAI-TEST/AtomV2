package com.tongxun.atongmu.parent.ui.schooltuition;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.tongxun.atongmu.parent.Base2Activity;
import com.tongxun.atongmu.parent.Constants;
import com.tongxun.atongmu.parent.R;
import com.tongxun.atongmu.parent.adapter.PayNoticeAdapter;
import com.tongxun.atongmu.parent.adapter.PayRecordAdapter;
import com.tongxun.atongmu.parent.model.OrderRecordModel;
import com.tongxun.atongmu.parent.model.TuitionModel;
import com.tongxun.atongmu.parent.util.DensityUtil;
import com.tongxun.atongmu.parent.util.RecycleViewDivider;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;

public class SchoolTuitionActivity extends Base2Activity<ISchoolTuitionContract.View, SchoolTuitionPresenter> implements ISchoolTuitionContract.View, PayNoticeAdapter.ItemClickListener {

    @BindView(R.id.iv_title_back)
    ImageView ivTitleBack;
    @BindView(R.id.tv_pay_notice)
    TextView tvPayNotice;
    @BindView(R.id.tv_pay_record)
    TextView tvPayRecord;
    @BindView(R.id.ll_title_homework)
    LinearLayout llTitleHomework;
    @BindView(R.id.rv_course_content)
    RecyclerView rvCourseContent;

    private PayNoticeAdapter mAdapter;

    private PayRecordAdapter recordAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_tuition);
        setStatusColor(R.color.colorWhite);
        ButterKnife.bind(this);
        setPosition(0);
        rvCourseContent.setItemAnimator(new DefaultItemAnimator());
        rvCourseContent.setLayoutManager(new LinearLayoutManager(this));
        rvCourseContent.addItemDecoration(new RecycleViewDivider(this, LinearLayoutManager.VERTICAL, DensityUtil.dip2px(this,10), getResources().getColor(R.color.colorLineGray)));
    }

    @Override
    protected SchoolTuitionPresenter initPresenter() {
        return new SchoolTuitionPresenter();
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @OnClick({R.id.iv_title_back, R.id.tv_pay_notice, R.id.tv_pay_record})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_title_back:
                finish();
                break;
            case R.id.tv_pay_notice:
                setPosition(0);
                break;
            case R.id.tv_pay_record:
                setPosition(1);
                break;
        }
    }

    private void setPosition(int i) {
        resetTitle();
        switch (i){
            case 0:
                tvPayNotice.setSelected(true);
                mPresenter.getPayNotice();
                break;
            case 1:
                tvPayRecord.setSelected(true);
                mPresenter.getPayRecord();
                break;
        }
    }

    private void resetTitle() {
        tvPayNotice.setSelected(false);
        tvPayRecord.setSelected(false);
    }

    @Override
    public void onError(String message) {
        rvCourseContent.setVisibility(View.INVISIBLE);
        Toasty.error(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPayNoticeSuccess(List<TuitionModel> datas) {
        rvCourseContent.setVisibility(View.VISIBLE);
        mAdapter=new PayNoticeAdapter(this,datas,this);
        rvCourseContent.setAdapter(mAdapter);
    }


    @Override
    public void onOrderRecordSuccess(List<OrderRecordModel> datas) {
        rvCourseContent.setVisibility(View.VISIBLE);

        recordAdapter=new PayRecordAdapter(this,datas);
        rvCourseContent.setAdapter(recordAdapter);
    }


    @Override
    public void onItemClick(TuitionModel model) {
        Intent intent=new Intent(SchoolTuitionActivity.this,PaySchoolOrderActivity.class);
        intent.putExtra("title",model.getItemTitle());
        intent.putExtra("remark",model.getRemark());
        intent.putExtra("total",model.getTotalNum());
        intent.putExtra("packgId",model.getItemId());
        intent.putParcelableArrayListExtra("list", (ArrayList<? extends Parcelable>) model.getItemList());
        startActivityForResult(intent, Constants.REQ_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode==RESULT_OK){
            if(requestCode==Constants.REQ_CODE){
                mPresenter.getPayNotice();
            }
        }
    }
}
