package com.example.baopengjian.ray_dailywork.april;

import android.graphics.Outline;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.widget.TextView;

import com.example.baopengjian.ray_dailywork.R;

/**
 * Created by Ray on 2018/4/25 .
 */

public class ShadowsActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.april_shadows_acitivity);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            (findViewById(R.id.tv_elevation)).setElevation(13f);
        }

    }
}
