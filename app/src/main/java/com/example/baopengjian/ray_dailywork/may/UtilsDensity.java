package com.example.baopengjian.ray_dailywork.may;

import android.content.Context;
import android.util.TypedValue;

/**
 * Created by PF0ZYBAJ on 2018/5/11.
 */

public class UtilsDensity {

    public static int dp2px(Context context, float dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpVal, context.getResources().getDisplayMetrics());
    }

    /**
     * 得到设备屏幕的宽度
     */
    public static int getScreenWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }
}
