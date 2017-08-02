package com.tongxun.atongmu.teacher.ui.notice;

import java.util.List;

/**
 * Created by Anro on 2017/8/2.
 */

public interface INoticeListContract {
    interface View {

        void onError(String message);

        void onNewListSuccess(List data);

        void onLoadMoreSuccess(List data);

        void onUpRedPointSuccess();

    }

    interface Presenter {

        void getListDate(String type);

        void loadMoreListDate(String type, String createDate);

        void upRedPoint(String type, String StatusId);
    }

    interface Interactor {
        void getListDate(String type, onFinishListener listener);

        void loadMoreListDate(String type, String createDate, onFinishListener listener);

        void upRedPoint(String type, String statusId, onFinishListener listener);

        interface onFinishListener {

            void onError(String message);

            void onSuccess(List data);

            void onLoadMoreSuccess(List data);

            void onUpRedPointSuccess();

        }
    }
}
