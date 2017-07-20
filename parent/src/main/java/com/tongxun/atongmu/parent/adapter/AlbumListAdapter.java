package com.tongxun.atongmu.parent.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tongxun.atongmu.parent.IonItemClickListener;
import com.tongxun.atongmu.parent.R;
import com.tongxun.atongmu.parent.model.ImageLoadBean;
import com.tongxun.atongmu.parent.util.GlideOption;
import com.tongxun.atongmu.parent.widget.SquareImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Anro on 2017/7/19.
 */

public class AlbumListAdapter extends RecyclerView.Adapter<AlbumListAdapter.AlbumListViewHolder> {



    private Context mContext;
    private List<ImageLoadBean> mlist = new ArrayList<>();

    private IonItemClickListener mlistener;

    public AlbumListAdapter(Context context, List<ImageLoadBean> list,IonItemClickListener listener) {
        mContext = context;
        mlist = list;
        mlistener=listener;
    }

    @Override
    public AlbumListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_album_list_layout, parent, false);
        AlbumListViewHolder holder = new AlbumListViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(AlbumListViewHolder holder, final int position) {
        Glide.with(mContext).load(mlist.get(position).getFirstImgPath()).apply(GlideOption.getPHOption()).into(holder.sivAlbumIcon);
        holder.tvAlbumName.setText(mlist.get(position).getDirName() + "(" + mlist.get(position).getCount() + ")");
        holder.llItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mlistener!=null){
                    mlistener.onItemClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    class AlbumListViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.siv_album_icon)
        SquareImageView sivAlbumIcon;
        @BindView(R.id.tv_album_name)
        TextView tvAlbumName;
        @BindView(R.id.ll_item)
        LinearLayout llItem;
        public AlbumListViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
