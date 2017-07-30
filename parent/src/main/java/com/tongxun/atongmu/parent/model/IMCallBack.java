package com.tongxun.atongmu.parent.model;

import java.util.List;

/**
 * Created by admin on 2017/7/30.
 */

public class IMCallBack {

    /**
     * groupId : 11123028983809
     * imUsername : par0000064145
     * imPassword : 123456
     * imNickName : 张路爸爸
     * contact_list : [{"username":"tec0000004115","name":"董金波老师","phone":"18912607789","avatar":""},{"username":"tec0000004117","name":"张倩倩老师","phone":"18862347893","avatar":"https://www.atongmu.net:8443/backwork/userfiles/images/kigTeacherPhoto/2017/07/f42edb31_179f_43a5_9eb7_70db44ecca39.jpg"},{"username":"tec0000004118","name":"张路老师","phone":"15850051246","avatar":"https://www.atongmu.net:8443/backwork/userfiles/images/kigTeacherPhoto/2017/04/ca1ee14b_ff5b_4e36_b0ea_8144e2457093.jpg"},{"username":"tec0000004119","name":"Anny老师","phone":"18896973509","avatar":"https://www.atongmu.net:8443/backwork/userfiles/images/kigTeacherPhoto/2017/02/cd08fc63_90ea_42fc_afb9_6b4117f0b7e0.jpg"},{"username":"tec0000004120","name":"窦晓冬老师","phone":"13601953860","avatar":"https://www.atongmu.net:8443/backwork/userfiles/images/kigTeacherPhoto/2017/03/2a81b4c0_9564_4d96_a134_92a4d400a85a.jpg"},{"username":"tec0000004121","name":"梁超贤老师","phone":"17312666725","avatar":""},{"username":"tec0000004122","name":"胡盛胜老师","phone":"18855583568","avatar":"https://www.atongmu.net:8443/backwork/userfiles/images/kigTeacherPhoto/2017/07/176e277f_e8c1_41f9_b922_91f003d88668.jpg"},{"username":"tec0000004994","name":"徐伟老师","phone":"13584672097","avatar":""},{"username":"tec0000004036","name":"Kerry老师","phone":"18006130352","avatar":"https://www.atongmu.net:8443/backwork/userfiles/images/kigTeacherPhoto/2017/04/1b3af9c4_a212_4f20_89ea_7f431ae14e95.jpg"},{"username":"tec0000004227","name":"刁刁芹老师","phone":"10000000001","avatar":""},{"username":"tec0000005120","name":"毛碧云老师","phone":"18012364520","avatar":""},{"username":"tec0000005127","name":"孟老师老师","phone":"13390881341","avatar":""},{"username":"tec0000001365","name":"孙斌斌老师","phone":"18012770352","avatar":""},{"username":"tec0000004622","name":"吴明老师","phone":"13451608523","avatar":""},{"username":"tec0000001421","name":"杨盼盼老师","phone":"18896703848","avatar":"https://www.atongmu.net:8443/backwork/userfiles/images/kigTeacherPhoto/2017/03/7d760e63_b05f_4dab_8a78_2a0123b6603b.jpg"},{"username":"tec0000004107","name":"杨正宝老师","phone":"13390897378","avatar":"https://www.atongmu.net:8443/backwork/userfiles/images/kigTeacherPhoto/2017/03/939aa526_25b3_4749_a05e_9eef3f57e1f2.jpg"},{"username":"tec0000002119","name":"印建荣老师","phone":"18015595986","avatar":""},{"username":"tec0000004541","name":"郑泽岫老师","phone":"13338695936","avatar":""},{"name":"张路爸爸","username":"par0000064145","phone":"15850051246","avatar":"https://www.atongmu.net:8443/backwork/userfiles/images/kigStudent/2017/07/6d307e76_a3b5_465c_9a00_f510889ba5fc.jpg"},{"name":"张路妈妈","username":"par0000067023","phone":"","avatar":""}]
     * status : success
     * message :
     */

    private String groupId;
    private String imUsername;
    private String imPassword;
    private String imNickName;
    private String status;
    private String message;
    private List<ContactModel> contact_list;

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getImUsername() {
        return imUsername;
    }

    public void setImUsername(String imUsername) {
        this.imUsername = imUsername;
    }

    public String getImPassword() {
        return imPassword;
    }

    public void setImPassword(String imPassword) {
        this.imPassword = imPassword;
    }

    public String getImNickName() {
        return imNickName;
    }

    public void setImNickName(String imNickName) {
        this.imNickName = imNickName;
    }

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

    public List<ContactModel> getContact_list() {
        return contact_list;
    }

    public void setContact_list(List<ContactModel> contact_list) {
        this.contact_list = contact_list;
    }


}
