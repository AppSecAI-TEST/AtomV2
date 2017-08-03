package com.tongxun.atongmu.teacher.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tongxun.atongmu.teacher.IonItemClickListener;
import com.tongxun.atongmu.teacher.R;
import com.tongxun.atongmu.teacher.model.DynamicModel;
import com.tongxun.atongmu.teacher.util.GlideOption;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Anro on 2017/8/3.
 */

public class DynamicAdapter extends RecyclerView.Adapter<DynamicAdapter.DynamicViewHolder> {



    private List<DynamicModel> mlist = new ArrayList<>();
    private Context mContext;
    private IonItemClickListener mListener;

    public DynamicAdapter(Context context, List<DynamicModel> list,IonItemClickListener listener) {
        mlist = list;
        mContext = context;
        mListener=listener;
    }

    @Override
    public DynamicViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_dynamic_layout, parent, false);
        DynamicViewHolder holder = new DynamicViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(DynamicViewHolder holder, final int position) {
        holder.dynamicItemTitle.setText(mlist.get(position).getTitle());
        holder.dynamicItemTime.setText(mlist.get(position).getCreateDate());
        holder.dynamicItemName.setText(mlist.get(position).getTeacherName());
        Glide.with(mContext)
                .load(mlist.get(position).getPhotoMin())
                .apply(GlideOption.getImageHolderOption())
                .into(holder.dynamicItemImg);
        if (mlist.get(position).getRecType().equals("活动")) {
            if (mlist.get(position).getCheckStatus().equals("0")) {
                holder.dynamicItemStatus.setImageResource(R.drawable.huodong_uncheck);
            } else if (mlist.get(position).getCheckStatus().equals("2")) {
                holder.dynamicItemStatus.setImageResource(R.drawable.huodong_check);
            }
        } else if (mlist.get(position).getRecType().equals("通知")) {
            if (mlist.get(position).getCheckStatus().equals("0")) {
                holder.dynamicItemStatus.setImageResource(R.drawable.tongzhi_uncheck);
            } else if (mlist.get(position).getCheckStatus().equals("2")) {
                holder.dynamicItemStatus.setImageResource(R.drawable.tongzhi_check);
            }
        }

        holder.llItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mListener!=null){
                    if(!mlist.get(position).getCheckStatus().equals("2")){
                        mListener.onItemClick(position);
                    }

                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    class DynamicViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.dynamic_item_img)
        ImageView dynamicItemImg;
        @BindView(R.id.dynamic_item_title)
        TextView dynamicItemTitle;
        @BindView(R.id.dynamic_item_time)
        TextView dynamicItemTime;
        @BindView(R.id.dynamic_item_name)
        TextView dynamicItemName;
        @BindView(R.id.dynamic_item_status)
        ImageView dynamicItemStatus;
        @BindView(R.id.ll_item)
        LinearLayout llItem;
        public DynamicViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
