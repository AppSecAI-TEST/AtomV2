package com.tongxun.atongmu.parent.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tongxun.atongmu.parent.R;
import com.tongxun.atongmu.parent.model.InviteFamilyBindModel;
import com.tongxun.atongmu.parent.model.InviteFamilyUnBindModel;
import com.tongxun.atongmu.parent.ui.my.invitefamily.InviteFamilyClickListener;
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
    private List mlist;
    private InviteFamilyClickListener mlistener;

    public InviteFamilyBindAdapter(Context context, List list, InviteFamilyClickListener listener) {
        mContext = context;
        mlist = list;
        mlistener = listener;
    }

    @Override
    public InviteBindViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_bind_family_layout, parent, false);
        InviteBindViewHolder viewHolder = new InviteBindViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(InviteBindViewHolder holder, final int position) {
        if (mlist.get(position) instanceof InviteFamilyBindModel) {
            final InviteFamilyBindModel model = (InviteFamilyBindModel) mlist.get(position);
            Glide.with(mContext).load(model.getHeadImage()).apply(GlideOption.getPHOption()).into(holder.civFace);
            holder.tvName.setText(model.getRelation());
            holder.tvLevel.setText(model.getLevel());
            if (model.getCardStatus().equals("unActivation")) {
                holder.tvLookBabyNum.setVisibility(View.GONE);
                holder.tvCardStatus.setVisibility(View.GONE);
                holder.tvInviteActivation.setVisibility(View.VISIBLE);
            } else {
                holder.tvLookBabyNum.setVisibility(View.VISIBLE);
                holder.tvCardStatus.setVisibility(View.VISIBLE);
                holder.tvInviteActivation.setVisibility(View.GONE);
                holder.tvLookBabyNum.setText("看过宝宝 " + model.getShowCount() + "次");
                holder.tvCardStatus.setText("接送卡号 " + model.getCardNumber());

            }
            holder.tvInviteActivation.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mlistener != null) {
                        mlistener.onRemindAction(model);
                    }
                }
            });
            holder.rlFamilyInfo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mlistener!=null){
                        mlistener.onLookDetail(model);
                    }
                }
            });
        }

        if (mlist.get(position) instanceof InviteFamilyUnBindModel) {
            final InviteFamilyUnBindModel model = (InviteFamilyUnBindModel) mlist.get(position);
            Glide.with(mContext).load(model.getHeadImage()).apply(GlideOption.getPHOption()).into(holder.civFace);
            holder.tvName.setText(model.getRelation());
            holder.tvLevel.setText("");
            holder.tvLookBabyNum.setVisibility(View.GONE);
            holder.tvCardStatus.setVisibility(View.GONE);
            holder.tvInviteActivation.setVisibility(View.VISIBLE);
            holder.tvInviteActivation.setText(mContext.getString(R.string.add));
            holder.tvInviteActivation.setBackgroundResource(R.drawable.invite_add_btn_shape);
            holder.tvInviteActivation.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mlistener != null) {
                        mlistener.onAddFamily(model.getRelation());
                    }
                }
            });
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
        @BindView(R.id.rl_family_info)
        RelativeLayout rlFamilyInfo;
        public InviteBindViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
