package com.tongxun.atongmu.parent.ui.Introduction;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.tongxun.atongmu.parent.Base2Activity;
import com.tongxun.atongmu.parent.Constants;
import com.tongxun.atongmu.parent.R;
import com.tongxun.atongmu.parent.model.SchoolIntroductModel;
import com.tongxun.atongmu.parent.util.SharePopupWindow;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;

public class SchoolIntroductionActivity extends Base2Activity<ISchoolIntroductionContract.View, SchoolIntroductionPresenter> implements ISchoolIntroductionContract.View {


    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.iv_toolbar_share)
    ImageView ivToolbarShare;
    @BindView(R.id.vp_container)
    ViewPager vpContainer;
    @BindView(R.id.iv_bottom_back)
    ImageView ivBottomBack;
    @BindView(R.id.iv_bottom_next)
    ImageView ivBottomNext;

    PagerAdapter mAdapter;
    @BindView(R.id.ll_school_introduce)
    LinearLayout llSchoolIntroduce;

    private List<Fragment> mFragment = new ArrayList<>();

    private ContactWeFragment schoolInfo;
    private ContactWeFragment schoolIntroducet;
    private ContactWeFragment leaderMessage;
    private ContactWeFragment schoolView;
    private ContactWeFragment schoolLevl;


    private TeacherStyleFragment teacherStyleFragment;

    private List<SchoolIntroductModel> mlist = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_introduction);
        ButterKnife.bind(this);
        setStatusColor(R.color.schoolIntroduction_bg);
        schoolInfo = new ContactWeFragment();
        schoolIntroducet = new ContactWeFragment();
        leaderMessage = new ContactWeFragment();
        schoolView = new ContactWeFragment();
        schoolLevl = new ContactWeFragment();
        teacherStyleFragment = new TeacherStyleFragment();

        mFragment.add(schoolInfo);
        mFragment.add(schoolIntroducet);
        mFragment.add(leaderMessage);
        mFragment.add(schoolView);
        mFragment.add(schoolLevl);
        mFragment.add(teacherStyleFragment);


        new TeacherStylePresenter(teacherStyleFragment);

        setViewPagerUI();

        setBottomUI();

        mPresenter.getSchoolIntroduct();

    }

    @Override
    protected SchoolIntroductionPresenter initPresenter() {
        return new SchoolIntroductionPresenter();
    }

    private void setBottomUI() {
        ivBottomBack.setSelected(false);
        ivBottomNext.setSelected(true);
    }

    private void setViewPagerUI() {
        mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragment.get(position);
            }

            @Override
            public int getCount() {
                return mFragment.size();
            }
        };
        vpContainer.setAdapter(mAdapter);
        vpContainer.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    ivToolbarShare.setVisibility(View.VISIBLE);
                    ivBottomBack.setSelected(false);
                    ivBottomNext.setSelected(true);
                } else if (position == mFragment.size() - 1) {
                    ivToolbarShare.setVisibility(View.GONE);
                    ivBottomBack.setSelected(true);
                    ivBottomNext.setSelected(false);
                } else {
                    ivToolbarShare.setVisibility(View.VISIBLE);
                    ivBottomBack.setSelected(true);
                    ivBottomNext.setSelected(true);
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        vpContainer.setOffscreenPageLimit(6);
    }


    @OnClick({R.id.title_back, R.id.iv_toolbar_share, R.id.iv_bottom_back, R.id.iv_bottom_next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                finish();
                break;
            case R.id.iv_toolbar_share:
                showSharePop();
                break;
            case R.id.iv_bottom_back:
                back();
                break;
            case R.id.iv_bottom_next:
                next();
                break;
        }
    }

    private void showSharePop() {
        int i=vpContainer.getCurrentItem();
        if(i<mFragment.size()-1){
            SharePopupWindow.getInstance().show(llSchoolIntroduce,mlist.get(i).getTitle(),"",mlist.get(i).getHtmlSharePath(), Constants.DEFAULTICON);
        }

    }

    private void next() {
        int position = vpContainer.getCurrentItem();
        if (position == mFragment.size() - 1) {
            return;
        }

        vpContainer.setCurrentItem(position + 1);
    }

    private void back() {
        int position = vpContainer.getCurrentItem();
        if (position == 0) {
            return;
        }

        vpContainer.setCurrentItem(position - 1);
    }


    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void onError(String message) {
        Toasty.error(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccessDate(List<SchoolIntroductModel> datas) {
        mlist = datas;
        for (SchoolIntroductModel model : datas) {
            if (model.getTitle().equals("校园信息")) {
                schoolInfo.setDate(model.getHtmlPath());
            }
            if (model.getTitle().equals("校园简介")) {
                schoolIntroducet.setDate(model.getHtmlPath());
            }
            if (model.getTitle().equals("园长寄语")) {
                leaderMessage.setDate(model.getHtmlPath());
            }
            if (model.getTitle().equals("校园实景")) {
                schoolView.setDate(model.getHtmlPath());
            }
            if (model.getTitle().equals("校园资质")) {
                schoolLevl.setDate(model.getHtmlPath());
            }
        }
    }
}
