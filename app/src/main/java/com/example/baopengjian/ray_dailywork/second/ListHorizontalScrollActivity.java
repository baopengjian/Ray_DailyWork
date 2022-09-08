package com.example.baopengjian.ray_dailywork.second;

import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.OverScroller;
import android.widget.RelativeLayout;
import android.widget.Scroller;
import android.widget.TextView;

import com.example.baopengjian.ray_dailywork.R;
import com.example.baopengjian.ray_dailywork.util.UtilsDensity;

/**
 * Created by Ray on 2018/5/10.
 * <p>
 * Requirement:
 * (1) 有一个竖向滑动的列表和一个表头，表头每列对应显示列表项；
 * (2) 列表的每行从第二列开始可以横向滚动，列表头的每项也可以横向滚动；
 * (3) 表头和每行滚动一致
 */

public class ListHorizontalScrollActivity extends AppCompatActivity {


    private View mHeadView;
    private HSListView lv;
    private HScrollLayout hsl;

    private HScrollAdapter mAdapter;
    /**
     * 滚动距离 0 - hScrollMaxWidth
     */
    private Scroller scrollerCompat;
    /**
     * hScrollMaxWidth的值为HScrollLayout 未显示局域的宽度
     */
    private int hScrollMaxWidth, scrollXPos;
    private RelativeLayout rl_title;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_horizontal_scroll_activity);

        lv = (HSListView) findViewById(R.id.lv);
        mAdapter = new HScrollAdapter(ListHorizontalScrollActivity.this);
        lv.setAdapter(mAdapter);
        mHeadView = getHeaderView();
        rl_title = (RelativeLayout) findViewById(R.id.rl_title);
        rl_title.addView(mHeadView);

        scrollerCompat = new Scroller(this);
        hsl.setScroller(scrollerCompat);
        mAdapter.setScroller(scrollerCompat);

        lv.setOnListener(new HSListView.HScrollListViewListener() {
            @Override
            public void onTouchDown(float x, float y) {
                scrollerCompat.forceFinished(true);
                scrollXPos = hsl.getScrollX();
                if (scrollXPos < 0) {
                    scrollXPos = 0;
                } else if (scrollXPos > hScrollMaxWidth) {
                    scrollXPos = hScrollMaxWidth;
                }
                setViewPosition(scrollXPos);
            }

            @Override
            public void onSliding(int moveDistanceX) {
                scrollXPos = scrollXPos + moveDistanceX;
                if (scrollXPos < 0) {
                    scrollXPos = 0;
                } else if (scrollXPos > hScrollMaxWidth) {
                    scrollXPos = hScrollMaxWidth;
                }

                setViewPosition(scrollXPos);
            }

            @Override
            public void onFling(int startX, int startY, int velocityX, int velocityY, int minX, int maxX, int minY, int maxY, int overX, int overY) {
                scrollerCompat.abortAnimation();
                if(hsl.getScrollX() <=0 || hsl.getScrollX() >= hScrollMaxWidth){

                }else{
                    scrollerCompat.fling(hsl.getScrollX(),0,-velocityX,0,0,hScrollMaxWidth,0,0);
                }

                scrollXPos = scrollerCompat.getFinalX();
                if (mAdapter != null) {
                    mAdapter.setScrollXPos(scrollXPos);
                }
            }

            @Override
            public void onTouchUp(float x, float y) {
                 scrollXPos = hsl.getScrollX();
                if (scrollXPos < 0) {
                    scrollXPos = 0;
                } else if (scrollXPos > hScrollMaxWidth) {
                    scrollXPos = hScrollMaxWidth;
                }
                setViewPosition(scrollXPos);
            }

            private void setViewPosition(int distance) {
                hsl.scrollTo(distance, 0);
                for (int i = 0; i < lv.getChildCount(); i++) {
                    if (lv.getChildAt(i).findViewById(R.id.hsl) != null) {
                        lv.getChildAt(i).findViewById(R.id.hsl).scrollTo(distance, 0);
                    }
                }

                if (mAdapter != null) {
                    mAdapter.setScrollXPos(distance);
                }
            }
        });
    }

    private View getHeaderView() {
        View view = View.inflate(this, R.layout.hs_item_adapter, null);
        TextView tv_title = view.findViewById(R.id.tv_title);
        tv_title.setText("列/标题");
        TextView tv_title1 = view.findViewById(R.id.tv_title1);
        tv_title1.setText("标题一");
        TextView tv_title2 = view.findViewById(R.id.tv_title2);
        tv_title2.setText("标题二");
        TextView tv_title3 = view.findViewById(R.id.tv_title3);
        tv_title3.setText("标题三");
        TextView tv_title4 = view.findViewById(R.id.tv_title4);
        tv_title4.setText("标题四");
        TextView tv_title5 = view.findViewById(R.id.tv_title5);
        tv_title5.setText("标题五");
        TextView tv_title6 = view.findViewById(R.id.tv_title6);
        tv_title6.setText("标题六");
        TextView tv_title7 = view.findViewById(R.id.tv_title7);
        tv_title7.setText("标题七");
        TextView tv_title8 = view.findViewById(R.id.tv_title8);
        tv_title8.setText("标题八");
        view.setBackgroundColor(Color.GRAY);

        Resources res = getResources();
        int colWidth = res.getDimensionPixelSize(R.dimen.col_width);
        int itemCount = 8;
        int itemWidth = res.getDimensionPixelSize(R.dimen.item_width);

        //HScrollLayout显示的item宽度
        int showWidth = UtilsDensity.getScreenWidth(this) - colWidth;
        hScrollMaxWidth = itemCount * itemWidth - showWidth;
        hsl = view.findViewById(R.id.hsl);
        return view;
    }

}
