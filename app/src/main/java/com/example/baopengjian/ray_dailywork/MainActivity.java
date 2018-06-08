package com.example.baopengjian.ray_dailywork;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.baopengjian.ray_dailywork.april.AprilActivity;
import com.example.baopengjian.ray_dailywork.june.JuneActivity;
import com.example.baopengjian.ray_dailywork.may.MayActivity;
import com.example.baopengjian.ray_dailywork.may.fragment.FragmentExchangeActivity;

public class MainActivity extends AppCompatActivity {

    static Class[] TARGETS = {AprilActivity.class,MayActivity.class,JuneActivity.class};
    static String[] TARGETS_DESC = {"April\n(自定义阴影、文本部分点击、pdf、新特性阴影)","May\n（列表横滚、柱状图、viewPagerLazyFragment）",
    "Jun\n(按钮切换)"};

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;

        ListView lv = (ListView) findViewById(R.id.lv);
        lv.setAdapter(new ArrayAdapter<>(context, android.R.layout.simple_list_item_1,TARGETS_DESC));
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(context, TARGETS[position]));
            }
        });
    }

    public void nonstop(View view){
        startActivity(new Intent(MainActivity.this, FragmentExchangeActivity.class));
    }

}
