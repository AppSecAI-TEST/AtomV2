package com.tongxun.atongmu.parent.ui.my.shuttlephoto;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.tongxun.atongmu.parent.Base2Activity;
import com.tongxun.atongmu.parent.R;
import com.tongxun.atongmu.parent.adapter.ShuttlePhotoAdapter;
import com.tongxun.atongmu.parent.model.ShuttlePhotoModel;
import com.tongxun.atongmu.parent.util.DensityUtil;
import com.tongxun.atongmu.parent.util.RecycleViewDivider;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;

public class ShuttlePhotoActivity extends Base2Activity<IShuttlePhotoContract.View, ShuttlePhotoPresenter> implements IShuttlePhotoContract.View {

    @BindView(R.id.iv_title_back)
    ImageView ivTitleBack;
    @BindView(R.id.tv_title_name)
    TextView tvTitleName;
    @BindView(R.id.iv_title_qr_code)
    ImageView ivTitleQrCode;
    @BindView(R.id.rv_shuttle_content)
    RecyclerView rvShuttleContent;
    private ShuttlePhotoAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shuttle_photo);
        ButterKnife.bind(this);
        mPresenter.getShuttlePhoto();
        rvShuttleContent.setItemAnimator(new DefaultItemAnimator());
        rvShuttleContent.setLayoutManager(new LinearLayoutManager(this));
        rvShuttleContent.addItemDecoration(new RecycleViewDivider(this, LinearLayoutManager.VERTICAL, DensityUtil.dip2px(this, 10), getResources().getColor(R.color.colorLineGray)));

    }

    @Override
    protected ShuttlePhotoPresenter initPresenter() {
        return new ShuttlePhotoPresenter();
    }

    @Override
    public void onError(String message) {
        Toasty.error(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccess(List<ShuttlePhotoModel> data) {
        mAdapter=new ShuttlePhotoAdapter(this,data);
        rvShuttleContent.setAdapter(mAdapter);
    }

    @OnClick({R.id.iv_title_back, R.id.iv_title_qr_code})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_title_back:
                finish();
                break;
            case R.id.iv_title_qr_code:
                break;
        }
    }
}
