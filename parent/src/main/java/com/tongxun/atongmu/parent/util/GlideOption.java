package com.tongxun.atongmu.parent.util;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

/**
 * Created by Anro on 2017/7/3.
 */

public class GlideOption {

    public static RequestOptions getPHOption(){
        RequestOptions options=new RequestOptions()
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE);
        return options;
    }
}
