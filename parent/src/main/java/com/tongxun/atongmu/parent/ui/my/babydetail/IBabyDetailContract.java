package com.tongxun.atongmu.parent.ui.my.babydetail;

import com.tongxun.atongmu.parent.BaseView;

/**
 * Created by Anro on 2017/7/28.
 */

public interface IBabyDetailContract {
    interface View extends BaseView{

        void onError(String message);

        void onSuccess();

        void onBirthSuccess();

    }

    interface Presenter{

        void setBabyFace(String path);

        void postBirthDay(String pickDate);
    }

    interface Interactor{

        void setBabyFace(String path,onFinishListener listener);

        void postBirthDay(String pickDate, onFinishListener listener);

        interface onFinishListener{

            void onError(String message);

            void onSuccess();

            void onBirthSuccess();
        }
    }
}
