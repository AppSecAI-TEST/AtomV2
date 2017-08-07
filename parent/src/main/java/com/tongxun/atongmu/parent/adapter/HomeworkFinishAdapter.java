package com.tongxun.atongmu.parent.adapter;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tongxun.atongmu.parent.R;
import com.tongxun.atongmu.parent.model.FinishWorkModel;
import com.tongxun.atongmu.parent.model.HomeFinishListModel;
import com.tongxun.atongmu.parent.ui.homework.IHomeworkFinishListener;
import com.tongxun.atongmu.parent.util.GlideOption;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Anro on 2017/8/1.
 */

public class HomeworkFinishAdapter extends BaseExpandableListAdapter {



    private Context mContext;
    private List<HomeFinishListModel> mGroupList;
    private IHomeworkFinishListener mListener;

    private AnimationDrawable voiceAnimation = null;



    public HomeworkFinishAdapter(Context context, List<HomeFinishListModel> groupArray, IHomeworkFinishListener listener) {
        mGroupList = groupArray;
        mContext = context;
        mListener = listener;
    }

    @Override
    public int getGroupCount() {
        return mGroupList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return mGroupList.get(groupPosition).getWorkModelList().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mGroupList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return mGroupList.get(childPosition).getWorkModelList().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_finish_homework_layout, parent, false);
            holder = new GroupHolder();
            holder.mMonth = (TextView) convertView.findViewById(R.id.id_month);
            convertView.setTag(holder);
        } else {
            holder = (GroupHolder) convertView.getTag();
        }
        holder.mMonth.setText(mGroupList.get(groupPosition).getDate());
        return convertView;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        FinishWorkModel model = mGroupList.get(groupPosition).getWorkModelList().get(childPosition);
        ViewHolder holder = null;

        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_month_homework_layout, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.tvHomeworkName.setText(model.getStudentName());
        Glide.with(mContext).load(model.getStudentImage()).apply(GlideOption.getFaceHolderOption()).into(holder.civHomeworkFace);
        holder.tvHomeworkTime.setText(model.getFinishTime());
        if (model.getHaveVoice().equals("true")) {
            holder.llAudio.setVisibility(View.VISIBLE);
            holder.tvAudioTime.setText(model.getVoiceLength() + "秒");
        } else {
            holder.llAudio.setVisibility(View.GONE);
        }

        if (model.getHaveVideo().equals("true")) {
            holder.niceHomeworkVideo.setVisibility(View.VISIBLE);
            Glide.with(mContext).load(model.getVideoImg()).apply(GlideOption.getPHOption()).into(holder.ivVideoImg);
        } else {
            holder.niceHomeworkVideo.setVisibility(View.GONE);
        }

        if (TextUtils.isEmpty(model.getFinishRemarks())) {
            holder.tvHomeworkContent.setVisibility(View.GONE);
        } else {
            holder.tvHomeworkContent.setVisibility(View.VISIBLE);
            holder.tvHomeworkContent.setText(model.getFinishRemarks());
        }
        if (TextUtils.isEmpty(model.getCommentTeacher())) {
            holder.llComment.setVisibility(View.GONE);
        } else {
            holder.llComment.setVisibility(View.VISIBLE);
            holder.tvCommentFlower.setText(model.getFlower() + "朵红花");
            if (TextUtils.isEmpty(model.getCommentMedia())) {
                holder.llCommentAudio.setVisibility(View.GONE);
            } else {
                holder.llCommentAudio.setVisibility(View.VISIBLE);
                holder.tvCommentAudioTime.setText(model.getCommentMediaLength() + "秒");
            }
            holder.tvCommentTeacher.setText(model.getCommentTeacher()+":");
            holder.tvCommentContent.setText(model.getCommentRate());
        }


        holder.ivShareHomework.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onShare(groupPosition, childPosition);
                }
            }
        });

        holder.ivDeleteHomework.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onDelete(groupPosition, childPosition);
            }
        });
        final ViewHolder finalHolder = holder;
        holder.llAudio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onPlayAudio(groupPosition, childPosition,mGroupList.get(groupPosition).getWorkModelList().get(childPosition).getVoiceUrl(), finalHolder.ivVoiceAnim);
            }
        });
        holder.niceHomeworkVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onPlayVideo(groupPosition, childPosition);
            }
        });
        holder.llCommentAudio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onPlayCommentAudio(groupPosition, childPosition,mGroupList.get(groupPosition).getWorkModelList().get(childPosition).getCommentMedia(),finalHolder.ivCommentVoiceAnim);
            }
        });
        holder.tvLookDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    class GroupHolder {
        public TextView mMonth;
    }

    static class ViewHolder {
        @BindView(R.id.civ_homework_face)
        CircleImageView civHomeworkFace;
        @BindView(R.id.tv_homework_name)
        TextView tvHomeworkName;
        @BindView(R.id.tv_homework_time)
        TextView tvHomeworkTime;
        @BindView(R.id.iv_share_homework)
        ImageView ivShareHomework;
        @BindView(R.id.iv_delete_homework)
        ImageView ivDeleteHomework;
        @BindView(R.id.iv_voice_anim)
        ImageView ivVoiceAnim;
        @BindView(R.id.tv_audio_time)
        TextView tvAudioTime;
        @BindView(R.id.ll_audio)
        LinearLayout llAudio;
        @BindView(R.id.tv_homework_content)
        TextView tvHomeworkContent;
        @BindView(R.id.iv_video_img)
        ImageView ivVideoImg;
        @BindView(R.id.nice_homework_video)
        RelativeLayout niceHomeworkVideo;
        @BindView(R.id.rv_homework_photo)
        RecyclerView rvHomeworkPhoto;
        @BindView(R.id.tv_comment_flower)
        TextView tvCommentFlower;
        @BindView(R.id.iv_comment_voice_anim)
        ImageView ivCommentVoiceAnim;
        @BindView(R.id.tv_comment_audio_time)
        TextView tvCommentAudioTime;
        @BindView(R.id.ll_comment_audio)
        LinearLayout llCommentAudio;
        @BindView(R.id.tv_comment_content)
        TextView tvCommentContent;
        @BindView(R.id.ll_comment)
        LinearLayout llComment;
        @BindView(R.id.tv_look_detail)
        TextView tvLookDetail;
        @BindView(R.id.tv_comment_teacher)
        TextView tvCommentTeacher;
        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
