package com.tongxun.atongmu.parent.ui.my.shuttlephoto;

import com.tongxun.atongmu.parent.BaseView;

/**
 * Created by Anro on 2017/7/27.
 */

public interface IShuttleEditContract {

    interface View extends BaseView{

        void onError(String message);

        void onSuccess();

        void onSaveSuccess();
    }

    interface Presenter{

        void postSignPhoto(String targetId, String path);

        void saveCardNum(String targetId, String cardStatus, String cardNum);
    }

    interface Interactor{

        void postSignPhoto(String targetId, String path,onFinishListener listener);

        void saveCardNum(String targetId, String cardStatus, String cardNum, onFinishListener listener);

        interface onFinishListener{
            void onSuccess();
            void onError(String message);

            void onSaveSuccess();

        }
    }
}
