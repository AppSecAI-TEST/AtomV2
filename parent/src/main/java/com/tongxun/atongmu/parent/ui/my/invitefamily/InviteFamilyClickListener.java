package com.tongxun.atongmu.parent.ui.my.invitefamily;

import com.tongxun.atongmu.parent.model.InviteFamilyBindModel;

/**
 * Created by Anro on 2017/8/7.
 */

public interface InviteFamilyClickListener  {
    void onAddFamily(String relation);

    void onLookDetail(InviteFamilyBindModel model);

    void onRemindAction(InviteFamilyBindModel model);
}
