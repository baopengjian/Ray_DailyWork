package com.example.baopengjian.ray_dailywork.eighth;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.baopengjian.ray_dailywork.R;

/**
 * Created by Ray on 2019-11-8.
 */
public class ShrinkPagerAdapter extends PagerAdapter {

    int[] COLOR = {Color.GREEN, Color.YELLOW, Color.BLUE, Color.RED};

    private Context context;

    public ShrinkPagerAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return COLOR.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = View.inflate(context, R.layout.view_pager_shrink_item, null);
        ((TextView) view.findViewById(R.id.tv)).setText(String.valueOf(position));
        (view.findViewById(R.id.rl)).setBackgroundColor(COLOR[position]);
       /* TextView tv = new TextView(context);
        ViewPager.LayoutParams params = new ViewPager.LayoutParams();
        tv.setLayoutParams(params);
        tv.setGravity(Gravity.CENTER);
        tv.setTextSize(20);
        tv.setText(String.valueOf(position));
        tv.setBackgroundColor(COLOR[position]);
        tv.setTextColor(Color.WHITE);*/
        container.addView(view);
        return view;
    }
}
