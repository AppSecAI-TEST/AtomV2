package com.tongxun.atongmu.parent.model;

import java.util.List;

/**
 * Created by Anro on 2017/8/1.
 */

public class FinishWorkCallBack {

    /**
     * datas : [{"jobId":"16a7c7c0d6c24a05b5f4832839540fcc","jobStatusId":"fd2979854dd84fbcbccdd2aa983984f2","studentName":"张路","studentImage":"https://www.atongmu.net:8443/backwork/userfiles/images/kigStudent/2017/07/6d307e76_a3b5_465c_9a00_f510889ba5fc.jpg","finishTime":"2017-04-19 09:08","finishRemarks":"邋遢我哦","shareUrl":"https://www.atongmu.net:8443/backwork/rest/kig/restShowJob?jobId=fd2979854dd84fbcbccdd2aa983984f2&studentId=a6979ee3478b41c6ba504b75e7ee969e","havePhoto":"true","finishPhotos":["https://www.atongmu.net:8443/backwork/userfiles/images/kigJob/2017/04/8502b783_c617_4b93_ab6f_5b8a0349bea3.jpeg"],"haveVideo":"false","videoUrl":"","videoImg":"","haveVoice":"false","voiceLength":"","voiceUrl":"","commentTeacher":"张倩倩","commentTime":"2017-04-19 10:14","commentRate":"点击你的那些年出门都快开始了南山南\n不想念谁看的目瞪口呆了动力","commentMedia":"","commentMediaLength":"","flower":"4"}]
     * status : success
     * message :
     */

    private String status;
    private String message;
    private List<FinishWorkModel> datas;

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

    public List<FinishWorkModel> getDatas() {
        return datas;
    }

    public void setDatas(List<FinishWorkModel> datas) {
        this.datas = datas;
    }


}
