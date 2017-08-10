package com.tongxun.atongmu.parent.adapter;

import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tongxun.atongmu.parent.IonItemClickListener;
import com.tongxun.atongmu.parent.R;
import com.tongxun.atongmu.parent.model.FriendCircleCommentModel;
import com.tongxun.atongmu.parent.model.FriendCircleModel;
import com.tongxun.atongmu.parent.model.FriendCirlceVoteModel;
import com.tongxun.atongmu.parent.ui.classcircle.ICircleListener;
import com.tongxun.atongmu.parent.util.AnroUtil;
import com.tongxun.atongmu.parent.util.DensityUtil;
import com.tongxun.atongmu.parent.util.GlideOption;
import com.tongxun.atongmu.parent.util.ScreenUtils;
import com.tongxun.atongmu.parent.widget.CircleCommentListView;

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

    private ICircleListener mlistener;

    private Context mContext;

    private FriendCirclePhotoAdapter photoAdapter;

    private CommentAdapter commentAdapter;

    public FriendCircleAdapter(Context context, List<FriendCircleModel> list) {
        mContext = context;
        mlist = list;
    }

    public void setListener(ICircleListener listener) {
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
            holder.tvItemContent.setText(AnroUtil.unicode2String(mlist.get(position).getContext()));

        }
        //item 头像
        Glide.with(mContext).load(mlist.get(position).getPersonPhoto()).apply(GlideOption.getImageHolderOption()).into(holder.civItemTeacherFace);
        holder.tvBrowse.setText(mContext.getResources().getString(R.string.browse_size) + mlist.get(position).getReadQty());
        if (!mlist.get(position).getShareQty().equals("0")) {
            holder.tvShare.setText(mlist.get(position).getShareQty());
        } else {
        }
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
            List<FriendCircleCommentModel> list=new ArrayList<>();
            if(mlist.get(position).getCommentPersons().size()>6){
                for (int i = 0; i < 6; i++) {
                    list.add(mlist.get(position).getCommentPersons().get(i));
                }
                holder.cirlceCommentMore.setVisibility(View.VISIBLE);
            }else {
                list.addAll(mlist.get(position).getCommentPersons());
                holder.cirlceCommentMore.setVisibility(View.GONE);
            }
            commentAdapter = new CommentAdapter(mContext, R.layout.circle_commet_item_layout, list);
            holder.circleCommentList.setAdapter(commentAdapter);
        } else {
            holder.tvRemarkPerson.setVisibility(View.GONE);
        }




        photoAdapter = new FriendCirclePhotoAdapter(mContext, mlist.get(position).getPhotos(), new IonItemClickListener() {
            @Override
            public void onItemClick(int pos) {
                mlistener.onPhotoClick(position, pos);
            }
        });
        holder.rvPhotoList.setItemAnimator(new DefaultItemAnimator());
        ViewGroup.LayoutParams layoutParams = holder.rvPhotoList.getLayoutParams();
        switch (mlist.get(position).getBodyType()) {
            case "0"://纯文本
                holder.niceCircleVideo.setVisibility(View.GONE);
                layoutParams.height = 0;
                break;
            case "1"://图文的时候
                holder.niceCircleVideo.setVisibility(View.GONE);
                int size = 0;
                if (mlist.get(position).getPhotos().size() == 1) {
                    holder.rvPhotoList.setLayoutManager(new GridLayoutManager(mContext, 1));
                    layoutParams.height = (ScreenUtils.getScreenWidth() / 3) * 2;
                } else if (mlist.get(position).getPhotos().size() == 2 || mlist.get(position).getPhotos().size() == 4) {
                    holder.rvPhotoList.setLayoutManager(new GridLayoutManager(mContext, 2));
                    if (mlist.get(position).getPhotos().size() == 2) {
                        layoutParams.height = ScreenUtils.getScreenWidth() / 2;
                    } else {
                        layoutParams.height = ScreenUtils.getScreenWidth();
                    }
                } else if (mlist.get(position).getPhotos().size() < 10) {
                    if (mlist.get(position).getPhotos().size() % 3 == 0) {
                        size = mlist.get(position).getPhotos().size() / 3;
                    } else {
                        size = mlist.get(position).getPhotos().size() / 3 + 1;
                    }
                    layoutParams.height = (ScreenUtils.getScreenWidth() / 3) * size;
                    holder.rvPhotoList.setLayoutManager(new GridLayoutManager(mContext, 3));
                } else {
                    holder.rvPhotoList.setLayoutManager(new GridLayoutManager(mContext, 3));
                    layoutParams.height = ScreenUtils.getScreenWidth() - DensityUtil.dip2px(mContext, 2);
                }
                break;
            case "2":
                layoutParams.height = 0;
                holder.niceCircleVideo.setVisibility(View.VISIBLE);
                Glide.with(mContext).load(mlist.get(position).getMediaImg()).apply(GlideOption.getPHOption()).into(holder.ivVideoImg);
                break;
        }
        holder.rvPhotoList.setLayoutParams(layoutParams);
        holder.rvPhotoList.setAdapter(photoAdapter);




        holder.tvVote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mlistener != null) {
                    mlistener.vote(position);
                }
            }
        });
        holder.tvShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mlistener != null) {
                    mlistener.share(position);
                }
            }
        });

        holder.tvRemark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mlistener != null) {
                    mlistener.remark(position);
                }
            }
        });

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
        @BindView(R.id.iv_video_img)
        ImageView ivVideoImg;
        @BindView(R.id.nice_circle_video)
        RelativeLayout niceCircleVideo;
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
        @BindView(R.id.circle_comment_list)
        CircleCommentListView circleCommentList;
        public FriendCircleViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
