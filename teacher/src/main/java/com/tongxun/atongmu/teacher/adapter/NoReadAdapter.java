package com.tongxun.atongmu.teacher.adapter;

import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hyphenate.util.DensityUtil;
import com.tongxun.atongmu.teacher.R;
import com.tongxun.atongmu.teacher.model.ActivityHaveActModel;
import com.tongxun.atongmu.teacher.model.ActivityNoReadModel;
import com.tongxun.atongmu.teacher.model.NoReadModel;
import com.tongxun.atongmu.teacher.ui.notice.NoReadItemClickListener;
import com.tongxun.atongmu.teacher.util.RecycleViewDivider;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Anro on 2017/8/3.
 */

public class NoReadAdapter extends RecyclerView.Adapter<NoReadAdapter.NoReadViewHolder> {


    private Context mContext;
    private List mlist;

    private NoReadPersonAdapter mAdapter;
    private NoReadItemClickListener mListener=null;

    public NoReadAdapter(Context context, List list,NoReadItemClickListener listener) {
        mContext = context;
        mlist = list;
        mListener=listener;
    }

    @Override
    public NoReadViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_no_read_layout, parent, false);
        NoReadViewHolder viewHolder = new NoReadViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(NoReadViewHolder holder, int position) {
        if(mlist.get(position) instanceof NoReadModel){
            NoReadModel model= (NoReadModel) mlist.get(position);
            holder.tvClassName.setText(model.getClassName());
            holder.tvNoReadNum.setText(model.getNoReadNum());

            holder.rvPerson.setItemAnimator(new DefaultItemAnimator());
            holder.rvPerson.setLayoutManager(new LinearLayoutManager(mContext));
            holder.rvPerson.addItemDecoration(new RecycleViewDivider(mContext, LinearLayoutManager.VERTICAL, DensityUtil.dip2px(mContext, 1), mContext.getResources().getColor(R.color.colorLineGray)));
            mAdapter=new NoReadPersonAdapter(mContext,model.getStudentList(),mListener);
            holder.rvPerson.setAdapter(mAdapter);
        }
        if(mlist.get(position) instanceof ActivityHaveActModel){
            ActivityHaveActModel model= (ActivityHaveActModel) mlist.get(position);
            holder.tvClassName.setText(model.getClassName());
            holder.tvNoReadNum.setText(model.getActManCount());

            holder.rvPerson.setItemAnimator(new DefaultItemAnimator());
            holder.rvPerson.setLayoutManager(new LinearLayoutManager(mContext));
            holder.rvPerson.addItemDecoration(new RecycleViewDivider(mContext, LinearLayoutManager.VERTICAL, DensityUtil.dip2px(mContext, 1), mContext.getResources().getColor(R.color.colorLineGray)));
            mAdapter=new NoReadPersonAdapter(mContext,model.getStudentData(),mListener);
            holder.rvPerson.setAdapter(mAdapter);
        }
        if(mlist.get(position) instanceof ActivityNoReadModel){
            ActivityNoReadModel model= (ActivityNoReadModel) mlist.get(position);
            holder.tvClassName.setText(model.getClassName());
            holder.tvNoReadNum.setText(model.getNoReadManCount());

            holder.rvPerson.setItemAnimator(new DefaultItemAnimator());
            holder.rvPerson.setLayoutManager(new LinearLayoutManager(mContext));
            holder.rvPerson.addItemDecoration(new RecycleViewDivider(mContext, LinearLayoutManager.VERTICAL, DensityUtil.dip2px(mContext, 1), mContext.getResources().getColor(R.color.colorLineGray)));
            mAdapter=new NoReadPersonAdapter(mContext,model.getStudentData(),mListener);
            holder.rvPerson.setAdapter(mAdapter);
        }
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    class NoReadViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_class_name)
        TextView tvClassName;
        @BindView(R.id.tv_no_read_num)
        TextView tvNoReadNum;
        @BindView(R.id.rv_person)
        RecyclerView rvPerson;
        public NoReadViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
