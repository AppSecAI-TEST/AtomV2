package com.tongxun.atongmu.teacher.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tongxun.atongmu.teacher.IonItemClickListener;
import com.tongxun.atongmu.teacher.R;
import com.tongxun.atongmu.teacher.model.FamilyPersonModel;
import com.tongxun.atongmu.teacher.util.GlideOption;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Anro on 2017/8/3.
 */

public class PersonStatusAdapter extends RecyclerView.Adapter<PersonStatusAdapter.PersonStatusHolder> {


    private Context mContext;
    private List<FamilyPersonModel> mlist;
    private IonItemClickListener mlistener;

    public PersonStatusAdapter(Context context, List<FamilyPersonModel> list,IonItemClickListener listener) {
        mContext = context;
        mlist = list;
        mlistener=listener;
    }

    @Override
    public PersonStatusHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_group_member_layout, parent, false);
        PersonStatusHolder holder = new PersonStatusHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(PersonStatusHolder holder, final int position) {
        Glide.with(mContext).load(mlist.get(position).getParent_photo()).apply(GlideOption.getFaceHolderOption()).into(holder.ivFace);
        holder.tvName.setText(mlist.get(position).getParent_name());
        if (mlist.get(position).getParent_level().equals("LV0")) {
            holder.tvLevel.setSelected(false);
        } else {
            holder.tvLevel.setSelected(true);
        }
        holder.tvRelation.setBackgroundResource(R.drawable.signwait_status_yellow);
        if(TextUtils.isEmpty(mlist.get(position).getParent_phone())){
            holder.ivPhone.setVisibility(View.GONE);
        }else {
            holder.ivPhone.setVisibility(View.VISIBLE);
        }

        holder.tvRelation.setText(mlist.get(position).getParent_relation());
        holder.ivPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mlistener!=null){
                    mlistener.onItemClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    class PersonStatusHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_face)
        CircleImageView ivFace;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_level)
        TextView tvLevel;
        @BindView(R.id.tv_relation)
        TextView tvRelation;
        @BindView(R.id.iv_phone)
        ImageView ivPhone;
        public PersonStatusHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
