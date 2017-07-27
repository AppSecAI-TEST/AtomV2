package com.tongxun.atongmu.parent.ui.my.growprofile;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.tongxun.atongmu.parent.Base2Activity;
import com.tongxun.atongmu.parent.Constants;
import com.tongxun.atongmu.parent.IonItemClickListener;
import com.tongxun.atongmu.parent.R;
import com.tongxun.atongmu.parent.adapter.GrowProfileAdapter;
import com.tongxun.atongmu.parent.model.GrowProfileModel;
import com.tongxun.atongmu.parent.ui.WebViewActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;

import static com.tongxun.atongmu.parent.R.id.iv_back;

public class GrowProfileActivity extends Base2Activity<IGrowProfileContract.View, GrowProfilePresenter> implements IGrowProfileContract.View, IonItemClickListener {

    @BindView(R.id.ll_no_data)
    LinearLayout llNoData;
    @BindView(R.id.vp_grow_profile)
    ViewPager vpGrowProfile;
    @BindView(iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;

    private GrowProfileAdapter mAdapter;

    private List<GrowProfileModel> mlist = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grow_profile);
        ButterKnife.bind(this);
        setStatusColor(R.color.colorGrowProfileBg);
        // vpGrowProfile.setPageMargin(DensityUtil.dip2px(this,25));
        mPresenter.getGrowProfileList();
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected GrowProfilePresenter initPresenter() {
        return new GrowProfilePresenter();
    }

    @Override
    public void onError(String message) {
        Toasty.error(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccess(List<GrowProfileModel> datas) {
        if (datas.size() == 0) {
            vpGrowProfile.setVisibility(View.GONE);
            llNoData.setVisibility(View.VISIBLE);
        } else {
            mlist = datas;
            String str=getString(R.string.grow_profile_title);
            String title=String.format(str,mlist.size()+"");
            tvTitle.setText(title);
            llNoData.setVisibility(View.GONE);
            vpGrowProfile.setVisibility(View.VISIBLE);
            mAdapter = new GrowProfileAdapter(this, datas, this);
            vpGrowProfile.setAdapter(mAdapter);
        }
    }

    @Override
    public void onItemClick(int position) {
        if (mlist != null) {
            WebViewActivity.startWebViewActivity(this, mlist.get(position).getTitle(), "", Constants.DEFAULTICON, mlist.get(position).getGrowthurl(), "white", true, mlist.get(position).getGrowthshareurl());
        }
    }

}
