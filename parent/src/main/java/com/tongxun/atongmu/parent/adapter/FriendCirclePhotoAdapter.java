package com.tongxun.atongmu.parent.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Anro on 2017/7/7.
 */

public class FriendCirclePhotoAdapter extends RecyclerView.Adapter<FriendCirclePhotoAdapter.CirclePhotoViewHolder> {

    public FriendCirclePhotoAdapter() {
    }

    @Override
    public CirclePhotoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(CirclePhotoViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class CirclePhotoViewHolder extends RecyclerView.ViewHolder{

        public CirclePhotoViewHolder(View itemView) {
            super(itemView);
        }
    }
}
