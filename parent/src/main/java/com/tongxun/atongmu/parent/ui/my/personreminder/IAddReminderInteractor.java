package com.tongxun.atongmu.parent.ui.my.personreminder;

import com.tongxun.atongmu.parent.model.MedicineModel;

import java.util.List;

/**
 * Created by Anro on 2017/7/29.
 */

public interface IAddReminderInteractor {


    void getReminderList(onFinishListener listener);

    void createNewReminder(String remark, String starttime, String days, List<String> mlist, onFinishListener listener);

    void deleteReminder(String note_id, onFinishListener listener);

    interface onFinishListener{

        void onError(String message);

        void onPostReminderSuccess();

        void onGetReminderSuccess(List<MedicineModel> medicine);

        void onDeleteReminderSuccess();
    }
}
