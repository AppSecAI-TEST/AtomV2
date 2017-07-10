package com.tongxun.atongmu.parent.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.tongxun.atongmu.parent.R;
import com.tongxun.atongmu.parent.util.GlideOption;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Anro on 2017/7/8.
 */

public class PickPhotoAdapter extends RecyclerView.Adapter<PickPhotoAdapter.PickPhotoViewHolder> {



    private Context mContext;
    private List<String> mlist = new ArrayList<>();
    private PickListener mlistener;

    public PickPhotoAdapter(Context context, List<String> list,PickListener listener) {
        mContext = context;
        mlist=list;
        mlistener=listener;
    }

    @Override
    public PickPhotoAdapter.PickPhotoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_pick_photo, parent, false);
        PickPhotoViewHolder viewHolder=new PickPhotoViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(PickPhotoViewHolder holder, final int position) {
        Glide.with(mContext)
                .load(mlist.get(position))
                .apply(GlideOption.getPHOption())
                .into(holder.ivPickPhoto);

        holder.ivPickPhotoDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mlistener!=null){
                    mlistener.delete(position);
                }
            }
        });
    }



    class PickPhotoViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_pick_photo)
        ImageView ivPickPhoto;
        @BindView(R.id.iv_pick_photo_delete)
        ImageView ivPickPhotoDelete;
        public PickPhotoViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public interface PickListener{
        void delete(int position);
    }
}
