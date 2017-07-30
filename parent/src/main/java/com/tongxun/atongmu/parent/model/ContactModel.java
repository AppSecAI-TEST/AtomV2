package com.tongxun.atongmu.parent.model;

import org.litepal.crud.DataSupport;

/**
 * Created by admin on 2017/7/30.
 */

public class ContactModel extends DataSupport {
    /**
     * username : tec0000004115
     * name : 董金波老师
     * phone : 18912607789
     * avatar :
     */

    private String username;
    private String name;
    private String phone;
    private String avatar;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
