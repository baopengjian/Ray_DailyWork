package com.example.baopengjian.ray_dailywork.nineth;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.NumberPicker;

import com.example.baopengjian.ray_dailywork.util.UtilsDensity;

import java.lang.reflect.Field;

/**
 * Created by Ray on 2019-11-21.
 */
public class TextNumberPicker extends NumberPicker {
    public TextNumberPicker(Context context) {
        super(context);
    }

    public TextNumberPicker(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TextNumberPicker(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public void addView(View child, int index, ViewGroup.LayoutParams params) {
        super.addView(child, index, params);
        updateView(child);
    }

    private void updateView(View child) {
        if (child instanceof EditText) {
            ((EditText) child).setTextSize(24);
        }
    }

    public static void setDividerColor(NumberPicker picker, ColorDrawable color) {
        Field field = null;
        try {
            field = NumberPicker.class.getDeclaredField("mSelectionDivider");
            if (field != null) {
                field.setAccessible(true);
                field.set(picker, color);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setDividerHeight(NumberPicker picker, int h) {
        Field[] fields = NumberPicker.class.getDeclaredFields();
        for (Field field : fields) {
            if (field.getName().equals("mSelectionDividerHeight")) {
                field.setAccessible(true);
                try {
                    field.set(picker, h);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }

                break;
            }
        }
    }
}
