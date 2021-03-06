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
    //上传分享
    @Override
    public void upShareCount(String circleId) {
        interactor.upShareCount(circleId,this);
    }
    //上传评论
    @Override
    public void postCircleComment(String circleId, String sourcePersonId,String commentSourceName, String remarks, String commentType) {
        interactor.postCircleComment(circleId,sourcePersonId,commentSourceName,remarks,commentType,this);
    }
    //加载更多圈子
    @Override
    public void loadMoreCircle(String createDate) {
        interactor.loadMoreCircle(createDate,this);
    }
    //删除圈子
    @Override
    public void deleteCircle(String circleId, int position) {
        interactor.deleteCircle(circleId,position,this);
    }
    //删除评论

    @Override
    public void deleteRemark(String commentId, int position, int pos) {
        interactor.deleteRemark(commentId,position,pos,this);
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
            mView.onLikeOrRemoveError(message);
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
    //评论成功
    @Override
    public void onCommentSuccess(String commentType, String commentId, String commentSourceName, String remarks) {
        if(mView!=null){
            mView.onCommentSuccess(commentType,commentId,commentSourceName,remarks);
        }
    }

    @Override
    public void onLoadMoreSuccess(List<FriendCircleModel> datas) {
        if(mView!=null){
            mView.setLoadMoreSuccess(datas);
        }
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
