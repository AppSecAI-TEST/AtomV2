package com.tongxun.atongmu.parent.ui.classcircle;

/**
 * Created by Anro on 2017/8/11.
 */

public interface ICircleItemDetailContract {

    interface View{

        void onLikeSuccess();

        void onRemoveListSuccess();

        void onLikeOrRemoveError(String message);

        void onCommentSuccess(String commentType, String commentId, String commentSourceName, String remarks);

        void onDeleteSuccess(int position);

        void onDeleteRemarkSuccess(int position, int pos);
    }

    interface Presenter{

        void removeItemLisk(int i, String circleId);

        void setItemLisk(int i, String circleId);

        void upShareCount(String circleId);

        void postCircleComment(String circleId, String sourcePersonId, String commentSourceName, String remarks, String commentType);

        void deleteCircle(String circleId, int i);

        void deleteRemark(String commentId, int i, int pos);
    }
}
