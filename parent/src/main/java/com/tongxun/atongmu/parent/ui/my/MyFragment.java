package com.tongxun.atongmu.parent.ui.my;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.tongxun.atongmu.parent.Constants;
import com.tongxun.atongmu.parent.R;
import com.tongxun.atongmu.parent.model.BabyInfoModel;
import com.tongxun.atongmu.parent.ui.my.accountchange.AccountChangeActivity;
import com.tongxun.atongmu.parent.ui.my.babydetail.BabyDetailActivity;
import com.tongxun.atongmu.parent.ui.my.feedback.OpinionFeedBackActivity;
import com.tongxun.atongmu.parent.ui.my.growprofile.GrowProfileActivity;
import com.tongxun.atongmu.parent.ui.my.invitefamily.InviteFamilyActivity;
import com.tongxun.atongmu.parent.ui.my.personreminder.ReminderActivity;
import com.tongxun.atongmu.parent.ui.my.recharge.RechargeActivity;
import com.tongxun.atongmu.parent.ui.my.shuttlephoto.ShuttlePhotoActivity;
import com.tongxun.atongmu.parent.ui.my.usehelp.UseHelpActivity;
import com.tongxun.atongmu.parent.util.GlideOption;
import com.tongxun.atongmu.parent.util.SharePreferenceUtil;

import org.litepal.crud.DataSupport;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;
import es.dmoral.toasty.Toasty;

/**
 * Created by Anro on 2017/7/21.
 */

public class MyFragment extends Fragment implements IMyContract.View<MyPresenter> {

    MyPresenter mPresenter;
    @BindView(R.id.civ_face)
    CircleImageView civFace;
    @BindView(R.id.tv_user_name)
    TextView tvUserName;
    @BindView(R.id.tv_user_level)
    TextView tvUserLevel;
    @BindView(R.id.ll_user_info)
    RelativeLayout llUserInfo;
    @BindView(R.id.ll_feedback)
    LinearLayout llFeedback;
    Unbinder unbinder;

    BabyInfoModel babyInfoModel;
    @BindView(R.id.civ_baby_face)
    CircleImageView civBabyFace;
    @BindView(R.id.tv_baby_name)
    TextView tvBabyName;
    @BindView(R.id.ll_use_help)
    LinearLayout llUseHelp;
    @BindView(R.id.ll_grow_profile)
    LinearLayout llGrowProfile;
    @BindView(R.id.ll_shuttle_photo)
    LinearLayout llShuttlePhoto;
    @BindView(R.id.tv_integral)
    TextView tvIntegral;
    @BindView(R.id.tv_flower)
    TextView tvFlower;
    @BindView(R.id.tv_family_num)
    TextView tvFamilyNum;
    @BindView(R.id.tv_circle_num)
    TextView tvCircleNum;
    @BindView(R.id.ll_baby_info)
    LinearLayout llBabyInfo;
    @BindView(R.id.ll_recharge)
    LinearLayout llRecharge;
    @BindView(R.id.ll_account_change)
    LinearLayout llAccountChange;
    @BindView(R.id.ll_person_reminder)
    LinearLayout llPersonReminder;
    @BindView(R.id.ll_family)
    LinearLayout llFamily;

    private boolean isUpDate = false;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onResume() {
        super.onResume();
        if (isUpDate) {
            isUpDate = false;
            babyInfoModel = DataSupport.where("tokenid= ? ", SharePreferenceUtil.getPreferences().getString(SharePreferenceUtil.TOKENID, "")).findFirst(BabyInfoModel.class);
            setBabyInfoUI();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_layout, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        babyInfoModel = DataSupport.where("tokenid= ? ", SharePreferenceUtil.getPreferences().getString(SharePreferenceUtil.TOKENID, "")).findFirst(BabyInfoModel.class);
        setBabyInfoUI();
    }

    private void setBabyInfoUI() {
        if (babyInfoModel != null) {
            tvBabyName.setText(babyInfoModel.getPersonName());
            Glide.with(this).load(babyInfoModel.getPhoto1()).apply(GlideOption.getFaceHolderOption()).into(civBabyFace);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        mPresenter.getFlowerHonor();
    }

    @Override
    public void setPresenter(MyPresenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onError(String message) {
        Toasty.error(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFlowerHonorSuccess(String integral, String flowers, String parentNum, String circleNum) {
        tvIntegral.setText(integral);
        tvFlower.setText(flowers);
        tvFamilyNum.setText(parentNum);
        tvCircleNum.setText(circleNum);
    }

    @Override
    public void onDestroy() {
        if (mPresenter != null) {
            mPresenter.detachView();
        }
        super.onDestroy();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.ll_user_info, R.id.ll_feedback, R.id.ll_family,R.id.ll_use_help, R.id.ll_grow_profile, R.id.ll_shuttle_photo, R.id.ll_baby_info, R.id.ll_recharge, R.id.ll_account_change, R.id.ll_person_reminder})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_user_info:
                break;
            case R.id.ll_feedback:
                goFeedBack();
                break;
            case R.id.ll_use_help:
                goUseHelp();
                break;
            case R.id.ll_grow_profile:
                goGrowProfile();
                break;
            case R.id.ll_shuttle_photo:
                goShuttlePhoto();
                break;
            case R.id.ll_baby_info:
                goBabyInfo();
                break;
            case R.id.ll_recharge:
                goRecharge();
                break;
            case R.id.ll_account_change:
                goAccountChange();
                break;
            case R.id.ll_person_reminder:
                goPresonReminder();
                break;
            case R.id.ll_family:
                goFamily();
                break;
        }
    }

    private void goFamily() {
        Intent intent=new Intent(getActivity(), InviteFamilyActivity.class);
        startActivity(intent);
    }

    private void goPresonReminder() {
        Intent intent = new Intent(getActivity(), ReminderActivity.class);
        startActivity(intent);
    }

    private void goAccountChange() {
        Intent intent = new Intent(getActivity(), AccountChangeActivity.class);
        getActivity().startActivityForResult(intent, Constants.ChANGE_ACCOUNT);
    }

    private void goRecharge() {
        Intent intent = new Intent(getActivity(), RechargeActivity.class);
        startActivity(intent);
    }

    private void goBabyInfo() {
        Intent intent = new Intent(getActivity(), BabyDetailActivity.class);
        getActivity().startActivityForResult(intent, Constants.ChANGE_ACCOUNT);
    }

    private void goShuttlePhoto() {
        Intent intent = new Intent(getActivity(), ShuttlePhotoActivity.class);
        startActivity(intent);
    }

    private void goGrowProfile() {
        Intent intent = new Intent(getActivity(), GrowProfileActivity.class);
        startActivity(intent);
    }

    private void goUseHelp() {
        Intent intent = new Intent(getActivity(), UseHelpActivity.class);
        startActivity(intent);
    }

    private void goFeedBack() {
        Intent intent = new Intent(getActivity(), OpinionFeedBackActivity.class);
        startActivity(intent);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == getActivity().RESULT_OK) {
            if (requestCode == Constants.CHANGE_INFO) {
                babyInfoModel = DataSupport.where("tokenid= ? ", SharePreferenceUtil.getPreferences().getString(SharePreferenceUtil.TOKENID, "")).findFirst(BabyInfoModel.class);
                setBabyInfoUI();
            }
        }
    }


    public void changeDate() {
        isUpDate = true;
    }
}
