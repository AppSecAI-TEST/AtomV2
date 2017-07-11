package com.tongxun.atongmu.parent.ui.im;

import android.os.Bundle;

import com.tongxun.atongmu.parent.Base2Activity;
import com.tongxun.atongmu.parent.R;

public class GroupMemberActivity extends Base2Activity<IGroupMemberContract.View,GroupMemberPresenter> implements IGroupMemberContract.View  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_member);
    }

    @Override
    protected GroupMemberPresenter initPresenter() {
        return new GroupMemberPresenter();
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }
}
