package com.tongxun.atongmu.teacher.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tongxun.atongmu.teacher.R;
import com.tongxun.atongmu.teacher.model.ActivityHaveActPresonModel;
import com.tongxun.atongmu.teacher.model.NoReadPresonModel;
import com.tongxun.atongmu.teacher.ui.notice.NoReadItemClickListener;
import com.tongxun.atongmu.teacher.util.GlideOption;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Anro on 2017/8/3.
 */

public class NoReadPersonAdapter extends RecyclerView.Adapter<NoReadPersonAdapter.NoReadPersonViewHolder> {



    private Context mContext;
    private List mlist;
    private NoReadItemClickListener mListener;

    public NoReadPersonAdapter(Context context, List list,NoReadItemClickListener listener) {
        mContext = context;
        mlist = list;
        mListener=listener;
    }

    @Override
    public NoReadPersonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_no_read_person_layout, parent, false);
        NoReadPersonViewHolder viewHolder = new NoReadPersonViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(NoReadPersonViewHolder holder, final int position) {
        if(mlist.get(position) instanceof NoReadPresonModel){
            final NoReadPresonModel model= (NoReadPresonModel) mlist.get(position);
            holder.tvName.setText(model.getPersonName());
            Glide.with(mContext).load(model.getPhoto1()).apply(GlideOption.getFaceHolderOption()).into(holder.civFace);
            holder.rlNoReadPerson.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mListener!=null){
                        mListener.onPresonClick(model.getStudentId());
                    }
                }
            });
        }

      if(mlist.get(position) instanceof ActivityHaveActPresonModel){
          final ActivityHaveActPresonModel model= (ActivityHaveActPresonModel) mlist.get(position);
          holder.tvName.setText(model.getPersonName());
          holder.tvRemarks.setVisibility(View.VISIBLE);
          holder.tvRemarks.setText(model.getRemarks());
          Glide.with(mContext).load(model.getPhoto1()).apply(GlideOption.getFaceHolderOption()).into(holder.civFace);
          holder.rlNoReadPerson.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  if(mListener!=null){
                      mListener.onPresonClick(model.getStudentId());
                  }
              }
          });
      }

    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    class NoReadPersonViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.civ_face)
        CircleImageView civFace;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_remarks)
        TextView tvRemarks;
        @BindView(R.id.rl_no_read_person)
        RelativeLayout rlNoReadPerson;

        public NoReadPersonViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
