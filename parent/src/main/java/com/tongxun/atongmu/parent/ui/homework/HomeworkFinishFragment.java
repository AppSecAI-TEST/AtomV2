package com.tongxun.atongmu.parent.ui.homework;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tongxun.atongmu.parent.R;

/**
 * Created by Anro on 2017/7/5.
 */

public class HomeworkFinishFragment extends Fragment implements IHomeworkFinishContract.View<HomeworkFinishPresenter> {

    private HomeworkFinishPresenter mPresenter=null;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_finish_layout,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void setPresenter(HomeworkFinishPresenter presenter) {
        mPresenter=presenter;
    }

    @Override
    public void onDestroy() {
        if(mPresenter!=null){
            mPresenter.detachView();
        }
        super.onDestroy();

    }
}
