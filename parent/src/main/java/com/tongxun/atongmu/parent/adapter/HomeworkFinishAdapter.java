package com.tongxun.atongmu.parent.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.tongxun.atongmu.parent.R;
import com.tongxun.atongmu.parent.model.HomeFinishListModel;

import java.util.List;

/**
 * Created by Anro on 2017/8/1.
 */

public class HomeworkFinishAdapter extends BaseExpandableListAdapter {

    private Context mContext;
    private List<HomeFinishListModel> mGroupList;

    public HomeworkFinishAdapter(Context context, List<HomeFinishListModel> groupArray) {
        mGroupList=groupArray;
        mContext=context;
    }

    @Override
    public int getGroupCount() {
        return mGroupList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mGroupList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupHolder holder=null;
        if(convertView==null){
            convertView= LayoutInflater.from(mContext).inflate(R.layout.item_finish_homework_layout,parent,false);
            holder=new GroupHolder();
            holder.mMonth= (TextView) convertView.findViewById(R.id.id_month);
            convertView.setTag(holder);
        }else {
            holder= (GroupHolder) convertView.getTag();
        }
        holder.mMonth.setText(mGroupList.get(groupPosition).getDate());
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildHolder holder=null;
        if(convertView==null){
            convertView= LayoutInflater.from(mContext).inflate(R.layout.item_month_homework_layout,parent,false);
            holder=new ChildHolder();
            holder.rv_course_content= (RecyclerView) convertView.findViewById(R.id.rv_course_content);
            convertView.setTag(holder);
        }else {
            holder= (ChildHolder) convertView.getTag();
        }


        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    class GroupHolder{
        public TextView mMonth;
    }

    class ChildHolder{
        RecyclerView rv_course_content;
    }
}
