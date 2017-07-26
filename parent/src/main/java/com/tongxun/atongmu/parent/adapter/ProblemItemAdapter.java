package com.tongxun.atongmu.parent.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tongxun.atongmu.parent.R;
import com.tongxun.atongmu.parent.model.ProblemHelpItemModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Anro on 2017/7/26.
 */

public class ProblemItemAdapter extends RecyclerView.Adapter<ProblemItemAdapter.FunIntroViewHolder> {


    private Context mContext;
    private List<ProblemHelpItemModel> mlist = new ArrayList<>();
    private IFunIntroListener mListener;

    public ProblemItemAdapter(Context context, List<ProblemHelpItemModel> list, IFunIntroListener listener) {
        mContext = context;
        mlist = list;
        mListener=listener;
    }

    @Override
    public FunIntroViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_function_introduct, parent, false);
        FunIntroViewHolder viewHolder=new FunIntroViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(FunIntroViewHolder holder, final int position) {
        holder.tvTitle.setText(mlist.get(position).getName());
        holder.llItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mListener!=null){
                    mListener.onPromItemClick(mlist.get(position).getUrl());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    class FunIntroViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.ll_item)
        RelativeLayout llItem;
        public FunIntroViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    public interface IFunIntroListener{
        void onPromItemClick(String url);
    }
}
