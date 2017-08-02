package com.tongxun.atongmu.teacher.ui.notice;

import com.tongxun.atongmu.teacher.model.NoticeNewsModel;

/**
 * Created by Anro on 2017/8/2.
 */

public interface INoticeNewsContract {
    interface View{


        void onError(String message);

        void onSuccess(NoticeNewsModel data);
    }

    interface Presenter{
        void getWebNoticeRedPoint();
    }

    interface Interactor{

        void getWebNoticeRedPoint(onFinishListener listener);

        interface onFinishListener{

            void onError(String message);

            void onSuccess(NoticeNewsModel data);
        }
    }
}
