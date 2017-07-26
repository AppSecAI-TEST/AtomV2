package com.tongxun.atongmu.parent.ui.my.growprofile;

/**
 * Created by Anro on 2017/7/26.
 */

public interface IGrowProfileContract {
    interface View {

    }

    interface Presenter {

        void getGrowProfileList();
    }

    interface Interactor {

        void getGrowProfileList(onFinishListener listener);

        interface onFinishListener{

        }
    }
}
