package com.tongxun.atongmu.parent.dialog;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.tongxun.atongmu.parent.R;
import com.tongxun.atongmu.parent.util.DensityUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Anro on 2017/8/10.
 */

public class cancelFamilyDialog extends BaseDialog {

    @BindView(R.id.iv_cancel)
    ImageView ivCancel;
    @BindView(R.id.tv_confirm)
    TextView tvConfirm;
    private onCancelFamilyListener mlistener;

    public cancelFamilyDialog(Context context,onCancelFamilyListener listener) {
        super(context, View.inflate(context, R.layout.dialog_cancel_family, null), new ViewGroup.LayoutParams(DensityUtil.dip2px(context, 350), DensityUtil.dip2px(context, 260)));
        ButterKnife.bind(this);
        mlistener=listener;
        ivCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mlistener!=null){
                    mlistener.cancel();
                }
            }
        });

        tvConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mlistener!=null){
                    mlistener.go();
                }
            }
        });
    }

    public interface onCancelFamilyListener{
        void go();
        void cancel();
    }


}
