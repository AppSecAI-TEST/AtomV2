package com.tongxun.atongmu.parent.ui.homework;

import com.tongxun.atongmu.parent.model.HomeworkNoFinishModel;

import java.util.List;

/**
 * Created by Anro on 2017/7/4.
 */

public interface IHomeworkNoFinishContract {
    interface Presenter{
        void getNoFinishHomeWork();

    }

    interface View<T> {
        void setPresenter(T presenter);
        void setData(List<HomeworkNoFinishModel> datas);
    }

    interface Interactor{
        void getNoFinishHomework(onFinishListener listener);

        interface onFinishListener{
            void onNoFinishSuccess(List<HomeworkNoFinishModel> datas);
            void onError(String message);
        }
    }
}
