package com.tongxun.atongmu.parent.model;

import java.util.List;

/**
 * Created by Anro on 2016/12/16.
 */

public class FeedBackRecord {

    /**
     * datas : [{"suggestId":"09b2c0965ed842c58fb31643fffce067","suggestDate":"2016-12-16 13:27:43","suggestText":"yiyi","photos":[{"photo":"http://www.atongmu.net:10010/backwork/userfiles/images/kigUserSuggest/2016/12/90aefeb3_e6aa_44cd_b126_13454f78afbe.jpg"}],"comment":""},{"suggestId":"35bebc7632514e69af897b7093289136","suggestDate":"2016-12-16 11:50:12","suggestText":"ooo","photos":[{"photo":"http://www.atongmu.net:10010/backwork/userfiles/images/kigUserSuggest/2016/12/bce28467_9d94_4227_b946_030af41f59ce.jpg"},{"photo":"http://www.atongmu.net:10010/backwork/userfiles/images/kigUserSuggest/2016/12/98f73084_fc93_466f_9b25_9d92a0642f14.jpg"},{"photo":"http://www.atongmu.net:10010/backwork/userfiles/images/kigUserSuggest/2016/12/f628f592_f835_4a84_8450_fbd3e4bcd4cd.jpg"}],"comment":"啊哈后发货的合法化收到回复　红烧豆腐华盛顿和粉红色大好时光发送的话安徽天后他瑞安人吴天昊公布的身份噶二胎已乱如何管理和是可敬的　　的覆盖件莱卡复活。、ａ／高度开发机构的。、　肯定是飞机哥就是的飞韩国、空间的身份及韩国哈迪ｏ／／、可是对方过后爱上对方给ｈ／。加上丢给他修啊，卡萨丁股好了，建设部等放假不干净安徽二房就是否合格，圣诞节房管局哈市就。"},{"suggestId":"8c8a7b3cf1bb4b399c0ee14766bd1f96","suggestDate":"2016-12-16 13:31:12","suggestText":"ooooo","photos":[{"photo":"http://www.atongmu.net:10010/backwork/userfiles/images/kigUserSuggest/2016/12/f773aba1_461e_49fa_b35b_6b4856c57168.jpg"}],"comment":""},{"suggestId":"d558d379d60c431bac510f0b84808324","suggestDate":"2016-12-16 12:40:17","suggestText":"oooooo","photos":[],"comment":""}]
     * status : success
     * message :
     */

    private String status;
    private String message;
    /**
     * suggestId : 09b2c0965ed842c58fb31643fffce067
     * suggestDate : 2016-12-16 13:27:43
     * suggestText : yiyi
     * photos : [{"photo":"http://www.atongmu.net:10010/backwork/userfiles/images/kigUserSuggest/2016/12/90aefeb3_e6aa_44cd_b126_13454f78afbe.jpg"}]
     * comment :
     */

    private List<DatasBean> datas;

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

    public List<DatasBean> getDatas() {
        return datas;
    }

    public void setDatas(List<DatasBean> datas) {
        this.datas = datas;
    }

    public static class DatasBean {
        private String suggestId;
        private String suggestDate;
        private String suggestText;
        private String comment;
        /**
         * photo : http://www.atongmu.net:10010/backwork/userfiles/images/kigUserSuggest/2016/12/90aefeb3_e6aa_44cd_b126_13454f78afbe.jpg
         */

        private List<PhotosBean> photos;

        public String getSuggestId() {
            return suggestId;
        }

        public void setSuggestId(String suggestId) {
            this.suggestId = suggestId;
        }

        public String getSuggestDate() {
            return suggestDate;
        }

        public void setSuggestDate(String suggestDate) {
            this.suggestDate = suggestDate;
        }

        public String getSuggestText() {
            return suggestText;
        }

        public void setSuggestText(String suggestText) {
            this.suggestText = suggestText;
        }

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }

        public List<PhotosBean> getPhotos() {
            return photos;
        }

        public void setPhotos(List<PhotosBean> photos) {
            this.photos = photos;
        }

        public static class PhotosBean {
            private String photo;

            public String getPhoto() {
                return photo;
            }

            public void setPhoto(String photo) {
                this.photo = photo;
            }
        }
    }
}
