package com.example.baopengjian.ray_dailywork.second;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.widget.ScrollerCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.OverScroller;

/**
 * Created by Ray on 2018/5/10.
 * 横向滚动的表头和List 的item中可以滚动的组件
 */

public class HScrollLayout extends LinearLayout {

    private OverScroller mScrollerCompat;

    public HScrollLayout(Context context) {
        super(context);
    }

    public HScrollLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public HScrollLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void computeScroll() {
        if (mScrollerCompat != null && mScrollerCompat.computeScrollOffset()) {
            Log.e("HScrollLayout",">>> "+mScrollerCompat.getCurrX());
            scrollTo(mScrollerCompat.getCurrX(), 0);
            invalidate();
        }
    }

    public void setScroller(OverScroller scroller) {
        mScrollerCompat = scroller;
    }
}
