package com.example.baopengjian.ray_dailywork.april;

import android.Manifest;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.example.baopengjian.ray_dailywork.R;
import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;

import java.io.File;

/**
 * Created by Ray on 2018/4/18 .
 */

public class DependsActivity extends AppCompatActivity {

    private PDFView pdfView;
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private TextView pageTv, pageTv1;
    private int p;

    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_depends);

        //获取动态权限
        getPermission();

        pdfView = (PDFView) findViewById(R.id.pdfView);
        pageTv = (TextView) findViewById(R.id.pageTv);
        pageTv1 = (TextView) findViewById(R.id.pageTv1);

        //final int myPage = (int) SPUtils.get(DependsActivity.this, "page", 0);
        //选择pdf

        downLoadAndSkip();
    }

    private void downLoadAndSkip(){
        new AppFileDownUtils("http://file.chmsp.com.cn/colligate/file/00100000224821.pdf", "00100000224821.pdf", new DownLoadListener() {
            @Override
            public void onDownLoadSuccess(File file) {
               load(file);
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


    private void load(File file){
        pdfView.fromFile(file)
//                .pages(0, 2, 3, 4, 5); // 把0 , 2 , 3 , 4 , 5 过滤掉
                //是否允许翻页，默认是允许翻页
                .enableSwipe(true)
                //pdf文档翻页是否是垂直翻页，默认是左右滑动翻页
                .swipeHorizontal(true)
                //
                .enableDoubletap(false)
                //设置默认显示第0页
                .defaultPage(0)
                //允许在当前页面上绘制一些内容，通常在屏幕中间可见。
//                .onDraw(onDrawListener)
//                // 允许在每一页上单独绘制一个页面。只调用可见页面
//                .onDrawAll(onDrawListener)
                //设置加载监听
                .onLoad(new OnLoadCompleteListener() {
                    @Override
                    public void loadComplete(int nbPages) {
                        pageTv.setText(nbPages + "");
                        pageTv1.setText(0 +  "/");
                    }
                })
                //设置翻页监听
                .onPageChange(new OnPageChangeListener() {

                    @Override
                    public void onPageChanged(int page, int pageCount) {
                        p = page;
                        pageTv1.setText(page + "/");
                    }
                })
                //设置页面滑动监听
//                .onPageScroll(onPageScrollListener)
//                .onError(onErrorListener)
                // 首次提交文档后调用。
//                .onRender(onRenderListener)
                // 渲染风格（就像注释，颜色或表单）
                .enableAnnotationRendering(false)
                .password(null)
                .scrollHandle(null)
                // 改善低分辨率屏幕上的渲染
                .enableAntialiasing(true)
                // 页面间的间距。定义间距颜色，设置背景视图
                .spacing(0)
                .load();
    }
    private void getPermission() {
        int hasWriteContactsPermission = ContextCompat.checkSelfPermission(DependsActivity.this,
                Manifest.permission.READ_EXTERNAL_STORAGE);
        if (hasWriteContactsPermission != PackageManager.PERMISSION_GRANTED) {
            if (!ActivityCompat.shouldShowRequestPermissionRationale(DependsActivity.this,
                    Manifest.permission.READ_EXTERNAL_STORAGE)) {
                ActivityCompat.requestPermissions(DependsActivity.this,
                        PERMISSIONS_STORAGE, REQUEST_EXTERNAL_STORAGE);
            }

            ActivityCompat.requestPermissions(DependsActivity.this,
                    PERMISSIONS_STORAGE, REQUEST_EXTERNAL_STORAGE);
        }

        while ((ContextCompat.checkSelfPermission(DependsActivity.this,
                Manifest.permission.READ_EXTERNAL_STORAGE)) != PackageManager.PERMISSION_GRANTED) {
        }
    }

}
