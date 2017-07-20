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
import com.tongxun.atongmu.parent.model.SignWaitModel;
import com.tongxun.atongmu.parent.ui.notice.ISignWaitListener;
import com.tongxun.atongmu.parent.util.GlideOption;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Anro on 2017/7/3.
 */

public class SignWaitAdapter extends RecyclerView.Adapter<SignWaitAdapter.SignUpViewHolder> {



    private Context mContext;
    private List<SignWaitModel> mlist;
    private ISignWaitListener mlistener = null;

    public SignWaitAdapter(Context context, List<SignWaitModel> list) {
        mContext = context;
        mlist = list;
    }

    @Override
    public SignUpViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_sign_waiting_layout, parent, false);
        SignUpViewHolder viewHolder = new SignUpViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(SignUpViewHolder holder, final int position) {
        Glide.with(mContext)
                .load(mlist.get(position).getActPhoto())
                .apply(GlideOption.getImageHolderOption())
                .into(holder.ivSignWaitingScreen);
        Glide.with(mContext)
                .load(mlist.get(position).getTecherPhoto())
                .apply(GlideOption.getPHOption())
                .into(holder.ivSignWaiting);
        holder.tvSignWaitingName.setText(mlist.get(position).getTecherName());
        holder.tvSignWaitingTime.setText(mlist.get(position).getAgenTime());

        if (mlist.get(position).getActStatus().equals("true")) {
            holder.ivSignWaitStatus.setVisibility(View.INVISIBLE);
            holder.tvSignWaitingConfirm.setVisibility(View.INVISIBLE);
            holder.tvSignWaitingPhone.setVisibility(View.INVISIBLE);
            holder.ivConfirmFace.setVisibility(View.VISIBLE);
            holder.tvConfirmName.setVisibility(View.VISIBLE);
            holder.tvConfirmTime.setVisibility(View.VISIBLE);
            holder.tvConfirmStauts.setVisibility(View.VISIBLE);
            Glide.with(mContext)
                    .load(mlist.get(position).getActPersonPhoto())
                    .apply(GlideOption.getPHOption())
                    .into(holder.ivConfirmFace);
            holder.tvConfirmName.setText(mlist.get(position).getRelation());
            holder.tvConfirmTime.setText(mlist.get(position).getActTime());
        } else {
            holder.ivSignWaitStatus.setVisibility(View.VISIBLE);
            holder.tvSignWaitingConfirm.setVisibility(View.VISIBLE);
            holder.tvSignWaitingPhone.setVisibility(View.VISIBLE);
            holder.ivConfirmFace.setVisibility(View.INVISIBLE);
            holder.tvConfirmName.setVisibility(View.INVISIBLE);
            holder.tvConfirmTime.setVisibility(View.INVISIBLE);
            holder.tvConfirmStauts.setVisibility(View.INVISIBLE);
        }
        holder.tvSignWaitingPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mlistener.onContrat(mlist.get(position).getTecherPhone());
            }
        });
        holder.tvSignWaitingConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mlistener.onConfirm(position);
            }
        });
        holder.ivSignWaitingScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mlistener.onImageLarge(mlist.get(position).getActPhoto());
            }
        });

    }

    public void setItemClickListener(ISignWaitListener listener) {
        mlistener = listener;
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }


    static class SignUpViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_sign_waiting)
        CircleImageView ivSignWaiting;
        @BindView(R.id.tv_sign_waiting_name)
        TextView tvSignWaitingName;
        @BindView(R.id.tv_sign_waiting_time)
        TextView tvSignWaitingTime;
        @BindView(R.id.iv_sign_waiting_screen)
        ImageView ivSignWaitingScreen;
        @BindView(R.id.tv_sign_waiting_confirm)
        TextView tvSignWaitingConfirm;
        @BindView(R.id.tv_sign_waiting_phone)
        TextView tvSignWaitingPhone;
        @BindView(R.id.iv_confirm_face)
        CircleImageView ivConfirmFace;
        @BindView(R.id.tv_confirm_name)
        TextView tvConfirmName;
        @BindView(R.id.tv_confirm_time)
        TextView tvConfirmTime;
        @BindView(R.id.iv_sign_wait_status)
        ImageView ivSignWaitStatus;
        @BindView(R.id.tv_confirm_stauts)
        TextView tvConfirmStauts;

        public SignUpViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
