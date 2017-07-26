package com.tongxun.atongmu.parent.adapter;

import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tongxun.atongmu.parent.R;
import com.tongxun.atongmu.parent.model.ProblemHelpModel;
import com.tongxun.atongmu.parent.util.DensityUtil;
import com.tongxun.atongmu.parent.util.RecycleViewDivider;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Anro on 2017/7/26.
 */

public class ProblemAdapter extends RecyclerView.Adapter<ProblemAdapter.ProblemViewHolder> implements ProblemItemAdapter.IFunIntroListener {


    private Context mContext;
    private List<ProblemHelpModel> mlist = new ArrayList<>();
    private ProblemItemAdapter problemItemAdapter;
    private IProblemListener iProblemListener;


    public ProblemAdapter(Context context, List<ProblemHelpModel> list,IProblemListener listener) {
        mlist = list;
        mContext = context;
        iProblemListener=listener;
    }

    @Override
    public ProblemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_problem_layout, parent, false);
        ProblemViewHolder viewHolder = new ProblemViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ProblemViewHolder holder, int position) {
        holder.tvTitle.setText(mlist.get(position).getTitle());
        holder.rvItemContent.setItemAnimator(new DefaultItemAnimator());
        holder.rvItemContent.setLayoutManager(new LinearLayoutManager(mContext));
        holder.rvItemContent.addItemDecoration(new RecycleViewDivider(mContext, LinearLayoutManager.VERTICAL, DensityUtil.dip2px(mContext, 1), mContext.getResources().getColor(R.color.colorLineGray)));
        problemItemAdapter=new ProblemItemAdapter(mContext,mlist.get(position).getHelp(),this);
        holder.rvItemContent.setAdapter(problemItemAdapter);

    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }


    @Override
    public void onPromItemClick(String url) {
        if(iProblemListener!=null){
            iProblemListener.onPromItemClick(url);
        }
    }

    class ProblemViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.rv_item_content)
        RecyclerView rvItemContent;
        public ProblemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface IProblemListener{
        void onPromItemClick(String url);
    }
}
