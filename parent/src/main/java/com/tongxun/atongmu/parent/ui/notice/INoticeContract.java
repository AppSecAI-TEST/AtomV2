package com.tongxun.atongmu.parent.ui.notice;

import com.tongxun.atongmu.parent.BaseView;
import com.tongxun.atongmu.parent.model.NoticeModel;
import com.tongxun.atongmu.parent.model.SignWaitModel;

import java.util.List;

/**
 * Created by Anro on 2017/6/30.
 */

public interface INoticeContract {
    interface View extends BaseView{
        void beginRefreshing();
        void beginLoadingMore();
        void setRefreshNoticeList(List<NoticeModel> list);
        void loadMoreNoticeList(List<NoticeModel> list);
        void setRefreshSignWaitList(List<SignWaitModel> list);
        void onError(String message);
        void onConfirmSuccess();
        void onConfirmError(String message);
    }

    interface Presenter{
        void getNotice(String type);
        void getMoreNotice(String type,String time);
        void getSignUpWaiting();
        void setConfirmSignUp(String ageId);
    }

    interface Interactor{
        void getTopNotice(String type,onFinishListener listener);
        void getMoreNotice(String type,String time,onFinishListener listener);
        void getSignUpWaiting(onFinishListener listener);
        void onConfirmSignUp(String ageId,onFinishListener listener);
        interface onFinishListener{
            void onNoticeSuccess(List<NoticeModel> list);
            void onNoticeError(String message);
            void onMoreNoticeSuccess(List<NoticeModel> list);
            void onSignUpSuccess(List<SignWaitModel> list);
            void onConfirmSuccess();
            void onConfirmError(String message);
        }
    }
}
