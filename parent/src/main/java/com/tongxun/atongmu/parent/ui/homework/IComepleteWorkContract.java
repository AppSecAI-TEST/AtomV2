package com.tongxun.atongmu.parent.ui.homework;

import com.tongxun.atongmu.parent.BaseView;

import java.util.List;

/**
 * Created by Anro on 2017/7/5.
 */

public interface IComepleteWorkContract {
    interface View extends BaseView{

        void onError(String message);

        void onCommitSuccess();

    }

    interface Presenter{
        void commitHomework(String jobId, String content, List<String> filelist, boolean isOss, String type, String path, String name, String mAduiloLength,String videoImageUrl);
    }

    interface Interactor{

        void getCommitOssPath(onFinishListener listener);

        void putOssFile(String savePath, String path, String name,onFinishListener listener);

        void commitHomework(String jobId, String content, List<String> filelist, String type, String osspath, String mAduiloLength, String videoImageUrl,onFinishListener listener);

        interface onFinishListener{

            void onError(String message);

            void ongetPathSuccess(String savePath);

            void onPutFileSuccess();

            void onCommitSuccess();
        }
    }
}
