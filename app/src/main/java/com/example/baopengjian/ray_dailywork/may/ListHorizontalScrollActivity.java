package com.example.baopengjian.ray_dailywork.may;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListView;

import com.example.baopengjian.ray_dailywork.R;

/**
 * Created by PF0ZYBAJ on 2018/5/10.
 */

public class ListHorizontalScrollActivity extends AppCompatActivity {



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_horizontal_scroll_activity);

        HSListView lv = (HSListView) findViewById(R.id.lv);
        lv.setAdapter(new HScrollAdapter(ListHorizontalScrollActivity.this) );
    }

}
