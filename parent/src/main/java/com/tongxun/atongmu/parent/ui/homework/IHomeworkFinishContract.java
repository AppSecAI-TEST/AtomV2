package com.tongxun.atongmu.parent.ui.homework;

import com.tongxun.atongmu.parent.model.FinishWorkModel;

import java.util.List;

/**
 * Created by Anro on 2017/7/5.
 */

public interface IHomeworkFinishContract {
    interface Presenter {

        void getFinishHomeworkDate();

        void getFinishHomeworkFromDate(int position, String date);
    }

    interface View<T> {
        void setPresenter(T presenter);

        void onError(String message);

        void onMonSuccess(List<String> datas);

        void onHomeWorkSuccess(int position, List<FinishWorkModel> datas);
    }

    interface Interactor {
        void getFinishHomeworkDate(onFinishListener listener);
        void getFinishHomeworkFromDate(int position, String date, onFinishListener listener);
        interface onFinishListener {
            void onGetDateSuccess();
            void onError(String message);

            void onMonSuccess(List<String> datas);

            void onHomeWorkSuccess(int position, List<FinishWorkModel> datas);
        }
    }
}
