package com.tongxun.atongmu.parent.ui.my.invitefamily;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tongxun.atongmu.parent.BaseActivity;
import com.tongxun.atongmu.parent.R;
import com.tongxun.atongmu.parent.model.InviteFamilyBindModel;
import com.tongxun.atongmu.parent.util.GlideOption;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class FamilyInfoActivity extends BaseActivity {

    @BindView(R.id.iv_title_back)
    ImageView ivTitleBack;
    @BindView(R.id.tv_title_name)
    TextView tvTitleName;
    @BindView(R.id.civ_face)
    CircleImageView civFace;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_relation)
    TextView tvRelation;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.tv_sign_card)
    TextView tvSignCard;
    @BindView(R.id.tv_gold)
    TextView tvGold;
    @BindView(R.id.tv_level)
    TextView tvLevel;
    @BindView(R.id.tv_cancel)
    TextView tvCancel;

    private InviteFamilyBindModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_family_info);
        ButterKnife.bind(this);
        setStatusColor(R.color.colorWhite);
        tvTitleName.setText(getString(R.string.family_info));
        Intent intent=getIntent();
        model=intent.getParcelableExtra("model");
        if(model==null){
            finish();
        }
        Glide.with(this).load(model.getHeadImage()).apply(GlideOption.getPHOption()).into(civFace);
        tvSignCard.setText(model.getCardNumber());
        tvName.setText(model.getPersonName());
        tvPhone.setText(model.getPersonPhone());
        tvRelation.setText(model.getRelation());


    }

    @OnClick({R.id.iv_title_back, R.id.tv_cancel})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_title_back:
                finish();
                break;
            case R.id.tv_cancel:
                showCancelFamilyDialog();
                break;
        }
    }

    private void showCancelFamilyDialog() {


    }
}
