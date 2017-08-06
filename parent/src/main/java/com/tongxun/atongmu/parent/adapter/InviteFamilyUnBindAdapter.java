package com.tongxun.atongmu.parent.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.tongxun.atongmu.parent.model.InviteFamilyUnBindModel;

import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by admin on 2017/8/6.
 */

public class InviteFamilyUnBindAdapter extends RecyclerView.Adapter<InviteFamilyUnBindAdapter.InviteUnBindAdapter> {

    private Context mContext;
    private List<InviteFamilyUnBindModel> mlist;

    public InviteFamilyUnBindAdapter(Context context, List<InviteFamilyUnBindModel> list) {
        mContext=context;
        mlist=list;
    }

    @Override
    public InviteUnBindAdapter onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(InviteUnBindAdapter holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    class InviteUnBindAdapter extends RecyclerView.ViewHolder{

        public InviteUnBindAdapter(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
