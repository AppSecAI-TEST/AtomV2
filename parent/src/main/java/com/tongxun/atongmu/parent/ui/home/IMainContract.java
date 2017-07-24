package com.tongxun.atongmu.parent.ui.home;

import com.tongxun.atongmu.parent.model.ModuleModel;

import java.util.List;

/**
 * Created by Anro on 2017/7/12.
 */

public interface IMainContract {
    interface View<T>{
        void setPresenter(T presenter);

        void onError(String message);

        void onModuleSuccess(List<ModuleModel> data);
    }

    interface Presenter {

        void getModuleList();
    }

    interface Interactor{
        void getModuleList(onFinishListener listener);
        interface onFinishListener{

            void onError(String message);

            void onSuccess(List<ModuleModel> data);
        }
    }
}
