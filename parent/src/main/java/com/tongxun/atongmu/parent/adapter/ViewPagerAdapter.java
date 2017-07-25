package com.tongxun.atongmu.parent.adapter;

import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.tongxun.atongmu.parent.R;

import java.util.List;

/**
 * Created by Anro on 2016/4/1.
 */
public class ViewPagerAdapter extends PagerAdapter {

    private List<View> views;
    private onGuideListener mlistener;

    public ViewPagerAdapter(List<View> views,  onGuideListener listener) {
        this.views = views;
        mlistener = listener;
    }

      //获取要滑动的控件的数量
    @Override
    public int getCount() {
        if(views !=null){
            return views.size();
        }
        return 0;
    }

    //来判断显示的是否是同一张图片，这里我们将两个参数相比较返回即可
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    //PagerAdapter只缓存三张要显示的图片，如果滑动的图片超出了缓存的范围，就会调用这个方法，将图片销毁
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager)container).removeView(views.get(position));
    }


    //当要显示的图片可以进行缓存的时候，会调用这个方法进行显示图片的初始化，我们将要显示的ImageView加入到ViewGroup中
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(views.get(position));
        //如果是最后一张图片
        if(position==views.size()-1){
            Button start_atongmu_btn= (Button) container.findViewById(R.id.start_atongmu_btn);
            start_atongmu_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mlistener!=null){
                        mlistener.onLogin();
                    }
                }
            });
           TextView start_atongmu_txt= (TextView) container.findViewById(R.id.guide_txt);
            start_atongmu_txt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mlistener!=null){
                        mlistener.onServiceProvision();
                    }
                }
            });



        }
        return views.get(position);

    }


    @Override
    public void restoreState(Parcelable state, ClassLoader loader) {

    }

    @Override
    public Parcelable saveState() {
        return null;
    }

    @Override
    public void startUpdate(ViewGroup container) {

    }

    public interface onGuideListener{
        void onLogin();
        void onServiceProvision();
    }


}
