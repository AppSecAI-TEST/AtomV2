package com.tongxun.atongmu.parent.ui.im;

import com.tongxun.atongmu.parent.BaseView;
import com.tongxun.atongmu.parent.model.GroupMemberModel;

import java.util.List;

/**
 * Created by Anro on 2017/7/11.
 */

public interface IGroupMemberContract {
    interface View extends BaseView{

        void onError(String message);

        void setRefreshAdapter(List<GroupMemberModel> parentdatas, List<GroupMemberModel> teacherdatas);
    }

    interface Presenter{

        void getGroupMember();
    }

    interface Interactor{
        void getGroupMember(onFinishListener listener);

        interface onFinishListener{
            void onSuccess(List<GroupMemberModel> parentdatas, List<GroupMemberModel> teacherdatas);
            void onError(String message);
        }
    }
}
