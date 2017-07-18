package com.tongxun.atongmu.parent.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tongxun.atongmu.parent.R;
import com.tongxun.atongmu.parent.model.PayItemModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Anro on 2017/7/18.
 */

public class PayItemAdapter extends RecyclerView.Adapter<PayItemAdapter.PayItemViewHolder> {


    private Context mContext;
    private List<PayItemModel> mlist = new ArrayList<>();

    public PayItemAdapter(Context context, List<PayItemModel> list) {
        mContext = context;
        mlist = list;
    }

    @Override
    public PayItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_pay_product, parent, false);
        PayItemViewHolder viewHolder = new PayItemViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(PayItemViewHolder holder, int position) {
        holder.tvName.setText(mlist.get(position).getTitle());
        holder.tvPrice.setText(mlist.get(position).getNumber());
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    class PayItemViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_price)
        TextView tvPrice;
        public PayItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
