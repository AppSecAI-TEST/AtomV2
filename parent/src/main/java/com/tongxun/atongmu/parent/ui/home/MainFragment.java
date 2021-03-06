package com.tongxun.atongmu.parent.ui.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.bumptech.glide.Glide;
import com.tongxun.atongmu.parent.R;
import com.tongxun.atongmu.parent.adapter.MainTipAdapter;
import com.tongxun.atongmu.parent.adapter.ModuleAdapter;
import com.tongxun.atongmu.parent.model.BannerDataBean;
import com.tongxun.atongmu.parent.model.ModuleModel;
import com.tongxun.atongmu.parent.ui.Introduction.SchoolIntroductionActivity;
import com.tongxun.atongmu.parent.ui.WebViewActivity;
import com.tongxun.atongmu.parent.ui.album.TimeAlbumActivity;
import com.tongxun.atongmu.parent.ui.babysign.BabySignInActivity;
import com.tongxun.atongmu.parent.ui.classcircle.FriendCircleActivity;
import com.tongxun.atongmu.parent.ui.healthygrowth.HealthyGrowthActivity;
import com.tongxun.atongmu.parent.ui.homework.HomeworkActivity;
import com.tongxun.atongmu.parent.ui.notice.NoticeActivity;
import com.tongxun.atongmu.parent.ui.recipes.RecipesActivity;
import com.tongxun.atongmu.parent.ui.schoolbus.BusMapActivity;
import com.tongxun.atongmu.parent.ui.schoolpm.PM25Activity;
import com.tongxun.atongmu.parent.ui.schooltuition.SchoolTuitionActivity;
import com.tongxun.atongmu.parent.ui.schoolvideo.VideoListActivity;
import com.tongxun.atongmu.parent.util.DensityUtil;
import com.tongxun.atongmu.parent.util.GlideOption;
import com.tongxun.atongmu.parent.util.RecycleViewDivider;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import es.dmoral.toasty.Toasty;

/**
 * Created by Anro on 2017/7/21.
 */

public class MainFragment extends Fragment implements IMainContract.View<MainPresenter>, OnItemClickListener {

    @BindView(R.id.connvenientbanner)
    ConvenientBanner connvenientbanner;
    @BindView(R.id.rv_main_notice)
    RecyclerView rvMainNotice;
    Unbinder unbinder;
    @BindView(R.id.vp_module)
    ConvenientBanner vpModule;
    @BindView(R.id.swp_refresh)
    SwipeRefreshLayout swpRefresh;
    @BindView(R.id.scroll_view)
    NestedScrollView scrollView;
    private MainPresenter mPresenter;

    private List bannerList = new ArrayList();
    private List modulepageList = new ArrayList();
    private List tipList = new ArrayList();

    private List<ModuleModel> moduleList = new ArrayList<>();

    private MainTipAdapter mTipAdapter;
    private boolean isPageFirstIn=true;

