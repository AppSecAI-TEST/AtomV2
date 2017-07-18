package com.tongxun.atongmu.parent.adapter;

import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.tongxun.atongmu.parent.R;
import com.tongxun.atongmu.parent.model.TuitionModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Anro on 2017/7/17.
 */

public class PayNoticeAdapter extends RecyclerView.Adapter<PayNoticeAdapter.PayNociceViewHolder> {


    private Context mContext;
    private List<TuitionModel> mlist = new ArrayList<>();

    private PayItemAdapter itemAdapter;

    private ItemClickListener mlistener;

    public PayNoticeAdapter(Context context, List<TuitionModel> list,ItemClickListener listener) {
        mContext = context;
        mlist = list;
        mlistener=listener;
    }



    @Override
    public PayNociceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_school_tuition, parent, false);
        PayNociceViewHolder viewHolder = new PayNociceViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(PayNociceViewHolder holder, final int position) {
        holder.tvItemTitle.setText(mlist.get(position).getItemTitle());
        holder.tvPrice.setText(mlist.get(position).getTotalNum());
        holder.tvRemark.setText(mlist.get(position).getRemark());
        holder.tvSchoolName.setText(mlist.get(position).getSchoolName());
        holder.tvCreateTime.setText(mlist.get(position).getCreateTime());
        holder.rvItemContent.setItemAnimator(new DefaultItemAnimator());
        holder.rvItemContent.setLayoutManager(new LinearLayoutManager(mContext));
        itemAdapter=new PayItemAdapter(mContext,mlist.get(position).getItemList());
        holder.rvItemContent.setAdapter(itemAdapter);
        holder.btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mlistener!=null){
                    mlistener.onItemClick(mlist.get(position));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    class PayNociceViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_item_title)
        TextView tvItemTitle;
        @BindView(R.id.rv_item_content)
        RecyclerView rvItemContent;
        @BindView(R.id.tv_price)
        TextView tvPrice;
        @BindView(R.id.tv_remark)
        TextView tvRemark;
        @BindView(R.id.tv_school_name)
        TextView tvSchoolName;
        @BindView(R.id.tv_create_time)
        TextView tvCreateTime;
        @BindView(R.id.btn_pay)
        Button btnPay;
        public PayNociceViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    public interface ItemClickListener{
        void onItemClick(TuitionModel model);
    }
}
