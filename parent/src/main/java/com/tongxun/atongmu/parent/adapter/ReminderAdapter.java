package com.tongxun.atongmu.parent.adapter;

import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.tongxun.atongmu.parent.IonItemClickListener;
import com.tongxun.atongmu.parent.R;
import com.tongxun.atongmu.parent.model.MedicineModel;
import com.tongxun.atongmu.parent.ui.homework.IPhotoItemClickListener;
import com.tongxun.atongmu.parent.util.ScreenUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by admin on 2017/7/29.
 */

public class ReminderAdapter extends RecyclerView.Adapter<ReminderAdapter.ReminderViewHolder> {


    private List<MedicineModel> mlist = new ArrayList<>();
    private Context mContext;
    private FriendCirclePhotoAdapter photoAdapter;
    private ReminderListener mListener;
    private SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");

    public ReminderAdapter(Context context, List<MedicineModel> list,ReminderListener listener) {
        mContext = context;
        mlist = list;
        mListener=listener;
    }

    @Override
    public ReminderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_reminder_layout, parent, false);
        ReminderViewHolder viewHolder = new ReminderViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ReminderViewHolder holder, final int position) {
        holder.tvStartTime.setText(mlist.get(position).getStart_time());
        Calendar calendar=Calendar.getInstance();
        try {
            Date date=simpleDateFormat.parse(mlist.get(position).getStart_time());
            calendar.setTime(date);
            calendar.add(Calendar.DATE,Integer.parseInt(mlist.get(position).getMedicine_days())-1);
            holder.tvEndTime.setText(simpleDateFormat.format(calendar.getTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        holder.tvRemark.setText(mlist.get(position).getNotes());
        photoAdapter = new FriendCirclePhotoAdapter(mContext, mlist.get(position).getImage(), new IonItemClickListener() {
            @Override
            public void onItemClick(int pos) {
                if(mListener!=null){
                    mListener.onPhoto(position,pos);
                }
            }
        });
        holder.rvCourseContent.setItemAnimator(new DefaultItemAnimator());
        ViewGroup.LayoutParams layoutParams = holder.rvCourseContent.getLayoutParams();

        int size = 0;
        if (mlist.get(position).getImage().size() % 3 == 0) {
            size = mlist.get(position).getImage().size() / 3;
        } else {
            size = mlist.get(position).getImage().size() / 3 + 1;
        }
        layoutParams.height = (ScreenUtils.getScreenWidth() / 3) * size;
        holder.rvCourseContent.setLayoutManager(new GridLayoutManager(mContext, 3));
        holder.rvCourseContent.setLayoutParams(layoutParams);
        holder.rvCourseContent.setAdapter(photoAdapter);
        holder.ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mListener!=null){
                    mListener.onDelete(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }



    class ReminderViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_delete)
        ImageView ivDelete;
        @BindView(R.id.tv_start_time)
        TextView tvStartTime;
        @BindView(R.id.tv_end_time)
        TextView tvEndTime;
        @BindView(R.id.tv_remark)
        TextView tvRemark;
        @BindView(R.id.rv_course_content)
        RecyclerView rvCourseContent;

        public ReminderViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface ReminderListener extends IPhotoItemClickListener{
        void onDelete(int position);
    }
}
