package com.example.baopengjian.ray_dailywork.may;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.widget.ScrollerCompat;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * Created by PF0ZYBAJ on 2018/5/10.
 */

public class HScrollLayout extends LinearLayout {

    private ScrollerCompat mScrollerCompat;

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
        super.computeScroll();
        if (mScrollerCompat != null && mScrollerCompat.computeScrollOffset()) {
            scrollTo(mScrollerCompat.getCurrX(),0);
            postInvalidate();
        }

    }
}
