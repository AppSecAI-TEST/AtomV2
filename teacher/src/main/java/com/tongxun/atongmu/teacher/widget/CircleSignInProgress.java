package com.tongxun.atongmu.teacher.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.tongxun.atongmu.teacher.util.DensityUtil;

/**
 * Created by Anro on 2017/8/8.
 */

public class CircleSignInProgress extends View {

    private Context mContext;
    private Paint mPaint;
    private RectF mRectF;

    private int mCircleLineStrokeWidth;
    private float arrived;
    private float unarrived;
    private float leave;

    public CircleSignInProgress(Context context) {
        this(context,null);
    }

    public CircleSignInProgress(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CircleSignInProgress(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext=context;
        mRectF=new RectF();
        mPaint=new Paint();
        mCircleLineStrokeWidth= DensityUtil.dip2px(context,13);
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

        canvas.drawColor(Color.TRANSPARENT);
        mPaint.setStrokeWidth(mCircleLineStrokeWidth);
        mPaint.setStyle(Paint.Style.STROKE);

        //位置
        mRectF.left=mCircleLineStrokeWidth/2;
        mRectF.top=mCircleLineStrokeWidth/2;
        mRectF.right=width-mCircleLineStrokeWidth/2;
        mRectF.bottom=height-mCircleLineStrokeWidth/2;

        float unArrivedEnd=((float) unarrived  * 360);

        mPaint.setColor(Color.parseColor("#c5fceb"));
        canvas.drawArc(mRectF, -90,360, false, mPaint);

        mPaint.setColor(Color.parseColor("#8d86fe"));
        canvas.drawArc(mRectF, -90, unArrivedEnd , false, mPaint);

        mPaint.setColor(Color.parseColor("#fd7089"));
        canvas.drawArc(mRectF, unArrivedEnd-90-1, ((float) leave  * 360) , false, mPaint);

    }

    public void setProgress(float arrived,float unarrived,float leave){
        this.arrived=arrived;
        this.unarrived=unarrived;
        this.leave=leave;
        this.invalidate();//请求重绘
    }
}
