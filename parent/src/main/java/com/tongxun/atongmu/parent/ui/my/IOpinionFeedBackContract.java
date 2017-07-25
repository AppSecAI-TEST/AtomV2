package com.tongxun.atongmu.parent.ui.my;

import com.tongxun.atongmu.parent.BaseView;

import java.util.List;

/**
 * Created by Anro on 2017/7/21.
 */

public interface IOpinionFeedBackContract {

    interface View extends BaseView{

    }

    interface Presenter{

        void sendToAtom(String commentText, String phoneModel, String phoneVersion, String appVersion, List<String> mlist);
    }

    interface Interactor{
        void sendToAtom(String commentText, String phoneModel, String phoneVersion, String appVersion, List<String> mlist,onFinishListener listener);
        interface onFinishListener{

        }
    }
}
