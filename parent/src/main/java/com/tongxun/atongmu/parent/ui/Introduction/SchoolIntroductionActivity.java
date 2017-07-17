package com.tongxun.atongmu.parent.ui.Introduction;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.tongxun.atongmu.parent.BaseActivity;
import com.tongxun.atongmu.parent.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SchoolIntroductionActivity extends BaseActivity {


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

    private List<Fragment> mFragment=new ArrayList<>();

    private ContactWeFragment contactWeFragment;

    private LeaderMessageFragment leaderMessageFragment;

    private TeacherStyleFragment teacherStyleFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_introduction);
        ButterKnife.bind(this);
        setStatusColor(R.color.schoolIntroduction_bg);
        contactWeFragment=new ContactWeFragment();
        leaderMessageFragment=new LeaderMessageFragment();
        teacherStyleFragment=new TeacherStyleFragment();

        mFragment.add(teacherStyleFragment);

        mFragment.add(contactWeFragment);
        mFragment.add(leaderMessageFragment);


        new TeacherStylePresenter(teacherStyleFragment);

        setViewPagerUI();


    }

    private void setViewPagerUI() {
        mAdapter=new FragmentPagerAdapter(getSupportFragmentManager()) {
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
    }




    @OnClick({R.id.title_back, R.id.iv_toolbar_share, R.id.iv_bottom_back, R.id.iv_bottom_next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                finish();
                break;
            case R.id.iv_toolbar_share:

                break;
            case R.id.iv_bottom_back:

                break;
            case R.id.iv_bottom_next:

                break;
        }
    }


}
