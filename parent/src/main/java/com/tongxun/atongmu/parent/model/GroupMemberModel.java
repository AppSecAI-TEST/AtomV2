package com.tongxun.atongmu.parent.model;

/**
 * Created by Anro on 2017/7/12.
 */

public class GroupMemberModel {
    /**
     * name : 阿童目
     * avatar : http://127.0.0.1:10010/backwork/static/images/1.jpg
     * level : LV0
     * relationship : 爸爸
     * phone : 136666666666
     */

    private String name;
    private String avatar;
    private String level;
    private String relationship;
    private String phone;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
