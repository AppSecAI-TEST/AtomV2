package com.tongxun.atongmu.parent.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tongxun.atongmu.parent.R;
import com.tongxun.atongmu.parent.model.HomeworkNoFinishModel;
import com.tongxun.atongmu.parent.util.GlideOption;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Anro on 2017/7/5.
 */

public class HomeworkNoFinishAdpater extends RecyclerView.Adapter<HomeworkNoFinishAdpater.NoFinishViewHolder> {



    private Context mContext;
    private List<HomeworkNoFinishModel> mlist = new ArrayList<>();

    public HomeworkNoFinishAdpater(Context context, List<HomeworkNoFinishModel> datas) {
        mContext = context;
        mlist = datas;
    }

    @Override
    public NoFinishViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_homework_nofinish, parent, false);
        NoFinishViewHolder viewHolder = new NoFinishViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(NoFinishViewHolder holder, int position) {
        holder.tvHomeworkName.setText(mlist.get(position).getTeaName());
        Glide.with(mContext)
                .load(mlist.get(position).getTeaImg())
                .apply(GlideOption.getPHOption())
                .into(holder.civHomeworkFace);
        holder.tvHomeworkTime.setText(mlist.get(position).getCreateDate());
    }


    @Override
    public int getItemCount() {
        return 0;
    }

    class NoFinishViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.civ_homework_face)
        CircleImageView civHomeworkFace;
        @BindView(R.id.tv_homework_name)
        TextView tvHomeworkName;
        @BindView(R.id.tv_homework_time)
        TextView tvHomeworkTime;


        public NoFinishViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


}
