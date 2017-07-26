package com.tongxun.atongmu.parent.ui.my.shuttlephoto;

import com.tongxun.atongmu.parent.model.ShuttlePhotoModel;

import java.util.List;

/**
 * Created by admin on 2017/7/26.
 */

public interface IShuttlePhotoContract {

    interface View{

        void onError(String message);

        void onSuccess(List<ShuttlePhotoModel> data);
    }
    interface Presenter{

        void getShuttlePhoto();
    }

    interface Interactor{
        void getShuttlePhoto(onFinishListener listener);
        interface onFinishListener{

            void onError(String message);

            void onSuccess(List<ShuttlePhotoModel> data);
        }
    }
}
