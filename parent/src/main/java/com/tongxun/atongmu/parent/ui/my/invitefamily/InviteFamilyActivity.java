package com.tongxun.atongmu.parent.ui.my.invitefamily;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.tongxun.atongmu.parent.Base2Activity;
import com.tongxun.atongmu.parent.Constants;
import com.tongxun.atongmu.parent.R;
import com.tongxun.atongmu.parent.adapter.InviteFamilyBindAdapter;
import com.tongxun.atongmu.parent.dialog.RemindActivitionDialog;
import com.tongxun.atongmu.parent.model.BabyInfoModel;
import com.tongxun.atongmu.parent.model.InviteFamilyBindModel;
import com.tongxun.atongmu.parent.model.InviteFamilyUnBindModel;
import com.tongxun.atongmu.parent.util.DensityUtil;
import com.tongxun.atongmu.parent.util.RecycleViewDivider;
import com.tongxun.atongmu.parent.util.SharePreferenceUtil;

import org.litepal.crud.DataSupport;

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

public class InviteFamilyActivity extends Base2Activity<InviteFamilyContract.View, InviteFamilyPresenter> implements InviteFamilyContract.View, InviteFamilyClickListener {

    @BindView(R.id.iv_title_back)
    ImageView ivTitleBack;
    @BindView(R.id.tv_title_name)
    TextView tvTitleName;
    @BindView(R.id.tv_invite_join)
    TextView tvInviteJoin;
    @BindView(R.id.rv_family_bind)
    RecyclerView rvFamilyBind;
    @BindView(R.id.rv_family_unbind)
    RecyclerView rvFamilyUnbind;
    private List<InviteFamilyBindModel> bindingData;
    private List<InviteFamilyUnBindModel> unBindingData;

    private InviteFamilyBindAdapter bindAdapter;
    private InviteFamilyBindAdapter unBindAdapter;

    private RemindActivitionDialog activitionDialog;

    private BabyInfoModel babyInfoModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invite_family);
        setStatusColor(R.color.colorWhite);
        ButterKnife.bind(this);
        mPresenter.getFamilyInfo();

        tvTitleName.setText(getString(R.string.family_info));
        babyInfoModel= DataSupport.where("tokenid= ? ", SharePreferenceUtil.getPreferences().getString(SharePreferenceUtil.TOKENID, "")).findFirst(BabyInfoModel.class);


        setRecyclerViewUI();
    }

    private void setRecyclerViewUI() {
        rvFamilyBind.setItemAnimator(new DefaultItemAnimator());
        rvFamilyBind.setLayoutManager(new GridLayoutManager(this,2));
        rvFamilyUnbind.setItemAnimator(new DefaultItemAnimator());
        rvFamilyUnbind.setLayoutManager(new GridLayoutManager(this,2));
        rvFamilyUnbind.addItemDecoration(new RecycleViewDivider(this, LinearLayoutManager.VERTICAL, DensityUtil.dip2px(this,20), getResources().getColor(R.color.colorGrayBg)));
        rvFamilyBind.addItemDecoration(new RecycleViewDivider(this, LinearLayoutManager.VERTICAL, DensityUtil.dip2px(this,20), getResources().getColor(R.color.colorGrayBg)));
    }

    @Override
    protected InviteFamilyPresenter initPresenter() {
        return new InviteFamilyPresenter();
    }

    @Override
    public void onError(String message) {
        Toasty.error(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onInviteInfoSuccess(List<InviteFamilyBindModel> bindingData, List<InviteFamilyUnBindModel> unBindingData) {
        this.bindingData = bindingData;
        this.unBindingData = unBindingData;
        bindAdapter=new InviteFamilyBindAdapter(this,bindingData,this);
        rvFamilyBind.setAdapter(bindAdapter);
        unBindAdapter=new InviteFamilyBindAdapter(this,unBindingData,this);
        rvFamilyUnbind.setAdapter(unBindAdapter);
    }

    @OnClick({R.id.iv_title_back, R.id.tv_invite_join})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_title_back:
                finish();
                break;
            case R.id.tv_invite_join:
                onAddFamily("");
                break;
        }
    }

    @Override
    public void onAddFamily(String relation) {
        Intent intent=new Intent(InviteFamilyActivity.this,AddFamilyActivity.class);
        intent.putExtra("relation",relation);
        startActivityForResult(intent, Constants.REQ_INTENT_CODE);
    }

    @Override
    public void onLookDetail(InviteFamilyBindModel model) {
        Intent intent=new Intent(this,FamilyInfoActivity.class);
        intent.putExtra("model",model);
        startActivityForResult(intent,Constants.REQ_CANCEL);
    }

    @Override
    public void onRemindAction(InviteFamilyBindModel model) {
        activitionDialog=new RemindActivitionDialog(this, model.getHeadImage(), model.getPersonName(), model.getPersonPhone(), model.getPassword(), model.getRelation(),new RemindActivitionDialog.InviteActivitionListener() {
            @Override
            public void onRemindActivition(String name, String account, String password, String sendType,String relation) {
                String shareurl = "http://www.atongmu.net/DownloadParents1.html";
                final String  body = babyInfoModel.getPersonName() + relation + ":"+babyInfoModel.getPersonName() +relation+"邀请您下载“阿童目”APP手机应用，实时了解"+babyInfoModel.getPersonName() +"在幼儿园的动态，点击下载"+shareurl+"（用户名：" + account + "  初始登录密码："+password+"）【阿童目】";
                if(sendType.equals("sms")){
                    Intent in = new Intent(Intent.ACTION_SENDTO);
                    in.setData(Uri.parse("smsto:" + account));
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
                activitionDialog.dismiss();
            }
        });
        activitionDialog.show();
    }
}
