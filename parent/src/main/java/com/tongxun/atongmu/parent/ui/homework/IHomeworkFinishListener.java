package com.tongxun.atongmu.parent.ui.homework;

import android.widget.ImageView;

/**
 * Created by Anro on 2017/8/5.
 */

public interface IHomeworkFinishListener {

   void onShare(int groupPosition,int itemPosition);

    void onDelete(int groupPosition, int childPosition);

    void onPlayAudio(int groupPosition, int childPosition, String videoUrl, ImageView ivVoiceAnim);

    void onPlayVideo(int groupPosition, int childPosition);

    void onPlayCommentAudio(int groupPosition, int childPosition, String commentMedia, ImageView ivCommentVoiceAnim);
}
