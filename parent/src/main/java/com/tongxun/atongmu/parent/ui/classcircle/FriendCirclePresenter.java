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
    //判断家长能否发布圈子
    @Override
    public void getParentIsCanPutCircle() {
        interactor.getParentIsCanPutCircle(this);
    }

    @Override
    public void onError(String message) {
        if(mView!=null){
            mView.onError(message);
        }
    }

    @Override
    public void onSuccess(String currentNickName, List<FriendCircleModel> datas) {
        if(mView!=null){
            mView.setRefreshSuccess(currentNickName,datas);
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

    @Override
    public void onCanPutFail() {
        if(mView!=null){
            mView.onCanPutFail();
        }
    }

    @Override
    public void onCanPutSuccess() {
        if(mView!=null){
            mView.onCanPutSuccess();
        }
    }


}
