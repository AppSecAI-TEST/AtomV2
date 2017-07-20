package com.tongxun.atongmu.parent.adapter;

import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tongxun.atongmu.parent.R;
import com.tongxun.atongmu.parent.model.OrderRecordModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Anro on 2017/7/20.
 */

public class PayRecordAdapter extends RecyclerView.Adapter<PayRecordAdapter.PayRecordViewHolder> {



    private Context mContext;
    private List<OrderRecordModel> mlist = new ArrayList<>();
    private PayItemAdapter itemAdapter;

    public PayRecordAdapter(Context context, List<OrderRecordModel> list) {
        mContext = context;
        mlist = list;
    }

    @Override
    public PayRecordViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_pay_record_layout, parent, false);
        PayRecordViewHolder viewHolder = new PayRecordViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(PayRecordViewHolder holder, int position) {
        holder.tvPayId.setText(mlist.get(position).getOrderId());
        holder.tvPayPrice.setText(mlist.get(position).getPackgPrice());
        holder.tvPayName.setText(mlist.get(position).getPayName());
        holder.tvPayType.setText(mlist.get(position).getPayType());
        holder.tvPayObject.setText(mlist.get(position).getSchoolName());
        holder.tvPayTime.setText(mlist.get(position).getCreateDate());

        holder.rvOrderRecord.setItemAnimator(new DefaultItemAnimator());
        holder.rvOrderRecord.setLayoutManager(new LinearLayoutManager(mContext));
        itemAdapter=new PayItemAdapter(mContext,mlist.get(position).getItemList());
        holder.rvOrderRecord.setAdapter(itemAdapter);

    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    class PayRecordViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_pay_id)
        TextView tvPayId;
        @BindView(R.id.tv_pay_price)
        TextView tvPayPrice;
        @BindView(R.id.tv_pay_name)
        TextView tvPayName;
        @BindView(R.id.tv_pay_type)
        TextView tvPayType;
        @BindView(R.id.tv_pay_object)
        TextView tvPayObject;
        @BindView(R.id.tv_pay_time)
        TextView tvPayTime;
        @BindView(R.id.rv_order_record)
        RecyclerView rvOrderRecord;

        public PayRecordViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
