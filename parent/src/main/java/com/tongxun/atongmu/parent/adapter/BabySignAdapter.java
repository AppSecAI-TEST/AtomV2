package com.tongxun.atongmu.parent.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tongxun.atongmu.parent.R;
import com.tongxun.atongmu.parent.model.BabySignInModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");


    private static int selectPosition = 0;

    private IBabySignItemClickListener mlistener;


    public BabySignAdapter(Context context, List<BabySignInModel> list, int size,int selectPosition,IBabySignItemClickListener listener) {
        mContext = context;
        mlist = list;
        mSize = size;
        mlistener=listener;
        this.selectPosition=selectPosition;
    }

    @Override
    public BabySignViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_sign_in_date, parent, false);
        BabySignViewHolder holder = new BabySignViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(BabySignViewHolder holder, final int position) {
        if (mlist.get(position).getType().equals("EMPTY")) {
            holder.tvDateNum.setVisibility(View.INVISIBLE);
            holder.tvDateType.setVisibility(View.INVISIBLE);
        } else {
            holder.tvDateNum.setVisibility(View.VISIBLE);
            holder.tvDateType.setVisibility(View.VISIBLE);
            //未到
            if (mlist.get(position).getType().equals("unArrived")) {
                holder.tvDateNum.setTextColor(mContext.getResources().getColor(R.color.colorWhite));
                holder.tvDateNum.setBackgroundResource(R.drawable.sign_green_shape);
                holder.tvDateType.setText(mContext.getResources().getString(R.string.unarrived));
                holder.tvDateType.setTextColor(mContext.getResources().getColor(R.color.colorSignGreen));
            }
            //已到
            if (mlist.get(position).getType().equals("arrived")) {
                holder.tvDateNum.setTextColor(mContext.getResources().getColor(R.color.colorFontBlack));
                holder.tvDateNum.setBackgroundResource(R.color.colorWhite);
                holder.tvDateType.setText("");
            }
            //迟到
            if (mlist.get(position).getType().equals("late")) {
                holder.tvDateNum.setTextColor(mContext.getResources().getColor(R.color.colorWhite));
                holder.tvDateNum.setBackgroundResource(R.drawable.sign_blue_shape);
                holder.tvDateType.setText(mContext.getResources().getString(R.string.late));
                holder.tvDateType.setTextColor(mContext.getResources().getColor(R.color.colorSignBlue));
            }
            //事假
            if (mlist.get(position).getType().equals("personalLeave")) {
                holder.tvDateNum.setTextColor(mContext.getResources().getColor(R.color.colorWhite));
                holder.tvDateNum.setBackgroundResource(R.drawable.sign_red_shape);
                holder.tvDateType.setText(mContext.getResources().getString(R.string.personalleave));
                holder.tvDateType.setTextColor(mContext.getResources().getColor(R.color.colorSignRed));
            }
            //病假
            if (mlist.get(position).getType().equals("sickLeave")) {
                holder.tvDateNum.setTextColor(mContext.getResources().getColor(R.color.colorWhite));
                holder.tvDateNum.setBackgroundResource(R.drawable.sign_red_shape);
                holder.tvDateType.setText(mContext.getResources().getString(R.string.sickleave));
                holder.tvDateType.setTextColor(mContext.getResources().getColor(R.color.colorSignRed));
            }
            //周六 周日
            if (mlist.get(position).getType().equals("")) {
                holder.tvDateNum.setTextColor(mContext.getResources().getColor(R.color.colorFontGray));
                holder.tvDateNum.setBackgroundResource(R.color.colorWhite);
                holder.tvDateType.setText("");
            }

            if (mlist.get(position).getType().equals("future")) {
                holder.tvDateNum.setTextColor(mContext.getResources().getColor(R.color.colorFontGray));
                holder.tvDateNum.setBackgroundResource(R.color.colorWhite);
                holder.tvDateType.setText("");
            }

            if (mlist.get(position).getDate().equals(simpleDateFormat.format(new Date()))) {
                String str = mContext.getResources().getString(R.string.today) + holder.tvDateType.getText();
                holder.tvDateType.setText(str);
            }

            if (position == selectPosition) {
                if(!mlist.get(position).getType().equals("EMPTY")){
                    holder.tvDateNum.setTextColor(mContext.getResources().getColor(R.color.colorWhite));
                    holder.tvDateNum.setBackgroundResource(R.drawable.sign_yellow_shape);
                    holder.tvDateType.setTextColor(mContext.getResources().getColor(R.color.colorMainYellow));
                }

            }

            holder.tvDateNum.setText("" + (position - mSize + 1));

            holder.llItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mlistener!=null){
                        if(!mlist.get(position).getType().equals("EMPTY")){
                            if(mlist.get(position).getType().equals("future")){
                                mlistener.onItemError();
                            }else {
                                selectPosition=position;
                                mlistener.onItemClick(position,mlist.get(position).getDate());
                            }
                        }

                    }
                }
            });

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
        @BindView(R.id.ll_item)
        LinearLayout llItem;
        public BabySignViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface IBabySignItemClickListener {
        void onItemClick(int position,String date);
        void onItemError();
    }


}
