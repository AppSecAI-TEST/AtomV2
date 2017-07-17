package com.tongxun.atongmu.parent.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hyphenate.easeui.utils.GlideOption;
import com.tongxun.atongmu.parent.R;
import com.tongxun.atongmu.parent.model.TeacherStyleModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Anro on 2017/7/17.
 */

public class TeacherStyleAdpater extends RecyclerView.Adapter<TeacherStyleAdpater.TeacherStyleViewHolder> {


    private Context mContext;
    private List<TeacherStyleModel> mlist = new ArrayList<>();
    private TeacherStyleListener mlistener;

    public TeacherStyleAdpater(Context context, List<TeacherStyleModel> list,TeacherStyleListener listener) {
        mContext = context;
        mlist = list;
        mlistener=listener;
    }

    @Override
    public TeacherStyleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_teacher_style, parent, false);
        TeacherStyleViewHolder viewHolder = new TeacherStyleViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(TeacherStyleViewHolder viewHolder, final int position) {
        viewHolder.fengcaiYuandingName.setText(mlist.get(position).getTecherName());
        viewHolder.fengcaiYuandingMotto.setText(mlist.get(position).getMotto());
        viewHolder.fengcaiYuandingPhone.setText(mlist.get(position).getPhoneCode());
        viewHolder.fengcaiYuandingClass.setText(mlist.get(position).getClassDesc());
        String photourl = mlist.get(position).getPhoto();
        if (photourl.equals("")) {
            viewHolder.fengcaiYuandingImg.setImageResource(R.drawable.icon_default);
        } else {
            Glide.with(mContext)
                    .load(photourl)
                    .apply(GlideOption.getPHOption())
                    .into(viewHolder.fengcaiYuandingImg);
        }
        viewHolder.fengcaiYuandingImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mlistener!=null){
                    mlistener.onItemClick(mlist.get(position).getTecherCode());
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    class TeacherStyleViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.fengcai_yuanding_img)
        CircleImageView fengcaiYuandingImg;
        @BindView(R.id.fengcai_yuanding_name)
        TextView fengcaiYuandingName;
        @BindView(R.id.fengcai_yuanding_motto)
        TextView fengcaiYuandingMotto;
        @BindView(R.id.fengcai_yuanding_phone)
        TextView fengcaiYuandingPhone;
        @BindView(R.id.fengcai_yuanding_class)
        TextView fengcaiYuandingClass;
        public TeacherStyleViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    public interface TeacherStyleListener{
        void onItemClick(String teacherCode);
    }
}
