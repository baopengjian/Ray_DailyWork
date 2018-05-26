package com.example.baopengjian.ray_dailywork.may.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.baopengjian.ray_dailywork.R;

/**
 * Created by Ray on 2018/5/16.
 * ViewPager中 获取Fragment显示与隐藏
 */

public class ViewPagerLazyFragment extends Fragment {

    public static final String TAG  = "ViewPagerListener";

    private String mType;

    //标记需要等待回调onMyResume;
    private boolean isWaitingForOnMyResume = false;

    public static ViewPagerLazyFragment newInstance(String type) {
        Bundle args = new Bundle();
        args.putString("type", type);
        ViewPagerLazyFragment fragment = new ViewPagerLazyFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.view_pager_listener_fragment, null);
        mType = getArguments().getString("type");
        ((TextView) view.findViewById(R.id.tv_tag)).setText(mType);

        view.findViewById(R.id.tv_jump).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getContext().startActivity(new Intent(getContext(),OtherActivity.class));
            }
        });
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (isWaitingForOnMyResume && getUserVisibleHint()) {
            isWaitingForOnMyResume = false;
            onVisible();
        }
    }



    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        Log.i(TAG,"ViewPagerListenerFragment setUserVisibleHint() type = " + mType + " isVisibleToUser="+isVisibleToUser);
        if (isVisibleToUser) {
            if (isResumed()) {
                onVisible();
            } else {
                isWaitingForOnMyResume = true;
            }
        } else {
            if (isResumed())
                onInVisible();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if(getUserVisibleHint()){
            isWaitingForOnMyResume = true;
            onInVisible();
        }

        Log.i(TAG,"ViewPagerListenerFragment onPause() type = "+mType);
    }

    /**
     * viewPager中界面每次可见调用；
     */
    public void onVisible() {
        Log.i(TAG,"ViewPagerListenerFragment onVisible() type = " + mType);
    }


    /**
     * viewPager中界面每次不可见调用；
     */
    public void onInVisible() {
        Log.i(TAG,"ViewPagerListenerFragment onInVisible() type = " + mType);
    }
}
