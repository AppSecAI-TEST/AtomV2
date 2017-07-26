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
import com.tongxun.atongmu.parent.model.ShuttlePhotoModel;
import com.tongxun.atongmu.parent.util.GlideOption;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by admin on 2017/7/26.
 */

public class ShuttlePhotoAdapter extends RecyclerView.Adapter<ShuttlePhotoAdapter.ShuttlePhotoViewHolder> {


    private List<ShuttlePhotoModel> mlist;
    private Context mContext;


    public ShuttlePhotoAdapter(Context context, List<ShuttlePhotoModel> list) {
        mContext = context;
        mlist = list;
    }

    @Override
    public ShuttlePhotoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_shuttle_photo_layout, parent, false);
        ShuttlePhotoViewHolder viewHolder = new ShuttlePhotoViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ShuttlePhotoViewHolder holder, int position) {
        holder.tvUserRelation.setText(mlist.get(position).getPersonRelation());
        holder.tvName.setText(mlist.get(position).getPersonDesc());
        if(TextUtils.isEmpty(mlist.get(position).getPhoto())){
            //// TODO: 2017/7/26
        }else {
            Glide.with(mContext).load(mlist.get(position).getPhoto()).apply(GlideOption.getPHOption()).into(holder.ivUserPhoto);
        }
        holder.tvCard.setText(mlist.get(position).getCardDesc());

    }

    @Override
    public int getItemCount() {
        return mlist.size();
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
}
