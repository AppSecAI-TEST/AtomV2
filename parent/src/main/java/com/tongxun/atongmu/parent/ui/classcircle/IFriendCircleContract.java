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
    }

    interface Presenter{
        void getTopCircle();
        void setItemLisk(int position,String sourceId);
        void removeItemLisk(int position,String sourceId);

    }

    interface Interactor{
        void getTopCircle(onFinishLinstener linstener);
        void setItemList(int position,String sourceId,onFinishLinstener linstener);
        void removeItemList(int postion,String sourceId,onFinishLinstener linstener);
        interface onFinishLinstener{
            void onError(String message);
            void onSuccess(List<FriendCircleModel> datas);

            void onLikeSuccess(int position);
            void onLikeOrRemoveError(String message);
        }
    }
}
