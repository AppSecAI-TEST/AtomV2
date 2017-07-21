package com.tongxun.atongmu.parent.ui.home;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.tongxun.atongmu.parent.BaseActivity;
import com.tongxun.atongmu.parent.Constants;
import com.tongxun.atongmu.parent.R;
import com.tongxun.atongmu.parent.application.ParentApplication;
import com.tongxun.atongmu.parent.ui.im.ChatActivity;
import com.tongxun.atongmu.parent.util.SharePreferenceUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;
import kr.co.namee.permissiongen.PermissionFail;
import kr.co.namee.permissiongen.PermissionGen;
import kr.co.namee.permissiongen.PermissionSuccess;

public class MainActivity extends BaseActivity {


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

    private String groupId;
    private String imUserName;
    private String imPassword;

    MyFragment myFragment = null;
    MainFragment mainFragment = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setStatusColor(R.color.colorWhite);
        ButterKnife.bind(this);

       /* Intent intent=new Intent(this, SchoolIntroductionActivity.class);
        startActivity(intent);*/
        groupId = SharePreferenceUtil.getPreferences().getString(SharePreferenceUtil.GROUPID, "");
        imUserName = SharePreferenceUtil.getPreferences().getString(SharePreferenceUtil.IMUSERNAME, "");
        imPassword = SharePreferenceUtil.getPreferences().getString(SharePreferenceUtil.IMPASSWORD, "");
        groupId = "240876150337831340";
        imUserName = "par0000027151";
        imPassword = "123456";

        PermissionGen.with(this)
                .addRequestCode(Constants.PERMISSION_PHONE_CODE)
                .permissions(
                        Manifest.permission.READ_PHONE_STATE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                )
                .request();

        setSelectPosition(0);
    }


    private void goIM() {
        if (EMClient.getInstance().isLoggedInBefore()) {
            EMClient.getInstance().groupManager().loadAllGroups();
            EMClient.getInstance().chatManager().loadAllConversations();
            Intent intent = new Intent(this, ChatActivity.class);
            intent.putExtra("groupId", groupId);
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

    @OnClick({R.id.tv_bottom_home, R.id.tv_bottom_im, R.id.tv_bottom_find, R.id.tv_bottom_life, R.id.tv_bottom_me})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_bottom_home:
                setSelectPosition(0);

                break;
            case R.id.tv_bottom_im:
                resetBottom();
                tvBottomIm.setSelected(true);
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
        }
    }

    private void setSelectPosition(int i) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        hideFragment(transaction);
        resetBottom();
        switch (i) {
            case 0:
                if(mainFragment==null){
                    mainFragment=new MainFragment();
                    transaction.add(R.id.fl_container, mainFragment);
                }else {
                    transaction.show(mainFragment);
                }

                tvTitleSetting.setVisibility(View.GONE);
                ivTitleQrCode.setVisibility(View.VISIBLE);
                tvBottomHome.setSelected(true);
                break;
            case 4:
                if(myFragment==null){
                    myFragment=new MyFragment();
                    transaction.add(R.id.fl_container, myFragment);
                    new MyPresenter(myFragment);
                }else {
                    transaction.show(myFragment);
                }
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


}
