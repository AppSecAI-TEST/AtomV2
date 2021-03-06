package com.tongxun.atongmu.parent.util;

import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.tongxun.atongmu.parent.R;
import com.tongxun.atongmu.parent.application.ParentApplication;

/**
 * Created by Anro on 2017/7/3.
 */

public class SharePopupWindow implements View.OnClickListener {

    private static PopupWindow pop = null;
    private TextView tvShareQQ = null;
    private TextView tvShareQQZone= null;
    private TextView tvShareWeChat= null;
    private TextView tvShareWeChatCircle= null;
    private TextView tvCancel=null;
    private View view=null;
    private LinearLayout ll_share;
    private ShareUtil.IShareListener mlIShareListener=null;

    private SharePopupWindow() {
        if (pop == null) {
            view = LayoutInflater.from(ParentApplication.getContext()).inflate(R.layout.share_popup_layout, null);
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
        this.show(v,title,content,titleUrl,imageUrl,null);
    }

    public void show(View v,String title, String content, String titleUrl, String imageUrl,ShareUtil.IShareListener iShareListener) {
        this.title=title;
        this.content=content;
        this.titleUrl=titleUrl;
        this.imageUrl=imageUrl;
        mlIShareListener=iShareListener;
        ll_share.startAnimation(AnimationUtils.loadAnimation(ParentApplication.getContext(),R.anim.popup_translate_up));
        int navigationBar = 0;
        if (NavigationBarUtil.checkDeviceHasNavigationBar(ParentApplication.getContext())) {
            navigationBar = NavigationBarUtil.getNavigationBarHeight(ParentApplication.getContext());
        }

        pop.showAtLocation(v, Gravity.BOTTOM, 0, navigationBar);
        pop.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                mlIShareListener=null;
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_share_qq:
                ShareUtil.shareToQQ(title,content,titleUrl,imageUrl,mlIShareListener);
                break;
            case R.id.tv_share_qqzone:
                ShareUtil.shareToQQZone(title,content,titleUrl,imageUrl,mlIShareListener);
                break;
            case R.id.tv_share_wechat:
                ShareUtil.shareToWx(title,content,titleUrl,imageUrl,mlIShareListener);
                break;
            case R.id.tv_share_wechat_circle:
                ShareUtil.shareToWxCircle(title,content,titleUrl,imageUrl,mlIShareListener);
                break;
            case R.id.tv_share_cancel:
                pop.dismiss();
                break;
        }
        pop.dismiss();
    }




}
