package com.tongxun.atongmu.parent.ui.my.growprofile;

import com.tongxun.atongmu.parent.model.GrowProfileModel;

import java.util.List;

/**
 * Created by Anro on 2017/7/26.
 */

public interface IGrowProfileContract {
    interface View {

        void onError(String message);

        void onSuccess(List<GrowProfileModel> datas);
    }

    interface Presenter {

        void getGrowProfileList();
    }

    interface Interactor {

        void getGrowProfileList(onFinishListener listener);

        interface onFinishListener{

            void onError(String message);

            void onSuccess(List<GrowProfileModel> datas);
        }
    }
}
