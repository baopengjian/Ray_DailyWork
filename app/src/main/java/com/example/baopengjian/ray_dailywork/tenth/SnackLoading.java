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
    private int referenceTime;

    private SnackLoading(Activity activity) {
        this.activity = activity;
        mView = View.inflate(activity, R.layout.snack_load_view, null);
        ((ViewGroup) activity.getWindow().getDecorView()).addView(mView);
        loadingView = mView.findViewById(R.id.iv_load);
        mView.findViewById(R.id.tv_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                referenceTime =0;
                dismiss();
            }
        });
        referenceTime = 0;
    }

    public void show() {
        if (snackInstance == null || snackInstance.get() == null) {
            return;
        }
        SnackLoading snackLoading = snackInstance.get();
        snackLoading.referenceTime++;
        if (mView != null && mView.getVisibility() == View.GONE) {
            mView.setVisibility(View.VISIBLE);
        }
        if (loadingView != null)
            loadingView.startAnimation();
    }

    public void dismiss() {
        if (snackInstance == null || snackInstance.get() == null) {
            return;
        }

        SnackLoading loadDialog = snackInstance.get();
        if (loadDialog.referenceTime > 0) {
            loadDialog.referenceTime--;
        }
        if (loadDialog.referenceTime == 0) {
            if (mView != null) {
                mView.setVisibility(View.GONE);
            }
            if (loadingView != null) {
                loadingView.stopAnimation();
            }
        }
    }


    public void clear() {
        if (loadingView != null) {
            loadingView.stopAnimation();
        }
        if (mView != null && mView.getParent() != null) {
            ((ViewGroup) mView.getParent()).removeView(mView);
        }
    }

    public static synchronized SnackLoading getInstance(Activity activity) {
        SnackLoading snackLoading;
        if (snackInstance == null || snackInstance.get() == null) {
            snackLoading = new SnackLoading(activity);
            snackInstance = new WeakReference<>(snackLoading);
        } else {
            snackLoading = snackInstance.get();
            if (snackLoading.activity != activity) {
                snackInstance.clear();
                snackLoading = new SnackLoading(activity);
                snackInstance = new WeakReference<>(snackLoading);
            }
        }
        return snackLoading;
    }


}
