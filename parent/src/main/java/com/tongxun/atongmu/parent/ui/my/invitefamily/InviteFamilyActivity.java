package com.tongxun.atongmu.parent.ui.my.invitefamily;

import android.os.Bundle;

import com.tongxun.atongmu.parent.Base2Activity;
import com.tongxun.atongmu.parent.R;

public class InviteFamilyActivity extends Base2Activity<InviteFamilyContract.View,InviteFamilyPresenter> implements InviteFamilyContract.View {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invite_family);
    }

    @Override
    protected InviteFamilyPresenter initPresenter() {
        return new InviteFamilyPresenter();
    }
}
