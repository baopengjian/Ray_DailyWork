package com.example.baopengjian.ray_dailywork.may;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.baopengjian.ray_dailywork.MainActivity;
import com.example.baopengjian.ray_dailywork.R;
import com.example.baopengjian.ray_dailywork.may.bar.BarChartViewActivity;
import com.example.baopengjian.ray_dailywork.may.fragment.FragmentExchangeActivity;


/**
 * Created by Ray on 2018/5/10.
 *
 */

public class MayActivity extends AppCompatActivity {

    static Class[] TARGETS = {ListHorizontalScrollActivity.class,BarChartViewActivity.class, FragmentExchangeActivity.class};
    static String[] TARGETS_DESC = {"列表横滚","柱状图","viewPager加载fragment懒加载及取消"};

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


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

    }
}
