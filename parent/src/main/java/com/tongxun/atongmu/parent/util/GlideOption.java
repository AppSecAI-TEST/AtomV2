package com.tongxun.atongmu.parent.util;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.tongxun.atongmu.parent.R;

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

    public static RequestOptions getImageHolderOption(){
        RequestOptions options=new RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.circle_imgload)
                .error(R.drawable.circle_imgload)
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE);
        return options;
    }
    public static RequestOptions getPhotoViewOption(){
        RequestOptions options=new RequestOptions()
                .dontAnimate()
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE);
        return options;
    }
}
