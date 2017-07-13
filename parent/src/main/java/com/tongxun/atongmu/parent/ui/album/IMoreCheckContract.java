package com.tongxun.atongmu.parent.ui.album;

import com.tongxun.atongmu.parent.model.MornCheckModel;

import java.util.List;

/**
 * Created by Anro on 2017/7/13.
 */

public interface IMoreCheckContract {
    interface View{
        void beginRefresh();
        void beginLoadMore();

        void setRefreshSuccess(List<MornCheckModel> datas);

        void onError(String string);
    }

    interface Presenter{

        void getMoreCheckAlbum(String date);

        void getTopMornCheckAlbum();
    }

    interface Interactor{
        void  getTopMornCheckAlbum(onFinishListener listener);
        interface onFinishListener{
            void onSuccess(List<MornCheckModel> datas);
            void onError(String string);
        }
    }
}
