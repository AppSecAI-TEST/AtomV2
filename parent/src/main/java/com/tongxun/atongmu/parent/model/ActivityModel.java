package com.tongxun.atongmu.parent.model;

/**
 * Created by Anro on 2017/7/5.
 */

public class ActivityModel {
    /**
     * id : ad6774a73ba946e78b5dce20ca10d821
     * isNewRecord : true
     * createDate : 2017-04-19 11:29:52
     * updateDate : 2017-04-19 11:29:52
     * classcId : fcd3914667c54c249a3fb3708945cb7f
     * photoMin : https://www.atongmu.net:8443/backwork/userfiles/images/kigNew/2017/01/0c2da665_2314_4292_afaa_fce411774abf.jpg
     * photoBody : https://www.atongmu.net:8443/backwork/userfiles/images/kigNew/2017/01/6a0657b8_e17a_40f9_b7c9_828084e3ed92.jpg
     * title : 后台发布的活动 有图
     * context :
     * checked : 1
     * startDate : 2017-01-19
     * endDate : 2017-01-24
     * kindgId : f63fec1722f246619dfc8d0796923e40
     * htmlPath : https://www.atongmu.net:8443/backwork/rest/kig/restShowActivity_v2?activityId=ad6774a73ba946e78b5dce20ca10d821
     * contractPerson : 傻大方火烧
     * contractPhone : 135535
     * kindgName : A阿童目新幼儿园QQQ
     * teacherId : 8641282d4cf549ae86270c00ce1ed6bb
     * noRead : true
     * activityPersonStatusId : e9264b3e315d4603b49b9fb21e7cd030
     * actManCount : 0
     * havenAct : false
     * personCode : STU0000013759
     * teacherName : 卞宝林
     * remarks :
     * havenActStudents : [{"id":"ba3e752eccc747f8aed2f930d98d9d01","isNewRecord":false,"personCode":"STU0000006996","personName":"测试","photo1":"/backwork/userfiles/images/kigStudent/2016/09/22ed1785_3f66_48de_826a_f48527bcacc2.jpg"},{"id":"464db2a574c64a568cb176cba5937e33","isNewRecord":false,"personCode":"STU0000013346","personName":"刁语萱","photo1":"/backwork/userfiles/images/kigStudent/2017/04/bdd2ad62_375f_46c5_9fae_f68f6af036aa.jpg"},{"id":"098835047E0A0C4CA0DF7412A5AA761C","isNewRecord":false,"personCode":"STU0000006978","personName":"董金波","photo1":"/backwork/userfiles/images/kigStudent/2017/01/ffc26e92_68f8_442d_8564_f46865bf9c6a.jpg"},{"id":"74206C2BBF95AB408B891B057A52BA13","isNewRecord":false,"personCode":"STU0000006984","personName":"窦晓冬","photo1":"/backwork/userfiles/images/kigStudent/2016/07/26f616d4_ef57_4497_94ce_63981d2d2230.png"}]
     */

    private boolean isNewRecord;
    private String createDate;
    private String updateDate;
    private String classcId;
    private String photoMin;
    private String photoBody;
    private String title;
    private String context;
    private String checked;
    private String startDate;
    private String endDate;
    private String kindgId;
    private String htmlPath;
    private String contractPerson;
    private String contractPhone;
    private String kindgName;
    private String teacherId;
    private String noRead;
    private String activityPersonStatusId;
    private int actManCount;
    private boolean havenAct;
    private String personCode;
    private String teacherName;
    private String remarks;


    public boolean isIsNewRecord() {
        return isNewRecord;
    }

    public void setIsNewRecord(boolean isNewRecord) {
        this.isNewRecord = isNewRecord;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getClasscId() {
        return classcId;
    }

    public void setClasscId(String classcId) {
        this.classcId = classcId;
    }

    public String getPhotoMin() {
        return photoMin;
    }

    public void setPhotoMin(String photoMin) {
        this.photoMin = photoMin;
    }

    public String getPhotoBody() {
        return photoBody;
    }

    public void setPhotoBody(String photoBody) {
        this.photoBody = photoBody;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getChecked() {
        return checked;
    }

    public void setChecked(String checked) {
        this.checked = checked;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getKindgId() {
        return kindgId;
    }

    public void setKindgId(String kindgId) {
        this.kindgId = kindgId;
    }

    public String getHtmlPath() {
        return htmlPath;
    }

    public void setHtmlPath(String htmlPath) {
        this.htmlPath = htmlPath;
    }

    public String getContractPerson() {
        return contractPerson;
    }

    public void setContractPerson(String contractPerson) {
        this.contractPerson = contractPerson;
    }

    public String getContractPhone() {
        return contractPhone;
    }

    public void setContractPhone(String contractPhone) {
        this.contractPhone = contractPhone;
    }

    public String getKindgName() {
        return kindgName;
    }

    public void setKindgName(String kindgName) {
        this.kindgName = kindgName;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getNoRead() {
        return noRead;
    }

    public void setNoRead(String noRead) {
        this.noRead = noRead;
    }

    public String getActivityPersonStatusId() {
        return activityPersonStatusId;
    }

    public void setActivityPersonStatusId(String activityPersonStatusId) {
        this.activityPersonStatusId = activityPersonStatusId;
    }

    public int getActManCount() {
        return actManCount;
    }

    public void setActManCount(int actManCount) {
        this.actManCount = actManCount;
    }

    public boolean isHavenAct() {
        return havenAct;
    }

    public void setHavenAct(boolean havenAct) {
        this.havenAct = havenAct;
    }

    public String getPersonCode() {
        return personCode;
    }

    public void setPersonCode(String personCode) {
        this.personCode = personCode;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

}
