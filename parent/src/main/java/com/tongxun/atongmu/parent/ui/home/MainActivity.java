package com.tongxun.atongmu.parent.ui.home;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.tongxun.atongmu.parent.BaseActivity;
import com.tongxun.atongmu.parent.Constants;
import com.tongxun.atongmu.parent.R;
import com.tongxun.atongmu.parent.application.ParentApplication;
import com.tongxun.atongmu.parent.model.AlbumFromDateCallBack;
import com.tongxun.atongmu.parent.model.BabyInfoModel;
import com.tongxun.atongmu.parent.ui.im.ChatActivity;
import com.tongxun.atongmu.parent.ui.im.ILoginIMInteractor;
import com.tongxun.atongmu.parent.ui.im.LoginIMInteractor;
import com.tongxun.atongmu.parent.ui.my.MyFragment;
import com.tongxun.atongmu.parent.ui.my.MyPresenter;
import com.tongxun.atongmu.parent.ui.my.SettingActivity;
import com.tongxun.atongmu.parent.util.GlideOption;
import com.tongxun.atongmu.parent.util.SharePreferenceUtil;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONObject;
import org.litepal.crud.DataSupport;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import es.dmoral.toasty.Toasty;
import kr.co.namee.permissiongen.PermissionFail;
import kr.co.namee.permissiongen.PermissionGen;
import kr.co.namee.permissiongen.PermissionSuccess;
import okhttp3.Call;
import okhttp3.MediaType;

import static com.tongxun.atongmu.parent.util.NavigationBarUtil.checkDeviceHasNavigationBar;
import static com.tongxun.atongmu.parent.util.NavigationBarUtil.getNavigationBarHeight;
import static com.tongxun.atongmu.parent.util.ScreenUtils.getScreenHeight;

public class MainActivity extends BaseActivity implements ILoginIMInteractor.onFinishListener {


    @BindView(R.id.fl_container)
    FrameLayout flContainer;
    @BindView(R.id.tv_bottom_home)
    TextView tvBottomHome;
    @BindView(R.id.tv_bottom_im)
    TextView tvBottomIm;
    @BindView(R.id.tv_bottom_find)
    TextView tvBottomFind;
    @BindView(R.id.tv_bottom_life)
    TextView tvBottomLife;
    @BindView(R.id.tv_bottom_me)
    TextView tvBottomMe;
    @BindView(R.id.ll_activity_main)
    LinearLayout llActivityMain;
    @BindView(R.id.tv_title_setting)
    TextView tvTitleSetting;
    @BindView(R.id.iv_title_qr_code)
    ImageView ivTitleQrCode;
    @BindView(R.id.civ_face)
    CircleImageView civFace;
    @BindView(R.id.tv_name)
    TextView tvName;

    private String groupId;
    private String imUserName;
    private String imPassword;

    MyFragment myFragment = null;
    MainFragment mainFragment = null;

    private BabyInfoModel babyInfoModel;
    private LoginIMInteractor interactor;

    //popupWindow
    private PopupWindow popupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setStatusColor(R.color.colorWhite);
        ButterKnife.bind(this);

        LoginChatIM();

        PermissionGen.with(this)
                .addRequestCode(Constants.PERMISSION_PHONE_CODE)
                .permissions(
                        Manifest.permission.READ_PHONE_STATE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                )
                .request();

        setSelectPosition(0);

        babyInfoModel = DataSupport.where("tokenid= ? ", SharePreferenceUtil.getPreferences().getString(SharePreferenceUtil.TOKENID, "")).findFirst(BabyInfoModel.class);

