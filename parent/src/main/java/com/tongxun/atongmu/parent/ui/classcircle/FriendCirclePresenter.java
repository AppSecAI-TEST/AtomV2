package com.tongxun.atongmu.parent.ui.classcircle;

import com.tongxun.atongmu.parent.BasePresenter;
import com.tongxun.atongmu.parent.model.FriendCircleModel;

import java.util.List;

/**
 * Created by Anro on 2017/7/5.
 */

public class FriendCirclePresenter extends BasePresenter<IFriendCircleContract.View> implements IFriendCircleContract.Presenter, IFriendCircleContract.Interactor.onFinishLinstener {

    private FriendInteractor interactor;

    public FriendCirclePresenter() {
        interactor=new FriendInteractor();
    }

    @Override
    public void getTopCircle() {
        interactor.getTopCircle(this);
    }

    @Override
    public void onError(String message) {

    }

    @Override
    public void onSuccess(List<FriendCircleModel> datas) {
        if(mView!=null){
            mView.setRefreshSuccess(datas);
        }
    }
}
