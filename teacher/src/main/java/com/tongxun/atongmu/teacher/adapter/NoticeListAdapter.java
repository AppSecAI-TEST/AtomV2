package com.tongxun.atongmu.teacher.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tongxun.atongmu.teacher.R;
import com.tongxun.atongmu.teacher.model.ActivityListModel;
import com.tongxun.atongmu.teacher.model.NewsListModel;
import com.tongxun.atongmu.teacher.model.NoticeListModel;
import com.tongxun.atongmu.teacher.ui.notice.INoticeListClickListener;
import com.tongxun.atongmu.teacher.util.GlideOption;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Anro on 2017/8/2.
 */

public class NoticeListAdapter extends RecyclerView.Adapter<NoticeListAdapter.NoticeListViewHolder> {


    private Context mContext;
    private List mList;
    private INoticeListClickListener mListener;

    public NoticeListAdapter(Context context, List list,INoticeListClickListener listener) {
        mContext = context;
        mList = list;
        mListener=listener;
    }

    @Override
    public NoticeListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_notice_list_layout, parent, false);
        NoticeListViewHolder viewHolder = new NoticeListViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(NoticeListViewHolder holder, final int position) {
        if (mList.get(position) instanceof NoticeListModel) {
            NoticeListModel model = (NoticeListModel) mList.get(position);
            Glide.with(mContext).load(model.getPhotoMin()).apply(GlideOption.getImageHolderOption()).into(holder.ivItemNotice);

            holder.tvItemTime.setText(model.getCreateDate());
            holder.tvItemTitle.setText(model.getTitle());
            if (model.isIsNewRecord()) {
                holder.ivItemStatus.setVisibility(View.VISIBLE);
            } else {
                holder.ivItemStatus.setVisibility(View.INVISIBLE);
            }


        } else if (mList.get(position) instanceof ActivityListModel) {
            ActivityListModel model = (ActivityListModel) mList.get(position);
            Glide.with(mContext).load(model.getPhotoMin()).apply(GlideOption.getImageHolderOption()).into(holder.ivItemNotice);

            holder.tvItemTime.setText(model.getCreateDate());
            holder.tvItemTitle.setText(model.getTitle());
            if (model.isIsNewRecord()) {
                holder.ivItemStatus.setVisibility(View.VISIBLE);
            } else {
                holder.ivItemStatus.setVisibility(View.INVISIBLE);
            }
        } else if (mList.get(position) instanceof NewsListModel) {
            NewsListModel model = (NewsListModel) mList.get(position);
            Glide.with(mContext).load(model.getPhotoMin()).apply(GlideOption.getImageHolderOption()).into(holder.ivItemNotice);

            holder.tvItemTime.setText(model.getCreateDate());
            holder.tvItemTitle.setText(model.getTitle());
            if (model.isIsNewRecord()) {
                holder.ivItemStatus.setVisibility(View.VISIBLE);
            } else {
                holder.ivItemStatus.setVisibility(View.INVISIBLE);
            }
        }

        holder.rlItemNotice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mListener!=null){
                    mListener.onNoticeNewsClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class NoticeListViewHolder extends RecyclerView.ViewHolder {
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
        public NoticeListViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