        setBabyInfoUI();
    }

    /**
     * 登录环信
     */
    private void LoginChatIM() {
        interactor = new LoginIMInteractor();

        imUserName = SharePreferenceUtil.getPreferences().getString(SharePreferenceUtil.IMUSERNAME, "");
        imPassword = SharePreferenceUtil.getPreferences().getString(SharePreferenceUtil.IMPASSWORD, "");
        if (TextUtils.isEmpty(imUserName) || TextUtils.isEmpty(imPassword)) {
            interactor.LoginIM(SharePreferenceUtil.getPreferences().getString(SharePreferenceUtil.TOKENID, ""), this);
        }else {
            loginChatUser(imUserName,imPassword);
        }
    }

    private void setBabyInfoUI() {
        if (babyInfoModel != null) {
            tvName.setText(babyInfoModel.getPersonName());
            Glide.with(this).load(babyInfoModel.getPhoto1()).apply(GlideOption.getFaceHolderOption()).into(civFace);
        }
    }


    private void goIM() {
        if (EMClient.getInstance().isLoggedInBefore()) {
            EMClient.getInstance().groupManager().loadAllGroups();
            EMClient.getInstance().chatManager().loadAllConversations();
            Intent intent = new Intent(this, ChatActivity.class);
            intent.putExtra("groupId", SharePreferenceUtil.getPreferences().getString(SharePreferenceUtil.GROUPID, ""));
            startActivity(intent);
        } else {
            EMClient.getInstance().login(imUserName, imPassword, new EMCallBack() {
                @Override
                public void onSuccess() {
                    EMClient.getInstance().groupManager().loadAllGroups();
                    EMClient.getInstance().chatManager().loadAllConversations();
                    Intent intent = new Intent(MainActivity.this, ChatActivity.class);
                    intent.putExtra("groupId", groupId);
                    startActivity(intent);
                }

                @Override
                public void onError(int i, String s) {
                    Log.d("TGA", "onError: ");
                }

                @Override
                public void onProgress(int i, String s) {

                }
            });
        }

    }


    private void resetBottom() {
        tvBottomHome.setSelected(false);
        tvBottomIm.setSelected(false);
        tvBottomFind.setSelected(false);
        tvBottomLife.setSelected(false);
        tvBottomMe.setSelected(false);
    }

    @PermissionSuccess(requestCode = 104)
    public void doEzOpenSDKInit() {
        ParentApplication.initEzOpenSDK();
    }

    @PermissionFail(requestCode = 104)
    public void doSomeError() {
        Toasty.error(this, getResources().getString(R.string.get_sd_permission_error), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        PermissionGen.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
    }

    @OnClick({R.id.tv_bottom_home, R.id.tv_bottom_im, R.id.tv_bottom_find, R.id.tv_bottom_life, R.id.tv_bottom_me, R.id.iv_title_qr_code, R.id.tv_title_setting})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_bottom_home:
                setSelectPosition(0);

                break;
            case R.id.tv_bottom_im:
               /* resetBottom();
                tvBottomIm.setSelected(true);*/
                goIM();
                break;
            case R.id.tv_bottom_find:
                resetBottom();
                tvBottomFind.setSelected(true);
                break;

            case R.id.tv_bottom_life:
                resetBottom();
                tvBottomLife.setSelected(true);
                break;
            case R.id.tv_bottom_me:
                setSelectPosition(4);
                break;
            case R.id.iv_title_qr_code:
                openQRCode();
                break;
            case R.id.tv_title_setting:
                goSetting();
                break;
        }
    }

    /**
     * 去设置
     */
    private void goSetting() {
        Intent intent = new Intent(MainActivity.this, SettingActivity.class);
        startActivity(intent);
    }

    /**
     * 打开二维码扫描
     */
    private void openQRCode() {
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
        Intent intent = new Intent(this, CaptureActivity.class);
        startActivityForResult(intent, Constants.REQ_CODE);
    }

    @PermissionFail(requestCode = 100)
    public void doCameraError() {
        Toasty.error(this, getResources().getString(R.string.get_camera_permission_error), Toast.LENGTH_LONG).show();
    }

    private void setSelectPosition(int i) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        hideFragment(transaction);
        resetBottom();
        switch (i) {
            case 0:
                if (mainFragment == null) {
                    mainFragment = new MainFragment();
                    transaction.add(R.id.fl_container, mainFragment);
                    new MainPresenter(mainFragment);
                } else {
                    transaction.show(mainFragment);
                }
                civFace.setVisibility(View.VISIBLE);
                tvName.setVisibility(View.VISIBLE);
                tvTitleSetting.setVisibility(View.GONE);
                ivTitleQrCode.setVisibility(View.VISIBLE);
                tvBottomHome.setSelected(true);
                break;
            case 4:
                if (myFragment == null) {
                    myFragment = new MyFragment();
                    transaction.add(R.id.fl_container, myFragment);
                    new MyPresenter(myFragment);
                } else {
                    transaction.show(myFragment);
                }
                civFace.setVisibility(View.GONE);
                tvName.setVisibility(View.GONE);
                tvTitleSetting.setVisibility(View.VISIBLE);
                ivTitleQrCode.setVisibility(View.GONE);
                tvBottomMe.setSelected(true);
                break;
            default:

                break;
        }

        transaction.commitAllowingStateLoss();
    }

    private void hideFragment(FragmentTransaction transaction) {
        if (myFragment != null) {
            transaction.hide(myFragment);
        }

        if (mainFragment != null) {
            transaction.hide(mainFragment);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == Constants.REQ_CODE) {
                //处理扫描结果（在界面上显示）
                if (null != data) {
                    Bundle bundle = data.getExtras();
                    if (bundle == null) {
                        return;
                    }
                    if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                        String result = bundle.getString(CodeUtils.RESULT_STRING);
                        if (result == null || result.equals("")) {
                            Toasty.error(this, getString(R.string.qr_code_error), Toast.LENGTH_SHORT).show();
                        } else {
                            String[] strs = result.split("\\|");
                            if (strs == null || strs.length < 2) {
                                Toasty.error(this, getString(R.string.qr_code_error), Toast.LENGTH_SHORT).show();
                            } else {
                                String registerid = strs[strs.length - 1];
                                postWebSignScan(registerid);
                            }
                        }

                    } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                        Toasty.error(this, getString(R.string.qr_code_error), Toast.LENGTH_LONG).show();
                    }
                }
            }

            if (requestCode == Constants.CHANGE_INFO) {
                babyInfoModel = DataSupport.where("tokenid= ? ", SharePreferenceUtil.getPreferences().getString(SharePreferenceUtil.TOKENID, "")).findFirst(BabyInfoModel.class);
                setBabyInfoUI();

            }
            if (requestCode == Constants.ChANGE_ACCOUNT) {
                babyInfoModel = DataSupport.where("tokenid= ? ", SharePreferenceUtil.getPreferences().getString(SharePreferenceUtil.TOKENID, "")).findFirst(BabyInfoModel.class);
                setBabyInfoUI();
                if (mainFragment != null) {
                    mainFragment.changeDate();
                }

                if (myFragment != null) {
                    myFragment.changeDate();
                }
            }
        }
    }


    /**
     * 上传签到数据
     *
     * @param registerid
     */
    private void postWebSignScan(String registerid) {
        String url=Constants.restPushStudentSignIn;
        OkHttpUtils.postString()
                .url(url)
                .content(CreateJson(registerid))
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .tag(this)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Toasty.error(MainActivity.this, getString(R.string.net_error), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Gson gson=new Gson();
                        AlbumFromDateCallBack callBack= null;
                        try {
                            callBack = gson.fromJson(response,AlbumFromDateCallBack.class);
                        } catch (JsonSyntaxException e) {
                            e.printStackTrace();
                        }
                        if(callBack!=null){
                            if(callBack.getStatus().equals("success")){
                                //签到成功
                                showSignpop(true);
                            }else {
                                //签到失败
                                showSignpop(false);
                            }
                        }else {
                            Toasty.error(MainActivity.this, getString(R.string.date_error), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }


    /**
     * 签到成功失败Popup
     *
     * @param b
     */
    private void showSignpop(boolean b) {
        View view = LayoutInflater.from(this).inflate(R.layout.sign_popup_layout, null);
        ImageView sign_pop_img = (ImageView) view.findViewById(R.id.sign_pop_img);
        if (b) {
            sign_pop_img.setImageResource(R.drawable.sign_success);
        } else {
            sign_pop_img.setImageResource(R.drawable.sign_fail);
        }
        Animation trananimation = AnimationUtils.loadAnimation(this, R.anim.bottom_icon_tran);
        sign_pop_img.setAnimation(trananimation);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
        int height = getScreenHeight();
        Rect frame = new Rect();
        getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
        int statusBarHeight = frame.top;
        popupWindow = new PopupWindow(view, ViewPager.LayoutParams.MATCH_PARENT, height - statusBarHeight, true);
        popupWindow.setTouchable(true);
        popupWindow.setTouchInterceptor(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });
        popupWindow.setBackgroundDrawable(getResources().getDrawable(R.drawable.white_bg));
        int navigationBar = 0;
        if (checkDeviceHasNavigationBar(getApplicationContext())) {
            navigationBar = getNavigationBarHeight(getApplicationContext());
        }
        popupWindow.showAtLocation(llActivityMain, Gravity.BOTTOM, 0, navigationBar);
    }

    private String CreateJson(String registerid) {
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject();
            jsonObject.put("tokenId", SharePreferenceUtil.getPreferences().getString(SharePreferenceUtil.TOKENID,""));
            jsonObject.put("registionId",registerid);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonObject.toString();
    }




    @Override
    public void onError(String message) {
        Toasty.error(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onIMSuccess(String groupId, String imNickName, String imUsername, String imPassword) {
        loginChatUser(imUsername, imPassword);
        SharedPreferences.Editor edit = SharePreferenceUtil.getEditor();
        edit.putString(SharePreferenceUtil.IMNICKNAME, imNickName);
        edit.putString(SharePreferenceUtil.IMUSERNAME, imUsername);
        edit.putString(SharePreferenceUtil.IMPASSWORD, imPassword);
        edit.putString(SharePreferenceUtil.GROUPID, groupId);
        edit.commit();
    }

    private void loginChatUser(String imUsername, String imPassword) {
        if (EMClient.getInstance().isLoggedInBefore()) {
            EMClient.getInstance().logout(true);
            EMClient.getInstance().login(imUsername, imPassword, new EMCallBack() {
                @Override
                public void onSuccess() {

                }

                @Override
                public void onError(int i, String s) {

                }

                @Override
                public void onProgress(int i, String s) {

                }
            });
        }
    }
}
