package com.example.baopengjian.ray_dailywork.april;

import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.StrictMode;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 文件下载：
 * 会重复下载，不会读取缓存
 */
public class AppFileDownUtils extends Thread {

    /**
     * 文件下载url，已做非空检查
     */
    private String mDownloadUrl;
    private String mFileName;

    private DownLoadListener mDownLoadListener;
    private Handler mHandler = new Handler(Looper.getMainLooper());
    public static final String DOWNlOAD_FILE = "/dxDownload";

    public AppFileDownUtils(String downloadUrl, String fileName, DownLoadListener listener) {
        mDownloadUrl = downloadUrl;
        mFileName = fileName;
        mDownLoadListener = listener;
    }

    @Override
    public void run() {
        try {
            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {

                File sdcardDir = Environment.getExternalStorageDirectory();
                String dlpath = sdcardDir.getPath() + DOWNlOAD_FILE;
                File folder = new File(dlpath);
                if (!folder.exists()) {
                    folder.mkdirs();
                }


                final File saveFilePath = new File(folder, mFileName);
                boolean downSuc = downloadFile(mDownloadUrl, saveFilePath);
                if (downSuc) {
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            if (mDownLoadListener != null) {
                                mDownLoadListener.onDownLoadSuccess(saveFilePath);
                            }
                        }
                    });
                }
            }
        } catch (Exception e) {
            onFail(e.getMessage());
        }
    }


    private void onFail(final String errorMsg) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                if (mDownLoadListener != null) {
                    mDownLoadListener.onDownLoadFailed(errorMsg);
                }
            }
        });
    }

    private synchronized void onLoading(final int progress) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                if (mDownLoadListener != null) {
                    mDownLoadListener.onDownLoading(progress);
                }
            }
        });
    }


    /**
     * Desc:文件下载
     *
     * @param downloadUrl  下载URL
     * @param saveFilePath 保存文件路径
     * @return ture:下载成功 false:下载失败
     */
    public boolean downloadFile(String downloadUrl, File saveFilePath) {
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectDiskReads().detectDiskWrites().detectNetwork().penaltyLog().build());
        boolean result;
        try {
            int downFileSize = 0;
            int totalSize;
            int progress;
            int tempProgress = 0;
            URL url = new URL(downloadUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            if (null == conn) {
                onFail("Connection连接失败");
                return false;
            }
            conn.setReadTimeout(10000);
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept-Encoding", "");
            conn.setDoInput(true);
            conn.connect();
            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                totalSize = conn.getContentLength();

                InputStream is = conn.getInputStream();
                FileOutputStream fos = new FileOutputStream(saveFilePath);
                byte[] buffer = new byte[1024];
                int i;
                while ((i = is.read(buffer)) != -1) {
                    fos.write(buffer, 0, i);
                    downFileSize = downFileSize + i;
                    progress = (int) (downFileSize * 100.0 / totalSize);
                    synchronized (this) {
                        if (progress != tempProgress) {
                            tempProgress = progress;
                            onLoading(tempProgress);
                        }
                    }
                }
                fos.flush();
                fos.close();
                is.close();
                result = true;
            } else {
                result = false;
                onFail("下载失败" + conn.getResponseCode());
            }
        } catch (Exception e) {
            result = false;
            onFail(e.getMessage());
        }
        return result;
    }
}
