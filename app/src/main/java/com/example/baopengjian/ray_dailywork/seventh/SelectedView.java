package com.example.baopengjian.ray_dailywork.seventh;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;

import com.example.baopengjian.ray_dailywork.R;
import com.example.baopengjian.ray_dailywork.util.UtilsDensity;

import java.util.List;

/**
 * Created by Ray on 2019-7-16.
 */
public class SelectedView extends LinearLayout {
    //保留可以滑动的拓展
    public int ITEM_WRAP = 0;
    private int mHeight, mWidth;
    private int mSelectedExpand = 10;
    private int mSelectedShader = 5;

    private int mHorizontalPadding = 10;
    private int mItemWidth;
    private List<Item> mList;
    public int mSeleted = -1;

    public SelectedView(Context context) {
        this(context, null);
    }

    public SelectedView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SelectedView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOrientation(HORIZONTAL);
        setGravity(Gravity.CENTER);
        setBackgroundColor(Color.GREEN);
        mSelectedExpand = UtilsDensity.dip2px(3);
        mSelectedShader =  UtilsDensity.dip2px(3);
        mItemWidth = UtilsDensity.dip2px(60);
        mHeight =  UtilsDensity.dip2px(72);
        mWidth =UtilsDensity.getScreenWidth(getContext());

    }


    public void setItems(final List<Item> list) {

        if (list == null || list.isEmpty()) {
            removeAllViews();
            return;
        }
        mList = list;
        removeAllViews();
        getView(list);
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            child.setTag(i);
            child.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = (int) v.getTag();
                    int  itemW;
                    View child;
                    if(switchSelected(pos)){
                        for(int j = 0;j<mList.size();j++){
                            itemW = list.get(j).isSlected ?( mItemWidth+2*mSelectedExpand+2*mSelectedShader): mItemWidth;
                            child = getChildAt(j);
                            LinearLayout.LayoutParams params = (LayoutParams) child.getLayoutParams();
                            params.width = itemW;
                            params.height = itemW;
                            params.rightMargin = 0;
                            params.leftMargin = 0;
                            int padding = mSelectedExpand+mSelectedShader;
                            if(j  !=mSeleted){
                                if(j  == 0){
                                    params.rightMargin = padding;
                                }else if(j  == list.size()-1){
                                    params.leftMargin = padding;
                                }else{
                                    params.rightMargin = padding;
                                    params.leftMargin = padding;
                                }
                            }
                            child.setLayoutParams(params);
                        }
                    }

                }
            });
        }
    }



    private boolean switchSelected(int pos) {
        if (mList == null || mList.isEmpty() || pos >= mList.size()) {
            return false;
        }
        boolean reusult = false;
        for (int i = 0; i < mList.size(); i++) {
            if (i == pos) {
                if (!mList.get(i).isSlected) {
                    mSeleted = i;
                    mList.get(i).isSlected = true;
                    reusult = true;
                }
            } else {
                mList.get(i).isSlected = false;
            }
        }

        return reusult;
    }


    private void getView(List<Item> list) {

            int  itemW;
            for (int i = 0; i < list.size(); i++) {
                View itemView = getItemView();
                if (list.get(i).isSlected) {
                    mSeleted = i;
                }
                itemW = list.get(i).isSlected ? (mItemWidth + 2 * mSelectedExpand+2*mSelectedShader) : mItemWidth;
                LayoutParams params = new LayoutParams(itemW, itemW);
                int padding = mSelectedExpand+mSelectedShader;
                if(i !=mSeleted){
                   if(i == 0){
                       params.rightMargin = padding;
                   }else if(i == list.size()-1){
                       params.leftMargin = padding;
                   }else{
                       params.rightMargin = padding;
                       params.leftMargin = padding;
                   }
                }
                addView(itemView, params);
            }


    }

    public View getItemView() {
        View view = View.inflate(getContext(), R.layout.layout_select_view_item, null);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            view.setId(View.generateViewId());
        }
        return view;
    }


    public static class Item {
        public boolean isSlected = false;

        @Override
        public boolean equals(Object obj) {
            return super.equals(obj);
        }
    }
}
