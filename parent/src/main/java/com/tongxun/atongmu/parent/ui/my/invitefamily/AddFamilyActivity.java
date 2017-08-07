package com.tongxun.atongmu.parent.ui.my.invitefamily;

import android.content.ContentResolver;
import android.content.CursorLoader;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.tongxun.atongmu.parent.BaseActivity;
import com.tongxun.atongmu.parent.Constants;
import com.tongxun.atongmu.parent.R;
import com.tongxun.atongmu.parent.dialog.RelationDialog;
import com.tongxun.atongmu.parent.model.BabyInfoModel;
import com.tongxun.atongmu.parent.model.BaseCallBack;
import com.tongxun.atongmu.parent.model.FamilyCallBack;
import com.tongxun.atongmu.parent.model.FamilyModel;
import com.tongxun.atongmu.parent.ui.my.babydetail.BabyDetailEditActivity;
import com.tongxun.atongmu.parent.util.SharePreferenceUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONObject;
import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.wechat.friends.Wechat;
import es.dmoral.toasty.Toasty;
import okhttp3.Call;
import okhttp3.MediaType;

public class AddFamilyActivity extends BaseActivity {

    @BindView(R.id.iv_title_back)
    ImageView ivTitleBack;
    @BindView(R.id.tv_title_name)
    TextView tvTitleName;
    @BindView(R.id.tv_sex)
    TextView tvSex;
    @BindView(R.id.ll_sex)
    LinearLayout llSex;
    @BindView(R.id.tv_name)
    EditText tvName;
    @BindView(R.id.tv_relation)
    TextView tvRelation;
    @BindView(R.id.tv_phone)
    EditText tvPhone;
    @BindView(R.id.tv_sms)
    TextView tvSms;
    @BindView(R.id.tv_wechat)
    TextView tvWechat;
    @BindView(R.id.iv_contact)
    ImageView ivContact;
    @BindView(R.id.btn_invite)
    Button btnInvite;
    private String relation;

    private RelationDialog relationDialog;

    private List<FamilyModel> mlist = new ArrayList<>();

    private String phoneNum;

    private String sendType="";

