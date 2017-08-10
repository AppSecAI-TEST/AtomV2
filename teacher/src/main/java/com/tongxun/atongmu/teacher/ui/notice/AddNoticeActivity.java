package com.tongxun.atongmu.teacher.ui.notice;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.view.Display;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.hyphenate.easeui.widget.EmojiEditText;
import com.tongxun.atongmu.teacher.Base2Activity;
import com.tongxun.atongmu.teacher.Constants;
import com.tongxun.atongmu.teacher.R;
import com.tongxun.atongmu.teacher.application.TeacherApplication;
import com.tongxun.atongmu.teacher.util.DensityUtil;
import com.tongxun.atongmu.teacher.util.NavigationBarUtil;
import com.tongxun.atongmu.teacher.util.SDCardUtil;
import com.tongxun.atongmu.teacher.util.SystemUtil;
import com.tongxun.atongmu.teacher.util.Trans2PinYin;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;
import kr.co.namee.permissiongen.PermissionFail;
import kr.co.namee.permissiongen.PermissionGen;
import kr.co.namee.permissiongen.PermissionSuccess;

import static com.tongxun.atongmu.teacher.R.id.id_notice_content_v2_edit;
import static com.tongxun.atongmu.teacher.R.id.id_notice_title_v2_edit;

public class AddNoticeActivity extends Base2Activity<IAddNoticeContract.View, AddNoticePresenter> implements IAddNoticeContract.View {

    @BindView(R.id.tv_title_left)
    TextView tvTitleLeft;
    @BindView(R.id.tv_title_name)
    TextView tvTitleName;
    @BindView(R.id.tv_title_right)
    TextView tvTitleRight;
    @BindView(id_notice_title_v2_edit)
    EmojiEditText idNoticeTitleV2Edit;
    @BindView(id_notice_content_v2_edit)
    EmojiEditText idNoticeContentV2Edit;
    @BindView(R.id.ll_add_notice)
    LinearLayout llAddNotice;

    private String action;
    private int heightDifference;

    private PopupWindow popupWindow;
    private PopupWindow classSelectPop;

    private String fileName;

