package com.tongxun.atongmu.parent.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tongxun.atongmu.parent.Base2Activity;
import com.tongxun.atongmu.parent.R;
import com.tongxun.atongmu.parent.adapter.AddCirclePhotoAdapter;
import com.tongxun.atongmu.parent.util.DividerGridItemDecoration;
import com.tongxun.atongmu.parent.util.PickPhotoPopupWindow;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddCirclePhotoActivity extends Base2Activity<IAddCirclePhotoContract.View, AddCirclePhotoPresenter> implements IAddCirclePhotoContract.View, photoClickListener, PickPhotoPopupWindow.popClickListener {

    @BindView(R.id.tv_homework_back)
    TextView tvHomeworkBack;
    @BindView(R.id.tv_title_name)
    TextView tvTitleName;
    @BindView(R.id.tv_homework_commit)
    TextView tvHomeworkCommit;
    @BindView(R.id.et_content)
    EditText etContent;
    @BindView(R.id.rv_photo)
    RecyclerView rvPhoto;
    @BindView(R.id.ll_add_photo)
    LinearLayout llAddPhoto;

    private List<String> mlist = new ArrayList<>();

    private AddCirclePhotoAdapter mAdapter;
    private boolean isCanVideo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_circle_photo);
        setStatusColor(R.color.colorWhite);
        ButterKnife.bind(this);
        Intent intent=getIntent();
        isCanVideo=intent.getBooleanExtra("isCanVideo",false);
        rvPhoto.setLayoutManager(new GridLayoutManager(this, 4));
        rvPhoto.setItemAnimator(new DefaultItemAnimator());
        rvPhoto.addItemDecoration(new DividerGridItemDecoration(this));
        mlist.add("SELECT");
        mAdapter = new AddCirclePhotoAdapter(this, mlist, false, 9, this);
        rvPhoto.setAdapter(mAdapter);
    }


    public static void startActivity(Context context,boolean isCanVideo){
        Intent intent=new Intent(context,AddCirclePhotoActivity.class);
        intent.putExtra("isCanVideo",isCanVideo);
        context.startActivity(intent);
    }

    @Override
    protected AddCirclePhotoPresenter initPresenter() {
        return new AddCirclePhotoPresenter();
    }

    @OnClick({R.id.tv_homework_back, R.id.tv_homework_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_homework_back:
                finish();
                break;
            case R.id.tv_homework_commit:
                break;
        }
    }

    @Override
    public void onAddPhoto(int num) {
        PickPhotoPopupWindow.getInstance().show(llAddPhoto, isCanVideo, this);
    }

    @Override
    public void onPhotoClick(String photoUrl) {

    }

    @Override
    public void onCamera() {

    }

    @Override
    public void onPhoto() {

    }

    @Override
    public void onVideo() {

    }
}
