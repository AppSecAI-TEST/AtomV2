package com.tongxun.atongmu.teacher.ui.personsign;

/**
 * Created by Anro on 2017/8/4.
 */

public interface IPersonContract {
    interface View{

    }

    interface Presenter{

        void getPersonSign();
    }

    interface Interactor{
        void getPersonSign(onFinishListener listener);
        interface onFinishListener{

        }
    }

}
