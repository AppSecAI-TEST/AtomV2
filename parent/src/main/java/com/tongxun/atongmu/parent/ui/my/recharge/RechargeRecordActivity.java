package com.tongxun.atongmu.parent.ui.my.recharge;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tongxun.atongmu.parent.BaseActivity;
import com.tongxun.atongmu.parent.R;
import com.tongxun.atongmu.parent.model.OrderModel;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.tongxun.atongmu.parent.R.id.iv_pay_status;
import static com.tongxun.atongmu.parent.R.id.ll_pay_surplus_time;
import static com.tongxun.atongmu.parent.R.id.pay_surplus_time_line;
import static com.tongxun.atongmu.parent.R.id.tv_pay_status;
import static com.tongxun.atongmu.parent.R.id.tv_pay_surplus_time;

public class RechargeRecordActivity extends BaseActivity {

    @BindView(R.id.iv_title_back)
    ImageView ivTitleBack;
    @BindView(R.id.tv_title_name)
    TextView tvTitleName;
    @BindView(R.id.recharge_pay_name)
    TextView rechargePayName;
    @BindView(R.id.recharge_pay_name_value)
    TextView rechargePayNameValue;
    @BindView(R.id.recharge_record_title)
    TextView rechargeRecordTitle;
    @BindView(R.id.recharge_record_content)
    TextView rechargeRecordContent;
    @BindView(R.id.recharge_record_info)
    TextView rechargeRecordInfo;
    @BindView(R.id.recharge_record_info_name)
    TextView rechargeRecordInfoName;
    @BindView(R.id.recharge_record_pay)
    TextView rechargeRecordPay;
    @BindView(R.id.recharge_record_pay_mode)
    TextView rechargeRecordPayMode;
    @BindView(R.id.recharge_record_person)
    TextView rechargeRecordPerson;
    @BindView(R.id.recharge_record_person_name)
    TextView rechargeRecordPersonName;
    @BindView(R.id.recharge_record_order)
    TextView rechargeRecordOrder;
    @BindView(R.id.recharge_record_order_num)
    TextView rechargeRecordOrderNum;
    @BindView(R.id.recharge_record_create)
    TextView rechargeRecordCreate;
    @BindView(R.id.recharge_record_createtime)
    TextView rechargeRecordCreatetime;
    @BindView(iv_pay_status)
    ImageView ivPayStatus;
    @BindView(tv_pay_status)
    TextView tvPayStatus;
    @BindView(pay_surplus_time_line)
    View paySurplusTimeLine;
    @BindView(tv_pay_surplus_time)
    TextView tvPaySurplusTime;
    @BindView(ll_pay_surplus_time)
    LinearLayout llPaySurplusTime;

    private OrderModel dataBean = null;

    private Timer timer = new Timer();

    private int time;

    private boolean isCancel=false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recharge_record);
        ButterKnife.bind(this);
        setStatusColor(R.color.colorWhite);
        Intent intent = getIntent();
        try {
            dataBean = intent.getParcelableExtra("Bean");
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (!TextUtils.isEmpty(dataBean.getOrderFailTime())) {
            time = Integer.parseInt(dataBean.getOrderFailTime());
        }

        set_datas();

        if(!TextUtils.isEmpty(dataBean.getOrderType())){
            if(dataBean.getOrderType().equals("未支付")){
                timer.schedule(timerTask, 1000, 1000);
            }
        }

    }

    private void set_datas() {
        if (!TextUtils.isEmpty(dataBean.getPackgTitle())) {
            rechargeRecordInfoName.setText(dataBean.getPackgTitle());
        }
        if (!TextUtils.isEmpty(dataBean.getPackgPrice())) {
            rechargeRecordContent.setText("￥" + dataBean.getPackgPrice());
        }
        if (!TextUtils.isEmpty(dataBean.getPayName())) {
            rechargePayNameValue.setText(dataBean.getPayName());
        }

        if (!TextUtils.isEmpty(dataBean.getPayType())) {
            rechargeRecordPayMode.setText(dataBean.getPayType());
        }
        if (!TextUtils.isEmpty(dataBean.getPayObject())) {
            rechargeRecordPersonName.setText(dataBean.getPayObject());
        }
        if (!TextUtils.isEmpty(dataBean.getCreateDate())) {
            rechargeRecordCreatetime.setText(dataBean.getCreateDate());
        }
        if (!TextUtils.isEmpty(dataBean.getPayId())) {
            rechargeRecordOrderNum.setText(dataBean.getPayId());
        }

        if (!TextUtils.isEmpty(dataBean.getOrderType())) {
            if (dataBean.getOrderType().equals("已支付")) {
                llPaySurplusTime.setVisibility(View.GONE);
                paySurplusTimeLine.setVisibility(View.GONE);
                ivPayStatus.setImageResource(R.drawable.pay_complete);
                tvPayStatus.setText("交易成功");
            } else if (dataBean.getOrderType().equals("未支付")) {
                llPaySurplusTime.setVisibility(View.VISIBLE);
                paySurplusTimeLine.setVisibility(View.VISIBLE);
                ivPayStatus.setImageResource(R.drawable.pay_wait);
                tvPayStatus.setText("待付款");
            } else {
                llPaySurplusTime.setVisibility(View.GONE);
                paySurplusTimeLine.setVisibility(View.GONE);
                ivPayStatus.setImageResource(R.drawable.pay_fail);
                tvPayStatus.setText("交易关闭");
            }
        }

    }


    TimerTask timerTask = new TimerTask() {
        @Override
        public void run() {
            time--;
            handler.sendEmptyMessage(0x001);
        }
    };

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0x001:
                    int minute = time / 60;
                    int second = time % 60;
                    if(minute==0 && second<=0){
                        handler.sendEmptyMessage(0x002);
                    }else {
                        tvPaySurplusTime.setText(minute + "分" + second + "秒");
                    }
                    if (time <= 0) {
                        timer.cancel();
                    }
                    break;
                case 0x002:
                    llPaySurplusTime.setVisibility(View.GONE);
                    tvPaySurplusTime.setVisibility(View.GONE);
                    ivPayStatus.setImageResource(R.drawable.pay_fail);
                    tvPayStatus.setText("交易关闭");
                    postWebCancelOrder();
                    break;
            }
        }
    };

    private void postWebCancelOrder() {


    }


    @OnClick(R.id.iv_title_back)
    public void onViewClicked() {
        finish();
    }
}
