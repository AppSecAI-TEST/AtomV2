package com.tongxun.atongmu.parent.ui.babysign;

/**
 * Created by Anro on 2017/7/13.
 */

public class BabySignInteractor implements IBabySignInContract.Interactor {
    @Override
    public void getSignInRecord(String format, onFinishListener listener) {
        listener.onSignInRecordSuccess();
    }
}
