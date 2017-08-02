package com.tongxun.atongmu.teacher.ui.notice;

import com.tongxun.atongmu.teacher.Constants;

/**
 * Created by Anro on 2017/8/2.
 */

public class NoReadInteractor implements INoReadContract.Interactor {
    @Override
    public void getNoReadList(String action, onFinishListener listener) {
        String url= "";
        if(action.equals("Notice")){
            url=Constants.restGetNoticeDetail_v2;
        }
    }
}
