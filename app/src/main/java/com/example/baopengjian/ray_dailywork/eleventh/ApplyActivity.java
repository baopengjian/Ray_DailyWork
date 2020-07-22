package com.example.baopengjian.ray_dailywork.eleventh;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;


import com.example.baopengjian.ray_dailywork.R;
import com.example.baopengjian.ray_dailywork.util.CommonAdapter;
import com.example.baopengjian.ray_dailywork.util.UtilStatusBar;
import com.example.baopengjian.ray_dailywork.util.UtilsDensity;
import com.example.baopengjian.ray_dailywork.util.ViewHolder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Ray on 2020-1-2.
 */
public class ApplyActivity extends AppCompatActivity {

    private static final int[] SUB_TITLE_IDS = {R.id.include_sub1, R.id.include_sub2, R.id.include_sub3};
    private static final String[] SUB_TITLE_DES = {"固定入口", "可定制入口(可拖拽)", "最近使用"};

    private RecyclerView gv1, gv2, gv3;
    private CustomViewPager viewPager;
    private TabLayout tabLayout;

    private List<MyFragment> bodyFragments;
    private ApplyPresenter presenter = new ApplyPresenter();
    private int mStep;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //    requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_apply);
        UtilStatusBar.setTranslateStatusBar(this);
        tabLayout = findViewById(R.id.community_container_tab_layout);
        viewPager = findViewById(R.id.viewPager);

        bodyFragments = new ArrayList<>();

        initSubTitle();
        initFragments();

        FragmentPagerAdapter mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return bodyFragments.get(position);
            }

            @Override
            public int getCount() {
                return bodyFragments.size();
            }

            //ViewPager与TabLayout绑定后，这里获取到PageTitle就是Tab的Text
            @Override
            public CharSequence getPageTitle(int position) {
                return bodyFragments.get(position).getmTitle();
            }
        };
        viewPager.setAdapter(mAdapter);
        viewPager.setOffscreenPageLimit(2);
        tabLayout.setupWithViewPager(viewPager);//将TabLayout和ViewPager关联起来。

        initData();
    }


    private void initData() {
        mStep = RecyclerAdapter.getStep(this, 4);
        initGv(gv1, presenter.getFix());
        initGv(gv2, presenter.getDynamic());
        initGv(gv3, presenter.getRecent());
    }

    private void initGv(RecyclerView recyclerView, List<Menu> menus) {
        recyclerView.setLayoutManager(new GridLayoutManager(ApplyActivity.this, 4));
        RecyclerAdapter   recyclerAdapter = new RecyclerAdapter(4, ApplyActivity.this, menus,recyclerView ==gv2, recyclerView);
        recyclerView.setAdapter(recyclerAdapter);
        }


        private void initSubTitle () {
            for (int i = 0; i < SUB_TITLE_IDS.length; i++) {
                ((TextView) findViewById(SUB_TITLE_IDS[i])).setText(SUB_TITLE_DES[i]);
            }

            gv1 = findViewById(R.id.gv1);
            gv2 = findViewById(R.id.gv2);
            gv3 = findViewById(R.id.gv3);
        }

        private void initFragments () {
            String[] array = getResources().getStringArray(R.array.eleven_array);
            for (String s : array) {
                MyFragment myFragment1 = new MyFragment();
                myFragment1.setmTitle(s);
                bodyFragments.add(myFragment1);
            }
        }
    }
