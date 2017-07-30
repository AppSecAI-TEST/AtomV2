package com.tongxun.atongmu.parent.ui.my.personreminder;

import java.util.List;

/**
 * Created by Anro on 2017/7/29.
 */

public interface IAddReminderContract {
    interface View{

        void onError(String message);

        void onSuccess();

    }

    interface Presenter{

        void createNewReminder(String remark, String starttime, String days, List<String> mlist);
    }
}
