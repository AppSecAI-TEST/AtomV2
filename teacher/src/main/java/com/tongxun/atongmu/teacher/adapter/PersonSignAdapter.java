package com.tongxun.atongmu.teacher.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tongxun.atongmu.teacher.IonItemClickListener;
import com.tongxun.atongmu.teacher.R;
import com.tongxun.atongmu.teacher.model.PersonSignModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Anro on 2017/8/8.
 */

public class PersonSignAdapter extends RecyclerView.Adapter<PersonSignAdapter.PersonSignViewHolder> {



    private Context mContext;
    private List<PersonSignModel> mlist;
    private IonItemClickListener mListener;

    public PersonSignAdapter(Context context, List<PersonSignModel> list,IonItemClickListener listener) {
        mContext = context;
        mlist = list;
        mListener=listener;
    }

    @Override
    public PersonSignViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_person_sign_layout, parent, false);
        PersonSignViewHolder viewHolder = new PersonSignViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(PersonSignViewHolder holder, final int position) {
        holder.tvClassName.setText(mlist.get(position).getClassName());
        holder.tvSum.setText(mlist.get(position).getClassTotal());
        holder.tvArrived.setText(mlist.get(position).getClassArrived());
        holder.tvUnarrived.setText(mlist.get(position).getClassUnArrived());
        holder.tvLeave.setText(mlist.get(position).getClassLeave());
        holder.llItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mListener!=null){
                    mListener.onItemClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    class PersonSignViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_class_name)
        TextView tvClassName;
        @BindView(R.id.tv_sum)
        TextView tvSum;
        @BindView(R.id.tv_arrived)
        TextView tvArrived;
        @BindView(R.id.tv_unarrived)
        TextView tvUnarrived;
        @BindView(R.id.tv_leave)
        TextView tvLeave;
        @BindView(R.id.ll_item)
        LinearLayout llItem;
        public PersonSignViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