    private boolean isUpDate=false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_layout, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initUI();
    }



    /**
     * 初始化幼儿园模块UI
     */
    private void initUI() {
        bannerList.add(R.drawable.index_banner1);
        initBannerUI();
        setRecyclerNoticeUI();
        // initModuleUI();
        swpRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swpRefresh.setRefreshing(false);
            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();
        if(isPageFirstIn){
            isPageFirstIn=false;
            mPresenter.getBannerList();
            mPresenter.getTipList();
            scrollView.scrollTo(0,0);
        }

        if(isUpDate){
            isUpDate=false;
            mPresenter.getBannerList();
            mPresenter.getTipList();
        }
        mPresenter.getModuleList();
    }

    private void setRecyclerNoticeUI() {

        for (int i = 0; i < 12; i++) {
            tipList.add(i);
        }
        rvMainNotice.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvMainNotice.setItemAnimator(new DefaultItemAnimator());
        rvMainNotice.addItemDecoration(new RecycleViewDivider(getActivity(), LinearLayoutManager.VERTICAL, DensityUtil.dip2px(getActivity(), 10), getResources().getColor(R.color.colorGrayBg)));
        mTipAdapter = new MainTipAdapter(getActivity(), tipList);
        rvMainNotice.setAdapter(mTipAdapter);
    }


    private void initModuleUI() {

        vpModule.setPages(
                new CBViewHolderCreator<MainModuleHolder>() {
                    @Override
                    public MainModuleHolder createHolder() {
                        return new MainModuleHolder();
                    }
                }, modulepageList)
                //设置两个点图片作为翻页指示器，不设置则没有指示器，可以根据自己需求自行配合自己的指示器,不需要圆点指示器可用不设
                .setPageIndicator(new int[]{R.drawable.page_indicator_gray, R.drawable.page_indicator_yellow})
                //设置指示器的方向
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL);
    }

    /**
     * 账号切换时
     */
    public void changeDate() {
        isUpDate=true;

    }


    class MainModuleHolder implements Holder, ModuleAdapter.moduleClickListener {
        RecyclerView recyclerView;

        @Override
        public View createView(Context context) {
            recyclerView = new RecyclerView(context);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setLayoutManager(new GridLayoutManager(context, 5));
            return recyclerView;
        }

        @Override
        public void UpdateUI(Context context, int position, Object data) {
             ModuleAdapter mAdapter = null;
            if (position < modulepageList.size() - 1) {
                mAdapter = new ModuleAdapter(getActivity(), moduleList.subList(position * 10, (position + 1) * 10), this);
            } else {
                mAdapter = new ModuleAdapter(getActivity(), moduleList.subList(position * 10, moduleList.size()), this);
            }

            recyclerView.setAdapter(mAdapter);
        }


        @Override
        public void onItemClick(String moduleId) {
            goToModule(moduleId);
        }
    }

    private void goToModule(String moduleId) {
        Intent intent = null;
        switch (moduleId) {
            case "1":
                intent = new Intent(getActivity(), NoticeActivity.class);
                break;
            case "3":
                intent = new Intent(getActivity(), HomeworkActivity.class);
                break;
            case "4":
                intent = new Intent(getActivity(), SchoolIntroductionActivity.class);
                break;
            case "5":
                intent = new Intent(getActivity(), RecipesActivity.class);
                break;
            case "6":
                intent = new Intent(getActivity(), BabySignInActivity.class);
                break;
            case "7":
                intent = new Intent(getActivity(), TimeAlbumActivity.class);
                break;
            case "8":
                intent = new Intent(getActivity(), HealthyGrowthActivity.class);
                break;
            case "10":
                intent = new Intent(getActivity(), BusMapActivity.class);
                break;
            case "13":
                intent = new Intent(getActivity(), VideoListActivity.class);
                break;
            case "16":
                intent = new Intent(getActivity(), FriendCircleActivity.class);
                break;
            case "15":
                intent=new Intent(getActivity(), PM25Activity.class);
                break;
            case "17":
                intent = new Intent(getActivity(), SchoolTuitionActivity.class);
                break;
            case "9":
                intent=new Intent(getActivity(),WebViewActivity.class);
                intent.putExtra("title", "");
                intent.putExtra("content", "");
                intent.putExtra("imageUrl", "");
                intent.putExtra("url", "http://m.4399er.com/xzt/mmegspdqlxbf/");
                intent.putExtra("shareUrl", "");
                intent.putExtra("isCanShare", false);
                intent.putExtra("type", "white");
                break;
            default:
                return;

        }
        startActivity(intent);
    }

    /**
     * banner图点击
     *
     * @param position
     */
    @Override
    public void onItemClick(int position) {
        if (bannerList.get(position) instanceof BannerDataBean) {
            BannerDataBean dataBean = (BannerDataBean) bannerList.get(position);
            if (dataBean.getIsInternal().equals("true")) {

            } else {
                WebViewActivity.startWebViewActivity(getActivity(), dataBean.getTitle(), "", dataBean.getPhoto(), dataBean.getAction(), "white", true, dataBean.getActionShare());
            }
        }
    }


    private void initBannerUI() {
        connvenientbanner.setPages(
                new CBViewHolderCreator<NetworkImageHolderView>() {
                    @Override
                    public NetworkImageHolderView createHolder() {
                        return new NetworkImageHolderView();
                    }
                }, bannerList)
                //设置两个点图片作为翻页指示器，不设置则没有指示器，可以根据自己需求自行配合自己的指示器,不需要圆点指示器可用不设
                .setPageIndicator(new int[]{R.drawable.page_indicator_white, R.drawable.page_indicator_yellow})
                //设置指示器的方向
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.ALIGN_PARENT_RIGHT)
                //设置指示器的方向
                .setOnItemClickListener(this);
    }


    public class NetworkImageHolderView implements Holder {
        private ImageView imageView;

        @Override
        public View createView(Context context) {
            imageView = new ImageView(context);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            return imageView;
        }

        @Override
        public void UpdateUI(Context context, int position, Object data) {
            if (data instanceof BannerDataBean) {
                Glide.with(context).load(((BannerDataBean) data).getPhoto()).apply(GlideOption.getPHOption()).into(imageView);
            } else {
                Glide.with(context).load(data).apply(GlideOption.getPHOption()).into(imageView);
            }

        }

    }


    @Override
    public void setPresenter(MainPresenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onError(String message) {
        Toasty.error(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onModuleSuccess(List<ModuleModel> data) {
        moduleList = data;
        modulepageList.clear();
        int pagenum = (data.size() % 10) == 0 ? (data.size() / 10) : (data.size() / 10 + 1);
        for (int i = 0; i < pagenum; i++) {
            modulepageList.add(i);
        }
        initModuleUI();
    }

    @Override
    public void onBannerSuccess(List<BannerDataBean> data) {
        bannerList.clear();
        bannerList.addAll(data);
        initBannerUI();
    }

    @Override
    public void onDestroy() {
        if (mPresenter != null) {
            mPresenter.detachView();
        }
        super.onDestroy();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
