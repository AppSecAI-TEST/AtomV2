package com.tongxun.atongmu.parent.ui.album;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.kaopiz.kprogresshud.KProgressHUD;
import com.tongxun.atongmu.parent.Base2Activity;
import com.tongxun.atongmu.parent.R;
import com.tongxun.atongmu.parent.adapter.TimeAlbumAdapter;
import com.tongxun.atongmu.parent.model.TimeAlbumModel;
import com.tongxun.atongmu.parent.ui.AddCirclePhotoActivity;
import com.tongxun.atongmu.parent.util.DensityUtil;
import com.tongxun.atongmu.parent.util.RecycleViewDivider;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;

public class TimeAlbumActivity extends Base2Activity<ITimeAlbumContract.View, TimeAlbumPresenter> implements ITimeAlbumContract.View, TimeAlbumAdapter.ITimeAlbumListener {

    @BindView(R.id.iv_title_back)
    ImageView ivTitleBack;
    @BindView(R.id.tv_title_name)
    TextView tvTitleName;
    @BindView(R.id.iv_circle_title_add)
    ImageView ivCircleTitleAdd;
    @BindView(R.id.rv_course_content)
    RecyclerView rvCourseContent;

    private TimeAlbumAdapter mAdapter;

    private KProgressHUD hud;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_album);
        setStatusColor(R.color.colorWhite);
        ButterKnife.bind(this);

        hud = KProgressHUD.create(this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel(getResources().getString(R.string.loading))
                .setCancellable(true)
                .setAnimationSpeed(1)
                .setDimAmount(0.5f);

        rvCourseContent.setItemAnimator(new DefaultItemAnimator());
        rvCourseContent.setLayoutManager(new LinearLayoutManager(this));
        rvCourseContent.addItemDecoration(new RecycleViewDivider(this, LinearLayoutManager.VERTICAL, DensityUtil.dip2px(this,10), getResources().getColor(R.color.colorLineGray)));

        mPresenter.getTimeAlbum();
        showProgress();
    }

    @Override
    protected TimeAlbumPresenter initPresenter() {
        return new TimeAlbumPresenter();
    }

    @Override
    public void showProgress() {
        hud.show();
    }

    @Override
    public void hideProgress() {
        hud.dismiss();
    }

    @OnClick({R.id.iv_title_back, R.id.iv_circle_title_add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_title_back:
                finish();
                break;
            case R.id.iv_circle_title_add:
                AddCirclePhotoActivity.startActivity(this,"TimeAlbum",10,false);
                break;
        }
    }


    @Override
    public void onError(String message) {
        hideProgress();
        Toasty.error(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setDates(List<TimeAlbumModel> datas) {
        hideProgress();
        mAdapter=new TimeAlbumAdapter(this,datas,this);
        rvCourseContent.setAdapter(mAdapter);
    }

    @Override
    public void goAlbumFromDate(String date) {
        Intent intent=new Intent(TimeAlbumActivity.this,AlbumFromDateActivity.class);
        intent.putExtra("date",date);
        startActivity(intent);
    }

    @Override
    public void goMornCheckAlbum() {
        Intent intent=new Intent(TimeAlbumActivity.this,MornCheckActivity.class);
        startActivity(intent);
    }
}
