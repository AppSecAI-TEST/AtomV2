package com.tongxun.atongmu.parent.ui.homework;

import com.tongxun.atongmu.parent.model.CourseListModel;

/**
 * Created by Anro on 2017/7/4.
 */

public interface ICourseContract {

    interface Presenter{
        void getCourseInfo();

    }

    interface View<T>{
        void setPresenter(T presenter);
        void setData(CourseListModel listModel);
    }

    interface Interactor{
        void getCourseInfo(onFinishListener listener);

        interface onFinishListener{
            void onSuccess(CourseListModel listModel);
            void onError(String message);
        }
    }
}
