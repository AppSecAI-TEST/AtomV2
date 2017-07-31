package com.tongxun.atongmu.parent.ui;

import java.util.List;

/**
 * Created by admin on 2017/7/28.
 */

public interface IAddCirclePhotoContract {

    interface View{

        void onSuccess();

        void onError(String message);
    }

    interface Presenter{

        void postTimeAlbum(String string, List<String> mlist);
    }

    interface Interactor{

        void postTimeAlbum(String content, List<String> mlist, onFinishListener listener);

        interface onFinishListener{

            void onError(String message);

            void onSuccess();
        }
    }
}
