package com.example.baopengjian.ray_dailywork.nineth;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;

import com.example.baopengjian.ray_dailywork.R;

/**
 * Created by Ray on 2019-11-21.
 */
public class StringPickerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.string_picker_activity);
    }

    AutomaticPp automaticPp;
    public void show(View v){
        if(automaticPp == null){
            automaticPp = new AutomaticPp(this);
        }
        if(automaticPp.isShowing()){
            automaticPp.dismiss();
        }else{
            automaticPp.showAtLocation(findViewById(R.id.rl), Gravity.BOTTOM,0 ,0 );
        }
    }
}
