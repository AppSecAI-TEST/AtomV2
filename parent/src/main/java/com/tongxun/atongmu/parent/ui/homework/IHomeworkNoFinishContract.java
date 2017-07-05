package com.tongxun.atongmu.parent.ui.homework;

/**
 * Created by Anro on 2017/7/4.
 */

public interface IHomeworkNoFinishContract {
    interface Presenter{
        void getNoFinishHomeWork();

    }

    interface View<T> {
        void setPresenter(T presenter);
        void setData();
    }

    interface Interactor{
        void getNoFinishHomework(onFinishListener listener);

        interface onFinishListener{
            void onSuccess();
            void onError(String message);
        }
    }
}
