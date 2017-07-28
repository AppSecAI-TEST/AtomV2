package com.tongxun.atongmu.parent.adapter;

import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tongxun.atongmu.parent.IonItemClickListener;
import com.tongxun.atongmu.parent.R;
import com.tongxun.atongmu.parent.model.AlbumFromDateModel;
import com.tongxun.atongmu.parent.util.DensityUtil;
import com.tongxun.atongmu.parent.util.ScreenUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Anro on 2017/7/13.
 */

public class AlbumFromDateAdapter extends RecyclerView.Adapter<AlbumFromDateAdapter.AlbumDateViewHolder> {


    private Context mContext;
    private List<AlbumFromDateModel> mlist = new ArrayList<>();

    private FriendCirclePhotoAdapter photoAdapter;

    public AlbumFromDateAdapter(Context context, List<AlbumFromDateModel> list) {
        mContext = context;
        mlist = list;
    }

    @Override
    public AlbumDateViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_album_from_date, parent, false);
        AlbumDateViewHolder viewHolder = new AlbumDateViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(AlbumDateViewHolder holder, int position) {
        holder.tvDate.setText(mlist.get(position).getDate());
        holder.tvTime.setText(mlist.get(position).getTime());
        if(TextUtils.isEmpty(mlist.get(position).getRemarks())){
            holder.tvContent.setVisibility(View.GONE);
        }else {
            holder.tvContent.setVisibility(View.VISIBLE);
            holder.tvContent.setText(mlist.get(position).getRemarks());
        }

        photoAdapter = new FriendCirclePhotoAdapter(mContext, mlist.get(position).getPhotos(), new IonItemClickListener() {
            @Override
            public void onItemClick(int position) {
                
            }
        });
        ViewGroup.LayoutParams layoutParams = holder.rvAlbumDate.getLayoutParams();
        holder.rvAlbumDate.setItemAnimator(new DefaultItemAnimator());
        int size=0;
        if(mlist.get(position).getPhotos().size()==1){
            holder.rvAlbumDate.setLayoutManager(new GridLayoutManager(mContext, 1));
            layoutParams.height =((ScreenUtils.getScreenWidth()- DensityUtil.dip2px(mContext,28)) / 3) * 2+DensityUtil.dip2px(mContext,20);
        }else if(mlist.get(position).getPhotos().size()==2){
            holder.rvAlbumDate.setLayoutManager(new GridLayoutManager(mContext, 2));
            layoutParams.height = (ScreenUtils.getScreenWidth() - DensityUtil.dip2px(mContext, 28)) / 2+DensityUtil.dip2px(mContext,20);
        }else if(mlist.get(position).getPhotos().size() < 10){
            if (mlist.get(position).getPhotos().size() % 3 == 0) {
                size = mlist.get(position).getPhotos().size() / 3;
            } else {
                size = mlist.get(position).getPhotos().size() / 3 + 1;
            }
            layoutParams.height = ((ScreenUtils.getScreenWidth()- DensityUtil.dip2px(mContext,28)) / 3) * size+DensityUtil.dip2px(mContext,20);
            holder.rvAlbumDate.setLayoutManager(new GridLayoutManager(mContext, 3));
        }else {
            holder.rvAlbumDate.setLayoutManager(new GridLayoutManager(mContext, 3));
            layoutParams.height = ScreenUtils.getScreenWidth() - DensityUtil.dip2px(mContext, 28)+DensityUtil.dip2px(mContext,20);
        }

        holder.rvAlbumDate.setLayoutParams(layoutParams);
        holder.rvAlbumDate.setAdapter(photoAdapter);

    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    class AlbumDateViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_date)
        TextView tvDate;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.tv_content)
        TextView tvContent;
        @BindView(R.id.rv_album_date)
        RecyclerView rvAlbumDate;

        public AlbumDateViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
