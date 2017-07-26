package com.tongxun.atongmu.parent.model;

import java.util.List;

/**
 * Created by admin on 2017/7/26.
 */

public class ShuttlePhotoCallBack {


    /**
     * status : success
     * data : [{"personId":"c793ce16865648fa9ce3cc8edb794fd7","personRelation":"爸爸","personDesc":"窦晓东","photo":"http://127.0.0.1:10010/backwork/userfiles/images/kigRelationSign/2017/02/8bdee6b2_ed4f_418b_a8e0_b0a26411ddd9.jpg","userState":"0","cardNumber":""},{"personId":"37360c3697aa4988aaa8799cafe149c6","personRelation":"哥哥","personDesc":"小胡","photo":"http://127.0.0.1:10010/backwork/userfiles/images/kigRelationSign/2017/01/fe71dfa7_b21b_455d_a759_11eb5854dd0f.jpg","userState":"0","cardNumber":""},{"personId":"6816c0aec6284601affc7dc0c0534eb5","personRelation":"其他","personDesc":"测试","photo":"","userState":"0","cardNumber":""},{"personId":"7af2b81886e54bdebe84849a790af5e4","personRelation":"爷爷","personDesc":"111","photo":"","userState":"0","cardNumber":""},{"personId":"0af802764d0346ac9f3f5abe6ee13686","personRelation":"同学","personDesc":"嘻嘻嘻嘻一下","photo":"","userState":"0","cardNumber":""}]
     */

    private String status;
    private List<ShuttlePhotoModel> data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ShuttlePhotoModel> getData() {
        return data;
    }

    public void setData(List<ShuttlePhotoModel> data) {
        this.data = data;
    }


}
