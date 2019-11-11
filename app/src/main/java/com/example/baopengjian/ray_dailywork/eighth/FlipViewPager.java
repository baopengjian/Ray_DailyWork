package com.example.baopengjian.ray_dailywork.eighth;

import android.content.Context;
import android.content.res.Resources;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

/**
 * Created by Ray on 2019-11-8.
 */
public class FlipViewPager extends ViewPager implements ViewPager.PageTransformer{
    private float maxScale = 0.0f;
    private int mPageMargin;
    private boolean animationEnabled = true;
    private boolean fadeEnabled = false;
    private float fadeFactor = 0.5f;


    public FlipViewPager(Context context) {
        this(context, null);
    }

    public FlipViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        // clipping should be off on the pager for its children so that they can scale out of bounds.
        setClipChildren(false);
        setClipToPadding(false);
        // to avoid fade effect at the end of the page
        setOverScrollMode(2);
        setPageTransformer(false, this);
        setOffscreenPageLimit(3);
        mPageMargin = dp2px(context.getResources(), 40);
        setPadding(mPageMargin, mPageMargin, mPageMargin, mPageMargin);
    }

    public int dp2px(Resources resource, int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, resource.getDisplayMetrics());
    }

    public void setAnimationEnabled(boolean enable) {
        this.animationEnabled = enable;
    }

    public void setFadeEnabled(boolean fadeEnabled) {
        this.fadeEnabled = fadeEnabled;
    }

    public void setFadeFactor(float fadeFactor) {
        this.fadeFactor = fadeFactor;
    }

    @Override
    public void setPageMargin(int marginPixels) {
        mPageMargin = marginPixels;
        setPadding(mPageMargin, mPageMargin, mPageMargin, mPageMargin);
    }

    @Override
    public void transformPage(View page, float position) {
        if (mPageMargin <= 0 || !animationEnabled) {
            return;
        }
        page.setPadding(mPageMargin / 3, mPageMargin / 3, mPageMargin / 3, mPageMargin / 3);

        if (maxScale == 0.0f && position > 0.0f && position < 1.0f) {
            maxScale = position;
        }
        position = position - maxScale;
        float absolutePosition = Math.abs(position);
        if (position <= -1.0f || position >= 1.0f) {
            if (fadeEnabled) {
                page.setAlpha(fadeFactor);
            }

        } else if (position == 0.0f) {
            page.setScaleX((1 + maxScale));
            page.setScaleY((1 + maxScale));
            page.setAlpha(1);
        } else {
            page.setScaleX(1 + maxScale * (1 - absolutePosition));
            page.setScaleY(1 + maxScale * (1 - absolutePosition));
            if (fadeEnabled) {
                page.setAlpha(Math.max(fadeFactor, 1 - absolutePosition));
            }
        }
    }
}
