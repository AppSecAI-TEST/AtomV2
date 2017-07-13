package com.tongxun.atongmu.parent.schoolvideo;

import com.tongxun.atongmu.parent.model.VideoListModel;

import java.util.List;

/**
 * Created by Anro on 2017/7/13.
 */

public interface IVideoListContract {

    interface View{

        void onError(String message);

        void onSuccess(List<VideoListModel> datas);

        void onSystemSuccess(String systemTime);
    }
    interface Presenter{

        void getVideoList();

        void getSystemTime();
    }

    interface Interactor{

        void getVideoList(onFinishListener listener);

        void getSystemTime(onFinishListener listener);

        interface onFinishListener{

            void onError(String message);

            void onSuccess(List<VideoListModel> datas);

            void onSystemSuccess(String systemTime);
        }
    }
}
