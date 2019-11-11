package com.example.baopengjian.ray_dailywork.eighth;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.baopengjian.ray_dailywork.R;

/**
 * Created by Ray on 2019-11-8.
 */
public class FlipFragment extends Fragment {

    private ViewGroup card;
    private String mContent = "";

    public static FlipFragment newInstance(String content) {
        FlipFragment fragment = new FlipFragment();
        fragment.mContent = content;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_flip,
                container, false);
        int bg = getRandomBgColor();

        card = rootView.findViewById(R.id.card);
        card.setBackgroundColor(bg);
        TextView title = rootView.findViewById(R.id.title);
        title.setText("" + mContent);
        title.setTextColor(Color.WHITE);
        return rootView;
    }

    private int getRandomBgColor() {
        return Color.rgb((int) Math.floor(Math.random() * 128) + 64,
                (int) Math.floor(Math.random() * 128) + 64,
                (int) Math.floor(Math.random() * 128) + 64);
    }
}