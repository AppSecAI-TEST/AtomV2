package com.tongxun.atongmu.teacher.model;

import java.util.List;

/**
 * Created by Anro on 2017/8/3.
 */

public interface IDynamicContract {
    interface View{

        void onError(String message);

        void onSuccess(List<DynamicModel> datas);
    }

    interface Presenter{

        void getDynamicList();
    }

    interface Interactor{
        void getDynamicList(onFinishListener listener);
        interface onFinishListener{

            void onError(String message);

            void onSuccess(List<DynamicModel> datas);
        }
    }
}
