package com.example.baopengjian.ray_dailywork.eighth;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.View;

/**
 * Created by Ray on 2019-11-8.
 */
public class FlipFragmentAdapter  extends FragmentStatePagerAdapter {

    private Context context;
    private String[] content;

    public FlipFragmentAdapter(FragmentManager fragmentManager,
                               Context context, String[] data) {
        super(fragmentManager);
        this.context = context;
        content = data;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        if (object != null) {
            return ((Fragment) object).getView() == view;
        } else {
            return false;
        }
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        // Causes adapter to reload all Fragments when
        // notifyDataSetChanged is called
        return POSITION_NONE;
    }

    @Override
    public Fragment getItem(int position) {
        return FlipFragment.newInstance(content[position]);
    }

    @Override
    public int getCount() {
        return content == null ? 0 : content.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return content[position];
    }
}
