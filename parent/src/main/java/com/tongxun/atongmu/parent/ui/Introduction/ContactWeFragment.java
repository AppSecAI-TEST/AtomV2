package com.tongxun.atongmu.parent.ui.Introduction;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;

import com.tongxun.atongmu.parent.R;
import com.tongxun.atongmu.parent.widget.CircleWebView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Anro on 2017/7/14.
 */

public class ContactWeFragment extends Fragment {

    @BindView(R.id.wb_circle)
    CircleWebView wbCircle;
    Unbinder unbinder;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        wbCircle.getSettings().setJavaScriptEnabled(true);
        wbCircle.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contact_we, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public void setDate(String htmlPath) {
        wbCircle.loadUrl(htmlPath);
    }
}
