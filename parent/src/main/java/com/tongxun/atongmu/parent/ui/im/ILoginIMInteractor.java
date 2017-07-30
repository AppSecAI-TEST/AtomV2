package com.tongxun.atongmu.parent.ui.im;

/**
 * Created by admin on 2017/7/30.
 */

public interface ILoginIMInteractor {
    void LoginIM(String tokenId,onFinishListener listener);
    interface onFinishListener{

        void onError(String message);

        void onIMSuccess(String groupId, String imNickName, String imUsername, String imPassword);
    }
}
