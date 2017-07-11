package com.tongxun.atongmu.parent.util;

import android.content.Context;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * Created by Anro on 2017/7/3.
 */

public class GlideOption {

    public static RequestOptions getPHOption(){
        RequestOptions options=new RequestOptions()
                .centerCrop()
                .dontAnimate()
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE);
        return options;
    }
    public static RequestOptions getRoundOption(Context context){
        RequestOptions options=new RequestOptions()
                .centerCrop()
                .dontAnimate()
                .bitmapTransform(new RoundedCornersTransformation(context, 30, 0, RoundedCornersTransformation.CornerType.BOTTOM))
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE);
        return options;
    }
}
