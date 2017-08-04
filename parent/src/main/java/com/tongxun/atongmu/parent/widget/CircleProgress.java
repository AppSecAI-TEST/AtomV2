package com.tongxun.atongmu.parent.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.View;

import com.tongxun.atongmu.parent.util.DensityUtil;

/**
 * Created by Anro on 2017/8/4.
 */

public class CircleProgress extends View {

    private Paint mPaint;
    private Context mContext;
    private RectF mRectF;
    private int mCircleLineStrokeWidth;
    private int mMaxProgress=100;
    private int mProgress=30;

    public CircleProgress(Context context) {
        super(context);
        mContext=context;
        mRectF=new RectF();
        mPaint=new Paint();
        mCircleLineStrokeWidth= DensityUtil.dip2px(context,4);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width=this.getWidth();
        int height=this.getHeight();
        if(width!=height){
            int min=Math.min(width,height);
            width=min;
            height=min;
        }
        // 设置画笔相关属性
        mPaint.setAntiAlias(true);//抗锯齿
        mPaint.setDither(true);//防抖动
        mPaint.setColor(Color.parseColor("#e6e6e6"));
        canvas.drawColor(Color.TRANSPARENT);
        mPaint.setStrokeWidth(mCircleLineStrokeWidth);
        mPaint.setStyle(Paint.Style.STROKE);

        //位置
        mRectF.left=mCircleLineStrokeWidth/2;
        mRectF.top=mCircleLineStrokeWidth/2;
        mRectF.right=width-mCircleLineStrokeWidth/2;
        mRectF.bottom=height-mCircleLineStrokeWidth/2;

        canvas.drawArc(mRectF,-90,360,false,mPaint);

        mPaint.setColor(Color.parseColor("#feba33"));
        canvas.drawArc(mRectF, -90, ((float) mProgress / mMaxProgress) * 360, false, mPaint);
    }
}