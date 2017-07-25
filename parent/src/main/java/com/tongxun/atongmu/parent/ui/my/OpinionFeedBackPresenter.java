package com.tongxun.atongmu.parent.ui.my;

import com.tongxun.atongmu.parent.BasePresenter;

import java.util.List;

/**
 * Created by Anro on 2017/7/21.
 */

public class OpinionFeedBackPresenter extends BasePresenter<IOpinionFeedBackContract.View> implements IOpinionFeedBackContract.Presenter, IOpinionFeedBackContract.Interactor.onFinishListener {

    OpinionFeedBackInteractor interactor;

    public OpinionFeedBackPresenter() {
        interactor = new OpinionFeedBackInteractor();
    }


    @Override
    public void sendToAtom(String commentText, String phoneModel, String phoneVersion, String appVersion, List<String> mlist) {
        interactor.sendToAtom(commentText, phoneModel, phoneVersion, appVersion, mlist, this);
    }
}
