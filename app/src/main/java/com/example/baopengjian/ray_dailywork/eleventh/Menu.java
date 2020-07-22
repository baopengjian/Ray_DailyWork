package com.example.baopengjian.ray_dailywork.eleventh;

public class Menu {

    private String name;
    private String picUrl;
    private String path;

    Menu() {

    }

    Menu(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
