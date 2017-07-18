package com.tongxun.atongmu.parent.ui.schooltuition;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.tongxun.atongmu.parent.Base2Activity;
import com.tongxun.atongmu.parent.R;
import com.tongxun.atongmu.parent.adapter.PayItemAdapter;
import com.tongxun.atongmu.parent.model.PayItemModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;

public class PaySchoolOrderActivity extends Base2Activity<IPaySchoolOrderContract.View, PaySchoolOrderPresenter> implements IPaySchoolOrderContract.View {

    @BindView(R.id.tv_item_title)
    TextView tvItemTitle;
    @BindView(R.id.rv_item_content)
    RecyclerView rvItemContent;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.tv_remark)
    TextView tvRemark;
    @BindView(R.id.wxpay_check)
    ImageView wxpayCheck;
    @BindView(R.id.rl_wx_pay)
    RelativeLayout rlWxPay;
    @BindView(R.id.apay_check)
    ImageView apayCheck;
    @BindView(R.id.rl_a_pay)
    RelativeLayout rlAPay;
    @BindView(R.id.btn_confirm)
    Button btnConfirm;
    @BindView(R.id.iv_title_back)
    ImageView ivTitleBack;
    @BindView(R.id.tv_title_name)
    TextView tvTitleName;
    private String title;
    private String remark;
    private String total;
    private String packgId;

    private int selectType = -1;
    private ArrayList<PayItemModel> mlist = new ArrayList<>();

    private PayItemAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_school_order);
        setStatusColor(R.color.colorWhite);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        title = intent.getStringExtra("title");
        remark = intent.getStringExtra("remark");
        total = intent.getStringExtra("total");
        packgId = intent.getStringExtra("packgId");
        mlist = intent.getParcelableArrayListExtra("list");

        tvItemTitle.setText(title);
        tvPrice.setText(total);
        tvRemark.setText(remark);
        btnConfirm.setText(getResources().getString(R.string.confirm_pay) + total);
        rvItemContent.setItemAnimator(new DefaultItemAnimator());
        rvItemContent.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new PayItemAdapter(this, mlist);
        rvItemContent.setAdapter(mAdapter);
    }

    @Override
    protected PaySchoolOrderPresenter initPresenter() {
        return new PaySchoolOrderPresenter();
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @OnClick({R.id.rl_wx_pay, R.id.rl_a_pay, R.id.btn_confirm,R.id.iv_title_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_wx_pay:
                setPayTypeSelect(0);
                break;
            case R.id.rl_a_pay:
                setPayTypeSelect(1);
                break;
            case R.id.btn_confirm:
                if (selectType == -1) {
                    Toasty.info(this, getResources().getString(R.string.select_pay_type), Toast.LENGTH_SHORT).show();
                } else {
                    if (selectType == 0) {
                        mPresenter.createOrder(packgId, "wechatpay");
                    } else {
                        mPresenter.createOrder(packgId, "alipay");
                    }
                }

                break;
            case R.id.iv_title_back:
                finish();
                break;
        }
    }

    private void setPayTypeSelect(int i) {
        resetPayType();
        switch (i) {
            case 0:
                selectType = 0;
                wxpayCheck.setSelected(true);
                break;
            case 1:
                selectType = 1;
                apayCheck.setSelected(true);
                break;
        }
    }

    private void resetPayType() {
        wxpayCheck.setSelected(false);
        apayCheck.setSelected(false);
    }

    @Override
    public void onError(String message) {
        Toasty.error(this, message, Toast.LENGTH_SHORT).show();
    }

    /**
     * 创建订单成功 调起支付
     *
     * @param orderString
     */
    @Override
    public void onSuccess(String orderString) {

    }
}
