package com.tongxun.atongmu.parent.adapter;

import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tongxun.atongmu.parent.IonItemClickListener;
import com.tongxun.atongmu.parent.R;
import com.tongxun.atongmu.parent.model.MornCheckModel;
import com.tongxun.atongmu.parent.ui.homework.IPhotoItemClickListener;
import com.tongxun.atongmu.parent.util.DensityUtil;
import com.tongxun.atongmu.parent.util.ScreenUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Anro on 2017/7/13.
 */

public class MornCheckAdapter extends RecyclerView.Adapter<MornCheckAdapter.MornCheckViewHolder> {


    private Context mContext;
    private List<MornCheckModel> mlist = new ArrayList<>();

    private FriendCirclePhotoAdapter photoAdapter;
    private IPhotoItemClickListener mlistener;


    public MornCheckAdapter(Context context, List<MornCheckModel> list,IPhotoItemClickListener listener) {
        mContext=context;
        mlist=list;
        mlistener=listener;
    }

    @Override
    public MornCheckViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_morn_check, parent, false);
        MornCheckViewHolder viewHolder = new MornCheckViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MornCheckViewHolder holder, final int position) {
        holder.tvDate.setText(mlist.get(position).getActMonth());
        photoAdapter = new FriendCirclePhotoAdapter(mContext, mlist.get(position).getMonthDate(), new IonItemClickListener() {
            @Override
            public void onItemClick(int pos) {
                if(mlistener!=null){
                    mlistener.onPhoto(position,pos);
                }
            }
        });
        ViewGroup.LayoutParams layoutParams = holder.rvPhotoList.getLayoutParams();
        holder.rvPhotoList.setItemAnimator(new DefaultItemAnimator());
        int size=0;
        if(mlist.get(position).getMonthDate().size()==1){
            holder.rvPhotoList.setLayoutManager(new GridLayoutManager(mContext, 1));
            layoutParams.height =((ScreenUtils.getScreenWidth()- DensityUtil.dip2px(mContext,28)) / 3) * 2+DensityUtil.dip2px(mContext,20);
        }else if(mlist.get(position).getMonthDate().size()==2){
            holder.rvPhotoList.setLayoutManager(new GridLayoutManager(mContext, 2));
            layoutParams.height = (ScreenUtils.getScreenWidth() - DensityUtil.dip2px(mContext, 28)) / 2+DensityUtil.dip2px(mContext,20);
        }else if(mlist.get(position).getMonthDate().size() < 10){
            if (mlist.get(position).getMonthDate().size() % 3 == 0) {
                size = mlist.get(position).getMonthDate().size() / 3;
            } else {
                size = mlist.get(position).getMonthDate().size() / 3 + 1;
            }
            layoutParams.height = ((ScreenUtils.getScreenWidth()- DensityUtil.dip2px(mContext,28)) / 3) * size+DensityUtil.dip2px(mContext,20);
            holder.rvPhotoList.setLayoutManager(new GridLayoutManager(mContext, 3));
        }else {
            holder.rvPhotoList.setLayoutManager(new GridLayoutManager(mContext, 3));
            layoutParams.height = ScreenUtils.getScreenWidth() - DensityUtil.dip2px(mContext, 28)+DensityUtil.dip2px(mContext,20);
        }

        holder.rvPhotoList.setLayoutParams(layoutParams);
        holder.rvPhotoList.setAdapter(photoAdapter);

    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    class MornCheckViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_date)
        TextView tvDate;
        @BindView(R.id.rv_photo_list)
        RecyclerView rvPhotoList;
        public MornCheckViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
