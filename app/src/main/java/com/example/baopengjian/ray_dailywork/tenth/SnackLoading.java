package com.example.baopengjian.ray_dailywork.tenth;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;

import com.example.baopengjian.ray_dailywork.R;

import java.lang.ref.WeakReference;

/**
 * Created by Ray on 2019-12-23.
 */
public class SnackLoading {


    private static WeakReference<SnackLoading> snackInstance;
    private Activity activity;
    private View mView;
    private LoadingView loadingView;

    private SnackLoading(Activity activity) {
        this.activity = activity;
        mView = View.inflate(activity, R.layout.snack_load_view, null);
        ((ViewGroup) activity.getWindow().getDecorView()).addView(mView);
        loadingView = mView.findViewById(R.id.iv_load);
        mView.findViewById(R.id.tv_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    public void show() {
        if (mView != null && mView.getVisibility() == View.GONE) {
            mView.setVisibility(View.VISIBLE);
        }
        if (loadingView != null)
            loadingView.startAnimation();
    }

    public void dismiss() {
        if (mView != null) {
            mView.setVisibility(View.GONE);
        }
        if (loadingView != null) {
            loadingView.stopAnimation();
        }
    }

    public void clear() {
        if (mView != null && mView.getParent() != null) {
            ((ViewGroup) mView.getParent()).removeView(mView);
        }
    }

    public static SnackLoading getInstance(Activity activity) {
        SnackLoading snackLoading = getCorrectInstance(activity);
        return snackLoading;
    }

    private static synchronized SnackLoading getCorrectInstance(Activity context) {
        SnackLoading snackLoading;
        if (snackInstance == null || snackInstance.get() == null) {
            snackLoading = new SnackLoading(context);
            snackInstance = new WeakReference<>(snackLoading);
        } else {
            snackLoading = snackInstance.get();
            if (snackLoading.activity != context) {
                snackInstance.clear();
                snackLoading = new SnackLoading(context);
                snackInstance = new WeakReference<>(snackLoading);
            }
        }

        return snackLoading;
    }


    /*public static LoadingView make(Activity activity) {

     *//*if (view == null) {
            view = new RelativeLayout(activity);
            loadingView = new LoadingView(activity);
            view.addView(loadingView, UtilsDensity.dip2px(100), ViewGroup.LayoutParams.WRAP_CONTENT);
            view.setGravity(Gravity.CENTER);
        } else {
            loadingView = (LoadingView) view.getChildAt(0);
        }*//*
        return null;
    }
*/

    public static LoadingView make(ViewGroup viewGroup) {

        return null;
    }
}
