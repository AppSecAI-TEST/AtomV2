package com.tongxun.atongmu.parent.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.tongxun.atongmu.parent.R;
import com.tongxun.atongmu.parent.model.WeekBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Anro on 2017/7/4.
 */

public class CourseListAdapter extends ArrayAdapter<WeekBean> {

    private Context mContext;
    private List<WeekBean> mlist;
    private int resourceId;

    public CourseListAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<WeekBean> objects) {

        super(context, resource, objects);
        mContext = context;
        mlist = objects;
        resourceId = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder=null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_list_course, parent, false);
            viewHolder=new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder= (ViewHolder) convertView.getTag();
        }
        viewHolder.tvItemTime.setText(mlist.get(position).getTime());
        viewHolder.tvItemContent.setText(mlist.get(position).getContent());
        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.tv_item_time)
        TextView tvItemTime;
        @BindView(R.id.tv_item_content)
        TextView tvItemContent;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
