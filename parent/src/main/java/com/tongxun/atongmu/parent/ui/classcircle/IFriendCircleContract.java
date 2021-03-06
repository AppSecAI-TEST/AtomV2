package com.tongxun.atongmu.parent.ui.classcircle;

import com.tongxun.atongmu.parent.model.FriendCircleModel;

import java.util.List;

/**
 * Created by Anro on 2017/7/5.
 */

public interface IFriendCircleContract {
    interface View{
        void beginRefreshing();
        void beginLoadingMore();
        void setRefreshSuccess(String currentNickName, List<FriendCircleModel> datas);

        void onLikeSuccess(int position);
        void onLikeOrRemoveError(String message);

        void onRemoveListSuccess(int position);

        void onError(String message);

        void onCanPutFail();

        void onCanPutSuccess();

        void onCommentSuccess(String commentType, String commentId, String commentSourceName, String remarks);

        void setLoadMoreSuccess(List<FriendCircleModel> datas);

        void onDeleteSuccess(int position);

        void onDeleteRemarkSuccess(int position, int pos);
    }

    interface Presenter{
        void getTopCircle();
        void setItemLisk(int position,String sourceId);
        void removeItemLisk(int position,String sourceId);

        void getParentIsCanPutCircle();

        void upShareCount(String circleId);

        void postCircleComment(String circleId, String sourcePersonId,String commentSourceName, String remarks, String commentType);

        void loadMoreCircle(String createDate);

        void deleteCircle(String circleId, int position);

        void deleteRemark(String commentId, int position, int pos);
    }



    interface Interactor{
        void getTopCircle(onFinishLinstener linstener);
        void getParentIsCanPutCircle(onFinishLinstener linstener);
        void setItemList(int position,String sourceId,onFinishLinstener linstener);
        void removeItemList(int position,String sourceId,onFinishLinstener linstener);

        void upShareCount(String circleId,onFinishLinstener linstener);

        void postCircleComment(String circleId, String sourcePersonId,String commentSourceName, String remarks, String commentType, onFinishLinstener linstener);

        void loadMoreCircle(String createDate,onFinishLinstener listener);

        void deleteCircle(String circleId, int position, onFinishLinstener listener);

        void deleteRemark(String commentId, int position, int pos, onFinishLinstener listener);

        interface onFinishLinstener{
            void onError(String message);
            void onSuccess(String currentNickName, List<FriendCircleModel> datas);
            void onLikeSuccess(int position);
            void onRemoveListSuccess(int position);
            void onLikeOrRemoveError(String message);
            void onCanPutFail();

            void onCanPutSuccess();

            void onCommentSuccess(String commentType, String commentId, String commentSourceName, String remarks);

            void onLoadMoreSuccess(List<FriendCircleModel> datas);

            void onDeleteSuccess(int position);

            void onDeleteRemarkSuccess(int position, int pos);
        }
    }
}
