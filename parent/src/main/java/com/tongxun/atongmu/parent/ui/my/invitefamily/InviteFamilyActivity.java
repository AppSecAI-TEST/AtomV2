package com.tongxun.atongmu.parent.ui.my.invitefamily;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.tongxun.atongmu.parent.Base2Activity;
import com.tongxun.atongmu.parent.R;
import com.tongxun.atongmu.parent.adapter.InviteFamilyBindAdapter;
import com.tongxun.atongmu.parent.adapter.InviteFamilyUnBindAdapter;
import com.tongxun.atongmu.parent.model.InviteFamilyBindModel;
import com.tongxun.atongmu.parent.model.InviteFamilyUnBindModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;

public class InviteFamilyActivity extends Base2Activity<InviteFamilyContract.View, InviteFamilyPresenter> implements InviteFamilyContract.View {

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
    private InviteFamilyUnBindAdapter unBindAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invite_family);
        setStatusColor(R.color.colorWhite);
        ButterKnife.bind(this);
        mPresenter.getFamilyInfo();

        tvTitleName.setText(getString(R.string.family_info));

        setRecyclerViewUI();
    }

    private void setRecyclerViewUI() {
        rvFamilyBind.setItemAnimator(new DefaultItemAnimator());
        rvFamilyBind.setLayoutManager(new GridLayoutManager(this,2));
        rvFamilyUnbind.setItemAnimator(new DefaultItemAnimator());
        rvFamilyUnbind.setLayoutManager(new GridLayoutManager(this,2));
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
        bindAdapter=new InviteFamilyBindAdapter(this,bindingData);
        rvFamilyBind.setAdapter(bindAdapter);
    }

    @OnClick({R.id.iv_title_back, R.id.tv_invite_join})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_title_back:
                break;
            case R.id.tv_invite_join:
                break;
        }
    }
}
