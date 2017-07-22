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

import com.tongxun.atongmu.parent.R;
import com.tongxun.atongmu.parent.ui.my.OpinionFeedBackActivity;

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

    @OnClick({R.id.ll_user_info, R.id.ll_feedback})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_user_info:
                break;
            case R.id.ll_feedback:
                goFeedBack();
                break;
        }
    }

    private void goFeedBack() {
        Intent intent=new Intent(getActivity(), OpinionFeedBackActivity.class);
        startActivity(intent);
    }
}
