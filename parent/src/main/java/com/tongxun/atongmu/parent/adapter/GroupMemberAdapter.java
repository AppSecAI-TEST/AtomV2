package com.tongxun.atongmu.parent.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tongxun.atongmu.parent.R;
import com.tongxun.atongmu.parent.model.GroupMemberModel;
import com.tongxun.atongmu.parent.util.GlideOption;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Anro on 2017/7/12.
 */

public class GroupMemberAdapter extends RecyclerView.Adapter<GroupMemberAdapter.GroupViewHolder> {



    private Context mContext;
    private List<GroupMemberModel> mlist = new ArrayList<>();
    private IGourpMemberListener mlistener;

    public GroupMemberAdapter(Context context, List<GroupMemberModel> list, IGourpMemberListener listener) {
        mlist = list;
        mContext = context;
        mlistener = listener;
    }

    @Override
    public GroupViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_group_member_layout, parent, false);
        GroupViewHolder viewHolder = new GroupViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(GroupViewHolder holder, final int position) {
        Glide.with(mContext).load(mlist.get(position).getAvatar()).apply(GlideOption.getPHOption()).into(holder.ivFace);
        holder.tvName.setText(mlist.get(position).getName());
        if (mlist.get(position).getLevel().equals("LV0")) {
            holder.tvLevel.setSelected(false);
        } else {
            holder.tvLevel.setSelected(true);
        }
        if (mlist.get(position).getRelationship().equals("园长")) {
            holder.tvRelation.setBackgroundResource(R.drawable.signwait_status_blue);
        } else {
            holder.tvRelation.setBackgroundResource(R.drawable.signwait_status_yellow);
        }
        holder.tvRelation.setText(mlist.get(position).getRelationship());
        holder.ivPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mlistener!=null){
                    mlistener.Phone(mlist.get(position).getPhone());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    class GroupViewHolder extends RecyclerView.ViewHolder {
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

        public GroupViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface IGourpMemberListener {
        void Phone(String phone);
    }
}
