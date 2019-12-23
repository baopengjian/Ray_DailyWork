package com.example.baopengjian.ray_dailywork.tenth;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.baopengjian.ray_dailywork.R;
import com.example.baopengjian.ray_dailywork.util.UtilsDensity;

/**
 * Created by Ray on 2019-12-18.
 */
public class LoadingDialogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loading_dialog_activity);
    }

    public void drawableDialog(View v) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                LoadDialog.showDialog(LoadingDialogActivity.this);
            }
        });
    }


    public void viewDialog(View v) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                LoadViewDialog.showDialog(LoadingDialogActivity.this);
            }
        });

    }

    public void dismiss(View v) {
        SnackLoading.getInstance(this).show();
        /*runOnUiThread(new Runnable() {
            @Override
            public void run() {
                LoadingView loadingView;
                if (view == null) {
                    view = new RelativeLayout(LoadingDialogActivity.this);
                    loadingView = new LoadingView(LoadingDialogActivity.this);
                    view.addView(loadingView, UtilsDensity.dip2px(100), ViewGroup.LayoutParams.WRAP_CONTENT);
                    view.setGravity(Gravity.CENTER);
                } else {
                    loadingView = (LoadingView) view.getChildAt(0);
                }


                if (view.getParent() != null) {
                    ((ViewGroup) view.getParent()).removeView(view);
                }
                ((ViewGroup) (LoadingDialogActivity.this.getWindow().getDecorView())).addView(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                loadingView.startAnimation();
            }
        });*/

    }

    private RelativeLayout view;
}
