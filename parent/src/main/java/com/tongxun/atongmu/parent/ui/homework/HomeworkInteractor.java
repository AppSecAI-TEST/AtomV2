package com.tongxun.atongmu.parent.ui.homework;

/**
 * Created by Anro on 2017/7/4.
 */

public class HomeworkInteractor implements IHomeworkNoFinishContract.Interactor {


    @Override
    public void getNoFinishHomework(onFinishListener listener) {
        listener.onSuccess();
    }
}
