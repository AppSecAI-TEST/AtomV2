package com.tongxun.atongmu.parent.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tongxun.atongmu.parent.R;
import com.tongxun.atongmu.parent.model.FriendCircleModel;
import com.tongxun.atongmu.parent.util.GlideOption;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Anro on 2017/7/6.
 */

public class FriendCircleAdapter extends RecyclerView.Adapter<FriendCircleAdapter.FriendCircleViewHolder> {



    private List<FriendCircleModel> mlist = new ArrayList<>();
    private Context mContext;

    public FriendCircleAdapter(Context context, List<FriendCircleModel> list) {
        mContext = context;
        mlist = list;
    }

    @Override
    public FriendCircleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_friend_circle_layout, parent, false);
        FriendCircleViewHolder viewHolder = new FriendCircleViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(FriendCircleViewHolder holder, int position) {
        holder.tvItemTeacherName.setText(mlist.get(position).getPersonName());
        holder.tvItemTime.setText(mlist.get(position).getCreateDate());
        if (TextUtils.isEmpty(mlist.get(position).getContext())) {
            holder.tvItemContent.setVisibility(View.GONE);
        } else {
            holder.tvItemContent.setVisibility(View.VISIBLE);
            holder.tvItemContent.setText(mlist.get(position).getContext());
        }
        Glide.with(mContext).load(mlist.get(position).getPersonPhoto()).apply(GlideOption.getPHOption()).into(holder.civItemTeacherFace);
        holder.tvBrowse.setText(mContext.getResources().getString(R.string.browse_size)+mlist.get(position).getReadQty());
        holder.tvShare.setText(mlist.get(position).getShareQty());
        if(mlist.get(position).getVoteSum()>0){
            if(mlist.get(position).isCurrentPersonVote()){
                holder.tvShare.setSelected(true);
            }

        }else {

        }
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    class FriendCircleViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.civ_item_teacher_face)
        CircleImageView civItemTeacherFace;
        @BindView(R.id.tv_item_teacher_name)
        TextView tvItemTeacherName;
        @BindView(R.id.tv_item_time)
        TextView tvItemTime;
        @BindView(R.id.tv_item_content)
        TextView tvItemContent;
        @BindView(R.id.iv_one_picture)
        ImageView ivOnePicture;
        @BindView(R.id.tv_browse)
        TextView tvBrowse;
        @BindView(R.id.tv_share)
        TextView tvShare;
        @BindView(R.id.tv_vote)
        TextView tvVote;
        @BindView(R.id.tv_remark)
        TextView tvRemark;
        @BindView(R.id.cirlce_comment_more)
        TextView cirlceCommentMore;

        public FriendCircleViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
