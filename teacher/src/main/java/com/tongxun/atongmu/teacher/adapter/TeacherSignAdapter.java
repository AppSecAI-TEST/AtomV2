package com.tongxun.atongmu.teacher.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tongxun.atongmu.teacher.R;
import com.tongxun.atongmu.teacher.model.TeacherSignModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Anro on 2017/8/8.
 */

public class TeacherSignAdapter extends RecyclerView.Adapter<TeacherSignAdapter.TeacherViewHolder> {


    private Context mContext;
    private List<TeacherSignModel> mlist;

    public TeacherSignAdapter(Context context, List<TeacherSignModel> list) {
        mlist = list;
        mContext = context;
    }

    @Override
    public TeacherViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_teacher_sign_layout, parent, false);
        TeacherViewHolder viewHolder=new TeacherViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(TeacherViewHolder holder, int position) {
        Glide.with(mContext).load(mlist.get(position).getTeacherImage()).into(holder.ivFace);
        holder.tvName.setText(mlist.get(position).getTeacherName());
        holder.tvIntoTime.setText(mlist.get(position).getIntoTime());
        holder.tvOutTime.setText(mlist.get(position).getOutTime());
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    class TeacherViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_face)
        CircleImageView ivFace;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_into_time)
        TextView tvIntoTime;
        @BindView(R.id.tv_into_type)
        TextView tvIntoType;
        @BindView(R.id.tv_out_time)
        TextView tvOutTime;
        @BindView(R.id.tv_out_type)
        TextView tvOutType;
        public TeacherViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
