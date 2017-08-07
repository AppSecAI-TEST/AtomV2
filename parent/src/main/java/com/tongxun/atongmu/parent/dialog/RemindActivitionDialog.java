package com.tongxun.atongmu.parent.dialog;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tongxun.atongmu.parent.R;
import com.tongxun.atongmu.parent.util.DensityUtil;
import com.tongxun.atongmu.parent.util.GlideOption;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Anro on 2017/8/7.
 */

public class RemindActivitionDialog extends BaseDialog {
    @BindView(R.id.civ_face)
    CircleImageView civFace;
    @BindView(R.id.tv_account)
    TextView tvAccount;
    @BindView(R.id.tv_sms)
    TextView tvSms;
    @BindView(R.id.tv_wechat)
    TextView tvWechat;
    @BindView(R.id.tv_confirm)
    TextView tvConfirm;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_password)
    TextView tvPassword;
    @BindView(R.id.iv_cancel)
    ImageView ivCancel;

    private InviteActivitionListener mlistener;
    private Context mContext;

    private String sendType = "";




    public RemindActivitionDialog(Context context, String imgUrl, final String name, final String account, final String password, final String relation, InviteActivitionListener listener) {
        super(context, View.inflate(context, R.layout.dialog_reminder_activition, null), new ViewGroup.LayoutParams(DensityUtil.dip2px(context, 350), DensityUtil.dip2px(context, 260)));
        ButterKnife.bind(this);
        mContext = context;
        mlistener = listener;
        Glide.with(mContext).load(imgUrl).apply(GlideOption.getImageHolderOption()).into(civFace);
        tvName.setText(name);
        tvAccount.setText("账号:" + account);
        tvPassword.setText("密码:" + password);
        tvSms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetType();
                tvSms.setSelected(true);
                sendType = "sms";
            }
        });

        tvWechat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetType();
                tvWechat.setSelected(true);
                sendType = "weChat";
            }
        });

        tvConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(sendType)) {
                    return;
                }
                if (mlistener != null) {
                    mlistener.onRemindActivition(name, account, password, sendType,relation);
                }
            }
        });
        ivCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    private void resetType() {
        tvSms.setSelected(false);
        tvWechat.setSelected(false);
    }


    public interface InviteActivitionListener {

        void onRemindActivition(String name, String account, String password, String sendType,String relation);
    }
}
