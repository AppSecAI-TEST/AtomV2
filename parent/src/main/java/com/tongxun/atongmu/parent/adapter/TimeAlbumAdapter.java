package com.tongxun.atongmu.parent.adapter;

import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tongxun.atongmu.parent.R;
import com.tongxun.atongmu.parent.model.TimeAlbumModel;
import com.tongxun.atongmu.parent.util.DensityUtil;
import com.tongxun.atongmu.parent.util.ScreenUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Anro on 2017/7/12.
 */

public class TimeAlbumAdapter extends RecyclerView.Adapter<TimeAlbumAdapter.TimeAlbumViewHolder> {


    private Context mContext;
    private List<TimeAlbumModel> mlist = new ArrayList<>();

    private FriendCirclePhotoAdapter photoAdapter;
    private ITimeAlbumListener mlistener;

    public TimeAlbumAdapter(Context context, List<TimeAlbumModel> list,ITimeAlbumListener listener) {
        mContext = context;
        mlist = list;
        mlistener=listener;
    }

    @Override
    public TimeAlbumViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_time_album, parent, false);
        TimeAlbumViewHolder viewHolder = new TimeAlbumViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(TimeAlbumViewHolder holder, final int position) {
        holder.tvAlbumName.setText(mlist.get(position).getMonth());
        holder.tvAlbumSize.setText(mlist.get(position).getMonthNum());

        photoAdapter = new FriendCirclePhotoAdapter(mContext, mlist.get(position).getPhotos());
        ViewGroup.LayoutParams layoutParams = holder.rvAlbumImage.getLayoutParams();
        holder.rvAlbumImage.setItemAnimator(new DefaultItemAnimator());
        int size=0;
        if(mlist.get(position).getPhotos().size()==1){
            holder.rvAlbumImage.setLayoutManager(new GridLayoutManager(mContext, 1));
            layoutParams.height =((ScreenUtils.getScreenWidth()- DensityUtil.dip2px(mContext,28)) / 3) * 2+DensityUtil.dip2px(mContext,20);
        }else if(mlist.get(position).getPhotos().size()==2){
            holder.rvAlbumImage.setLayoutManager(new GridLayoutManager(mContext, 2));
            layoutParams.height = (ScreenUtils.getScreenWidth() - DensityUtil.dip2px(mContext, 28)) / 2+DensityUtil.dip2px(mContext,20);
        }else if(mlist.get(position).getPhotos().size()==3){
            holder.rvAlbumImage.setLayoutManager(new GridLayoutManager(mContext, 3));
            layoutParams.height = (ScreenUtils.getScreenWidth() - DensityUtil.dip2px(mContext, 28)) / 3+DensityUtil.dip2px(mContext,20);
        }else {
            if (mlist.get(position).getPhotos().size() % 4 == 0) {
                size = mlist.get(position).getPhotos().size() / 4;
            } else {
                size = mlist.get(position).getPhotos().size() / 4 + 1;
            }
            layoutParams.height = ((ScreenUtils.getScreenWidth() - DensityUtil.dip2px(mContext, 28)) / 4) * size+DensityUtil.dip2px(mContext,20);
            holder.rvAlbumImage.setLayoutManager(new GridLayoutManager(mContext, 4));
        }
        holder.rvAlbumImage.setLayoutParams(layoutParams);
        holder.rvAlbumImage.setAdapter(photoAdapter);

        holder.rlTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mlistener!=null){
                    if(position==0){
                        mlistener.goMornCheckAlbum();
                    }else {
                        mlistener.goAlbumFromDate(mlist.get(position).getMonth());
                    }

                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    @OnClick(R.id.rl_title)
    public void onViewClicked() {
    }

    class TimeAlbumViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_album_name)
        TextView tvAlbumName;
        @BindView(R.id.tv_album_size)
        TextView tvAlbumSize;
        @BindView(R.id.rv_album_image)
        RecyclerView rvAlbumImage;
        @BindView(R.id.rl_title)
        RelativeLayout rlTitle;

        public TimeAlbumViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    public interface ITimeAlbumListener{
        void goAlbumFromDate(String date);

        void goMornCheckAlbum();
    }
}
