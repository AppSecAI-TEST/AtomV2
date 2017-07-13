package com.tongxun.atongmu.parent.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tongxun.atongmu.parent.R;
import com.tongxun.atongmu.parent.model.VideoListModel;
import com.tongxun.atongmu.parent.util.SDCardUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Anro on 2017/7/13.
 */

public class VideoListAdapter extends RecyclerView.Adapter<VideoListAdapter.VideoListHolder> {


    private Context mContext;

    private List<VideoListModel> mlist = new ArrayList<>();

    private videoListListener mlistener=null;

    public VideoListAdapter(Context context, List<VideoListModel> list,videoListListener listener) {
        mContext = context;
        mlist = list;
        mlistener=listener;
    }

    @Override
    public VideoListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_video_list, parent, false);
        VideoListHolder holder = new VideoListHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(VideoListHolder holder, final int position) {
        File f=new File(SDCardUtil.getInstance().getFilePath()+mlist.get(position).getDeviceId()+mlist.get(position).getChannelNo()+".jpg");
        if(f.exists()){
            Glide.with(mContext).load(f).into(holder.ivMoviePic);
            holder.ivMoviePlay.setVisibility(View.INVISIBLE);
        }else {
            holder.ivMoviePic.setImageResource(R.drawable.nomovie);
            holder.ivMoviePlay.setVisibility(View.VISIBLE);
        }
        holder.tvCameraName.setText(mlist.get(position).getDeviceName());
        holder.tvCameraTime.setText(mContext.getResources().getString(R.string.time_video_live)+mlist.get(position).getStartTime()+"-"+mlist.get(position).getEndTime());
        holder.ivMoviePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mlistener!=null){
                    mlistener.onClick(mlist.get(position).getDeviceId(),
                            mlist.get(position).getChannelNo(),
                            mlist.get(position).getStartTime(),
                            mlist.get(position).getEndTime(),
                            mlist.get(position).getVerificationCode(),
                            mlist.get(position).getVideoClarity());
                }
            }
        });
    }



    @Override
    public int getItemCount() {
        return mlist.size();
    }

    class VideoListHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_movie_pic)
        ImageView ivMoviePic;
        @BindView(R.id.iv_movie_play)
        ImageView ivMoviePlay;
        @BindView(R.id.tv_camera_name)
        TextView tvCameraName;
        @BindView(R.id.tv_camera_time)
        TextView tvCameraTime;
        public VideoListHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    public interface videoListListener{
        void onClick(String deviceId,String channelNo,String startTime,String endTime,String verificitionCode,String videoClarity);
    }
}
