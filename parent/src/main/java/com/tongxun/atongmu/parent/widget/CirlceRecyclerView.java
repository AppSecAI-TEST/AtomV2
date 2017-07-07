package com.tongxun.atongmu.parent.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * Created by Anro on 2017/7/7.
 */

public class CirlceRecyclerView extends RecyclerView {
    public CirlceRecyclerView(Context context) {

        super(context);
    }

    public CirlceRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CirlceRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
    /**
     * 设置不滚动
     */
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);

    }
}
