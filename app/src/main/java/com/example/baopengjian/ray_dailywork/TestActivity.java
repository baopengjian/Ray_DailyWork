package com.example.baopengjian.ray_dailywork;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * Created by Ray on 2018/6/25.
 */
public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mouth);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("Fragments",">>>>onResume>>>> ");
    }


    @Override
    protected void onPostResume() {
        super.onPostResume();
        Log.i("Fragments",">>>>onPostResume>>>> ");
    }

    @Override
    protected void onResumeFragments() {
        super.onResumeFragments();
        Log.i("Fragments",">>>>onResumeFragments>>>> ");
    }
}
