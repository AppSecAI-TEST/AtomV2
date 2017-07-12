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
                .placeholder(R.drawable.icon_default)
                .error(R.drawable.icon_default)
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE);
        return options;
    }

}
