package com.tongxun.atongmu.parent.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tongxun.atongmu.parent.R;
import com.tongxun.atongmu.parent.model.ModuleModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Anro on 2017/7/24.
 */

public class ModuleAdapter extends RecyclerView.Adapter<ModuleAdapter.ModuleViewHolder> {



    private Context mContext;
    private List<ModuleModel> mlist = new ArrayList();

    private moduleClickListener mlistener;

    public ModuleAdapter(Context context, List<ModuleModel> list, moduleClickListener listener) {
        mContext = context;
        mlist = list;
        mlistener = listener;
    }

    @Override
    public ModuleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_module_layout, parent, false);
        ModuleViewHolder viewHolder = new ModuleViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ModuleViewHolder holder, final int position) {
        holder.mainGridItemText.setText(mlist.get(position).getEnName());
        if (mlist.get(position).isHavenNewRecord()) {
            holder.mainGridRedPoint.setVisibility(View.VISIBLE);
        } else {
            holder.mainGridRedPoint.setVisibility(View.INVISIBLE);
        }
        holder.mainGridItemImg.setImageResource(getModelPhoto(Integer.parseInt(mlist.get(position).getEnId())));
        holder.llItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mlistener!=null){
                    holder.mainGridRedPoint.setVisibility(View.INVISIBLE);
                    mlistener.onItemClick(mlist.get(position).getEnId());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    class ModuleViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.main_grid_item_img)
        ImageView mainGridItemImg;
        @BindView(R.id.main_grid_red_point)
        ImageView mainGridRedPoint;
        @BindView(R.id.main_grid_item_text)
        TextView mainGridItemText;
        @BindView(R.id.ll_item)
        LinearLayout llItem;
        public ModuleViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    private int getModelPhoto(int i) {
        int res = 0;
        switch (i) {
            case 1:
                res = R.drawable.tongzhihuodong;
                break;
           /* case 2:
                res=R.drawable.xiaoyuanxinwen;
                break;*/
            case 3:
                res = R.drawable.zuoyekecheng;
                break;
            case 4:
                res = R.drawable.xiaoyuanjianjie;
                break;
            case 5:
                res = R.drawable.shipu;
                break;
            case 6:
                res = R.drawable.qiandao;
                break;
            case 7:
                res=R.drawable.xiangce;
                break;
           case 8:
                res=R.drawable.jiankang;
                break;
            case 9:
                res=R.drawable.icon_happy_area;
                break;
            case 10:
                res=R.drawable.xiaoche;
                break;
           /* case 11:
                res=R.drawable.xingqubuluo;
                break;
            case 12:
                res=R.drawable.shangcheng;
                break;*/
            case 13:
                res=R.drawable.realtime_video;
                break;
            case 15:
                res=R.drawable.icon_pm_25;
                break;
            case 16:
                res=R.drawable.icon_class_circle;
                break;
            case 17:
                res=R.drawable.icon_school_tution;
                break;
            default:
                res = R.drawable.icon_default;
                break;
        }
        return res;
    }

    public  interface moduleClickListener{
        void onItemClick(String moduleId);
    }
}
