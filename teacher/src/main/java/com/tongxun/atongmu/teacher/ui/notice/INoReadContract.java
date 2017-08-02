package com.tongxun.atongmu.teacher.ui.notice;

/**
 * Created by Anro on 2017/8/2.
 */

public interface INoReadContract {
    interface View{

    }

    interface Presenter{

        void getNoReadList(String action);
    }

    interface Interactor{
        void getNoReadList(String action,onFinishListener listener);

        interface onFinishListener{

        }
    }
}
