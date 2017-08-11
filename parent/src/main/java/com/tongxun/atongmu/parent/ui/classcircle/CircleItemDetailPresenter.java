package com.tongxun.atongmu.parent.ui.classcircle;

import com.tongxun.atongmu.parent.BasePresenter;
import com.tongxun.atongmu.parent.model.FriendCircleModel;

import java.util.List;

/**
 * Created by Anro on 2017/8/11.
 */

public class CircleItemDetailPresenter extends BasePresenter<ICircleItemDetailContract.View> implements ICircleItemDetailContract.Presenter, IFriendCircleContract.Interactor.onFinishLinstener {

    private FriendInteractor interactor;
    public CircleItemDetailPresenter() {
        interactor=new FriendInteractor();
    }


    @Override
    public void removeItemLisk(int i, String circleId) {
        interactor.removeItemList(i,circleId,this);
    }

    @Override
    public void setItemLisk(int i, String circleId) {
        interactor.setItemList(i,circleId,this);
    }

    @Override
    public void upShareCount(String circleId) {
        interactor.upShareCount(circleId,this);
    }

    @Override
    public void postCircleComment(String circleId, String sourcePersonId, String commentSourceName, String remarks, String commentType) {
        interactor.postCircleComment(circleId,sourcePersonId,commentSourceName,remarks,commentType,this);
    }

    @Override
    public void deleteCircle(String circleId, int i) {
        interactor.deleteCircle(circleId,i,this);
    }

    @Override
    public void deleteRemark(String commentId, int i, int pos) {
        interactor.deleteRemark(commentId,i,pos,this);
    }

    @Override
    public void onError(String message) {

    }

    @Override
    public void onSuccess(String currentNickName, List<FriendCircleModel> datas) {

    }

    @Override
    public void onLikeSuccess(int position) {
        if(mView!=null){
            mView.onLikeSuccess();
        }
    }

    @Override
    public void onRemoveListSuccess(int position) {
        if(mView!=null){
            mView.onRemoveListSuccess();
        }
    }

    @Override
    public void onLikeOrRemoveError(String message) {
        if(mView!=null){
            mView.onLikeOrRemoveError(message);
        }
    }

    @Override
    public void onCanPutFail() {

    }

    @Override
    public void onCanPutSuccess() {

    }

    @Override
    public void onCommentSuccess(String commentType, String commentId, String commentSourceName, String remarks) {
        if(mView!=null){
            mView.onCommentSuccess(commentType,commentId,commentSourceName,remarks);
        }
    }

    @Override
    public void onLoadMoreSuccess(List<FriendCircleModel> datas) {

    }

    @Override
    public void onDeleteSuccess(int position) {
        if(mView!=null){
            mView.onDeleteSuccess(position);
        }
    }

    @Override
    public void onDeleteRemarkSuccess(int position, int pos) {
        if(mView!=null){
            mView.onDeleteRemarkSuccess(position,pos);
        }
    }


}
