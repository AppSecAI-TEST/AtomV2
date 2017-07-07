package com.tongxun.atongmu.parent.model;

import java.util.List;

/**
 * Created by Anro on 2017/7/5.
 */

public class HomeworkNofinishCallBack {

    /**
     * status : success
     * message :
     * datas : [{"photoList":["http://127.0.0.1:10010/backwork/userfiles/images/kigJob/2017/05/6a0cdfc1_4075_4f49_8797_4d5aefb5522e.jpeg"],"photoSize":1,"videoImg":"","voiceLength":"","haveVoice":"false","content":"本周作业:回去和爸爸妈妈一起做\u201c溶解\u201d实验，并用自己的方式记录实验结果，下周来园与大家分享你的实验经历！实验材料不限制，但是，请一定要做一下\u201c糖果\u201d的溶解实验！","voiceUrl":"","jobId":"ec7cf9a0b89f4c85b335c3c295d84e66","teaName":"李蒙蒙老师","videoUrl":"","haveVideo":"false","teaImg":"","createDate":"2017-05-19 12:03:04"},{"photoList":["http://127.0.0.1:10010/backwork/userfiles/images/kigJob/2017/04/d9f84437_efb4_4f7f_b01b_388001ed74a6.jpeg"],"photoSize":1,"videoImg":"","voiceLength":"","haveVoice":"false","content":"亲爱的宝妈宝爸下午好，宝贝的书包里放了作业本，请宝爸宝妈们和宝贝一起完成作业24-26页，星期一的时候麻烦放到班级的桌子上哦，在这里提前祝大家五一小长假愉快啦🌹🌹🌹🌹","voiceUrl":"","jobId":"6b1660531f6e437d86d660a7339735ff","teaName":"李燕老师","videoUrl":"","haveVideo":"false","teaImg":"","createDate":"2017-04-28 17:44:19"},{"photoList":["http://127.0.0.1:10010/backwork/userfiles/images/kigJob/2017/04/d9f7abba_5988_494c_8f39_9341bfd4e367.jpeg"],"photoSize":1,"videoImg":"","voiceLength":"","haveVoice":"false","content":"亲爱的宝爸宝妈们下午好，宝贝们的书包里放了书本，请和宝贝一起完成18-20页，周一记得带来放到班级的桌上哦🌹🌹祝大家周末愉快","voiceUrl":"","jobId":"763859b96bd74a7dbea17501c1fdf094","teaName":"李燕老师","videoUrl":"","haveVideo":"false","teaImg":"","createDate":"2017-04-21 16:19:23"}]
     */

    private String status;
    private String message;
    private List<HomeworkNoFinishModel> datas;

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

    public List<HomeworkNoFinishModel> getDatas() {
        return datas;
    }

    public void setDatas(List<HomeworkNoFinishModel> datas) {
        this.datas = datas;
    }


}
