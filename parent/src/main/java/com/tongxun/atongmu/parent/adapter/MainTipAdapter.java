package com.tongxun.atongmu.parent.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tongxun.atongmu.parent.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anro on 2017/7/29.
 */

public class MainTipAdapter extends RecyclerView.Adapter<MainTipAdapter.MainTipHolder> {

    private List mList=new ArrayList();
    private Context mContext;


    public MainTipAdapter(Context context,List list) {
        mContext=context;
        mList=list;
    }

    @Override
    public MainTipHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.item_main_tip_layout,parent,false);
        MainTipHolder holder=new MainTipHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MainTipHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class MainTipHolder extends RecyclerView.ViewHolder{

        public MainTipHolder(View itemView) {
            super(itemView);
        }
    }
}
