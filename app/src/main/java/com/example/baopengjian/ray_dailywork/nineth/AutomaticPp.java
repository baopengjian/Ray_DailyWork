package com.example.baopengjian.ray_dailywork.nineth;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.NumberPicker;
import android.widget.PopupWindow;

import com.example.baopengjian.ray_dailywork.R;


/**
 * Created by Ray on 2019-11-21.
 */
public class AutomaticPp {

    private PopupWindow popupWindow;

    public static final String[] TAB1 = {"每月", "每周", "每双周"};
    public static final String[] TAB_WEEK = {"周一", "周二", "周三", "周四", "周五"};

    public AutomaticPp(Context context) {
        View pop = View.inflate(context, R.layout.pp_financial_auto, null);
        popupWindow = new PopupWindow(pop, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setTouchable(true);
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable(context.getResources(), (Bitmap) null));
        //  popupWindow.setAnimationStyle(R.style.shareMenuShow);

        TextNumberPicker tp1 = pop.findViewById(R.id.tp1);
        tp1.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {

            }
        });
        tp1.setDisplayedValues(TAB1);
        tp1.setMaxValue(TAB1.length - 1);
        tp1.setMinValue(0);
        tp1.setValue(0);
        tp1.setFormatter(new NumberPicker.Formatter() {
            @Override
            public String format(int i) {
                return TAB1[i];
            }
        });
        tp1.setDescendantFocusability(DatePicker.FOCUS_BLOCK_DESCENDANTS);
        tp1.setWrapSelectorWheel(false);

        TextNumberPicker tp2 = pop.findViewById(R.id.tp2);
        tp2.setDisplayedValues(TAB_WEEK);
        tp2.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {

            }
        });
        tp2.setMaxValue(TAB_WEEK.length - 1);
        tp2.setMinValue(0);
        tp2.setValue(0);
        tp2.setFormatter(new NumberPicker.Formatter() {
            @Override
            public String format(int i) {
                return String.valueOf(i);
            }
        });
        tp2.setDescendantFocusability(DatePicker.FOCUS_BLOCK_DESCENDANTS);
        tp2.setWrapSelectorWheel(false);
    }

    public void showAtLocation(View view, int bottom, int x, int y) {
        popupWindow.showAtLocation(view, bottom, x, y);
    }


    public void dismiss() {
        popupWindow.dismiss();
    }


    public boolean isShowing() {
        return popupWindow.isShowing();
    }
}
