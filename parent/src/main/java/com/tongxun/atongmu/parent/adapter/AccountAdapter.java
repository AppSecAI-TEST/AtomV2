package com.tongxun.atongmu.parent.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.tongxun.atongmu.parent.R;
import com.tongxun.atongmu.parent.model.BabyInfoModel;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static android.R.attr.path;

/**
 * Created by Anro on 2017/7/29.
 */

public class AccountAdapter extends ArrayAdapter<BabyInfoModel> {
    private int resourceId;
    private Context mContext;
    private String mTokenId;
    public AccountAdapter(Context context, int resource, List<BabyInfoModel> objects,String tokenId) {
        super(context, resource, objects);
        resourceId=resource;
        mContext=context;
        mTokenId=tokenId;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BabyInfoModel babyDataBean=getItem(position);
        ViewHolder viewHolder=null;
        if(convertView==null){
            convertView= LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
            viewHolder=new ViewHolder();
            viewHolder.mFaceimage= (ImageView) convertView.findViewById(R.id.id_account_item_faceimg);
            viewHolder.mName= (TextView) convertView.findViewById(R.id.id_account_item_text);
            viewHolder.mCheck= (ImageView) convertView.findViewById(R.id.id_account_item_check);
            convertView.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) convertView.getTag();
        }
        viewHolder.mName.setText(babyDataBean.getPersonName());
        Glide.with(mContext)
                .load(getItem(position).getPhoto1())
                .into(viewHolder.mFaceimage);
        if(babyDataBean.getTokenId().equals(mTokenId)){
            viewHolder.mCheck.setVisibility(View.VISIBLE);
        }else {
            viewHolder.mCheck.setVisibility(View.INVISIBLE);
        }
        return convertView;
    }

    class ViewHolder{
        ImageView mFaceimage;
        TextView mName;
        ImageView mCheck;
    }
}
