package com.example.baopengjian.ray_dailywork.first;

import java.io.File;

/**
 * @author Ray on 2018/1/12.
 */

public interface DownLoadListener {
    /**
     * 下载成功
     *
     * @param file
     */
    void onDownLoadSuccess(File file);

    /**
     * 下载失败
     *
     * @param msg
     */
    void onDownLoadFailed(String msg);

    /**
     * 下载进度
     *
     * @param progress
     */
    void onDownLoading(int progress);
}
