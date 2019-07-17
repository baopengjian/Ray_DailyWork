package com.example.baopengjian.ray_dailywork.seventh;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.example.baopengjian.ray_dailywork.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ray on 2019-7-16.
 */
public class SelectedViewActivity extends FragmentActivity {


    SelectedView sv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_view_activity);
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        sv =findViewById(R.id.sv);
        sv.post(new Runnable() {
            @Override
            public void run() {
                List<SelectedView.Item> list = new ArrayList<>();
                for (int i = 0; i < 5; i++) {
                    SelectedView.Item item = new SelectedView.Item();
                    if (i == 2) {
                        item.isSlected = true;
                    }
                    list.add(item);
                }
                sv.setItems(list);
            }
        });
    }


}
