package com.tongxun.atongmu.parent.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.tongxun.atongmu.parent.BaseActivity;
import com.tongxun.atongmu.parent.IPhotoSelectListener;
import com.tongxun.atongmu.parent.R;
import com.tongxun.atongmu.parent.adapter.AtomAlbumPhotoAdapter;
import com.tongxun.atongmu.parent.util.DividerGridItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;

public class AtomAlbumPhotoActivity extends BaseActivity implements IPhotoSelectListener {

    @BindView(R.id.tv_homework_back)
    TextView tvHomeworkBack;
    @BindView(R.id.tv_homework_commit)
    TextView tvHomeworkCommit;
    @BindView(R.id.rv_atom_album)
    RecyclerView rvAtomAlbum;
    @BindView(R.id.tv_title_name)
    TextView tvTitleName;
    private List<String> mlist = new ArrayList<>();

    private AtomAlbumPhotoAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atom_album_photo);
        setStatusColor(R.color.colorWhite);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        mlist = intent.getStringArrayListExtra("Data");
        tvTitleName.setText(getResources().getString(R.string.picture_select));
        tvHomeworkCommit.setText(getResources().getString(R.string.confirm));
        rvAtomAlbum.setItemAnimator(new DefaultItemAnimator());
        rvAtomAlbum.setLayoutManager(new GridLayoutManager(this, 4));
        rvAtomAlbum.addItemDecoration(new DividerGridItemDecoration(this));
        mAdapter = new AtomAlbumPhotoAdapter(this, mlist, this);
        rvAtomAlbum.setAdapter(mAdapter);
        rvAtomAlbum.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if(newState==2){
                    Glide.with(AtomAlbumPhotoActivity.this).pauseRequests();
                }else {
                    Glide.with(AtomAlbumPhotoActivity.this).resumeRequests();

                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }

    @Override
    public void setPhotoSelect(int position) {
        mAdapter.notifyItemChanged(position);
    }

    @Override
    public void setAddError() {
        Toasty.info(this, getResources().getString(R.string.photo_select_max), Toast.LENGTH_SHORT).show();
    }


    @OnClick({R.id.tv_homework_back, R.id.tv_homework_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_homework_back:
                finish();
                break;
            case R.id.tv_homework_commit:
                if(PhotoSelectContainer.getFileList().size()>0){
                    setResult(RESULT_OK);
                    finish();
                }else {
                    Toasty.info(this,getResources().getString(R.string.please_select_photo), Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
