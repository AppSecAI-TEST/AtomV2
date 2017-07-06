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
    }

    interface Presenter{
        void getTopCircle();
    }

    interface Interactor{
        void getTopCircle(onFinishLinstener linstener);
        interface onFinishLinstener{
            void onError(String message);

            void onSuccess(List<FriendCircleModel> datas);
        }
    }
}
