package com.tongxun.atongmu.parent.model;

/**
 * Created by admin on 2017/7/26.
 */

public class ShuttlePhotoModel {
    /**
     * personId : c793ce16865648fa9ce3cc8edb794fd7
     * personRelation : 爸爸
     * personDesc : 窦晓东
     * photo : http://127.0.0.1:10010/backwork/userfiles/images/kigRelationSign/2017/02/8bdee6b2_ed4f_418b_a8e0_b0a26411ddd9.jpg
     * userState : 0
     * cardNumber :
     */

    private String personId;
    private String personRelation;
    private String personDesc;
    private String photo;
    private String userState;
    private String cardDesc;
    private String cardNumber;

    public String getCardDesc() {
        return cardDesc;
    }

    public void setCardDesc(String cardDesc) {
        this.cardDesc = cardDesc;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getPersonRelation() {
        return personRelation;
    }

    public void setPersonRelation(String personRelation) {
        this.personRelation = personRelation;
    }

    public String getPersonDesc() {
        return personDesc;
    }

    public void setPersonDesc(String personDesc) {
        this.personDesc = personDesc;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getUserState() {
        return userState;
    }

    public void setUserState(String userState) {
        this.userState = userState;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }
}
