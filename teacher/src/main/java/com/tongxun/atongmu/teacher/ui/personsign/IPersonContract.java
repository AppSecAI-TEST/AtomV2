package com.tongxun.atongmu.teacher.ui.personsign;

import com.tongxun.atongmu.teacher.model.PersonSignModel;

import java.util.List;

/**
 * Created by Anro on 2017/8/4.
 */

public interface IPersonContract {
    interface View{

        void onError(String message);

        void onPersonSignSuccess(String identity, String total, String car, String arrived, String leave, String unArrived, String arrivedPre, String unArrivedPre, String leavePre, List<PersonSignModel> datas);
    }

    interface Presenter{

        void getPersonSign(String selectDate);
    }

    interface Interactor{
        void getPersonSign(String selectDate, onFinishListener listener);
        interface onFinishListener{

            void onError(String message);

            void onPersonSignSuccess(String identity, String total, String car, String arrived, String unArrived, String leave, String arrivedPre, String unArrivedPre, String leavePre, List<PersonSignModel> datas);
        }
    }

}

