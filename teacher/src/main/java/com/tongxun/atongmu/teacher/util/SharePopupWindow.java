package com.tongxun.atongmu.teacher.util;

import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.tongxun.atongmu.teacher.R;
import com.tongxun.atongmu.teacher.application.TeacherApplication;


/**
 * Created by Anro on 2017/7/3.
 */

public class SharePopupWindow implements View.OnClickListener, ShareUtil.IShareListener {

    private static PopupWindow pop = null;
    private TextView tvShareQQ = null;
    private TextView tvShareQQZone= null;
    private TextView tvShareWeChat= null;
    private TextView tvShareWeChatCircle= null;
    private TextView tvCancel=null;
    private View view=null;
    private LinearLayout ll_share;

    private SharePopupWindow() {
        if (pop == null) {
            view = LayoutInflater.from(TeacherApplication.getContext()).inflate(R.layout.share_popup_layout, null);
            pop = new PopupWindow(view, ViewPager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT, true);
        //    pop.setBackgroundDrawable(new BitmapDrawable());
            pop.setFocusable(true);
            pop.setOutsideTouchable(false);
            tvShareQQ= (TextView) view.findViewById(R.id.tv_share_qq);
            tvShareQQZone= (TextView) view.findViewById(R.id.tv_share_qqzone);
            tvShareWeChat= (TextView) view.findViewById(R.id.tv_share_wechat);
            tvShareWeChatCircle= (TextView) view.findViewById(R.id.tv_share_wechat_circle);
            tvCancel= (TextView) view.findViewById(R.id.tv_share_cancel);
            ll_share= (LinearLayout) view.findViewById(R.id.ll_share);


        }
        tvShareQQ.setOnClickListener(this);
        tvShareQQZone.setOnClickListener(this);
        tvShareWeChat.setOnClickListener(this);
        tvShareWeChatCircle.setOnClickListener(this);
        tvCancel.setOnClickListener(this);
    }

    private static SharePopupWindow instance;

    public static SharePopupWindow getInstance(){
        if(instance==null){
            instance=new SharePopupWindow();
        }
        return instance;
    }

    private String title;
    private String content;
    private String titleUrl;
    private String imageUrl;


    public void show(View v,String title, String content, String titleUrl, String imageUrl) {
        this.title=title;
        this.content=content;
        this.titleUrl=titleUrl;
        this.imageUrl=imageUrl;

        ll_share.startAnimation(AnimationUtils.loadAnimation(TeacherApplication.getContext(),R.anim.popup_translate_up));
        int navigationBar = 0;
        if (NavigationBarUtil.checkDeviceHasNavigationBar(TeacherApplication.getContext())) {
            navigationBar = NavigationBarUtil.getNavigationBarHeight(TeacherApplication.getContext());
        }

        pop.showAtLocation(v, Gravity.BOTTOM, 0, navigationBar);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_share_qq:
                ShareUtil.shareToQQ(title,content,titleUrl,imageUrl,this);
                break;
            case R.id.tv_share_qqzone:
                ShareUtil.shareToQQZone(title,content,titleUrl,imageUrl,this);
                break;
            case R.id.tv_share_wechat:
                ShareUtil.shareToWx(title,content,titleUrl,imageUrl,this);
                break;
            case R.id.tv_share_wechat_circle:
                ShareUtil.shareToWxCircle(title,content,titleUrl,imageUrl,this);
                break;
            case R.id.tv_share_cancel:
                pop.dismiss();
                break;
        }
        pop.dismiss();
    }


    @Override
    public void onShareSuccess() {

    }

    @Override
    public void onError() {

    }

    @Override
    public void onCancel() {

    }
}
