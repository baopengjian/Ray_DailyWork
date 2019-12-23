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
        LoadViewDialog.showDialog(LoadingDialogActivity.this);
    }

    public void dismiss(View v) {
        SnackLoading.getInstance(this).show();
    }

}
