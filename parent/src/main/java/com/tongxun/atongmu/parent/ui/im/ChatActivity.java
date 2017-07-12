package com.tongxun.atongmu.parent.ui.im;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.hyphenate.easeui.EaseConstant;
import com.hyphenate.easeui.ui.EaseBaseActivity;
import com.hyphenate.easeui.ui.EaseChatFragment;
import com.readystatesoftware.systembartint.SystemBarTintManager;
import com.tongxun.atongmu.parent.R;
import com.tongxun.atongmu.parent.util.ActivityControl;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChatActivity extends EaseBaseActivity {
    @BindView(R.id.iv_title_back)
    ImageView ivTitleBack;
    @BindView(R.id.tv_title_name)
    TextView tvTitleName;
    @BindView(R.id.tv_title_right)
    TextView tvTitleRight;
    @BindView(R.id.chat_container)
    FrameLayout chatContainer;
    private EaseChatFragment chatFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        SystemBarTintManager tintManager=new SystemBarTintManager(this);
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setStatusBarTintColor(getResources().getColor(R.color.colorWhite));
        ButterKnife.bind(this);
        Intent intent=getIntent();
        String groupId=intent.getStringExtra("groupId");

        Bundle bundle=new Bundle();
        bundle.putInt(EaseConstant.EXTRA_CHAT_TYPE,EaseConstant.CHATTYPE_GROUP);//群聊
        bundle.putString(EaseConstant.EXTRA_USER_ID,groupId);//群聊

        chatFragment=new ChatFragment();
        chatFragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().add(R.id.chat_container,chatFragment).commitAllowingStateLoss();
        ActivityControl.addActivity(this);

    }

    @OnClick({R.id.iv_title_back, R.id.tv_title_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_title_back:
                finish();
                break;
            case R.id.tv_title_right:
                Intent intent=new Intent(this,GroupMemberActivity.class);
                startActivity(intent);
                break;
        }
    }


    @Override
    protected void onDestroy() {
        ActivityControl.removeActivity(this);
        super.onDestroy();
    }
}
