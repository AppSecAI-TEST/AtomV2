package com.tongxun.atongmu.parent.ui.my.feedback;

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

    @Override
    public void sendToGarten(String commentText, List<String> mlist) {
        interactor.sendToGarten(commentText,mlist, this);
    }

    @Override
    public void onError(String message) {
        if(mView!=null){
            mView.onError(message);
        }
    }

    @Override
    public void onSuccess() {
        if(mView!=null){
            mView.onSuccess();
        }
    }


}
