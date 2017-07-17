package com.tongxun.atongmu.parent.ui.Introduction;

import com.tongxun.atongmu.parent.BaseView;
import com.tongxun.atongmu.parent.model.TeacherStyleModel;

import java.util.List;

/**
 * Created by Anro on 2017/7/14.
 */

public interface ISchoolIntroductionContract {
    interface View<T> extends BaseView{
       void setPresenter(T presenter);

        void onError(String message);

        void onRefreshAdapter(List<TeacherStyleModel> data);
    }

    interface Presenter{

        void getTeacherStyleList();
    }

    interface Interactor{
        void getTeacherStyleList(onFinishListener listener);



        interface onFinishListener{

            void onError(String message);

            void onSuccess(List<TeacherStyleModel> data);
        }
    }
}
