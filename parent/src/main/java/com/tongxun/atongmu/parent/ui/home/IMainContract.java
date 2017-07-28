package com.tongxun.atongmu.parent.ui.home;

import com.tongxun.atongmu.parent.model.BannerDataBean;
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

        void onBannerSuccess(List<BannerDataBean> data);
    }

    interface Presenter {

        void getModuleList();

        void getBannerList();

        void getTipList();
    }

    interface Interactor{
        void getModuleList(onFinishListener listener);

        void getBannerList(onFinishListener listener);

        void getTipList(onFinishListener listener);

        interface onFinishListener{

            void onError(String message);

            void onSuccess(List<ModuleModel> data);

            void onBannerSuccess(List<BannerDataBean> data);
        }
    }
}
