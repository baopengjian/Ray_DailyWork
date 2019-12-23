package com.example.baopengjian.ray_dailywork;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.baopengjian.ray_dailywork.tenth.LoadingDialogActivity;
import com.example.baopengjian.ray_dailywork.util.CommonAdapter;
import com.example.baopengjian.ray_dailywork.util.ViewHolder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;

        ListView lv =  findViewById(R.id.lv);
        lv.setAdapter(new CommonAdapter<ResolveInfo>(MainActivity.this, queryAppInfo(), android.R.layout.simple_list_item_1) {
            @Override
            public void convert(ViewHolder helper, final ResolveInfo item) {
                ((TextView) helper.getConvertView()).setText(item.activityInfo.loadLabel(getPackageManager()));
                helper.getConvertView().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            Intent intent = new Intent(MainActivity.this, Class.forName(item.activityInfo.name));
                            startActivity(intent);
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });

    }

    public void nonstop(View view) {
        startActivity(new Intent(MainActivity.this, LoadingDialogActivity.class));
    }


    public List<ResolveInfo> queryAppInfo() {
        PackageManager manager = getPackageManager();
        Intent intent = new Intent("com.example.bpj.dailwork");
        List<ResolveInfo> list = manager.queryIntentActivities(intent, 0);
        Collections.sort(list, new Comparator<ResolveInfo>() {
            @Override
            public int compare(ResolveInfo o1, ResolveInfo o2) {
                String label1 = String.valueOf(o1.activityInfo.loadLabel(getPackageManager()));
                String label2 = String.valueOf(o2.activityInfo.loadLabel(getPackageManager()));
                return label1.compareTo(label2);
            }
        });
        return list;
    }
}
