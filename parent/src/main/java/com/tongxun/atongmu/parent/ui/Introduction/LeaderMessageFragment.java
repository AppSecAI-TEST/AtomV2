package com.tongxun.atongmu.parent.ui.Introduction;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tongxun.atongmu.parent.R;
import com.tongxun.atongmu.parent.model.TeacherStyleModel;

import java.util.List;

/**
 * Created by Anro on 2017/7/14.
 */

public class LeaderMessageFragment extends Fragment implements ISchoolIntroductionContract.View<SchoolIntroductionPresenter> {

    private SchoolIntroductionPresenter mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_leader_message,container,false);
        return view;
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void setPresenter(SchoolIntroductionPresenter presenter) {
        mPresenter=presenter;
    }

    @Override
    public void onError(String message) {

    }

    @Override
    public void onRefreshAdapter(List<TeacherStyleModel> data) {

    }
}
