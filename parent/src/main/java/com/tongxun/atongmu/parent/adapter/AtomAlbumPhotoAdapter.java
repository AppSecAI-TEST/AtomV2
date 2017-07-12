package com.tongxun.atongmu.parent.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tongxun.atongmu.parent.IPhotoSelectListener;
import com.tongxun.atongmu.parent.R;
import com.tongxun.atongmu.parent.ui.PhotoSelectContainer;
import com.tongxun.atongmu.parent.widget.SquareImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Anro on 2017/7/10.
 */

public class AtomAlbumPhotoAdapter extends RecyclerView.Adapter<AtomAlbumPhotoAdapter.ViewHolder> {

    private Context mContext;
    private List<String> mlist = new ArrayList<>();

    private IPhotoSelectListener mlistener;

    public AtomAlbumPhotoAdapter(Context context, List<String> list,IPhotoSelectListener listener) {
        mContext = context;
        mlist = list;
        mlistener=listener;
    }

    @Override
    public AtomAlbumPhotoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_atom_photo_laytou, parent, false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(AtomAlbumPhotoAdapter.ViewHolder holder, final int position) {
        if(PhotoSelectContainer.isHaveFile(mlist.get(position))){
            holder.tvPictureNum.setVisibility(View.VISIBLE);
        }else {
            holder.tvPictureNum.setVisibility(View.GONE);
        }
        Glide.with(mContext)
                .load(mlist.get(position))

                .into(holder.ivPicture);
        holder.ivPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mlistener!=null){
                    if(PhotoSelectContainer.isHaveFile(mlist.get(position))){
                        PhotoSelectContainer.removeFile(mlist.get(position));
                        mlistener.setPhotoSelect(position);
                    }else {
                        if(PhotoSelectContainer.addFile(mlist.get(position))){
                            mlistener.setPhotoSelect(position);
                        }else {
                            mlistener.setAddError();
                        }


                    }

                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_picture)
        SquareImageView ivPicture;
        @BindView(R.id.tv_picture_num)
        TextView tvPictureNum;


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
