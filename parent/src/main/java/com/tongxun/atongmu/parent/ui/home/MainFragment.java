package com.tongxun.atongmu.parent.ui.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
import com.tongxun.atongmu.parent.ui.schooltuition.SchoolTuitionActivity;
import com.tongxun.atongmu.parent.ui.schoolvideo.VideoListActivity;
import com.tongxun.atongmu.parent.util.GlideOption;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;
import cn.bingoogolapple.refreshlayout.BGARefreshViewHolder;
import es.dmoral.toasty.Toasty;

/**
 * Created by Anro on 2017/7/21.
 */

public class MainFragment extends Fragment implements IMainContract.View<MainPresenter>, BGARefreshLayout.BGARefreshLayoutDelegate, OnItemClickListener {

    @BindView(R.id.connvenientbanner)
    ConvenientBanner connvenientbanner;
    @BindView(R.id.rv_main_notice)
    RecyclerView rvMainNotice;
    Unbinder unbinder;
    @BindView(R.id.vp_module)
    ConvenientBanner vpModule;
    @BindView(R.id.refresh_main_notice)
    BGARefreshLayout refreshMainNotice;
    private MainPresenter mPresenter;

    private List bannerList = new ArrayList();
    private List modulepageList = new ArrayList();

    private List<ModuleModel> moduleList = new ArrayList<>();

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
        mPresenter.getModuleList();
        mPresenter.getBannerList();
        mPresenter.getTipList();
    }

    /**
     * 初始化幼儿园模块UI
     */
    private void initUI() {
        bannerList.add(R.drawable.index_banner1);
        initBannerUI();
        setRecyclerNoticeUI();
        // initModuleUI();
    }

    private void setRecyclerNoticeUI() {

        refreshMainNotice.setDelegate(this);
        // 设置下拉刷新和上拉加载更多的风格     参数1：应用程序上下文，参数2：是否具有上拉加载更多功能
        BGARefreshViewHolder refreshViewHolder = new BGANormalRefreshViewHolder(getActivity(), true);
        // 设置下拉刷新和上拉加载更多的风格
        refreshMainNotice.setRefreshViewHolder(refreshViewHolder);
    }


    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        refreshMainNotice.endRefreshing();
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        return false;
    }

    private void initModuleUI() {
        rvMainNotice.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvMainNotice.setItemAnimator(new DefaultItemAnimator());
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
                mAdapter = new ModuleAdapter(getActivity(), moduleList.subList(position * 10, (position + 1) * 10),this);
            } else {
                mAdapter = new ModuleAdapter(getActivity(), moduleList.subList(position * 10, moduleList.size() - 1),this);
            }

            recyclerView.setAdapter(mAdapter);
        }


        @Override
        public void onItemClick(String moduleName) {
            goToModule(moduleName);
        }
    }

    private void goToModule(String moduleName) {
        Intent intent=null;
        switch (moduleName){
            case "活动通知":
                intent=new Intent(getActivity(), NoticeActivity.class);
                break;
            case "作业课程":
                intent=new Intent(getActivity(), HomeworkActivity.class);
                break;
            case "校园简介":
                intent=new Intent(getActivity(), SchoolIntroductionActivity.class);
                break;
            case "天天食谱":
                intent=new Intent(getActivity(), RecipesActivity.class);
                break;
            case "宝宝签到":
                intent=new Intent(getActivity(), BabySignInActivity.class);
                break;
            case "时光相册":
                intent=new Intent(getActivity(), TimeAlbumActivity.class);
                break;
            case "健康成长":
                intent=new Intent(getActivity(), HealthyGrowthActivity.class);
                break;
            case "校车动态":
                intent=new Intent(getActivity(), BusMapActivity.class);
                break;
            case "实时视频":
                intent=new Intent(getActivity(), VideoListActivity.class);
                break;
            case "班级圈子":
                intent=new Intent(getActivity(), FriendCircleActivity.class);
                break;
            case "校园缴费":
                intent=new Intent(getActivity(), SchoolTuitionActivity.class);
                break;
        }
        startActivity(intent);
    }

    /**
     * banner图点击
     * @param position
     */
    @Override
    public void onItemClick(int position) {
       if(bannerList.get(position) instanceof BannerDataBean){
           BannerDataBean dataBean= (BannerDataBean) bannerList.get(position);
           if(dataBean.getIsInternal().equals("true")){

           }else {
               WebViewActivity.startWebViewActivity(getActivity(),dataBean.getTitle(),"",dataBean.getPhoto(),dataBean.getAction(),"white",true,dataBean.getActionShare());
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
            if(data instanceof BannerDataBean){
                Glide.with(context).load(((BannerDataBean) data).getPhoto()).apply(GlideOption.getPHOption()).into(imageView);
            }else {
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
