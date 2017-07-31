package com.tongxun.atongmu.parent.ui.homework;

import com.tongxun.atongmu.parent.BaseView;

import java.util.List;

/**
 * Created by Anro on 2017/7/5.
 */

public interface IComepleteWorkContract {
    interface View extends BaseView{

        void onError(String message);
    }

    interface Presenter{
        void commitHomework(String content, List<String> filelist, boolean isOss, String path, String name);
    }

    interface Interactor{

        void getCommitOssPath(onFinishListener listener);

        void putOssFile(String savePath, String path, String name,onFinishListener listener);

        interface onFinishListener{

            void onError(String message);

            void ongetPathSuccess(String savePath);
        }
    }
}
