package com.tongxun.atongmu.parent.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tongxun.atongmu.parent.R;
import com.tongxun.atongmu.parent.model.FriendCirclePhotoModel;
import com.tongxun.atongmu.parent.model.MornCheckPhoto;
import com.tongxun.atongmu.parent.util.GlideOption;
import com.tongxun.atongmu.parent.widget.SquareImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Anro on 2017/7/7.
 */

public class FriendCirclePhotoAdapter extends RecyclerView.Adapter<FriendCirclePhotoAdapter.CirclePhotoViewHolder> {



    private Context mContext;
    private List mlist = new ArrayList<>();


    public FriendCirclePhotoAdapter(Context context, List photos) {
        mContext = context;
        mlist = photos;
    }

    @Override
    public CirclePhotoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_circle_photo_layout, parent, false);
        CirclePhotoViewHolder viewHolder = new CirclePhotoViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CirclePhotoViewHolder holder, int position) {
        if(mlist.get(position) instanceof FriendCirclePhotoModel){
            FriendCirclePhotoModel model= (FriendCirclePhotoModel) mlist.get(position);
            if(mlist.size()>9){
                if(position==8){
                    holder.ivItemBg.setVisibility(View.VISIBLE);
                    holder.tvItemNum.setVisibility(View.VISIBLE);
                    holder.ivItemImg.setVisibility(View.VISIBLE);
                    holder.tvItemNum.setText("+" + (mlist.size() - 9));
                }
                if(position<8) {
                    holder.ivItemBg.setVisibility(View.GONE);
                    holder.tvItemNum.setVisibility(View.GONE);
                    holder.ivItemImg.setVisibility(View.VISIBLE);
                }
                if(position>8){
                    holder.ivItemBg.setVisibility(View.GONE);
                    holder.tvItemNum.setVisibility(View.GONE);
                    holder.ivItemImg.setVisibility(View.GONE);
                }
            }else {
                holder.ivItemImg.setVisibility(View.VISIBLE);
                holder.ivItemBg.setVisibility(View.GONE);
                holder.tvItemNum.setVisibility(View.GONE);
            }
            if(position<9){
                Glide.with(mContext)
                        .load(model.getPhoto())
                        .apply(GlideOption.getImageHolderOption())
                        .into(holder.ivItemImg);
            }
        }else if(mlist.get(position) instanceof String){
            holder.ivItemImg.setVisibility(View.VISIBLE);
            holder.ivItemBg.setVisibility(View.GONE);
            holder.tvItemNum.setVisibility(View.GONE);
            Glide.with(mContext)
                    .load(mlist.get(position))
                    .apply(GlideOption.getImageHolderOption())
                    .into(holder.ivItemImg);
        }else if(mlist.get(position) instanceof MornCheckPhoto){
            MornCheckPhoto photo= (MornCheckPhoto) mlist.get(position);
            holder.ivItemImg.setVisibility(View.VISIBLE);
            holder.ivItemBg.setVisibility(View.GONE);
            holder.tvItemNum.setVisibility(View.GONE);
            Glide.with(mContext)
                    .load(photo.getHeadPhoto())
                    .apply(GlideOption.getImageHolderOption())
                    .into(holder.ivItemImg);
        }


    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    class CirclePhotoViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_item_img)
        SquareImageView ivItemImg;
        @BindView(R.id.iv_item_bg)
        SquareImageView ivItemBg;
        @BindView(R.id.tv_item_num)
        TextView tvItemNum;
        public CirclePhotoViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
