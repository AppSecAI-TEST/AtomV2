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
    public void setItemLisk(int position, String sourceId) {
        interactor.setItemList(position,sourceId,this);
    }

    @Override
    public void removeItemLisk(int position, String sourceId) {
        interactor.removeItemList(position,sourceId,this);
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

    @Override
    public void onLikeSuccess(int position) {
        if(mView!=null){
            mView.onLikeSuccess(position);
        }
    }

    /**
     * 取消点赞成功
     * @param position
     */
    @Override
    public void onRemoveListSuccess(int position) {
        if(mView!=null){
            mView.onRemoveListSuccess(position);
        }
    }

    @Override
    public void onLikeOrRemoveError(String message) {
        if(mView!=null){
            mView.onLikeOrRemoveError();
        }
    }


}
