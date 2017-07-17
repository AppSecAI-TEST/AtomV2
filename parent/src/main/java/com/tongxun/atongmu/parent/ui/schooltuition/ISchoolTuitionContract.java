package com.tongxun.atongmu.parent.ui.schooltuition;

import com.tongxun.atongmu.parent.BaseView;
import com.tongxun.atongmu.parent.model.TuitionModel;

import java.util.List;

/**
 * Created by Anro on 2017/7/17.
 */

public interface ISchoolTuitionContract {

    interface View extends BaseView{

        void onError(String message);

        void onPayNoticeSuccess(List<TuitionModel> datas);
    }

    interface Presenter{

        void getPayNotice();
    }

    interface Interactor{
        void getPayNotice(onFinishListener listener);
        interface onFinishListener{

            void onError(String message);

            void onSuccess(List<TuitionModel> datas);
        }
    }
}
