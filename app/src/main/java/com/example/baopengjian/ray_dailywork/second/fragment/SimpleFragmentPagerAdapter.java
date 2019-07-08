package com.example.baopengjian.ray_dailywork.second.fragment;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


/**
 * Created by Ray on 2018/5/14.
 * 逆回购-列表adapter
 */

public class SimpleFragmentPagerAdapter extends FragmentPagerAdapter {

    private Fragment[] mFragments;

    public SimpleFragmentPagerAdapter(FragmentManager fm, @NonNull Fragment[] fragments) {
        super(fm);
        mFragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments[position];
    }

    @Override
    public int getCount() {
        return mFragments.length;
    }
}
