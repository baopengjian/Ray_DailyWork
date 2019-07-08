package com.example.baopengjian.ray_dailywork.first;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.baopengjian.ray_dailywork.R;

public class AprilActivity extends AppCompatActivity {

    static Class[] TARGETS = {ButtonShadowActivity.class,PartiallyClickableActivity.class,LoadPdfActivity.class,ShadowsActivity.class};
    static String[] TARGETS_DESC = {"按钮阴影效果","TextView设置部分颜色+部分点击","加载网络pdf文件","20180425阴影效果"};

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mouth);
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

}
