package com.tongxun.atongmu.parent.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tongxun.atongmu.parent.R;
import com.tongxun.atongmu.parent.model.InviteFamilyBindModel;
import com.tongxun.atongmu.parent.util.GlideOption;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by admin on 2017/8/6.
 */

public class InviteFamilyBindAdapter extends RecyclerView.Adapter<InviteFamilyBindAdapter.InviteBindViewHolder> {


    private Context mContext;
    private List<InviteFamilyBindModel> mlist;

    public InviteFamilyBindAdapter(Context context, List<InviteFamilyBindModel> list) {
        mContext = context;
        mlist = list;
    }

    @Override
    public InviteBindViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_bind_family_layout, parent, false);
        InviteBindViewHolder viewHolder = new InviteBindViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(InviteBindViewHolder holder, int position) {
        Glide.with(mContext).load(mlist.get(position).getHeadImage()).apply(GlideOption.getPHOption()).into(holder.civFace);
        holder.tvName.setText(mlist.get(position).getRelation());
        holder.tvLevel.setText(mlist.get(position).getLevel());
        if(mlist.get(position).getCardStatus().equals("unActivation")){
            holder.tvLookBabyNum.setVisibility(View.GONE);
            holder.tvCardStatus.setVisibility(View.GONE);
            holder.tvInviteActivation.setVisibility(View.VISIBLE);
        }else {
            holder.tvLookBabyNum.setVisibility(View.VISIBLE);
            holder.tvCardStatus.setVisibility(View.VISIBLE);
            holder.tvInviteActivation.setVisibility(View.GONE);
            holder.tvLookBabyNum.setText("看过宝宝 "+mlist.get(position).getShowCount()+"次");
            holder.tvCardStatus.setText("接送卡号 "+mlist.get(position).getCardNumber());
        }

    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    class InviteBindViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.civ_face)
        CircleImageView civFace;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_level)
        TextView tvLevel;
        @BindView(R.id.tv_look_baby_num)
        TextView tvLookBabyNum;
        @BindView(R.id.tv_card_status)
        TextView tvCardStatus;
        @BindView(R.id.tv_invite_activation)
        TextView tvInviteActivation;
        public InviteBindViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
