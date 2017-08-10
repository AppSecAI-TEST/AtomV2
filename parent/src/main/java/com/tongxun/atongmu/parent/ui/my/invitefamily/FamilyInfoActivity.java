package com.tongxun.atongmu.parent.ui.my.invitefamily;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.tongxun.atongmu.parent.BaseActivity;
import com.tongxun.atongmu.parent.Constants;
import com.tongxun.atongmu.parent.R;
import com.tongxun.atongmu.parent.dialog.cancelFamilyDialog;
import com.tongxun.atongmu.parent.model.BaseCallBack;
import com.tongxun.atongmu.parent.model.InviteFamilyBindModel;
import com.tongxun.atongmu.parent.util.GlideOption;
import com.tongxun.atongmu.parent.util.SharePreferenceUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import es.dmoral.toasty.Toasty;
import okhttp3.Call;
import okhttp3.MediaType;

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

    private cancelFamilyDialog dialog;

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
        dialog=new cancelFamilyDialog(this, new cancelFamilyDialog.onCancelFamilyListener() {
            @Override
            public void go() {
                cancelFamily();
                dialog.dismiss();
            }

            @Override
            public void cancel() {
                dialog.dismiss();
            }
        });
        dialog.show();

    }

    private void cancelFamily() {
        String url= Constants.restCancelStudentRelation;
        OkHttpUtils.postString()
                .url(url)
                .content(CreateJson())
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .tag(this)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Toasty.error(FamilyInfoActivity.this, getString(R.string.net_error), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Gson gson=new Gson();
                        BaseCallBack callBack= null;
                        try {
                            callBack = gson.fromJson(response,BaseCallBack.class);
                        } catch (JsonSyntaxException e) {
                            e.printStackTrace();
                        }
                        if(callBack!=null){
                            if(callBack.getStatus().equals("success")){
                                setResult(RESULT_OK);
                                finish();
                            }else {
                                Toasty.error(FamilyInfoActivity.this,callBack.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }else {
                            Toasty.error(FamilyInfoActivity.this,getString(R.string.date_error), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private String CreateJson() {
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject();
            jsonObject.put("tokenId", SharePreferenceUtil.getPreferences().getString(SharePreferenceUtil.TOKENID,""));
            jsonObject.put("sourceId",model.getPersonId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonObject.toString();
    }
}
