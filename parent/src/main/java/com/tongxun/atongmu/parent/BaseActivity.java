package com.tongxun.atongmu.parent;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.readystatesoftware.systembartint.SystemBarTintManager;
import com.tongxun.atongmu.parent.util.ActivityControl;

/**
 * Created by Anro on 2017/6/26.
 */

public abstract class BaseActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStatusTran();
        ActivityControl.addActivity(this);
    }

    private void setStatusTran() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

    protected void setStatusColor(int color) {
        SystemBarTintManager tintManager=new SystemBarTintManager(this);
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setStatusBarTintColor(getResources().getColor(color));
    }


    @Override
    protected void onDestroy() {
        ActivityControl.removeActivity(this);
        super.onDestroy();
    }




}
