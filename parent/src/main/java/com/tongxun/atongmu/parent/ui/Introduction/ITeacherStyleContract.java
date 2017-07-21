package com.tongxun.atongmu.parent.ui.Introduction;

import com.tongxun.atongmu.parent.BaseView;
import com.tongxun.atongmu.parent.model.SchoolIntroductModel;
import com.tongxun.atongmu.parent.model.TeacherStyleModel;

import java.util.List;

/**
 * Created by Anro on 2017/7/21.
 */

public class ITeacherStyleContract {
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

        void getSchoolIntroduct(onFinishListener listener);


        interface onFinishListener{

            void onError(String message);

            void onSuccess(List<TeacherStyleModel> data);

            void onIntroductSuccess(List<SchoolIntroductModel> datas);
        }
    }
}
