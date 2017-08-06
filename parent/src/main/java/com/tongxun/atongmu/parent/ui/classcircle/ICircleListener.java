package com.tongxun.atongmu.parent.ui.classcircle;

/**
 * Created by Anro on 2017/7/6.
 */

public interface ICircleListener {
    void vote(int position);
    void share(int position);
    void onPhotoClick(int groupPosition,int itemPosition);
}
