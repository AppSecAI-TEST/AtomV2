package com.tongxun.atongmu.parent.ui.my.feedback;

import com.tongxun.atongmu.parent.BaseView;

import java.util.List;

/**
 * Created by Anro on 2017/7/21.
 */

public interface IOpinionFeedBackContract {

    interface View extends BaseView{

        void onError(String message);

        void onSuccess();

    }

    interface Presenter{

        void sendToAtom(String commentText, String phoneModel, String phoneVersion, String appVersion, List<String> mlist);

        void sendToGarten(String commentText,List<String> mlist);
    }

    interface Interactor{
        void sendToAtom(String commentText, String phoneModel, String phoneVersion, String appVersion, List<String> mlist,onFinishListener listener);

        void sendToGarten(String commentText, List<String> mlist, onFinishListener listener);

        interface onFinishListener{

            void onError(String message);

            void onSuccess();

        }
    }
}
