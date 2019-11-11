package com.example.baopengjian.ray_dailywork.eighth;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.baopengjian.ray_dailywork.R;

/**
 * Created by Ray on 2019-11-8.
 * 轮播图显示前后项一部分
 */
public class ShrinkViewpagerActivity extends AppCompatActivity {

    private ViewPager vp;
    protected static final String[] PAGE_TITLES = new String[]{"Page 1", "Page 2",
            "Page 3", "Page 4", "Page 5"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shrink_viewpager);
        initViewPager();
        initViewPager1();
    }

    private void initViewPager1() {
        ViewPager vp = findViewById(R.id.vp);
        vp.setAdapter(new ShrinkPagerAdapter(ShrinkViewpagerActivity.this));
    }

    private void initViewPager() {
        FlipViewPager mPager = findViewById(R.id.flipViewPager);
        mPager.setAdapter(new FlipFragmentAdapter(getSupportFragmentManager(),
                ShrinkViewpagerActivity.this, PAGE_TITLES));
        mPager.setAnimationEnabled(true);
        mPager.setFadeEnabled(true);
        mPager.setFadeFactor(0.6f);
    }
}
