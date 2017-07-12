package com.tongxun.atongmu.parent.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.tongxun.atongmu.parent.BaseActivity;
import com.tongxun.atongmu.parent.R;
import com.tongxun.atongmu.parent.ui.im.ChatActivity;
import com.tongxun.atongmu.parent.util.SharePreferenceUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements View.OnClickListener {


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

    private String groupId;
    private String imUserName;
    private String imPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setStatusColor(R.color.colorWhite);
        ButterKnife.bind(this);
        tvBottomHome.setOnClickListener(this);
        tvBottomIm.setOnClickListener(this);
        tvBottomFind.setOnClickListener(this);
        tvBottomLife.setOnClickListener(this);
        tvBottomMe.setOnClickListener(this);

        /*Intent intent=new Intent(this, GroupMemberActivity.class);
        startActivity(intent);*/
        groupId= SharePreferenceUtil.getPreferences().getString(SharePreferenceUtil.GROUPID,"");
        imUserName= SharePreferenceUtil.getPreferences().getString(SharePreferenceUtil.IMUSERNAME,"");
        imPassword= SharePreferenceUtil.getPreferences().getString(SharePreferenceUtil.IMPASSWORD,"");
        groupId="240876150337831340";
        imUserName="par0000027151";
        imPassword="123456";


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_bottom_home:
                resetBottom();
                tvBottomHome.setSelected(true);
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
                resetBottom();
                tvBottomMe.setSelected(true);
                break;
        }
    }

    private void goIM() {
        if(EMClient.getInstance().isLoggedInBefore()){
            EMClient.getInstance().groupManager().loadAllGroups();
            EMClient.getInstance().chatManager().loadAllConversations();
            Intent intent=new Intent(this, ChatActivity.class);
            intent.putExtra("groupId",groupId);
            startActivity(intent);
        }else {
            EMClient.getInstance().login(imUserName, imPassword, new EMCallBack() {
                @Override
                public void onSuccess() {
                    EMClient.getInstance().groupManager().loadAllGroups();
                    EMClient.getInstance().chatManager().loadAllConversations();
                    Intent intent=new Intent(MainActivity.this, ChatActivity.class);
                    intent.putExtra("groupId",groupId);
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
}
