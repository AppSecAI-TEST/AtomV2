package com.tongxun.atongmu.teacher.ui.notice;

import com.tongxun.atongmu.teacher.model.ActivityHaveActModel;
import com.tongxun.atongmu.teacher.model.ActivityNoReadModel;

import java.util.List;

/**
 * Created by Anro on 2017/8/2.
 */

public interface INoReadContract {
    interface View{

        void onError(String message);

        void onSuccess(List datas);

        void onActivitySuccess(List<ActivityHaveActModel> havenActStudents, List<ActivityNoReadModel> noReadActStudents);
    }

    interface Presenter{

        void getNoReadList(String action, String sourceId);
    }

    interface Interactor{
        void getNoReadList(String action, String sourceId, onFinishListener listener);

        interface onFinishListener{

            void onError(String message);

            void onSuccess(List noReadStudents);

            void onActiviytSuccess(List<ActivityHaveActModel> havenActStudents, List<ActivityNoReadModel> noReadActStudents);
        }
    }
}
