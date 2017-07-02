package com.tongxun.atongmu.parent.util;

import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.tencent.qzone.QZone;
import cn.sharesdk.wechat.friends.Wechat;

/**
 * Created by admin on 2017/7/1.
 */

public class ShareUtil {
    /**
     * 分享到QQ空间
     * @param title
     * @param content
     * @param titleUrl
     * @param imgUrl
     */
    public static void shareToQQZone(String title,String content,String titleUrl,String imgUrl){
        Platform.ShareParams sp = new Platform.ShareParams();
        sp.setTitle(title);
        sp.setTitleUrl(titleUrl);
        sp.setText(content);
        sp.setImageUrl(imgUrl);
        sp.setSite("阿童目");
        sp.setSiteUrl("http://www.atongmu.net/");
        Platform qzone = ShareSDK.getPlatform(QZone.NAME);
        // 设置分享事件回调（注：回调放在不能保证在主线程调用，不可以在里面直接处理UI操作）
        qzone.setPlatformActionListener(new PlatformActionListener() {
            public void onError(Platform arg0, int arg1, Throwable arg2) {
                //失败的回调，arg:平台对象，arg1:表示当前的动作，arg2:异常信息
            }

            public void onComplete(Platform arg0, int arg1, HashMap arg2) {
                //分享成功的回调
            }

            public void onCancel(Platform arg0, int arg1) {
                //取消分享的回调
            }
        });
        // 执行图文分享
        qzone.share(sp);
    }

    public static void shareToQQ(String title,String content,String titleUrl,String imgUrl){
        Platform.ShareParams sp = new Platform.ShareParams();
        sp.setTitle(title);
        sp.setTitleUrl(titleUrl);
        sp.setText(content);
        sp.setImageUrl(imgUrl);
        sp.setSite("阿童目");
        sp.setSiteUrl("http://www.atongmu.net/");
        Platform qq = ShareSDK.getPlatform(QQ.NAME);
        // 设置分享事件回调（注：回调放在不能保证在主线程调用，不可以在里面直接处理UI操作）
        qq.setPlatformActionListener(new PlatformActionListener() {
            public void onError(Platform arg0, int arg1, Throwable arg2) {
                //失败的回调，arg:平台对象，arg1:表示当前的动作，arg2:异常信息
            }

            public void onComplete(Platform arg0, int arg1, HashMap arg2) {
                //分享成功的回调
            }

            public void onCancel(Platform arg0, int arg1) {
                //取消分享的回调
            }
        });
        // 执行图文分享
        qq.share(sp);
    }

    public static void shareToWx(String title,String content,String titleUrl,String imgUrl){
        Platform.ShareParams sp = new Platform.ShareParams();
        sp.setTitle(title);
        sp.setTitleUrl(titleUrl);
        sp.setText(content);
        sp.setImageUrl(imgUrl);
        sp.setSite("阿童目");
        sp.setSiteUrl("http://www.atongmu.net/");
        Platform  wx= ShareSDK.getPlatform(Wechat.NAME);
        // 设置分享事件回调（注：回调放在不能保证在主线程调用，不可以在里面直接处理UI操作）
        wx.setPlatformActionListener(new PlatformActionListener() {
            public void onError(Platform arg0, int arg1, Throwable arg2) {
                //失败的回调，arg:平台对象，arg1:表示当前的动作，arg2:异常信息
            }

            public void onComplete(Platform arg0, int arg1, HashMap arg2) {
                //分享成功的回调
            }

            public void onCancel(Platform arg0, int arg1) {
                //取消分享的回调
            }
        });
        // 执行图文分享
        wx.share(sp);
    }


}
