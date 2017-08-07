package com.tongxun.atongmu.parent.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tongxun.atongmu.parent.IonItemClickListener;
import com.tongxun.atongmu.parent.R;
import com.tongxun.atongmu.parent.model.SignDetailModel;
import com.tongxun.atongmu.parent.util.GlideOption;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Anro on 2017/7/15.
 */

public class BabySignDetailAdapter extends RecyclerView.Adapter {



    private Context mContext;
    private List<SignDetailModel> mlist = new ArrayList<>();
    private String type;
    private IonItemClickListener mlistener;

    public BabySignDetailAdapter(Context context, List<SignDetailModel> datas, String type,IonItemClickListener listener) {
        mlist = datas;
        mContext = context;
        this.type = type;
        mlistener=listener;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (type.equals("sign")) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_sign_in_detail, parent, false);
            SignDetailViewHolder viewHolder = new SignDetailViewHolder(view);
            return viewHolder;
        } else {
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_sign_in_leave, parent, false);
            LeveViewHolder viewHolder = new LeveViewHolder(view);
            return viewHolder;
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof LeveViewHolder) {
            ((LeveViewHolder) holder).tvLeaveTitle.setText(mlist.get(position).getTitle());
            ((LeveViewHolder) holder).tvLeaveStartTime.setText(mlist.get(position).getStartDate());
            ((LeveViewHolder) holder).tvLeaveEndTime.setText(mlist.get(position).getEndDate());
            ((LeveViewHolder) holder).tvLeaveRemark.setText(mlist.get(position).getRemarks());
        }
        if(holder instanceof SignDetailViewHolder){
           ((SignDetailViewHolder) holder).tvTitle.setText(mlist.get(position).getSignType());
           ((SignDetailViewHolder) holder).tvTime.setText(mlist.get(position).getSignTime());
           ((SignDetailViewHolder) holder).tvRemark.setText(mlist.get(position).getPersonName());
            Glide.with(mContext).load(mlist.get(position).getImgUrl()).apply(GlideOption.getImageHolderOption()).into(((SignDetailViewHolder) holder).ivImage);
            ((SignDetailViewHolder) holder).ivImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mlistener!=null){
                        mlistener.onItemClick(position);
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }


    class LeveViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_leave_title)
        TextView tvLeaveTitle;
        @BindView(R.id.tv_leave_start_time)
        TextView tvLeaveStartTime;
        @BindView(R.id.tv_leave_end_time)
        TextView tvLeaveEndTime;
        @BindView(R.id.tv_leave_remark)
        TextView tvLeaveRemark;

        public LeveViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class SignDetailViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_image)
        ImageView ivImage;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.tv_remark)
        TextView tvRemark;
        public SignDetailViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
