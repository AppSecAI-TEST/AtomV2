package com.tongxun.atongmu.parent.ui.my.personreminder;

import com.tongxun.atongmu.parent.model.MedicineModel;

import java.util.List;

/**
 * Created by admin on 2017/7/29.
 */

public interface IReminderContract {
    interface View{

        void onError(String message);

        void onGetReminderSuccess(List<MedicineModel> medicine);

        void onDeleteReminderSuccess();
    }

    interface Presenter{

        void getReminderList();

        void deleteReminder(String note_id);
    }
}
