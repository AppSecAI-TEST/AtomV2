package com.tongxun.atongmu.parent.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.hyphenate.easeui.utils.GlideOption;
import com.tongxun.atongmu.parent.R;
import com.tongxun.atongmu.parent.ui.photoClickListener;
import com.tongxun.atongmu.parent.widget.SquareImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Anro on 2017/7/22.
 */

public class AddPhotoAdapter extends RecyclerView.Adapter<AddPhotoAdapter.AddPhotoViewHolder> {



    private List<String> mlist = new ArrayList<>();
    private Context mContext;
    private int maxSize;
    private photoClickListener mListener;

    public AddPhotoAdapter(Context context, List<String> list, int size, photoClickListener listener) {
        mlist = list;
        mContext = context;
        maxSize = size;
        mListener = listener;
    }


    @Override
    public AddPhotoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_add_photo_layout, parent, false);
        AddPhotoViewHolder viewHolder = new AddPhotoViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(AddPhotoViewHolder holder, final int position) {
        if (mlist.get(position).equals("SELECT")) {
            holder.ivPhoto.setImageResource(R.drawable.icon_add_photo);
            holder.ivPhotoDelete.setVisibility(View.GONE);
        } else {
            holder.ivPhotoDelete.setVisibility(View.VISIBLE);
            Glide.with(mContext).load(mlist.get(position)).apply(GlideOption.getPHOption()).into(holder.ivPhoto);
        }
        holder.ivPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    if (mlist.get(position).equals("SELECT")) {
                        if (mlist.size() - 1 != maxSize) {
                            mListener.onAddPhoto(maxSize - (mlist.size() - 1));
                        }

                    } else {
                      //  mListener.onPhotoClick(mlist.get(position));
                    }
                }
            }
        });
        holder.ivPhotoDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mListener!=null){
                    mListener.onDeletePhoto(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    class AddPhotoViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_photo)
        SquareImageView ivPhoto;
        @BindView(R.id.iv_photo_play)
        ImageView ivPhotoPlay;
        @BindView(R.id.iv_photo_delete)
        ImageView ivPhotoDelete;
        public AddPhotoViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


}
