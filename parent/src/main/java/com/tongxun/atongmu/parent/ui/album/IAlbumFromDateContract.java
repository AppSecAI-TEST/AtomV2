package com.tongxun.atongmu.parent.ui.album;

import com.tongxun.atongmu.parent.BaseView;
import com.tongxun.atongmu.parent.model.AlbumFromDateModel;

import java.util.List;

/**
 * Created by Anro on 2017/7/13.
 */

public interface IAlbumFromDateContract {

    interface View extends BaseView{

        void onRefreshAdapter(List<AlbumFromDateModel> datas);
        void onError(String message);
    }

    interface Presenter{

        void getAlbumFromDate(String date);
    }

    interface Interactor{
        void getAlbumFromDate(String date,onFinishListener listener);
        interface onFinishListener{
            void onSuccess(List<AlbumFromDateModel> datas);
            void onError(String string);
        }
    }

}
