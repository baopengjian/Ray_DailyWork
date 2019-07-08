package com.example.baopengjian.ray_dailywork.first;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Ray on 2018/4/9 .
 * requirement 阴影圆角效果
 * Core API：圆角  Canvas.drawRoundRect(@NonNull RectF rect, float rx, float ry, @NonNull Paint paint)
 *                  rx、ry 圆角的x\y方向半径
 *
 *           阴影 Paint.setShadowLayer(float radius, float dx, float dy, int shadowColor)
 *           radius阴影半径 dx 横向偏移 dy 纵向偏移
 */

public class RoundShadowView extends View {

    private int mWidth,mHeight;
    private Paint mPaint;
    private int mRoundRadius;
    private int mShadowRadius;

    public RoundShadowView(Context context) {
        super(context);
        init();
    }

    public RoundShadowView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RoundShadowView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setLayerType(View.LAYER_TYPE_SOFTWARE,null);

        mShadowRadius = dip2px(getContext(),10);
        yOffset = dip2px(getContext(),5);
        mRoundRadius = dip2px(getContext(),6);

        mPaint = new Paint();
        mPaint.setStrokeWidth(dip2px(getContext(),1));
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(0xffEEEEEE);

        //曲线更加圆润 0x4dFF9541
        mPaint.setAntiAlias(true);
        mPaint.setShadowLayer(mShadowRadius,0,yOffset,0x40FF9541);
    }
    private int yOffset;
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        mWidth = getWidth();
        mHeight = getHeight();
        //左、上、右（右边到控件左边）、下（底部到控件顶部）
        RectF rectF = new RectF(mShadowRadius/2,0,mWidth - mShadowRadius/2,mHeight - mShadowRadius/4 -yOffset);
        canvas.drawRoundRect(rectF,mRoundRadius,mRoundRadius, mPaint);
    }

    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
