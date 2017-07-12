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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tongxun.atongmu.parent.R;
import com.tongxun.atongmu.parent.model.HomeworkNoFinishModel;
import com.tongxun.atongmu.parent.ui.homework.IHomeworkNoFinishListener;
import com.tongxun.atongmu.parent.util.DensityUtil;
import com.tongxun.atongmu.parent.util.GlideOption;
import com.tongxun.atongmu.parent.util.ScreenUtils;
import com.xiao.nicevideoplayer.NiceVideoPlayer;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Anro on 2017/7/5.
 */

public class HomeworkNoFinishAdpater extends RecyclerView.Adapter<HomeworkNoFinishAdpater.NoFinishViewHolder> {


    private Context mContext;
    private List<HomeworkNoFinishModel> mlist = new ArrayList<>();

    private FriendCirclePhotoAdapter photoAdapter;

    private static IHomeworkNoFinishListener mlistener;

    public HomeworkNoFinishAdpater(Context context, List<HomeworkNoFinishModel> datas) {
        mContext = context;
        mlist = datas;
    }

    public static void setListener(IHomeworkNoFinishListener listener){
        mlistener=listener;
    }

    @Override
    public NoFinishViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_homework_nofinish, parent, false);
        NoFinishViewHolder viewHolder = new NoFinishViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(NoFinishViewHolder holder, final int position) {
        holder.tvHomeworkName.setText(mlist.get(position).getTeaName());
        Glide.with(mContext)
                .load(mlist.get(position).getTeaImg())
                .apply(GlideOption.getPHOption())
                .into(holder.civHomeworkFace);
        holder.tvHomeworkTime.setText(mlist.get(position).getCreateDate());
      /*  if (mlist.get(position).getHaveVoice().equals("true")) {
            holder.llAudio.setVisibility(View.VISIBLE);
            holder.tvAudioTime.setText(mlist.get(position).getVoiceLength());
        } else {
            holder.llAudio.setVisibility(View.GONE);
        }*/

        if (mlist.get(position).getHaveVideo().equals("true")) {
            holder.niceHomeworkVideo.setVisibility(View.VISIBLE);
        } else {
            holder.niceHomeworkVideo.setVisibility(View.GONE);
        }

        if (TextUtils.isEmpty(mlist.get(position).getContent())) {
            holder.tvHomeworkContent.setVisibility(View.GONE);
        } else {
            holder.tvHomeworkContent.setVisibility(View.VISIBLE);
            holder.tvHomeworkContent.setText(mlist.get(position).getContent());
        }

        photoAdapter = new FriendCirclePhotoAdapter(mContext, mlist.get(position).getPhotoList());

        ViewGroup.LayoutParams layoutParams = holder.rvHomeworkPhoto.getLayoutParams();
        holder.rvHomeworkPhoto.setItemAnimator(new DefaultItemAnimator());
        int size = 0;
        if (mlist.get(position).getPhotoSize() == 1) {
            holder.rvHomeworkPhoto.setLayoutManager(new GridLayoutManager(mContext, 1));
            layoutParams.height = (ScreenUtils.getScreenWidth() / 3) * 2;
        } else if (mlist.get(position).getPhotoSize() == 2) {
            holder.rvHomeworkPhoto.setLayoutManager(new GridLayoutManager(mContext, 2));
            layoutParams.height = (ScreenUtils.getScreenWidth() - DensityUtil.dip2px(mContext, 28)) / 2;
        } else if (mlist.get(position).getPhotoSize() == 4) {
            holder.rvHomeworkPhoto.setLayoutManager(new GridLayoutManager(mContext, 2));
            layoutParams.height = ScreenUtils.getScreenWidth() - DensityUtil.dip2px(mContext, 28);
        } else {
            if (mlist.get(position).getPhotoSize() % 3 == 0) {
                size = mlist.get(position).getPhotoSize() / 3;
            } else {
                size = mlist.get(position).getPhotoSize() / 3 + 1;
            }
            layoutParams.height = ((ScreenUtils.getScreenWidth() - DensityUtil.dip2px(mContext, 28)) / 3) * size;
            holder.rvHomeworkPhoto.setLayoutManager(new GridLayoutManager(mContext, 3));
        }
        holder.rvHomeworkPhoto.setLayoutParams(layoutParams);
        holder.rvHomeworkPhoto.setAdapter(photoAdapter);
        /**
         * 去完成作业
         */
        holder.ivFinishHomework.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mlistener!=null){
                    mlistener.goComplete(mlist.get(position).getJobId());
                }
            }
        });

        holder.llAudio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mlistener!=null){
                    mlistener.playAudio(position);
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return mlist.size();
    }

   public   class NoFinishViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.civ_homework_face)
        CircleImageView civHomeworkFace;
        @BindView(R.id.tv_homework_name)
        TextView tvHomeworkName;
        @BindView(R.id.tv_homework_time)
        TextView tvHomeworkTime;
        @BindView(R.id.tv_audio_time)
        TextView tvAudioTime;
        @BindView(R.id.ll_audio)
        LinearLayout llAudio;
        @BindView(R.id.tv_homework_content)
        TextView tvHomeworkContent;
        @BindView(R.id.nice_homework_video)
        NiceVideoPlayer niceHomeworkVideo;
        @BindView(R.id.rv_homework_photo)
        RecyclerView rvHomeworkPhoto;
        @BindView(R.id.iv_finish_homework)
        ImageView ivFinishHomework;

        public NoFinishViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


}