    private BabyInfoModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_family);
        ButterKnife.bind(this);
        setStatusColor(R.color.colorWhite);
        Intent intent = getIntent();
        relation = intent.getStringExtra("relation");
        tvTitleName.setText(getString(R.string.family_info));
        if (!TextUtils.isEmpty(relation)) {
            tvRelation.setText(relation);
        } else {
            getRelationList();
        }
        model = DataSupport.where("tokenid= ? ", SharePreferenceUtil.getPreferences().getString(SharePreferenceUtil.TOKENID, "")).findFirst(BabyInfoModel.class);

    }

    private void getRelationList() {
        String url = Constants.getInvitationRelationList;
        OkHttpUtils.postString()
                .url(url)
                .content(CreateJson())
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .tag(this)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Toasty.error(AddFamilyActivity.this, getString(R.string.net_error), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Gson gson = new Gson();
                        FamilyCallBack callBack = null;
                        try {
                            callBack = gson.fromJson(response, FamilyCallBack.class);
                        } catch (JsonSyntaxException e) {
                            e.printStackTrace();
                        }
                        if (callBack != null) {
                            if (callBack.getStatus().equals("success")) {
                                mlist = callBack.getDatas();
                            } else {
                                Toasty.error(AddFamilyActivity.this, callBack.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toasty.error(AddFamilyActivity.this, getString(R.string.date_error), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }


    private String CreateJson() {
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject();
            jsonObject.put("tokenId", SharePreferenceUtil.getPreferences().getString(SharePreferenceUtil.TOKENID, ""));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonObject.toString();
    }

    @OnClick({R.id.iv_title_back, R.id.ll_sex, R.id.tv_sms, R.id.tv_wechat, R.id.tv_relation, R.id.iv_contact,R.id.btn_invite})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_title_back:
                finish();
                break;
            case R.id.tv_relation:
                showRelationDialog();
                break;
            case R.id.ll_sex:
                goBabyDetailSex();
                break;
            case R.id.tv_sms:
                resetNoticeType();
                sendType="sms";
                tvSms.setSelected(true);
                break;
            case R.id.tv_wechat:
                resetNoticeType();
                sendType="weChat";
                tvWechat.setSelected(true);
                break;
            case R.id.iv_contact:
                startActivityForResult(new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI), Constants.REQ_CONTENT);
                break;
            case R.id.btn_invite:
                sendInvite();
                break;
        }
    }

    private void sendInvite() {
      String shareurl = "http://www.atongmu.net/DownloadParents1.html";
      final String  body = model.getPersonName() + relation + ":"+model.getPersonName()+relation+"邀请您下载“阿童目”APP手机应用，实时了解"+model.getPersonName()+"在幼儿园的动态，点击下载"+shareurl+"（用户名：" + tvPhone.getText().toString() + "  初始登录密码：123456）【阿童目】";


        if(checkInfo()){
            String url=Constants.restAddNewRelationPerson_v3;
            OkHttpUtils.postString()
                    .url(url)
                    .content(CreateInviteJson())
                    .mediaType(MediaType.parse("application/json; charset=utf-8"))
                    .tag(this)
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                            Toasty.error(AddFamilyActivity.this, getString(R.string.net_error), Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onResponse(String response, int id) {
                            Gson gson = new Gson();
                            BaseCallBack callBack = null;
                            try {
                                callBack = gson.fromJson(response, BaseCallBack.class);
                            } catch (JsonSyntaxException e) {
                                e.printStackTrace();
                            }
                            if (callBack != null) {
                                if (callBack.getStatus().equals("success")) {
                                    if(sendType.equals("sms")){
                                        Intent in = new Intent(Intent.ACTION_SENDTO);
                                        in.setData(Uri.parse("smsto:" + phoneNum));
                                        in.putExtra("sms_body",body);
                                        startActivity(in);
                                    }else {
                                        Platform.ShareParams sp = new Platform.ShareParams();
                                        sp.setShareType(Platform.SHARE_TEXT);
                                        sp.setText(body);
                                        sp.setTitle("阿童目");

                                        Platform weChat = ShareSDK.getPlatform(Wechat.NAME);
                                        weChat.setPlatformActionListener(new PlatformActionListener() {
                                            @Override
                                            public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {

                                            }

                                            @Override
                                            public void onError(Platform platform, int i, Throwable throwable) {

                                            }

                                            @Override
                                            public void onCancel(Platform platform, int i) {

                                            }
                                        });
                                        weChat.share(sp);
                                    }
                                } else {
                                    Toasty.error(AddFamilyActivity.this, callBack.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toasty.error(AddFamilyActivity.this, getString(R.string.date_error), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }else {
            Toasty.info(this, getString(R.string.invite_info), Toast.LENGTH_SHORT).show();
        }
        
    }

    private String CreateInviteJson() {
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject();
            jsonObject.put("tokenId", SharePreferenceUtil.getPreferences().getString(SharePreferenceUtil.TOKENID, ""));
            jsonObject.put("name", tvName.getText().toString());
            jsonObject.put("relation", tvRelation.getText().toString());
            jsonObject.put("phone", tvPhone.getText().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonObject.toString();
    }

    private boolean checkInfo() {
        if(TextUtils.isEmpty(tvSex.getText().toString())){

            return false;
        }
        if(TextUtils.isEmpty(tvName.getText().toString())){
            return false;
        }
        if(tvRelation.equals("与宝宝的关系")){
            return false;
        }

        if(TextUtils.isEmpty(tvPhone.getText().toString())){
            return false;
        }

        if(TextUtils.isEmpty(sendType)){
            return false;
        }
        
        return true;
    }

    private void showRelationDialog() {
        if (mlist != null && mlist.size() > 0) {
            relationDialog = new RelationDialog(this, new RelationDialog.GoRelationDialog() {
                @Override
                public void go(String text) {
                    tvRelation.setText(text);
                    relationDialog.dismiss();
                }
            }, mlist);
            relationDialog.show();
        }

    }

    private void resetNoticeType() {
        tvSms.setSelected(false);
        tvWechat.setSelected(false);
    }

    private void goBabyDetailSex() {
        Intent intent = new Intent(AddFamilyActivity.this, BabyDetailEditActivity.class);
        intent.putExtra("action", "Invite");
        intent.putExtra("type", "sex");
        intent.putExtra("value", tvSex.getText().toString());
        startActivityForResult(intent, Constants.REQ_INTENT_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == Constants.REQ_INTENT_CODE) {
                String type = data.getStringExtra("type");
                String value = data.getStringExtra("value");
                if (type.equals("sex")) {
                    tvSex.setText(value);
                }

            }
            if (requestCode == Constants.REQ_CONTENT) {
                if (data == null) {
                    Log.e("TAG", "data ==== null");
                    return;
                }
                ContentResolver resolver = getContentResolver();
                Uri contactData = data.getData();
//            Uri contactData = Uri.parse("content://com.android.contacts/contacts"); // 访问所有联系人
                if (contactData == null) {
                    Log.e("TAG", "contactData ===== null");
                    return;
                }
                Cursor cursor = null;
                CursorLoader cursorLoader = new CursorLoader(this, contactData, null, null, null, null);
                cursor = cursorLoader.loadInBackground();
//            Cursor cursor = resolver.query(contactData,null,null,null,null);
//            cursor.moveToFirst();
                if (cursor == null) {
                    Log.e("TAG", "cursor ===== null");
                    return;
                }
                if (cursor.moveToFirst()) {
                    Log.e("TAG", "cursor normal");
                    //获取名字
//            String username = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                    //联系人ID
                    String contactId = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                    //获取电话号码
                    Cursor phone = resolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                            null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=" + contactId,
                            null,
                            null);
                    if (phone != null) {
                        phone.moveToNext();
                        phoneNum = phone.getString(phone.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

                        phoneNum = phoneNum.replace("+86", "");
                        phoneNum = phoneNum.replace("-", "");
                        phoneNum = phoneNum.replace(" ", "");

                        tvPhone.setText(phoneNum);
                        tvPhone.setTextColor(Color.parseColor("#434343"));
                        phone.close();
                    }
                } else {
                    phoneNum = "";
                    tvPhone.setText(phoneNum);
                }
                cursor.close();
            }

        }
    }

}
