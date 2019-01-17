package com.example.baopengjian.ray_dailywork;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.baopengjian.ray_dailywork.april.AprilActivity;
import com.example.baopengjian.ray_dailywork.fifth.DynamicPermissionRequestActivity;
import com.example.baopengjian.ray_dailywork.june.JuneActivity;
import com.example.baopengjian.ray_dailywork.may.MayActivity;
import com.example.baopengjian.ray_dailywork.may.fragment.FragmentExchangeActivity;
import com.example.baopengjian.ray_dailywork.september.SeptemberActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    static Class[] TARGETS = {AprilActivity.class, MayActivity.class, JuneActivity.class, SeptemberActivity.class,DynamicPermissionRequestActivity.class};
    static String[] TARGETS_DESC = {"01\n(自定义阴影、文本部分点击、pdf、新特性阴影)", "02\n（列表横滚、柱状图、viewPagerLazyFragment）",
            "03\n(按钮切换)", "04\n(Bundle传输数据过大)","05\n动态权限申请"};

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;

        ListView lv = (ListView) findViewById(R.id.lv);
        lv.setAdapter(new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, TARGETS_DESC));
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(context, TARGETS[position]);
                startActivity(intent);
            }
        });
    }

    public void nonstop(View view) {
        startActivity(new Intent(MainActivity.this, TestActivity.class));
    }

}
