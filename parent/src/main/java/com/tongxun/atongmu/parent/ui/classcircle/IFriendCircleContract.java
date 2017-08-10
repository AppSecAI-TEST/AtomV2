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
        void onLikeOrRemoveError();

        void onRemoveListSuccess(int position);

        void onError(String message);

        void onCanPutFail();

        void onCanPutSuccess();

        void onCommentSuccess(String commentType, String commentId, String commentSourceName, String remarks);
    }

    interface Presenter{
        void getTopCircle();
        void setItemLisk(int position,String sourceId);
        void removeItemLisk(int position,String sourceId);

        void getParentIsCanPutCircle();

        void upShareCount(String circleId);

        void postCircleComment(String circleId, String sourcePersonId,String commentSourceName, String remarks, String commentType);
    }

    interface Interactor{
        void getTopCircle(onFinishLinstener linstener);
        void getParentIsCanPutCircle(onFinishLinstener linstener);
        void setItemList(int position,String sourceId,onFinishLinstener linstener);
        void removeItemList(int position,String sourceId,onFinishLinstener linstener);

        void upShareCount(String circleId,onFinishLinstener linstener);

        void postCircleComment(String circleId, String sourcePersonId,String commentSourceName, String remarks, String commentType, onFinishLinstener linstener);

        interface onFinishLinstener{
            void onError(String message);
            void onSuccess(String currentNickName, List<FriendCircleModel> datas);
            void onLikeSuccess(int position);
            void onRemoveListSuccess(int position);
            void onLikeOrRemoveError(String message);
            void onCanPutFail();

            void onCanPutSuccess();

            void onCommentSuccess(String commentType, String commentId, String commentSourceName, String remarks);
        }
    }
}
