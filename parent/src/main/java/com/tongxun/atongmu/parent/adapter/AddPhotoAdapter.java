package com.tongxun.atongmu.parent.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anro on 2017/7/22.
 */

public class AddPhotoAdapter extends RecyclerView.Adapter<AddPhotoAdapter.AddPhotoViewHolder> {

    private  List<String> mlist=new ArrayList<>();
    public AddPhotoAdapter(Context context, List<String> list) {
        mlist=list;

    }

    @Override
    public AddPhotoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(AddPhotoViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class AddPhotoViewHolder extends RecyclerView.ViewHolder{

        public AddPhotoViewHolder(View itemView) {
            super(itemView);
        }
    }
}
