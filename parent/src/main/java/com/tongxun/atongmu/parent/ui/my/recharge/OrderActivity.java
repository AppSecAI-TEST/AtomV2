package com.tongxun.atongmu.parent.ui.my.recharge;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.tongxun.atongmu.parent.BaseActivity;
import com.tongxun.atongmu.parent.Constants;
import com.tongxun.atongmu.parent.R;
import com.tongxun.atongmu.parent.model.BaseCallBack;
import com.tongxun.atongmu.parent.model.OrderCallBack;
import com.tongxun.atongmu.parent.model.OrderModel;
import com.tongxun.atongmu.parent.util.SharePreferenceUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;
import okhttp3.Call;
import okhttp3.MediaType;

public class OrderActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.iv_title_back)
    ImageView ivTitleBack;
    @BindView(R.id.tv_title_name)
    TextView tvTitleName;
    @BindView(R.id.lv_order)
    ListView lvOrder;
    @BindView(R.id.srl_order)
    SwipeRefreshLayout srlOrder;

    private List<OrderModel> mlist=new ArrayList<>();

    private OrderAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        ButterKnife.bind(this);
        setStatusColor(R.color.colorWhite);
        srlOrder.setColorSchemeColors(
                Color.parseColor("#ff33b5e5"),
                Color.parseColor("#ffff4444"),
                Color.parseColor("#ffffbb33"),
                Color.parseColor("#ff99cc00")
        );
        srlOrder.setOnRefreshListener(this);


        mAdapter=new OrderAdapter(this,R.layout.activity_order_item_layout,mlist);
        lvOrder.setAdapter(mAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        getWebOrder();
    }

    private void getWebOrder() {
        String url= Constants.restPayOrderList_v2;
        OkHttpUtils.postString()
                .url(url)
                .content(CreateJson())
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .tag(this)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Toasty.error(OrderActivity.this, getString(R.string.net_error), Toast.LENGTH_SHORT).show();
                        srlOrder.setRefreshing(false);
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Gson gson=new Gson();
                        OrderCallBack callBack= null;
                        try {
                            callBack = gson.fromJson(response,OrderCallBack.class);
                        } catch (JsonSyntaxException e) {
                            e.printStackTrace();
                        }
                        if(callBack!=null){
                            if(callBack.getStatus().equals("success")){
                                if(mlist.size()>0){
                                    mlist.clear();
                                }
                                mlist.addAll(callBack.getData());
                                mAdapter.notifyDataSetChanged();
                            }else {
                                Toasty.error(OrderActivity.this,callBack.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }else {
                            Toasty.error(OrderActivity.this, getString(R.string.date_error), Toast.LENGTH_SHORT).show();
                        }

                        srlOrder.setRefreshing(false);
                    }
                });
    }

    private void postWebDeleteOrder(int position) {
        String  url=Constants.restPayOrderDelete_v2;
        OkHttpUtils.postString()
                .url(url)
                .content(CreateDeleteJson(position))
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .tag(this)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Toasty.error(OrderActivity.this, getString(R.string.net_error), Toast.LENGTH_SHORT).show();
                        srlOrder.setRefreshing(false);
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Gson gson=new Gson();
                        BaseCallBack callBack= null;
                        try {
                            callBack = gson.fromJson(response,BaseCallBack.class);
                        } catch (JsonSyntaxException e) {
                            e.printStackTrace();
                        }
                        if(callBack!=null){
                            if(callBack.getStatus().equals("success")){
                                getWebOrder();
                            }else {
                                Toasty.error(OrderActivity.this,callBack.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }else {
                            Toasty.error(OrderActivity.this, getString(R.string.date_error), Toast.LENGTH_SHORT).show();
                        }

                        srlOrder.setRefreshing(false);
                    }
                });
    }

    private void postWebCancelOrder(int position) {
        String  url=Constants.restPayOrderCancel_v2;
        OkHttpUtils.postString()
                .url(url)
                .content(CreateDeleteJson(position))
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .tag(this)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Toasty.error(OrderActivity.this, getString(R.string.net_error), Toast.LENGTH_SHORT).show();
                        srlOrder.setRefreshing(false);
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Gson gson=new Gson();
                        BaseCallBack callBack= null;
                        try {
                            callBack = gson.fromJson(response,BaseCallBack.class);
                        } catch (JsonSyntaxException e) {
                            e.printStackTrace();
                        }
                        if(callBack!=null){
                            if(callBack.getStatus().equals("success")){
                                getWebOrder();
                            }else {
                                Toasty.error(OrderActivity.this,callBack.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }else {
                            Toasty.error(OrderActivity.this, getString(R.string.date_error), Toast.LENGTH_SHORT).show();
                        }

                        srlOrder.setRefreshing(false);
                    }
                });
    }

    private String CreateDeleteJson(int position) {
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject();
            jsonObject.put("tokenId", SharePreferenceUtil.getPreferences().getString(SharePreferenceUtil.TOKENID,""));
            jsonObject.put("orderId", mlist.get(position).getOrderId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonObject.toString();
    }

    private String CreateJson() {
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject();
            jsonObject.put("tokenId", SharePreferenceUtil.getPreferences().getString(SharePreferenceUtil.TOKENID,""));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonObject.toString();
    }


    @OnClick(R.id.iv_title_back)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void onRefresh() {
        getWebOrder();
    }

    class OrderAdapter extends ArrayAdapter<OrderModel> {

        int resourceId;
        public OrderAdapter(Context context, int resource, List<OrderModel> objects) {
            super(context, resource, objects);
            resourceId=resource;
        }

        @NonNull
        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            OrderViewHolder viewholder = null;
            if(convertView==null){
                convertView= LayoutInflater.from(OrderActivity.this).inflate(resourceId,parent,false);
                viewholder=new OrderViewHolder();
                viewholder.order_img= (ImageView) convertView.findViewById(R.id.iv_order_item);
                viewholder.packg_title= (TextView) convertView.findViewById(R.id.tv_order_item_title);
                viewholder.packg_content= (TextView) convertView.findViewById(R.id.tv_order_item_remarks);
                viewholder.order_price_value= (TextView) convertView.findViewById(R.id.tv_order_item_price_value);
                viewholder.order_createtime_value= (TextView) convertView.findViewById(R.id.tv_order_item_createtime_value);
                viewholder.order_type= (TextView) convertView.findViewById(R.id.tv_order_item_type);
                viewholder.order_type_value= (TextView) convertView.findViewById(R.id.tv_order_item_type_value);
                viewholder.order_cancel= (TextView) convertView.findViewById(R.id.tv_order_cancel);
                viewholder.order_confirm= (TextView) convertView.findViewById(R.id.tv_order_confirm);
                viewholder.order_line=convertView.findViewById(R.id.order_line);
                viewholder.order_click= (LinearLayout) convertView.findViewById(R.id.ll_click);
                viewholder.order_delete= (ImageView) convertView.findViewById(R.id.iv_order_delete);
                viewholder.mRelativeLayout= (RelativeLayout) convertView.findViewById(R.id.rl_item_to_detail);
                convertView.setTag(viewholder);
            }else {
                viewholder= (OrderViewHolder) convertView.getTag();
            }
            final OrderModel dataBean=getItem(position);
            if(dataBean.getPackgType().equals("欢乐")){
                viewholder.order_img.setImageResource(R.drawable.happy_order_icon);
            }else if(dataBean.getPackgType().equals("功能")) {
                viewholder.order_img.setImageResource(R.drawable.fun_order_icon);
            }else {
                viewholder.order_img.setImageResource(R.drawable.circle_imgload);
            }
            viewholder.packg_title.setText(dataBean.getPackgTitle());
            viewholder.packg_content.setText(dataBean.getPackgRemark());
            viewholder.order_price_value.setText("￥"+dataBean.getPackgPrice());
            viewholder.order_createtime_value.setText(dataBean.getCreateDate());
            if(dataBean.getOrderType().equals("已支付")){
                viewholder.order_click.setVisibility(View.GONE);
                viewholder.order_line.setVisibility(View.GONE);
                viewholder.order_delete.setVisibility(View.VISIBLE);
                viewholder.order_type.setVisibility(View.VISIBLE);
                viewholder.order_type_value.setVisibility(View.VISIBLE);
                viewholder.order_type_value.setText("充值成功");
            }else if(dataBean.getOrderType().equals("未支付")){
                viewholder.order_click.setVisibility(View.VISIBLE);
                viewholder.order_line.setVisibility(View.VISIBLE);
                viewholder.order_delete.setVisibility(View.GONE);
                viewholder.order_type.setVisibility(View.GONE);
                viewholder.order_type_value.setVisibility(View.GONE);
            }else if(dataBean.getOrderType().equals("已取消")) {
                viewholder.order_click.setVisibility(View.GONE);
                viewholder.order_line.setVisibility(View.GONE);
                viewholder.order_delete.setVisibility(View.VISIBLE);
                viewholder.order_type.setVisibility(View.VISIBLE);
                viewholder.order_type_value.setVisibility(View.VISIBLE);
                viewholder.order_type_value.setText("已取消");
            }else {
                viewholder.order_click.setVisibility(View.GONE);
                viewholder.order_line.setVisibility(View.GONE);
                viewholder.order_type.setVisibility(View.VISIBLE);
                viewholder.order_type_value.setVisibility(View.VISIBLE);
                viewholder.order_type_value.setText("订单异常");
            }

            viewholder.order_cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    postWebCancelOrder(position);
                }
            });

            viewholder.order_confirm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(OrderActivity.this,PayOrderActivity.class);
                    intent.putExtra("bean",mlist.get(0));
                    intent.putExtra("action","Continue");
                    startActivity(intent);
                }
            });

            viewholder.mRelativeLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(OrderActivity.this,RechargeRecordActivity.class);
                    intent.putExtra("Bean",dataBean);
                    startActivity(intent);
                }
            });
            viewholder.order_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    postWebDeleteOrder(position);
                }
            });

            return convertView;
        }



        class OrderViewHolder{
            ImageView order_img;
            TextView packg_title;
            TextView packg_content;
            TextView order_price_value;
            TextView order_createtime_value;
            TextView order_type;
            TextView order_type_value;
            TextView order_cancel;
            TextView order_confirm;
            View order_line;
            LinearLayout order_click;
            ImageView order_delete;
            RelativeLayout mRelativeLayout;
        }
    }



}