    private Map<String, String> map = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_notice);
        setStatusColor(R.color.colorWhite);
        ButterKnife.bind(this);
        tvTitleRight.setText(getString(R.string.next_step));
        Intent intent = getIntent();
        action = intent.getStringExtra("action");

        llAddNotice.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Rect rect = new Rect();
                //获取当前界面可视部分
                getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
                //获取屏幕的高度
                int screenHeight = getWindow().getDecorView().getRootView().getHeight();
                //此处就是用来获取键盘的高度的， 在键盘没有弹出的时候 此高度为0 键盘弹出的时候为一个正数
                heightDifference = screenHeight - rect.bottom;
                if (heightDifference > 200) {
                    if (idNoticeContentV2Edit.hasFocus()) {
                        showpopupWindow(heightDifference);
                    }

                } else {
                    if (popupWindow != null && popupWindow.isShowing()) {
                        popupWindow.dismiss();
                    }
                }
            }
        });

        idNoticeContentV2Edit.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    if (heightDifference > 200) {
                        showpopupWindow(heightDifference);
                    }
                }
            }
        });
        idNoticeTitleV2Edit.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    if (heightDifference > 200) {
                        if (popupWindow != null && popupWindow.isShowing()) {
                            popupWindow.dismiss();
                        }
                    }
                }
            }
        });
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK){
            if(popupWindow!=null){
                popupWindow.dismiss();
                return true;
            }

            if(classSelectPop!=null){
                classSelectPop.dismiss();
                return true;
            }
        }


        return super.onKeyDown(keyCode, event);
    }

    private void showpopupWindow(int heightDifference) {
        View view = LayoutInflater.from(this).inflate(R.layout.add_notice_v2_popup, null);
        popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, (int) DensityUtil.dip2px(AddNoticeActivity.this, 45), true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setFocusable(false);
        popupWindow.setOutsideTouchable(false);
        ImageView id_notice_v2_down = (ImageView) view.findViewById(R.id.id_notice_v2_down);
        ImageView id_notice_v2_camera = (ImageView) view.findViewById(R.id.id_notice_v2_camera);
        ImageView id_notice_v2_photo = (ImageView) view.findViewById(R.id.id_notice_v2_photo);
        id_notice_v2_down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
                closeKeyInput();
            }
        });
        id_notice_v2_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takeCamera();
                popupWindow.dismiss();
                closeKeyInput();
            }
        });
        id_notice_v2_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickPhoto();
                popupWindow.dismiss();
                closeKeyInput();
            }
        });
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {

            }
        });
        popupWindow.showAtLocation(view, Gravity.BOTTOM, 0, heightDifference);
    }

    private void closeKeyInput() {
        InputMethodManager im = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        im.hideSoftInputFromWindow(getCurrentFocus().getApplicationWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }

    private void pickPhoto() {
        PermissionGen.with(this)
                .addRequestCode(Constants.PERMISSION_PHOTO_CODE)
                .permissions(
                        Manifest.permission.READ_EXTERNAL_STORAGE
                )
                .request();
    }

    private void takeCamera() {
        PermissionGen.with(this)
                .addRequestCode(Constants.PERMISSION_CAMERA_CODE)
                .permissions(
                        Manifest.permission.CAMERA,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                )
                .request();
    }

    /**
     * 获取相机权限后打开系统相机
     */
    @PermissionSuccess(requestCode = 100)
    public void doCamera() {
        fileName = UUID.randomUUID() + ".jpg";
        SystemUtil.opSystemCamera(this, SDCardUtil.getInstance().getFilePath() + fileName, Constants.REQ_CAMERA);
    }

    @PermissionFail(requestCode = 100)
    public void doSomeError() {
        Toasty.error(this, getResources().getString(R.string.get_camera_permission_error), Toast.LENGTH_LONG).show();
    }

    /**
     * 获取存储卡权限后打开相册
     */
    @PermissionSuccess(requestCode = 102)
    public void doPhoto() {
        SystemUtil.StartSystemAlbum(this, Constants.REQ_PHOTO);
    }

    @PermissionFail(requestCode = 102)
    public void doPhotoError() {
        Toasty.error(this, getResources().getString(R.string.get_sd_permission_error), Toast.LENGTH_LONG).show();
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        PermissionGen.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
    }

    @Override
    protected AddNoticePresenter initPresenter() {
        return new AddNoticePresenter();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == Constants.REQ_CAMERA) {
                int index = idNoticeContentV2Edit.getSelectionStart();//得到光标的位置
                Editable editable = idNoticeContentV2Edit.getText();
                editable.insert(index, "\n");
                String path = "<img>" + fileName + "</img>";//文字替换图片的文件名
                map.put(fileName, SDCardUtil.getInstance().getFilePath() + fileName);//文件名对应路径存进Map
                SpannableString spannableString = new SpannableString(path);
                int position = path.indexOf("<img>");
                if (position == 0) {
                    String key = path.substring(position + 5, path.length() - 6);
                    Bitmap bitmap = SDCardUtil.getInstance().loadFixedBitmap(map.get(key), getScreenWidth() - DensityUtil.dip2px(AddNoticeActivity.this, 36));
                    bitmap = Bitmap.createBitmap(bitmap, 0, bitmap.getHeight() / 2 - 150, bitmap.getWidth(), 300);
                    Drawable drawable = new BitmapDrawable(bitmap);
                    int width = getScreenWidth() - DensityUtil.dip2px(AddNoticeActivity.this, 36);
                    drawable.setBounds(0, 0, width, bitmap.getHeight());
                    spannableString.setSpan(new ImageSpan(drawable, ImageSpan.ALIGN_BASELINE), 0, path.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                }
                editable.insert(index, spannableString);
                editable.insert(index, "\n");

            } else if (requestCode == Constants.REQ_PHOTO) {
                int index = idNoticeContentV2Edit.getSelectionStart();
                Editable editable = idNoticeContentV2Edit.getText();
                editable.insert(index, "\n");
                Uri uri = data.getData();
                String path = SDCardUtil.getInstance().changeUriToFilePath(uri);
                File file = new File(path);
                String key = Trans2PinYin.getInstance().convertAll(file.getName());
                if (key.substring(key.lastIndexOf(".") + 1).toLowerCase().equals("gif")) {
                    key = key.substring(0, key.lastIndexOf(".")) + ".jpg";
                }
                String string = "<img>" + key + "</img>";
                map.put(key, path);
                SpannableString spannableString = new SpannableString(string);
                int position = string.indexOf("<img>");
                if (position == 0) {
                    String str = string.substring(position + 5, string.length() - 6);
                    Bitmap bitmap = SDCardUtil.getInstance().loadFixedBitmap(map.get(str), getScreenWidth() - DensityUtil.dip2px(AddNoticeActivity.this, 36));
                    if (bitmap.getHeight() > 300) {
                        bitmap = Bitmap.createBitmap(bitmap, 0, bitmap.getHeight() / 2 - 150, bitmap.getWidth(), 300);
                    } else {
                        bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight());
                    }
                    Drawable drawable = new BitmapDrawable(bitmap);
                    int width = getScreenWidth() - DensityUtil.dip2px(AddNoticeActivity.this, 36);
                    drawable.setBounds(0, 0, width, bitmap.getHeight());
                    spannableString.setSpan(new ImageSpan(drawable, ImageSpan.ALIGN_BASELINE), 0, string.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                }
                editable.insert(index, spannableString);
                editable.insert(index, "\n");
            }
        }
    }


    //Activity 中获取屏幕高度
    private int getScreenWidth() {
        Display display = getWindowManager().getDefaultDisplay();
        Point point = new Point();
        display.getSize(point);
        // int screenWidth = point.x;
        return point.x;
    }

    @OnClick({R.id.tv_title_left, R.id.tv_title_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_title_left:
                finish();
                break;
            case R.id.tv_title_right:
                showClassSelectPop();
                closeKeyInput();
                break;
        }
    }

    private void showClassSelectPop() {
        View view=LayoutInflater.from(AddNoticeActivity.this).inflate(R.layout.notice_class_select_layout,null);
        final LinearLayout llPopup= (LinearLayout) view.findViewById(R.id.ll_popup);
        TextView tv_cancel= (TextView) view.findViewById(R.id.tv_cancel);
        classSelectPop = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
        classSelectPop.setBackgroundDrawable(new BitmapDrawable());
        classSelectPop.setFocusable(false);
        classSelectPop.setOutsideTouchable(false);
        int navigationBar = 0;
        if (NavigationBarUtil.checkDeviceHasNavigationBar(TeacherApplication.getContext())) {
            navigationBar = NavigationBarUtil.getNavigationBarHeight(TeacherApplication.getContext());
        }
        llPopup.startAnimation(AnimationUtils.loadAnimation(TeacherApplication.getContext(), R.anim.popup_translate_up));
        classSelectPop.showAtLocation(llAddNotice, Gravity.BOTTOM, 0, navigationBar);
        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animation=AnimationUtils.loadAnimation(TeacherApplication.getContext(), R.anim.popup_translate_down);
                llPopup.startAnimation(animation);
                animation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        classSelectPop.dismiss();
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });

            }
        });
    }


}
