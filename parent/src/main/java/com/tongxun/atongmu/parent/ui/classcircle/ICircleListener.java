package com.tongxun.atongmu.parent.ui.classcircle;

/**
 * Created by Anro on 2017/7/6.
 */

public interface ICircleListener {
    void vote(int position);
    void share(int position);
    void onPhotoClick(int groupPosition,int itemPosition);
    void remark(int position);

    void delete(int position);

    void remarkBack(int position, int pos);

    void deleteRemark(int position, int pos);

    void playVideo(int position);

    void commentMore(int position);
}
