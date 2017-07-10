package com.tongxun.atongmu.parent.ui;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.kaopiz.kprogresshud.KProgressHUD;
import com.tongxun.atongmu.parent.BaseActivity;
import com.tongxun.atongmu.parent.R;
import com.tongxun.atongmu.parent.model.ImageLoadBean;
import com.tongxun.atongmu.parent.util.SDCardUtil;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;

/**
 * 照片相册列表
 */
public class AtomAlbumActivity extends BaseActivity {

    private static final int DATA_LOAD_COMPLETE = 1001;
    private static final int PICK_PHOTO = 1002;
    KProgressHUD hud;
    @BindView(R.id.tv_homework_back)
    TextView tvHomeworkBack;
    @BindView(R.id.tv_title_name)
    TextView tvTitleName;
    @BindView(R.id.tv_homework_commit)
    TextView tvHomeworkCommit;
    @BindView(R.id.rv_atom_album)
    RecyclerView rvAtomAlbum;

    private ArrayList<String> allList = new ArrayList<>();
    private List<ImageLoadBean> dirlist = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atom_album_photo);
        setStatusColor(R.color.colorWhite);
        ButterKnife.bind(this);

        if (!SDCardUtil.getInstance().isHasSdcard()) {
            Toasty.warning(this, getResources().getString(R.string.sd_mounted_error), Toast.LENGTH_SHORT).show();
            return;
        }
        hud = KProgressHUD.create(this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel(getResources().getString(R.string.loading))
                .setCancellable(true)
                .setAnimationSpeed(1)
                .setDimAmount(0.5f)
                .show();
        new Thread() {
            @Override
            public void run() {
                /**
                 * 获取系统所有URI
                 */
                Uri mImgUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                ContentResolver cr = getContentResolver();
                Cursor cursor = cr.query(mImgUri,
                        null,
                        MediaStore.Images.Media.MIME_TYPE + "= ? or " + MediaStore.Images.Media.MIME_TYPE + "= ?",
                        new String[]{"image/jpeg", "image/png"},
                        MediaStore.Images.Media.DATE_MODIFIED + " desc");

                Set<String> mDirPath = new HashSet<>();

                while (cursor.moveToNext()) {
                    //获取图片的绝对路径
                    String path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
                    allList.add(path);
                    File parentFile = new File(path).getParentFile();
                    if (parentFile == null) {
                        continue;
                    }

                    if (parentFile.list() == null || parentFile.list().length == 0) {
                        continue;
                    }

                    String dirpath = parentFile.getAbsolutePath();
                    ImageLoadBean imageLoadBean = null;

                    if (mDirPath.contains(dirpath)) {
                        continue;
                    } else {
                        mDirPath.add(dirpath);
                        imageLoadBean = new ImageLoadBean();
                        imageLoadBean.setDirPath(dirpath);
                        imageLoadBean.setFirstImgPath(path);
                        imageLoadBean.setDirName(new File(dirpath).getName());
                    }

                    int num = parentFile.list(new FilenameFilter() {
                        @Override
                        public boolean accept(File dir, String filename) {
                            filename = filename.toLowerCase();
                            if (filename.endsWith(".jpg") || filename.endsWith(".jpeg") || filename.endsWith(".png"))
                                return true;
                            return false;
                        }
                    }).length;

                    imageLoadBean.setCount(num);
                    dirlist.add(imageLoadBean);
                }

                ImageLoadBean imageLoaderBean = new ImageLoadBean();
                imageLoaderBean.setCount(allList.size());
                imageLoaderBean.setDirPath(getResources().getString(R.string.all_image));
                imageLoaderBean.setDirName(getResources().getString(R.string.all_image));
                imageLoaderBean.setFirstImgPath(allList.get(0));
                dirlist.add(0, imageLoaderBean);
                cursor.close();
                handler.sendEmptyMessage(DATA_LOAD_COMPLETE);

            }
        }.start();
        tvTitleName.setText(getResources().getString(R.string.album_select));
        tvHomeworkCommit.setText(getResources().getString(R.string.cancel));
        tvHomeworkBack.setVisibility(View.INVISIBLE);
    }



    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case DATA_LOAD_COMPLETE:
                    if (hud != null) {
                        hud.dismiss();
                    }
                    dataBindView();
                    break;
            }
        }
    };

    private void dataBindView() {
        if (allList.size() == 0) {
            Toasty.warning(this, getResources().getString(R.string.no_picture), Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent = new Intent(AtomAlbumActivity.this, AtomAlbumPhotoActivity.class);
        intent.putStringArrayListExtra("Data", allList);
        startActivityForResult(intent,PICK_PHOTO);
    }

    @OnClick(R.id.tv_homework_commit)
    public void onViewClicked() {
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode==RESULT_OK){
            if(requestCode==PICK_PHOTO){
                setResult(RESULT_OK);
                finish();
            }
        }

    }
}
