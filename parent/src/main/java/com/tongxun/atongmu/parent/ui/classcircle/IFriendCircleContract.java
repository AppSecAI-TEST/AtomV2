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
        void setRefreshSuccess(List<FriendCircleModel> datas);

        void onLikeSuccess(int position);
        void onLikeOrRemoveError();

        void onRemoveListSuccess(int position);

        void onError(String message);
    }

    interface Presenter{
        void getTopCircle();
        void setItemLisk(int position,String sourceId);
        void removeItemLisk(int position,String sourceId);

        void getParentIsCanPutCircle(String classId);
    }

    interface Interactor{
        void getTopCircle(onFinishLinstener linstener);
        void getParentIsCanPutCircle(String classId ,onFinishLinstener linstener);
        void setItemList(int position,String sourceId,onFinishLinstener linstener);
        void removeItemList(int position,String sourceId,onFinishLinstener linstener);
        interface onFinishLinstener{
            void onError(String message);
            void onSuccess(List<FriendCircleModel> datas);
            void onLikeSuccess(int position);
            void onRemoveListSuccess(int position);
            void onLikeOrRemoveError(String message);
        }
    }
}
