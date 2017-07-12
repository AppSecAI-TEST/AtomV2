package com.tongxun.atongmu.parent.ui.im;

import android.Manifest;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.kaopiz.kprogresshud.KProgressHUD;
import com.tongxun.atongmu.parent.Base2Activity;
import com.tongxun.atongmu.parent.Constants;
import com.tongxun.atongmu.parent.R;
import com.tongxun.atongmu.parent.adapter.GroupMemberAdapter;
import com.tongxun.atongmu.parent.dialog.CommonDialog;
import com.tongxun.atongmu.parent.model.GroupMemberModel;
import com.tongxun.atongmu.parent.util.SystemUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;
import kr.co.namee.permissiongen.PermissionFail;
import kr.co.namee.permissiongen.PermissionGen;
import kr.co.namee.permissiongen.PermissionSuccess;

public class GroupMemberActivity extends Base2Activity<IGroupMemberContract.View, GroupMemberPresenter> implements IGroupMemberContract.View, GroupMemberAdapter.IGourpMemberListener {

    @BindView(R.id.iv_title_back)
    ImageView ivTitleBack;
    @BindView(R.id.tv_title_name)
    TextView tvTitleName;
    @BindView(R.id.rv_family)
    RecyclerView rvFamily;
    @BindView(R.id.rv_gardener)
    RecyclerView rvGardener;
    private KProgressHUD hud;

    private GroupMemberAdapter parentAdapter;
    private GroupMemberAdapter teacherAdapter;

    private CommonDialog commonDialog;

    private String mPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_member);
        ButterKnife.bind(this);
        setStatusColor(R.color.colorWhite);
        tvTitleName.setText(getResources().getString(R.string.group_member));
        hud = KProgressHUD.create(this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel(getResources().getString(R.string.loading))
                .setCancellable(true)
                .setAnimationSpeed(1)
                .setDimAmount(0.5f);
        rvFamily.setItemAnimator(new DefaultItemAnimator());
        rvFamily.setLayoutManager(new LinearLayoutManager(this));
        rvGardener.setItemAnimator(new DefaultItemAnimator());
        rvGardener.setLayoutManager(new LinearLayoutManager(this));
        mPresenter.getGroupMember();
    }

    @Override
    protected GroupMemberPresenter initPresenter() {
        return new GroupMemberPresenter();
    }

    @Override
    public void showProgress() {
        hud.show();
    }

    @Override
    public void hideProgress() {
        hud.dismiss();
    }

    @Override
    public void onError(String message) {
        Toasty.error(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setRefreshAdapter(List<GroupMemberModel> parentdatas, List<GroupMemberModel> teacherdatas) {
        parentAdapter=new GroupMemberAdapter(this,parentdatas,this);
        teacherAdapter=new GroupMemberAdapter(this,teacherdatas,this);
        rvFamily.setAdapter(parentAdapter);
        rvGardener.setAdapter(teacherAdapter);
    }

    @OnClick(R.id.iv_title_back)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void Phone(final String phone) {
        mPhone=phone;

        commonDialog=new CommonDialog(this, getResources().getString(R.string.is_call_phone) + phone + "?", getResources().getString(R.string.confirm), getResources().getString(R.string.cancel), new CommonDialog.GoCommonDialog() {
            @Override
            public void go() {
                PermissionGen.with(GroupMemberActivity.this)
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

    @PermissionSuccess(requestCode = 104)
    public void doPhone() {
        SystemUtil.openSystemPhone(GroupMemberActivity.this,mPhone);
    }

    @PermissionFail(requestCode = 104)
    public void doSomeError() {
        Toasty.error(this, getResources().getString(R.string.get_phone_permission_error), Toast.LENGTH_LONG).show();
    }



}
