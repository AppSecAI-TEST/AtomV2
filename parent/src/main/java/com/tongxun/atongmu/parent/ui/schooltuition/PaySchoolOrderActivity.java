package com.tongxun.atongmu.parent.ui.schooltuition;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.tongxun.atongmu.parent.Base2Activity;
import com.tongxun.atongmu.parent.R;
import com.tongxun.atongmu.parent.adapter.PayItemAdapter;
import com.tongxun.atongmu.parent.model.PayItemModel;
import com.tongxun.atongmu.parent.model.WxPayModel;
import com.tongxun.atongmu.parent.util.PayResult;

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

    private static final int SDK_PAY_FLAG = 1531;

    // IWXAPI 是第三方app和微信通信的openapi接口
    private IWXAPI api;


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

        /**
         * 注册APPID 将app注册到微信
         */
        api = WXAPIFactory.createWXAPI(this, getResources().getString(R.string.wx_app_id));

        api.registerApp(getResources().getString(R.string.wx_app_id));
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
    public void onSuccess(final String orderString) {
        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                // 构造PayTask 对象
                PayTask alipay = new PayTask(PaySchoolOrderActivity.this);
                // 调用支付接口，获取支付结果
                String result = alipay.pay(orderString, true);

                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };

        // 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

    @Override
    public void onWxSuccess(WxPayModel model) {
        api.registerApp(getResources().getString(R.string.wx_app_id));
        PayReq req = new PayReq();
        req.appId = model.getAppid();//appid
        req.partnerId = model.getPartnerid(); //商户号
        req.prepayId = model.getPrepayid();//预支付交易回话Id
        req.nonceStr = model.getNoncestr();//随机字符串
        req.timeStamp = String.valueOf(model.getTimestamp());//时间戳
        req.packageValue = "Sign=WXPay";//固定值sign=WXPAY
        req.sign = model.getSign();//签名
        req.extData = "app data"; // optional
        // 在支付之前，如果应用没有注册到微信，应该先调用IWXMsg.registerApp将应用注册到微信
        api.sendReq(req);
    }

    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    PayResult payResult = new PayResult((String) msg.obj);
                    /**
                     * 同步返回的结果必须放置到服务端进行验证（验证的规则请看https://doc.open.alipay.com/doc2/
                     * detail.htm?spm=0.0.0.0.xdvAU6&treeId=59&articleId=103665&
                     * docType=1) 建议商户依赖异步通知
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息

                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为“9000”则代表支付成功，具体状态码代表含义可参考接口文档
                    if (TextUtils.equals(resultStatus, "9000")) {
                        Toasty.success(PaySchoolOrderActivity.this, getResources().getString(R.string.pay_success), Toast.LENGTH_SHORT).show();
                        setResult(RESULT_OK);
                        finish();
                    } else {
                        // 判断resultStatus 为非"9000"则代表可能支付失败
                        // "8000"代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，最终交易是否成功以服务端异步通知为准（小概率状态）
                        if (TextUtils.equals(resultStatus, "8000")) {
                            Toasty.error(PaySchoolOrderActivity.this, getResources().getString(R.string.pay_waiting), Toast.LENGTH_SHORT).show();

                        } else {
                            // 其他值就可以判断为支付失败，包括用户主动取消支付，或者系统返回的错误
                            Toasty.error(PaySchoolOrderActivity.this, getResources().getString(R.string.pay_error), Toast.LENGTH_SHORT).show();

                        }
                    }
                    break;

                }
                default:
                    break;
            }
        }

        ;
    };
}
