package com.tongxun.atongmu.parent.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tongxun.atongmu.parent.R;
import com.tongxun.atongmu.parent.model.CourseModel;
import com.tongxun.atongmu.parent.util.DensityUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Anro on 2017/7/4.
 */

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.ViewHolder> {



    private List<CourseModel> mlist;
    private Context mContext;

    public CourseAdapter(Context context, List<CourseModel> list) {
        mContext = context;
        mlist = list;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_course_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        if (mlist.get(position).getNowDay().equals("true")) {
            holder.llCourseWeek.setBackgroundColor(mContext.getResources().getColor(R.color.colorMainYellow));
            holder.tvCourseDate.setTextColor(mContext.getResources().getColor(R.color.colorWhite));
            holder.tvCourseWeek.setTextColor(mContext.getResources().getColor(R.color.colorWhite));
        } else {
            holder.llCourseWeek.setBackgroundColor(mContext.getResources().getColor(R.color.colorLineGray));
            holder.tvCourseDate.setTextColor(mContext.getResources().getColor(R.color.colorFontBlack));
            holder.tvCourseWeek.setTextColor(mContext.getResources().getColor(R.color.colorFontBlack));
        }

        holder.tvCourseWeek.setText(mlist.get(position).getWeek());
        holder.tvCourseDate.setText(mlist.get(position).getDate());

        if (mlist.get(position).getMon().size() == 0 && mlist.get(position).getAft().size() == 0) {
            holder.llHaveWeek.setVisibility(View.GONE);
            holder.tvNoCourse.setVisibility(View.VISIBLE);
            ViewGroup.LayoutParams layoutParams = holder.rlItemCourse.getLayoutParams();
            layoutParams.height = DensityUtil.dip2px(mContext, 48);
            holder.rlItemCourse.setLayoutParams(layoutParams);
        } else {
            holder.llHaveWeek.setVisibility(View.VISIBLE);
            holder.tvNoCourse.setVisibility(View.GONE);
            if (mlist.get(position).getMon().size() == 0) {
                holder.llMonWeek.setVisibility(View.GONE);
            } else {
                holder.llMonWeek.setVisibility(View.VISIBLE);
            }
            if (mlist.get(position).getAft().size() == 0) {
                holder.llAftWeek.setVisibility(View.GONE);
            } else {
                holder.llAftWeek.setVisibility(View.VISIBLE);
            }

            int monsize = mlist.get(position).getMon().size();
            int aftsize = mlist.get(position).getAft().size();


            int max = Math.max(monsize, aftsize);

            if (monsize == 0) {
                if (aftsize > 2) {
                    ViewGroup.LayoutParams layoutParams = holder.rlItemCourse.getLayoutParams();
                    layoutParams.height = DensityUtil.dip2px(mContext, 24 * aftsize);
                    holder.rlItemCourse.setLayoutParams(layoutParams);
                } else {
                    ViewGroup.LayoutParams layoutParams = holder.rlItemCourse.getLayoutParams();
                    layoutParams.height = DensityUtil.dip2px(mContext, 48);
                    holder.rlItemCourse.setLayoutParams(layoutParams);
                }
            } else if (aftsize == 0) {
                if (monsize > 2) {
                    ViewGroup.LayoutParams layoutParams = holder.rlItemCourse.getLayoutParams();
                    layoutParams.height = DensityUtil.dip2px(mContext, 24 * monsize);
                    holder.rlItemCourse.setLayoutParams(layoutParams);
                } else {
                    ViewGroup.LayoutParams layoutParams = holder.rlItemCourse.getLayoutParams();
                    layoutParams.height = DensityUtil.dip2px(mContext, 48);
                    holder.rlItemCourse.setLayoutParams(layoutParams);
                }
            } else {
                if (max >= 2) {
                    ViewGroup.LayoutParams layoutParams = holder.rlItemCourse.getLayoutParams();
                    layoutParams.height = DensityUtil.dip2px(mContext, 24 * max * 2);
                    holder.rlItemCourse.setLayoutParams(layoutParams);
                } else {
                    ViewGroup.LayoutParams layoutParams = holder.rlItemCourse.getLayoutParams();
                    int px = DensityUtil.dip2px(mContext, 48);
                    layoutParams.height = px * (monsize + aftsize);
                    holder.rlItemCourse.setLayoutParams(layoutParams);
                }
            }
        }
        CourseListAdapter monAdapter = new CourseListAdapter(mContext, R.layout.item_list_course, mlist.get(position).getMon());
        CourseListAdapter aftAdapter = new CourseListAdapter(mContext, R.layout.item_list_course, mlist.get(position).getAft());
        holder.lvMonContent.setAdapter(monAdapter);
        holder.lvAftContent.setAdapter(aftAdapter);

    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.lv_mon_content)
        ListView lvMonContent;
        @BindView(R.id.lv_aft_content)
        ListView lvAftContent;
        @BindView(R.id.ll_course_week)
        LinearLayout llCourseWeek;
        @BindView(R.id.ll_mon_week)
        LinearLayout llMonWeek;
        @BindView(R.id.ll_aft_week)
        LinearLayout llAftWeek;
        @BindView(R.id.ll_have_week)
        LinearLayout llHaveWeek;
        @BindView(R.id.tv_no_course)
        TextView tvNoCourse;
        @BindView(R.id.tv_course_week)
        TextView tvCourseWeek;
        @BindView(R.id.tv_course_date)
        TextView tvCourseDate;
        @BindView(R.id.rl_item_course)
        RelativeLayout rlItemCourse;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
