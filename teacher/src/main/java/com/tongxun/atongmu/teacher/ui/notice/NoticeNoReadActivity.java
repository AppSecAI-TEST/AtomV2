package com.tongxun.atongmu.teacher.ui.notice;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.tongxun.atongmu.teacher.BaseActivity;
import com.tongxun.atongmu.teacher.Constants;
import com.tongxun.atongmu.teacher.IonItemClickListener;
import com.tongxun.atongmu.teacher.R;
import com.tongxun.atongmu.teacher.adapter.PersonStatusAdapter;
import com.tongxun.atongmu.teacher.dialog.CommonDialog;
import com.tongxun.atongmu.teacher.model.FamilyGroupCallBack;
import com.tongxun.atongmu.teacher.model.FamilyPersonModel;
import com.tongxun.atongmu.teacher.util.SharePreferenceUtil;
import com.tongxun.atongmu.teacher.util.SystemUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;
import kr.co.namee.permissiongen.PermissionFail;
import kr.co.namee.permissiongen.PermissionGen;
import kr.co.namee.permissiongen.PermissionSuccess;
import okhttp3.Call;
import okhttp3.MediaType;

public class NoticeNoReadActivity extends BaseActivity implements IonItemClickListener {

    @BindView(R.id.iv_title_back)
    ImageView ivTitleBack;
    @BindView(R.id.tv_title_name)
    TextView tvTitleName;
    @BindView(R.id.rl_title)
    RelativeLayout rlTitle;
    @BindView(R.id.rv_content)
    RecyclerView rvContent;

    private String studentId;

    private PersonStatusAdapter mAdapter;

    private List<FamilyPersonModel> mlist = new ArrayList<>();

    private CommonDialog commonDialog;

    private String mPhone;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_no_read);
        setStatusColor(R.color.colorWhite);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        studentId = intent.getStringExtra("studentId");
        tvTitleName.setText(R.string.reminder_read);

        setRecycerViewUI();
        if (studentId != null) {
            getFamilyGroup();
        }
    }

    private void setRecycerViewUI() {
        rvContent.setItemAnimator(new DefaultItemAnimator());
        rvContent.setLayoutManager(new LinearLayoutManager(this));
    }

    private void getFamilyGroup() {
        String url = Constants.restGetClasscStudentRelation_v2;
        OkHttpUtils.postString()
                .url(url)
                .content(CreateJson())
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .tag(this)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Toasty.error(NoticeNoReadActivity.this, getString(R.string.net_error), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Gson gson = new Gson();
                        FamilyGroupCallBack callBack = null;
                        try {
                            callBack = gson.fromJson(response, FamilyGroupCallBack.class);
                        } catch (JsonSyntaxException e) {
                            e.printStackTrace();
                        }

                        if (callBack != null) {
                            if (callBack.getStatus().equals("success")) {

                                setRecyclerDate(callBack.getDatas());

                            } else {
                                Toasty.error(NoticeNoReadActivity.this, callBack.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toasty.error(NoticeNoReadActivity.this, getString(R.string.date_error), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void setRecyclerDate(List<FamilyPersonModel> datas) {
        mlist = datas;
        mAdapter = new PersonStatusAdapter(this, datas, this);
        rvContent.setAdapter(mAdapter);
    }

    private String CreateJson() {
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject();
            jsonObject.put("tokenId", SharePreferenceUtil.getPreferences().getString(SharePreferenceUtil.TOKENID, ""));
            jsonObject.put("studentId", studentId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonObject.toString();
    }


    @Override
    public void onItemClick(int position) {
        if (mlist != null) {
            mPhone= mlist.get(position).getParent_phone();
            commonDialog=new CommonDialog(this, getResources().getString(R.string.is_call_phone) + mPhone + "?", getResources().getString(R.string.confirm), getResources().getString(R.string.cancel), new CommonDialog.GoCommonDialog() {
                @Override
                public void go() {
                    PermissionGen.with(NoticeNoReadActivity.this)
                            .addRequestCode(Constants.PERMISSION_PHONE_CODE)
                            .permissions(
                                    Manifest.permission.CALL_PHONE
                            )
                            .request();
                    commonDialog.dismiss();
                }

                @Override
                public void cancel() {
                    commonDialog.dismiss();
                }
            });
            commonDialog.show();

        }
    }

    @PermissionSuccess(requestCode = 104)
    public void doPhone() {
        SystemUtil.openSystemPhone(NoticeNoReadActivity.this,mPhone);
    }

    @PermissionFail(requestCode = 104)
    public void doSomeError() {
        Toasty.error(this, getResources().getString(R.string.get_phone_permission_error), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        PermissionGen.onRequestPermissionsResult(this,requestCode,permissions,grantResults);
    }

    @OnClick(R.id.iv_title_back)
    public void onViewClicked() {
        finish();
    }
}
