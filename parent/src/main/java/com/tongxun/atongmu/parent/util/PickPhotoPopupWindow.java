package com.tongxun.atongmu.parent.util;

import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.tongxun.atongmu.parent.R;
import com.tongxun.atongmu.parent.application.ParentApplication;

import static com.tongxun.atongmu.parent.R.id.ll_popup;

/**
 * Created by Anro on 2017/7/24.
 */

public class PickPhotoPopupWindow implements View.OnClickListener, PopupWindow.OnDismissListener {

    private Button itemPopupCamera;
    private Button itemPopupsPhoto;
    private View itmeVideoLine;
    private Button itemPopupVideo;
    LinearLayout llPopup;
    Button itemPopupCancel;
    LinearLayout itemLlPopup;
    private PopupWindow pop = null;
    private View view = null;
    private popClickListener mlistener;

    private static PickPhotoPopupWindow instance;


    public static PickPhotoPopupWindow getInstance() {
        if (instance == null) {
            instance = new PickPhotoPopupWindow();
        }
        return instance;
    }

    public PickPhotoPopupWindow() {
        if (pop == null) {
            view = LayoutInflater.from(ParentApplication.getContext()).inflate(R.layout.pick_photo_popup_layout, null);

            pop = new PopupWindow(view, ViewPager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT, true);
            //    pop.setBackgroundDrawable(new BitmapDrawable());
            pop.setFocusable(true);
            pop.setOutsideTouchable(false);
            itemPopupCamera = (Button) view.findViewById(R.id.item_popup_camera);
            itemPopupsPhoto = (Button) view.findViewById(R.id.item_popups_photo);
            itemPopupVideo = (Button) view.findViewById(R.id.item_popup_video);
            itemPopupCancel = (Button) view.findViewById(R.id.item_popup_cancel);
            llPopup = (LinearLayout) view.findViewById(ll_popup);
            itemLlPopup = (LinearLayout) view.findViewById(R.id.item_ll_popup);
            itmeVideoLine = view.findViewById(R.id.itme_video_line);
        }
        itemPopupCamera.setOnClickListener(this);
        itemPopupsPhoto.setOnClickListener(this);
        itemPopupVideo.setOnClickListener(this);
        itemPopupCancel.setOnClickListener(this);
        pop.setOnDismissListener(this);

    }

    public void show(View v, boolean isHaveVideo,popClickListener listener) {
        mlistener=listener;
        if (isHaveVideo) {
            itemPopupVideo.setVisibility(View.VISIBLE);
            itmeVideoLine.setVisibility(View.VISIBLE);
        } else {
            itemPopupVideo.setVisibility(View.GONE);
            itmeVideoLine.setVisibility(View.GONE);
        }
        llPopup.startAnimation(AnimationUtils.loadAnimation(ParentApplication.getContext(), R.anim.popup_translate_up));

        int navigationBar = 0;
        if (NavigationBarUtil.checkDeviceHasNavigationBar(ParentApplication.getContext())) {
            navigationBar = NavigationBarUtil.getNavigationBarHeight(ParentApplication.getContext());
        }

        pop.showAtLocation(v, Gravity.BOTTOM, 0, navigationBar);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.item_popup_camera:
                if(mlistener!=null){
                    mlistener.onCamera();
                }
                pop.dismiss();
                break;
            case R.id.item_popups_photo:
                if(mlistener!=null){
                    mlistener.onPhoto();
                }
                pop.dismiss();
                break;
            case R.id.item_popup_video:
                if(mlistener!=null){
                    mlistener.onVideo();
                }
                pop.dismiss();
                break;
            case R.id.item_popup_cancel:
                pop.dismiss();
                break;
        }
    }

    @Override
    public void onDismiss() {
        if(mlistener!=null){
            mlistener=null;
        }
    }


    public interface popClickListener {
        void onCamera();

        void onPhoto();

        void onVideo();
    }
}
