package com.tongxun.atongmu.parent.ui.album;

import com.tongxun.atongmu.parent.BaseView;
import com.tongxun.atongmu.parent.model.TimeAlbumModel;

import java.util.List;

/**
 * Created by Anro on 2017/7/12.
 */

public interface ITimeAlbumContract {
    interface View extends BaseView{
        void onError(String message);
        void setDates(List<TimeAlbumModel> datas);
    }

    interface Presenter{

        void getTimeAlbum();
    }

    interface Interactor{
        void getTimeAlbum(onFinishListener listener);
        interface onFinishListener{
            void onSuccess(List<TimeAlbumModel> datas);
            void onError(String message);
        }
    }
}
