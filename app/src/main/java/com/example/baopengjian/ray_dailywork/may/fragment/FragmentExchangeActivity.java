package com.example.baopengjian.ray_dailywork.may.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.baopengjian.ray_dailywork.R;

/**
 * Created by Ray on 2018/5/25.
 * ViewPager 加载Fragment， fragment实现懒加载
 * requireMement:
 *      适用场景：ViewPager加载Fragment
 *                 fragment显示的时候才加载数据
 *                 viewPager切换，隐藏的fragment可以取消
 *                 跳转其他页面请求取消，
 *                 返回页面重新请求
 *      注意：普通Activity中fragment懒加载不用此种方式显示
 */

public class FragmentExchangeActivity extends AppCompatActivity {

    private ViewPager vp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_exchange_acitivity);
        vp  = (ViewPager) findViewById(R.id.vp);

        ViewPagerLazyFragment[] fragments = { ViewPagerLazyFragment.newInstance("1"), ViewPagerLazyFragment.newInstance("2")};
        SimpleFragmentPagerAdapter adapter = new SimpleFragmentPagerAdapter(getSupportFragmentManager(), fragments);
        vp.setAdapter(adapter);
        vp.setOffscreenPageLimit(0);
    }
}
