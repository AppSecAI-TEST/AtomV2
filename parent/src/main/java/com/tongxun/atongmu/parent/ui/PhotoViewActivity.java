package com.tongxun.atongmu.parent.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.github.chrisbanes.photoview.PhotoView;
import com.tongxun.atongmu.parent.BaseActivity;
import com.tongxun.atongmu.parent.R;
import com.tongxun.atongmu.parent.util.GlideOption;
import com.tongxun.atongmu.parent.util.SDCardUtil;
import com.tongxun.atongmu.parent.widget.PhotoViewPager;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.FileCallBack;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;
import okhttp3.Call;

public class PhotoViewActivity extends BaseActivity {

    @BindView(R.id.iv_title_back)
    ImageView ivTitleBack;
    @BindView(R.id.tv_num)
    TextView tvNum;
    @BindView(R.id.iv_save)
    TextView ivSave;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.vp_container)
    PhotoViewPager vpContainer;
    private List<String> mlist = new ArrayList<>();
    private boolean haveContent;

    private PagerAdapter mAdapter;
    private List<PhotoView> photoViewList = new ArrayList<>();

    private PhotoView[] mImageViews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_view);
        ButterKnife.bind(this);
        setStatusColor(R.color.transparent);
        Intent intent = getIntent();
        haveContent = intent.getBooleanExtra("haveContent", false);
        if (haveContent) {

        } else {
            try {
                mlist = intent.getStringArrayListExtra("list");
                mImageViews = new PhotoView[mlist.size()];
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        tvNum.setText("1/" + mlist.size());
        setViewPagerUI();
    }

    private void setViewPagerUI() {
        mAdapter = new PagerAdapter() {

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                PhotoView photoView = new PhotoView(PhotoViewActivity.this);
                photoView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                Glide.with(PhotoViewActivity.this).load(mlist.get(position)).apply(GlideOption.getPhotoViewOption()).into(photoView);
                mImageViews[position] = photoView;
                container.addView(photoView);
                return photoView;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView(mImageViews[position]);
            }

            @Override
            public int getCount() {
                if (haveContent) {
                    return 0;
                } else {
                    return mlist.size();
                }

            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }


        };
        vpContainer.setAdapter(mAdapter);
        vpContainer.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tvNum.setText((position + 1) + "/" + mlist.size());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    public static void startActivity(Context context, ArrayList<String> list) {
        Intent intent = new Intent(context, PhotoViewActivity.class);
        intent.putExtra("haveContent", false);
        intent.putStringArrayListExtra("list", list);
        context.startActivity(intent);
    }

    @OnClick({R.id.iv_title_back, R.id.iv_save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_title_back:
                finish();
                break;
            case R.id.iv_save:
                saveImage();
                break;
        }
    }

    /**
     * 保存图片
     */
    private void saveImage() {
        int position = vpContainer.getCurrentItem();
        OkHttpUtils
                .get()
                .url(mlist.get(position))
                .build()
                .execute(new FileCallBack(SDCardUtil.getInstance().getFilePath(), UUID.randomUUID()+".jpg") {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Toasty.success(PhotoViewActivity.this, getString(R.string.save_image_fail), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(File response, int id) {
                        Toasty.success(PhotoViewActivity.this, getString(R.string.save_image_success), Toast.LENGTH_SHORT).show();
                    }

                });
    }
}
