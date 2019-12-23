package com.example.baopengjian.ray_dailywork.tenth;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.example.baopengjian.ray_dailywork.R;
import com.example.baopengjian.ray_dailywork.util.BitmapUtil;

/**
 * Created by Ray on 2019-12-18.
 */
public class LoadingView extends View {

    private Paint paint = new Paint();

    private Paint mBitMapPaint;
    private Bitmap mBitMapSRC, mBitMapDST, mBgBitmap;
    private int dx;
    private ValueAnimator animator;

    public LoadingView(Context context) {
        this(context, null);
    }

    public LoadingView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    private void init() {
        setLayerType(LAYER_TYPE_SOFTWARE, null);
        mBitMapPaint = new Paint();
        mBitMapPaint.setColor(Color.RED);

        mBgBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.load1);
        mBitMapDST = BitmapFactory.decodeResource(getResources(), R.drawable.load2);
        mBitMapSRC = Bitmap.createBitmap(mBitMapDST.getWidth(), mBitMapDST.getHeight(), Bitmap.Config.ARGB_8888);


    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int w, h;
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int measureWidth = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int measureHeight = MeasureSpec.getSize(heightMeasureSpec);

        if (widthMode == MeasureSpec.EXACTLY && heightMode == MeasureSpec.EXACTLY) {
            w = measureWidth;
            h = measureHeight;
            mBgBitmap = BitmapUtil.scaleBitmap(mBgBitmap, w, h);
        } else if (widthMode == MeasureSpec.EXACTLY) {
            w = measureWidth;
            h = (measureWidth * mBgBitmap.getHeight()) / mBgBitmap.getWidth();
            mBgBitmap = BitmapUtil.scaleBitmap(mBgBitmap, w, h);
        } else if (heightMode == MeasureSpec.EXACTLY) {
            h = measureHeight;
            w = (measureHeight * mBgBitmap.getWidth()) / mBgBitmap.getHeight();
            mBgBitmap = BitmapUtil.scaleBitmap(mBgBitmap, w, h);
        } else {
            w = mBgBitmap.getWidth();
            h = mBgBitmap.getHeight();
        }

        mBitMapDST = BitmapUtil.scaleBitmap(mBitMapDST, w, h);
        mBitMapSRC = BitmapUtil.scaleBitmap(mBitMapSRC, w, h);
        setMeasuredDimension(w, h);
        setBackgroundDrawable(new BitmapDrawable(getResources(), mBgBitmap));
        startAnimation();
    }

    Canvas c;

    @Override
    protected void onDraw(Canvas canvas) {
        //   canvas.drawBitmap(mBgBitmap, 0, 0, paint);
        int layerId = canvas.saveLayer(0, 0, getWidth(), getHeight(), null, Canvas.ALL_SAVE_FLAG);
        if (c == null) {
            c = new Canvas(mBitMapSRC);
        }
        c.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);

        // 画不透明的矩形区域
        c.drawRect(dx, 0, mBitMapDST.getWidth(), mBitMapDST.getHeight(), mBitMapPaint);
        // 画目标图片
        canvas.drawBitmap(mBitMapDST, 0, 0, mBitMapPaint);
        mBitMapPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        canvas.drawBitmap(mBitMapSRC, 0, 0, mBitMapPaint);
        mBitMapPaint.setXfermode(null);
        canvas.restoreToCount(layerId);
    }


    public synchronized void startAnimation() {
        if (animator == null) {
            initAnimation();
        }
        if (animator.isRunning()) {
            return;
        }
        animator.start();
    }


    public synchronized void stopAnimation() {
        animator.cancel();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        recycleBitmap(mBgBitmap);
        recycleBitmap(mBitMapDST);
        recycleBitmap(mBitMapSRC);
    }

    private void recycleBitmap(Bitmap bitmap) {
        if (bitmap != null && !bitmap.isRecycled()) {
            bitmap.recycle();
        }
    }

    private void initAnimation() {
        animator = ValueAnimator.ofInt(0, mBitMapDST.getWidth());
        animator.setDuration(1000);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setInterpolator(new LinearInterpolator() {
            @Override
            public float getInterpolation(float input) {
                return 1.2f * input;
            }
        });
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                dx = (int) animation.getAnimatedValue();
                postInvalidate();
            }
        });
    }


}
