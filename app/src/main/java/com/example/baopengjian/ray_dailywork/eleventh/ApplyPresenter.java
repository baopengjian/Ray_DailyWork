package com.example.baopengjian.ray_dailywork.eleventh;

import java.util.ArrayList;
import java.util.List;

public class ApplyPresenter {


    public List<Menu> getFix(){
        List list = new ArrayList();
        list.add(new Menu("开户"));
        list.add(new Menu("业务办理"));
        list.add(new Menu("新股申购"));
        list.add(new Menu("开户"));
        return list;
    }

    public List<Menu> getRecent(){
        List list = new ArrayList();
        list.add(new Menu("业务办理"));
        list.add(new Menu("新股申购"));
        list.add(new Menu("理财"));
        list.add(new Menu("交易"));
        list.add(new Menu("开户"));
        list.add(new Menu("业务办理"));
        list.add(new Menu("新股申购"));
        list.add(new Menu("理财"));
        return list;
    }

    public List<Menu> getDynamic() {
        List list = new ArrayList();
        list.add(new Menu("业务办理"));
        list.add(new Menu("新股申购"));
        list.add(new Menu("理财"));
        return list;
    }
}
