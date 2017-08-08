package com.tongxun.atongmu.teacher.ui.personsign;

import com.tongxun.atongmu.teacher.model.TeacherSignModel;

import java.util.List;

/**
 * Created by Anro on 2017/8/8.
 */

public interface ITeacherSignContract {
    interface View{

        void onError(String message);

        void onSuccess(List<TeacherSignModel> arrived, List<TeacherSignModel> unArrived);
    }

    interface Presenter{

        void getTeacherSign(String classcId, String selectDate);
    }

    interface Interactor{

        void getTeacherSign(String classcId, String selectDate,onFinishListener listener);

        interface onFinishListener{

            void onError(String message);

            void onSuccess(List<TeacherSignModel> arrived, List<TeacherSignModel> unArrived);
        }
    }
}
