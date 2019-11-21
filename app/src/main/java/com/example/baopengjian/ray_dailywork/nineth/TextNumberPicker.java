package com.example.baopengjian.ray_dailywork.nineth;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.NumberPicker;

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
            ((EditText)child).setTextSize(24);
        }
    }
}
