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
import com.tongxun.atongmu.parent.model.RecipesModel;
import com.tongxun.atongmu.parent.util.GlideOption;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Anro on 2017/7/11.
 */

public class RecipesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {



    private Context mContext;
    private List<RecipesModel> mlist = new ArrayList();

    private imageDetailListener mlistener=null;

    public RecipesAdapter(Context context, List list,imageDetailListener listener) {
        mlist = list;
        mContext = context;
        mlistener=listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mlist.size() == 1) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_recipes_meal, parent, false);
            MealViewHolder viewHolder = new MealViewHolder(view);
            return viewHolder;
        } else {
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_recipes_many_meal, parent, false);
            ManyMealViewHolder viewHolder = new ManyMealViewHolder(view);
            return viewHolder;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof MealViewHolder) {
            MealViewHolder viewHolder= (MealViewHolder) holder;
            Glide.with(mContext).load(mlist.get(position).getImageUrl()).apply(GlideOption.getPHOption()).into(((MealViewHolder) holder).ivIcon);
            if(mlist.size()>0){
                Glide.with(mContext).load(mlist.get(position).getImages().get(0)).apply(GlideOption.getImageHolderOption()).into(viewHolder.ivImage);
            }
            viewHolder.tvTitle.setText(mlist.get(position).getTitle());
            viewHolder.tvContent.setText(mlist.get(position).getContent());
            viewHolder.ivImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mlistener.onPhotoViewImage(mlist.get(position).getImages());
                }
            });

        }
        if (holder instanceof ManyMealViewHolder) {
            ManyMealViewHolder viewHolder= (ManyMealViewHolder) holder;
            Glide.with(mContext).load(mlist.get(position).getImageUrl()).apply(GlideOption.getPHOption()).into(viewHolder.ivIcon);
            Glide.with(mContext).load(mlist.get(position).getImages().get(0)).apply(GlideOption.getImageHolderOption()).into(viewHolder.ivImage);
            viewHolder.tvTitle.setText(mlist.get(position).getTitle());
            viewHolder.tvContent.setText(mlist.get(position).getContent());
            viewHolder.ivImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mlistener!=null){
                        mlistener.onPhotoViewImage(mlist.get(position).getImages());
                    }
                }
            });
        }


    }


    @Override
    public int getItemCount() {
        return mlist.size();
    }

    class ManyMealViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_icon)
        ImageView ivIcon;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.iv_image)
        ImageView ivImage;
        @BindView(R.id.tv_content)
        TextView tvContent;
        public ManyMealViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class MealViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_icon)
        ImageView ivIcon;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.iv_image)
        ImageView ivImage;
        @BindView(R.id.tv_content)
        TextView tvContent;

        public MealViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface imageDetailListener{
        void onPhotoViewImage(List<String> images);
    }
}
