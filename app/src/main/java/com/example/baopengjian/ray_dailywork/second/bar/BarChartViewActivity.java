package com.example.baopengjian.ray_dailywork.second.bar;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.baopengjian.ray_dailywork.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Ray on 2018/5/22.
 */

public class BarChartViewActivity extends AppCompatActivity {

    private BarChartView bv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bar_chart_view_activity);

        bv = findViewById(R.id.bv);

        List<BarChartView.Bar> list = new ArrayList<>();
        Random random = new Random();
        int rad;
        int[] color = new int[10];
        for(int i = 0;i < 10;i++){
            BarChartView.Bar  bar = new BarChartView.Bar();
            if( i <3){
                bar.setDesc(i+""+"dfa");
            }else{
                bar.setDesc(i+""+"dfasefasefase");
            }

            rad = random.nextInt(i+1)+3;
            bar.setData(rad+"");
            bar.setDataFloat(rad);
            list.add(bar);
            if(i == 0){
                color[i] = R.color.bar_color_1;
            }else{
                color[i] = R.color.bar_color_2;
            }
        }
        bv.setBarColors(color);
        bv.setBars(list);
        bv.setTitles("左边标题","右边标题");
    }
}
