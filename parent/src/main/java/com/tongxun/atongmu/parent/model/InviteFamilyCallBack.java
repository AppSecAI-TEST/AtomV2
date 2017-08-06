package com.tongxun.atongmu.parent.model;

import java.util.List;

/**
 * Created by admin on 2017/8/6.
 */

public class InviteFamilyCallBack {

    /**
     * bindingData : [{"personName":"张路爸爸","personPhone":"15850051246","showCount":"0","password":"123456","level":"LV0","headImage":"https://www.atongmu.net:8443/backwork/userfiles/images/kigStudent/2017/08/a25123d1_3ecb_43fe_98c7_36cbf3ac7cb2.jpg","personId":"b1a6273498de462f948ee9285fecb8d0","cardStatus":"haveCard","cardNumber":"4113779889","relation":"爸爸"},{"personName":"mm","personPhone":"","showCount":"0","password":"123456","level":"LV0","headImage":"https://www.atongmu.net:8443/backwork/userfiles/images/userPhoto/woman.png","personId":"1a5101d07a41451b88b05c7ddc5e7efc","cardStatus":"unActivation","cardNumber":"0000555368","relation":"妈妈"}]
     * unBindingData : [{"headImage":"https://www.atongmu.net:8443/backwork/userfiles/images/userPhoto/old_man.png","relation":"爷爷"},{"headImage":"https://www.atongmu.net:8443/backwork/userfiles/images/userPhoto/old_woman.png","relation":"奶奶"},{"headImage":"https://www.atongmu.net:8443/backwork/userfiles/images/userPhoto/old_woman.png","relation":"外婆"},{"headImage":"https://www.atongmu.net:8443/backwork/userfiles/images/userPhoto/old_man.png","relation":"外公"},{"headImage":"https://www.atongmu.net:8443/backwork/userfiles/images/userPhoto/man.png","relation":"叔叔"},{"headImage":"https://www.atongmu.net:8443/backwork/userfiles/images/userPhoto/old_woman.png","relation":"姥姥"},{"headImage":"https://www.atongmu.net:8443/backwork/userfiles/images/userPhoto/old_man.png","relation":"姥爷"}]
     * status : success
     * message :
     */

    private String status;
    private String message;
    private List<InviteFamilyBindModel> bindingData;
    private List<InviteFamilyUnBindModel> unBindingData;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<InviteFamilyBindModel> getBindingData() {
        return bindingData;
    }

    public void setBindingData(List<InviteFamilyBindModel> bindingData) {
        this.bindingData = bindingData;
    }

    public List<InviteFamilyUnBindModel> getUnBindingData() {
        return unBindingData;
    }

    public void setUnBindingData(List<InviteFamilyUnBindModel> unBindingData) {
        this.unBindingData = unBindingData;
    }


}
