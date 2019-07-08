package com.example.baopengjian.ray_dailywork.first;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.example.baopengjian.ray_dailywork.R;

import java.io.File;

/**
 * Created by Ray on 2018/4/18 .
 * #1 requirement 加载网络pdf文件 http://file.chmsp.com.cn/colligate/file/00100000224821.pdf
 * #2 scheme:
 *      (1)  webView加载 ，谷歌服务有限制
 *      (2)  跳转浏览器，体验差
 *      (3)  下载后调用手机内APP，体验差
 *      (4)  三方依赖库
 */

public class LoadPdfActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_pdf);

        // #1 webViewLoadPdf();

       //#2 skipBrowser();

        //#3 downLoadAndSkip();

        depends();
    }

    private void depends() {
        startActivity(new Intent(LoadPdfActivity.this,DependsActivity.class));
    }



    //下载后调用app
    private void downLoadAndSkip(){
        new AppFileDownUtils("http://file.chmsp.com.cn/colligate/file/00100000224821.pdf", "00100000224821.pdf", new DownLoadListener() {
            @Override
            public void onDownLoadSuccess(File file) {
                startActivity(getPdfFileIntent(file));
            }

            @Override
            public void onDownLoadFailed(String msg) {

            }

            @Override
            public void onDownLoading(int progress) {
                Log.i("onDownLoading",">>> progress ="+progress);
            }
        }).start();
    }

    public Intent getPdfFileIntent(File file) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Uri uri = Uri.fromFile(file);
        intent.setDataAndType(uri, "application/pdf");
        return Intent.createChooser(intent, "Open File");
    }


    //体验差 跳转浏览器,两次跳转（浏览器+ 点击下载后的通知跳转能够打开pdf的app）
    private void skipBrowser(){
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        Uri content_url = Uri.parse("http://file.chmsp.com.cn/colligate/file/00100000224821.pdf");
        intent.setData(content_url);
        startActivity(intent);
    }

    //找不到网页
    private void webViewLoadPdf() {
        //wv
        WebView wb = findViewById(R.id.wv);
        wb.getSettings().setJavaScriptEnabled(true);
        wb.getSettings().setPluginState(WebSettings.PluginState.ON);
        wb.loadUrl("https://docs.google.com/viewer?url=http://file.chmsp.com.cn/colligate/file/00100000224821.pdf");
    }
}
