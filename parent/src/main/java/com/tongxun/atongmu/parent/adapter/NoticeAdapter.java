package com.tongxun.atongmu.parent.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tongxun.atongmu.parent.IonItemClickListener;
import com.tongxun.atongmu.parent.R;
import com.tongxun.atongmu.parent.model.NoticeModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by admin on 2017/7/1.
 */

public class NoticeAdapter extends RecyclerView.Adapter<NoticeAdapter.NoticeViewHolder> {


    private Context mContext;
    private List<NoticeModel> mlist;
    private IonItemClickListener mlistener=null;


    public NoticeAdapter(Context context, List<NoticeModel> list) {
        mContext = context;
        mlist = list;
    }

    @Override
    public NoticeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_notice_layout, parent, false);
        NoticeViewHolder viewHolder = new NoticeViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(NoticeViewHolder viewHolder, final int position) {
        Glide.with(mContext).load(mlist.get(position).getPhotoMin()).into(viewHolder.ivItemNotice);
        viewHolder.tvItemTitle.setText(mlist.get(position).getTitle());
        viewHolder.tvItemTime.setText(mlist.get(position).getCreateDate());
        if (mlist.get(position).getNoRead().endsWith("true")) {
            viewHolder.ivItemStatus.setVisibility(View.VISIBLE);
        } else {
            viewHolder.ivItemStatus.setVisibility(View.INVISIBLE);
        }

        viewHolder.rlItemNotice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mlistener!=null){
                    mlistener.onItemClick(position);
                }
            }
        });

    }

    public void setItemClickListener(IonItemClickListener listener){
        mlistener=listener;
    }




    @Override
    public int getItemCount() {
        return mlist.size();
    }

    static class NoticeViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_item_notice)
        ImageView ivItemNotice;
        @BindView(R.id.tv_item_title)
        TextView tvItemTitle;
        @BindView(R.id.tv_item_time)
        TextView tvItemTime;
        @BindView(R.id.iv_item_status)
        ImageView ivItemStatus;
        @BindView(R.id.rl_item_notice)
        RelativeLayout rlItemNotice;

        public NoticeViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


}
