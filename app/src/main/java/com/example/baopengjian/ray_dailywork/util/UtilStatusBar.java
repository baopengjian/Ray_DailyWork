package com.example.baopengjian.ray_dailywork.util;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.example.baopengjian.ray_dailywork.R;


/**
 * 初始化状态栏和标题
 * @author Ray
 */
public class UtilStatusBar {

    /**
     * 布局中必须有一个id为top的view来设置状态栏背景
     * 必须要在 setContentView之后调用
     *
     * @param activity 当前页面
     */
    public static void setTranslateStatusBar(Activity activity) {
        setTranslateStatusBar(activity, R.id.status_bar);
    }

    /**
     * 设置状态栏透明
     * 必须要在 setContentView之后调用
     *
     * @param topId    状态栏背景View的id
     * @param activity 当前页面
     */
    public static void setTranslateStatusBar(Activity activity, int topId) {
        View status_bar = activity.findViewById(topId);// 标题栏id
        if (status_bar != null) {
            setTranslateStatusBar(activity, status_bar);
        }
    }

    /**
     * 当Activity含有多个Fragment，每个Fragment需要自己设置StatusBar时：
     * Activity调用 setIndStatusBar() 设置状态栏透明
     * Fragment调用 setIndStatusBarHeight() 设置状态栏高度，颜色需自行定义
     * @param activity 当前Activity对象
     */
    public static void setIndStatusBar(Activity activity) {
        // 4.4以上处理
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) { // android
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);// 状态栏透明
        }
        //5.0 以上处理
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = activity.getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }
    /**
     * 当Activity含有多个Fragment，每个Fragment需要自己设置StatusBar,如颜色不同时：
     * Activity调用 setIndStatusBar() 设置状态栏透明
     * Fragment调用 setIndStatusBarHeight() 设置状态栏高度，颜色需自行定义
     * @param activity 当前Activity对象
     * @param fragmentView Fragment 的onCreateView()返回的View
     * @param id 状态栏StatusBar对应的id
     */
    public static void setIndStatusBarHeight(Activity activity,View fragmentView,int id) {
        View view = fragmentView.findViewById(id);
        if (view != null) {
            ViewGroup.LayoutParams params = view.getLayoutParams();
            params.height = getStatusBarHeight(activity);
            view.setLayoutParams(params);
        }
    }

    public static void setTranslateStatusBar(Activity activity, View view) {
        // 4.4以上处理
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) { // android
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);// 状态栏透明
            if (view != null) {
                ViewGroup.LayoutParams params = view.getLayoutParams();
                params.height = getStatusBarHeight(activity);
                view.setLayoutParams(params);
            }
        }
        //5.0 以上处理
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = activity.getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }

    public static void setInvisibleStatusBar(Activity activity, View view, boolean overScreen) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if(overScreen){
                activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_OVERSCAN);
            }else {
                activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);// 状态栏透明
            }
            if (view != null) {
                ViewGroup.LayoutParams params = view.getLayoutParams();
                params.height = getStatusBarHeight(activity);
                view.setLayoutParams(params);
            }
        }
        //5.0 以上处理
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = activity.getWindow();
            if(overScreen){
                window.clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_OVERSCAN);
            }else {
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            }
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN); //隐藏状态栏
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }

    public static int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height",
                "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    public static int getStatusBarHeight() {
        int result = 0;
        int resourceId = Resources.getSystem().getIdentifier("status_bar_height",
                "dimen", "android");
        if (resourceId > 0) {
            result = Resources.getSystem().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    /**
     * onContentChange之前 onCrete方法中
     * @param bDark true状态栏字颜色变黑
     */
    public static void setStatusBarMode(Activity activity, boolean bDark) {
        //6.0以上
        if(Build.VERSION.SDK_INT >= 23) {

            View decorView = activity.getWindow().getDecorView();

            if (decorView != null) {

                int vis = decorView.getSystemUiVisibility();

                if (bDark) {

                    vis |= 0x00002000;

                } else {

                    vis &= ~0x00002000;

                }

                decorView.setSystemUiVisibility(vis);
            }
        }

    }



}
