package com.tongxun.atongmu.parent.ui.homework;

import android.widget.ImageView;

/**
 * Created by Anro on 2017/7/8.
 */

public interface IHomeworkNoFinishListener  {
    void goComplete(String jobId);

    void playAudio(int position,String url, ImageView ivVoiceAnim);
}
