package com.tongxun.atongmu.parent.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tongxun.atongmu.parent.R;
import com.tongxun.atongmu.parent.model.FriendCircleModel;
import com.tongxun.atongmu.parent.model.FriendCirlceVoteModel;
import com.tongxun.atongmu.parent.ui.classcircle.ICircleListener;
import com.tongxun.atongmu.parent.util.GlideOption;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Anro on 2017/7/6.
 */

public class FriendCircleAdapter extends RecyclerView.Adapter<FriendCircleAdapter.FriendCircleViewHolder> {



    private List<FriendCircleModel> mlist = new ArrayList<>();

    private static ICircleListener mlistener;
    private Context mContext;

    public FriendCircleAdapter(Context context, List<FriendCircleModel> list) {
        mContext = context;
        mlist = list;
    }

    public static void setListener(ICircleListener listener) {
        mlistener = listener;
    }

    @Override
    public FriendCircleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_friend_circle_layout, parent, false);
        FriendCircleViewHolder viewHolder = new FriendCircleViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(FriendCircleViewHolder holder, final int position) {
        holder.tvItemTeacherName.setText(mlist.get(position).getPersonName());
        holder.tvItemTime.setText(mlist.get(position).getCreateDate());
        if (TextUtils.isEmpty(mlist.get(position).getContext())) {
            holder.tvItemContent.setVisibility(View.GONE);
        } else {
            holder.tvItemContent.setVisibility(View.VISIBLE);
            holder.tvItemContent.setText(mlist.get(position).getContext());
        }
        Glide.with(mContext).load(mlist.get(position).getPersonPhoto()).apply(GlideOption.getPHOption()).into(holder.civItemTeacherFace);
        holder.tvBrowse.setText(mContext.getResources().getString(R.string.browse_size) + mlist.get(position).getReadQty());
        holder.tvShare.setText(mlist.get(position).getShareQty());
        //点赞
        if (mlist.get(position).getVoteSum() > 0) {
            holder.tvVotePerson.setVisibility(View.VISIBLE);
            if (mlist.get(position).isCurrentPersonVote()) {
                holder.tvVote.setSelected(true);
            } else {
                holder.tvVote.setSelected(false);
            }
            String likestr = "";
            for (FriendCirlceVoteModel voteModel : mlist.get(position).getVotePersons()) {
                likestr += voteModel.getVoteNickName() + "、";
            }
            likestr = likestr.substring(0, likestr.length() - 1);
            holder.tvVotePerson.setText(likestr);
        } else {
            holder.tvVote.setSelected(false);
            holder.tvVotePerson.setVisibility(View.GONE);
        }

        //评论
        if (mlist.get(position).getCommentPersons().size() > 0) {
            holder.tvRemarkPerson.setVisibility(View.VISIBLE);
            holder.tvRemarkPerson.setText(mlist.get(position).getCommentPersons().size() + mContext.getResources().getString(R.string.num_remark));
        } else {
            holder.tvRemarkPerson.setVisibility(View.GONE);
        }

        holder.tvVote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mlistener != null) {
                    mlistener.vote(position);
                }
            }
        });

        switch (mlist.get(position).getBodyType()) {
            case "0"://纯文本
                holder.ivOnePicture.setVisibility(View.GONE);
                holder.rlVideoLayout.setVisibility(View.GONE);
                holder.rvPhotoList.setVisibility(View.GONE);
                break;
            case "1"://图文的时候
                holder.rlVideoLayout.setVisibility(View.GONE);
                if(mlist.get(position).getPhotos().size()==1){
                    holder.ivOnePicture.setVisibility(View.VISIBLE);
                    holder.rvPhotoList.setVisibility(View.GONE);
                    Glide.with(mContext).load(mlist.get(position).getPhotos().get(0).getPhoto()).apply(GlideOption.getPHOption()).into(holder.ivOnePicture);
                }else {
                    holder.ivOnePicture.setVisibility(View.GONE);
                    holder.rvPhotoList.setVisibility(View.VISIBLE);
                    if(mlist.get(position).getPhotos().size()==2 ||mlist.get(position).getPhotos().size()==4){

                    }else {

                    }
                }
                break;
            case "2":
                holder.ivOnePicture.setVisibility(View.GONE);
                holder.rlVideoLayout.setVisibility(View.VISIBLE);
                holder.rvPhotoList.setVisibility(View.GONE);
                break;
        }


    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }


    @OnClick({R.id.iv_one_picture, R.id.tv_share, R.id.tv_vote, R.id.tv_remark, R.id.cirlce_comment_more})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_one_picture:
                break;
            case R.id.tv_share:
                break;
            case R.id.tv_vote:

                break;
            case R.id.tv_remark:
                break;
            case R.id.cirlce_comment_more:
                break;
        }
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
        @BindView(R.id.rl_video_layout)
        RelativeLayout rlVideoLayout;
        @BindView(R.id.rv_photo_list)
        RecyclerView rvPhotoList;
        @BindView(R.id.tv_browse)
        TextView tvBrowse;
        @BindView(R.id.tv_share)
        TextView tvShare;
        @BindView(R.id.tv_vote)
        TextView tvVote;
        @BindView(R.id.tv_remark)
        TextView tvRemark;
        @BindView(R.id.tv_vote_person)
        TextView tvVotePerson;
        @BindView(R.id.tv_remark_person)
        TextView tvRemarkPerson;
        @BindView(R.id.cirlce_comment_more)
        TextView cirlceCommentMore;

        public FriendCircleViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
