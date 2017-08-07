package com.tongxun.atongmu.parent.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by admin on 2017/8/6.
 */

public class InviteFamilyBindModel implements Parcelable {
    /**
     * personName : 张路爸爸
     * personPhone : 15850051246
     * showCount : 0
     * password : 123456
     * level : LV0
     * headImage : https://www.atongmu.net:8443/backwork/userfiles/images/kigStudent/2017/08/a25123d1_3ecb_43fe_98c7_36cbf3ac7cb2.jpg
     * personId : b1a6273498de462f948ee9285fecb8d0
     * cardStatus : haveCard
     * cardNumber : 4113779889
     * relation : 爸爸
     */

    private String personName;
    private String personPhone;
    private String showCount;
    private String password;
    private String level;
    private String headImage;
    private String personId;
    private String cardStatus;
    private String cardNumber;
    private String relation;

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getPersonPhone() {
        return personPhone;
    }

    public void setPersonPhone(String personPhone) {
        this.personPhone = personPhone;
    }

    public String getShowCount() {
        return showCount;
    }

    public void setShowCount(String showCount) {
        this.showCount = showCount;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getHeadImage() {
        return headImage;
    }

    public void setHeadImage(String headImage) {
        this.headImage = headImage;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getCardStatus() {
        return cardStatus;
    }

    public void setCardStatus(String cardStatus) {
        this.cardStatus = cardStatus;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.personName);
        dest.writeString(this.personPhone);
        dest.writeString(this.showCount);
        dest.writeString(this.password);
        dest.writeString(this.level);
        dest.writeString(this.headImage);
        dest.writeString(this.personId);
        dest.writeString(this.cardStatus);
        dest.writeString(this.cardNumber);
        dest.writeString(this.relation);
    }

    public InviteFamilyBindModel() {
    }

    protected InviteFamilyBindModel(Parcel in) {
        this.personName = in.readString();
        this.personPhone = in.readString();
        this.showCount = in.readString();
        this.password = in.readString();
        this.level = in.readString();
        this.headImage = in.readString();
        this.personId = in.readString();
        this.cardStatus = in.readString();
        this.cardNumber = in.readString();
        this.relation = in.readString();
    }

    public static final Parcelable.Creator<InviteFamilyBindModel> CREATOR = new Parcelable.Creator<InviteFamilyBindModel>() {
        @Override
        public InviteFamilyBindModel createFromParcel(Parcel source) {
            return new InviteFamilyBindModel(source);
        }

        @Override
        public InviteFamilyBindModel[] newArray(int size) {
            return new InviteFamilyBindModel[size];
        }
    };
}
