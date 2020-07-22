package com.example.baopengjian.ray_dailywork.eleventh;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.baopengjian.ray_dailywork.R;

import java.util.ArrayList;

/**
 * Created by Ray on 2020-1-2.
 */
public class MyFragment extends Fragment {

    private String mTitle;
    private RecyclerView recyclerView;
    private RecyclerAdapter recyclerAdapter;

    private ArrayList<Menu> mData;

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);

        mData = new ArrayList<>();
        for (int i = 1; i < 16; i++) {
            mData.add(new Menu("宫格" + i));
        }
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),4));
        recyclerAdapter = new RecyclerAdapter(4,getActivity(), mData);
        recyclerView.setAdapter(recyclerAdapter);
        return view;
    }

    public String getmTitle() {
        return mTitle;
    }
}



