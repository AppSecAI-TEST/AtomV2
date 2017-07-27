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
import com.tongxun.atongmu.parent.IonItemClickListener;
import com.tongxun.atongmu.parent.R;
import com.tongxun.atongmu.parent.model.ShuttlePhotoModel;
import com.tongxun.atongmu.parent.util.GlideOption;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by admin on 2017/7/26.
 */

public class ShuttlePhotoAdapter extends RecyclerView.Adapter {


    private List<ShuttlePhotoModel> mlist;
    private Context mContext;
    private IonItemClickListener mListener;

    public ShuttlePhotoAdapter(Context context, List<ShuttlePhotoModel> list,IonItemClickListener listener) {
        mContext = context;
        mlist = list;
        mListener=listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType==0){
            View view = LayoutInflater.from(mContext).inflate(R.layout.shuttle_photo_top_layout, parent, false);
            ShuttlePhotoTopHolder viewHolder=new ShuttlePhotoTopHolder(view);
            return viewHolder;
        }else {
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_shuttle_photo_layout, parent, false);
            ShuttlePhotoViewHolder viewHolder = new ShuttlePhotoViewHolder(view);
            return viewHolder;
        }

    }



    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int position) {


        if(viewHolder instanceof  ShuttlePhotoViewHolder){
            ShuttlePhotoViewHolder holder= (ShuttlePhotoViewHolder) viewHolder;
            int mPosition=position-1;
            holder.tvUserRelation.setText(mlist.get(mPosition).getPersonRelation());
            holder.tvName.setText(mlist.get(mPosition).getPersonDesc());
            if(TextUtils.isEmpty(mlist.get(mPosition).getPhoto())){

            }else {
                Glide.with(mContext).load(mlist.get(mPosition).getPhoto()).apply(GlideOption.getPHOption()).into(holder.ivUserPhoto);
            }
            holder.tvCard.setText(mlist.get(mPosition).getCardDesc());
            holder.rlItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mListener!=null){
                        mListener.onItemClick(position-1);
                    }
                }
            });
        }


    }


    @Override
    public int getItemViewType(int position) {
        if(position==0){
            return 0;
        }
        return 1;
    }

    @Override
    public int getItemCount() {
        return mlist.size()+1;
    }

    class ShuttlePhotoViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_user_photo)
        ImageView ivUserPhoto;
        @BindView(R.id.tv_user_relation)
        TextView tvUserRelation;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_card)
        TextView tvCard;
        @BindView(R.id.rl_item)
        RelativeLayout rlItem;
        public ShuttlePhotoViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class ShuttlePhotoTopHolder extends RecyclerView.ViewHolder{

        public ShuttlePhotoTopHolder(View itemView) {
            super(itemView);

        }
    }
}
