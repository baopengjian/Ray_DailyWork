package com.example.baopengjian.ray_dailywork.nineth;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.NumberPicker;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.example.baopengjian.ray_dailywork.R;
import com.example.baopengjian.ray_dailywork.util.UtilsDensity;


/**
 * Created by Ray on 2019-11-21.
 */
public class AutomaticPp {

    private PopupWindow popupWindow;
    private TextNumberPicker tp1, tp2;

    public static final String[] TAB1 = {"每月", "每周", "每双周"};
    public static final String[] TAB_WEEK = {"周一", "周二", "周三", "周四", "周五"};
    public static final int MONTH_MAX = 28;

    public AutomaticPp(final Context context) {
        View pop = View.inflate(context, R.layout.pp_financial_auto, null);
        popupWindow = new PopupWindow(pop, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setTouchable(true);
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable(context.getResources(), (Bitmap) null));

        tp1 = pop.findViewById(R.id.tp1);
        tp1.setDisplayedValues(TAB1);
        tp1.setMaxValue(TAB1.length - 1);
       /* tp1.setFormatter(new NumberPicker.Formatter() {
            @Override
            public String format(int i) {
                return TAB1[i];
            }
        });*/
        initPicker(tp1, 0);
        tp2 = pop.findViewById(R.id.tp2);
        tp2.setMaxValue(MONTH_MAX);
        initPicker(tp2, 1);


        tp1.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                if (newVal == 0) {
                    tp2.setMinValue(1);
                    tp2.setMaxValue(28);
                    tp2.setDisplayedValues(null);
                    tp2.setValue(1);
                } else {
                    tp2.setMinValue(0);
                    tp2.setMaxValue(TAB_WEEK.length - 1);
                    tp2.setDisplayedValues(TAB_WEEK);
                    if (oldVal == 0) {
                        tp2.setValue(0);
                    }
                }
            }
        });

        pop.findViewById(R.id.tv_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
        pop.findViewById(R.id.tv_submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "tp1 =" + tp1.getValue() + "  tp2 =" + tp2.getValue(), Toast.LENGTH_LONG).show();
                popupWindow.dismiss();
            }
        });
    }

    private void initPicker(TextNumberPicker picker, int min) {
        picker.setMinValue(min);
        picker.setValue(min);
        picker.setDescendantFocusability(DatePicker.FOCUS_BLOCK_DESCENDANTS);
        picker.setWrapSelectorWheel(false);
        TextNumberPicker.setDividerColor(picker, new ColorDrawable(Color.parseColor("#ADB0A7")));
        TextNumberPicker.setDividerHeight(picker, UtilsDensity.dip2px(0.5f));
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
