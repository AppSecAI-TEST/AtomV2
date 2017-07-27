package com.tongxun.atongmu.parent.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.tongxun.atongmu.parent.IonItemClickListener;
import com.tongxun.atongmu.parent.R;
import com.tongxun.atongmu.parent.model.GrowProfileModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Anro on 2017/7/27.
 */

public class GrowProfileAdapter extends PagerAdapter {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_num)
    TextView tvNum;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_sex)
    TextView tvSex;
    @BindView(R.id.tv_age)
    TextView tvAge;
    @BindView(R.id.tv_open_file)
    TextView tvOpenFile;
    @BindView(R.id.iv_question)
    ImageView ivQuestion;
    private List<GrowProfileModel> mlist;
    private Context mContext;
    View[] views;

    private IonItemClickListener mListener;


    public GrowProfileAdapter(Context context, List<GrowProfileModel> list, IonItemClickListener Listener) {
        mlist = list;
        mContext = context;
        views = new View[mlist.size()];
        mListener = Listener;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_grow_profile, container, false);
        ButterKnife.bind(this, view);
        tvTitle.setText(mlist.get(position).getTitle());
        tvTime.setText(mlist.get(position).getDate());

        tvName.setText(mlist.get(position).getName());
        tvAge.setText(mlist.get(position).getAge());
        tvSex.setText(mlist.get(position).getSex());

        container.addView(view);
        views[position] = view;
        tvOpenFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onItemClick(position);
                }
            }
        });
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(views[position]);
    }

    @Override
    public int getCount() {
        return mlist.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public float getPageWidth(int position) {
        return 0.85f;
    }


}