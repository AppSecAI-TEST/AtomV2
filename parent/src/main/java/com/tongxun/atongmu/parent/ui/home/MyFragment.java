package com.tongxun.atongmu.parent.ui.home;

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

import com.bumptech.glide.Glide;
import com.tongxun.atongmu.parent.R;
import com.tongxun.atongmu.parent.model.BabyInfoModel;
import com.tongxun.atongmu.parent.ui.my.feedback.OpinionFeedBackActivity;
import com.tongxun.atongmu.parent.ui.my.usehelp.UseHelpActivity;
import com.tongxun.atongmu.parent.util.GlideOption;
import com.tongxun.atongmu.parent.util.SharePreferenceUtil;

import org.litepal.crud.DataSupport;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;

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

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
    public void setPresenter(MyPresenter presenter) {
        mPresenter = presenter;
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

    @OnClick({R.id.ll_user_info, R.id.ll_feedback,R.id.ll_use_help})
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
        }
    }

    private void goUseHelp() {
        Intent intent = new Intent(getActivity(), UseHelpActivity.class);
        startActivity(intent);
    }

    private void goFeedBack() {
        Intent intent = new Intent(getActivity(), OpinionFeedBackActivity.class);
        startActivity(intent);
    }
}
