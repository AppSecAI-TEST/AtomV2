package com.hyphenate.easeui.utils;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.hyphenate.easeui.R;

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
                .placeholder(R.drawable.icon_notice_no_img)
                .error(R.drawable.icon_notice_no_img)
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE);
        return options;
    }

    public static RequestOptions getFaceHolderOption(){
        RequestOptions options=new RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.icon_default)
                .error(R.drawable.icon_default)
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
