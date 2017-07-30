package com.tongxun.atongmu.parent.ui.my.recharge;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.tongxun.atongmu.parent.Base2Activity;
import com.tongxun.atongmu.parent.R;
import com.tongxun.atongmu.parent.model.RechargeItemModel;
import com.tongxun.atongmu.parent.model.WxPayModel;
import com.tongxun.atongmu.parent.util.PayResult;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;

import static com.tongxun.atongmu.parent.R.id.apay_check;
import static com.tongxun.atongmu.parent.R.id.pay_comfirm;
import static com.tongxun.atongmu.parent.R.id.wxpay_check;

public class PayOrderActivity extends Base2Activity<IPayOrderContract.View, PayOrderPresenter> implements IPayOrderContract.View {

    @BindView(R.id.iv_title_back)
    ImageView ivTitleBack;
    @BindView(R.id.tv_title_name)
    TextView tvTitleName;
    @BindView(R.id.pay_order_list)
    ListView payOrderList;
    @BindView(R.id.wxpay_imgview)
    ImageView wxpayImgview;
    @BindView(R.id.wxpay_name)
    TextView wxpayName;
    @BindView(wxpay_check)
    ImageView wxpayCheck;
    @BindView(R.id.apay_imgview)
    ImageView apayImgview;
    @BindView(R.id.apay_name)
    TextView apayName;
    @BindView(apay_check)
    ImageView apayCheck;
    @BindView(pay_comfirm)
    Button payComfirm;
    @BindView(R.id.rl_wx_pay)
    RelativeLayout rlWxPay;
    @BindView(R.id.rl_a_pay)
    RelativeLayout rlAPay;

    private String payAction="";
    private RechargeItemModel model;
    List<RechargeItemModel> mlist = new ArrayList<>();

    private PayAdapter mAdapter;

    private float record;

    private String action="";

    private static final int SDK_PAY_FLAG = 1531;

    // IWXAPI 是第三方app和微信通信的openapi接口
    private IWXAPI api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_order);
        setStatusColor(R.color.colorWhite);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        action = intent.getStringExtra("action");
        if (action.equals("New")) {
            model = intent.getParcelableExtra("bean");
        }
        if (model != null) {
            mlist.add(model);
        }

        mAdapter = new PayAdapter(PayOrderActivity.this, R.layout.pay_order_item_layout, mlist);

        for (RechargeItemModel bean : mlist) {
            double price = Double.parseDouble(bean.getPackgPrice());
            record += price;
        }
        payComfirm.setText("确认开通￥" + record + "");

        payOrderList.setAdapter(mAdapter);

        /**
         * 注册APPID 将app注册到微信
         */
        api = WXAPIFactory.createWXAPI(this, getResources().getString(R.string.wx_app_id));

        api.registerApp(getResources().getString(R.string.wx_app_id));
    }

    @Override
    protected PayOrderPresenter initPresenter() {
        return new PayOrderPresenter();
    }

    @OnClick({R.id.rl_wx_pay, R.id.rl_a_pay, R.id.pay_comfirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_wx_pay:
                resetImg();
                wxpayCheck.setImageResource(R.drawable.recharge_check);
                payAction = "WXPAY";
                break;
            case R.id.rl_a_pay:
                resetImg();
                apayCheck.setImageResource(R.drawable.recharge_check);
                payAction = "APAY";
                break;
            case R.id.pay_comfirm:
                if(TextUtils.isEmpty(payAction)){
                    Toasty.info(this, "请选择支付方式", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (payAction.equals("APAY")) {
                    mPresenter.postWebPayMoney("支付宝",mlist.get(0).getPackgId());
                } else if (payAction.equals("WXPAY")) {
                    mPresenter.postWebPayMoney("微信",mlist.get(0).getPackgId());
                }
                break;
        }
    }

    private void resetImg() {
        apayCheck.setImageResource(R.drawable.recharge_uncheck);
        wxpayCheck.setImageResource(R.drawable.recharge_uncheck);
    }

    @Override
    public void onError(String message) {
        Toasty.error(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccess(final String orderString) {
        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                // 构造PayTask 对象
                PayTask alipay = new PayTask(PayOrderActivity.this);
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
                        Toasty.success(PayOrderActivity.this, getResources().getString(R.string.pay_success), Toast.LENGTH_SHORT).show();
                        setResult(RESULT_OK);
                        finish();
                    } else {
                        // 判断resultStatus 为非"9000"则代表可能支付失败
                        // "8000"代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，最终交易是否成功以服务端异步通知为准（小概率状态）
                        if (TextUtils.equals(resultStatus, "8000")) {
                            Toasty.error(PayOrderActivity.this, getResources().getString(R.string.pay_waiting), Toast.LENGTH_SHORT).show();

                        } else {
                            // 其他值就可以判断为支付失败，包括用户主动取消支付，或者系统返回的错误
                            Toasty.error(PayOrderActivity.this, getResources().getString(R.string.pay_error), Toast.LENGTH_SHORT).show();

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


    class PayAdapter extends ArrayAdapter<RechargeItemModel> {
        int resourceId;
        Context mContext;

        public PayAdapter(Context context, int resource, List<RechargeItemModel> objects) {
            super(context, resource, objects);
            resourceId = resource;
            mContext = context;
        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view;
            final ViewHolder viewHolder;
            if (convertView == null) {
                view = LayoutInflater.from(mContext).inflate(resourceId, null);
                viewHolder = new ViewHolder();
                viewHolder.mtitle = (TextView) view.findViewById(R.id.pay_order_item_name);
                viewHolder.mremark = (TextView) view.findViewById(R.id.pay_order_item_content);
                view.setTag(viewHolder);
            } else {
                view = convertView;
                viewHolder = (ViewHolder) view.getTag();
            }
            RechargeItemModel datebean = getItem(position);
            viewHolder.mtitle.setText(datebean.getPackgTitle() + "￥" + datebean.getPackgPrice());
            viewHolder.mremark.setText(datebean.getPackgRemark());
            return view;
        }


        class ViewHolder {
            TextView mtitle;
            TextView mremark;

        }
    }

}
