package com.tongxun.atongmu.parent.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tongxun.atongmu.parent.R;
import com.tongxun.atongmu.parent.model.BabySignInModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Anro on 2017/7/14.
 */

public class BabySignAdapter extends RecyclerView.Adapter<BabySignAdapter.BabySignViewHolder> {


    private Context mContext;
    private List<BabySignInModel> mlist = new ArrayList<>();

    private int mSize;



    public BabySignAdapter(Context context, List<BabySignInModel> list, int size) {
        mContext = context;
        mlist = list;
        mSize=size;
    }

    @Override
    public BabySignViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_sign_in_date, parent, false);
        BabySignViewHolder holder=new BabySignViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(BabySignViewHolder holder, int position) {
        if(mlist.get(position).getType().equals("EMPTY")){
            holder.tvDateNum.setVisibility(View.INVISIBLE);
            holder.tvDateType.setVisibility(View.INVISIBLE);
        }else {
            holder.tvDateNum.setVisibility(View.VISIBLE);
            holder.tvDateType.setVisibility(View.VISIBLE);
            //未到
            if(mlist.get(position).getType().equals("unArrived")){
                holder.tvDateNum.setTextColor(mContext.getResources().getColor(R.color.colorWhite));
                holder.tvDateNum.setBackgroundResource(R.drawable.sign_green_shape);
                holder.tvDateType.setText(mContext.getResources().getString(R.string.unarrived));
                holder.tvDateType.setTextColor(mContext.getResources().getColor(R.color.colorSignGreen));
            }
            //已到
            if(mlist.get(position).getType().equals("arrived")){
                holder.tvDateNum.setTextColor(mContext.getResources().getColor(R.color.colorFontBlack));
                holder.tvDateNum.setBackgroundResource(R.color.colorWhite);
            }
            //迟到
            if(mlist.get(position).getType().equals("late")){
                holder.tvDateNum.setTextColor(mContext.getResources().getColor(R.color.colorWhite));
                holder.tvDateNum.setBackgroundResource(R.drawable.sign_blue_shape);
                holder.tvDateType.setText(mContext.getResources().getString(R.string.late));
                holder.tvDateType.setTextColor(mContext.getResources().getColor(R.color.colorSignBlue));
            }
            //事假
            if(mlist.get(position).getType().equals("personalLeave")){
                holder.tvDateNum.setTextColor(mContext.getResources().getColor(R.color.colorWhite));
                holder.tvDateNum.setBackgroundResource(R.drawable.sign_red_shape);
                holder.tvDateType.setText(mContext.getResources().getString(R.string.personalleave));
                holder.tvDateType.setTextColor(mContext.getResources().getColor(R.color.colorSignRed));
            }
            //病假
            if(mlist.get(position).getType().equals("sickLeave")){
                holder.tvDateNum.setTextColor(mContext.getResources().getColor(R.color.colorWhite));
                holder.tvDateNum.setBackgroundResource(R.drawable.sign_red_shape);
                holder.tvDateType.setText(mContext.getResources().getString(R.string.sickleave));
                holder.tvDateType.setTextColor(mContext.getResources().getColor(R.color.colorSignRed));
            }
            //不准点击区域
            if(mlist.get(position).getType().equals("")){
                holder.tvDateNum.setTextColor(mContext.getResources().getColor(R.color.colorFontGray));
                holder.tvDateNum.setBackgroundResource(R.color.colorWhite);
            }

            holder.tvDateNum.setText(""+(position-mSize+1));
        }


    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    class BabySignViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_date_num)
        TextView tvDateNum;
        @BindView(R.id.tv_date_type)
        TextView tvDateType;
        public BabySignViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
