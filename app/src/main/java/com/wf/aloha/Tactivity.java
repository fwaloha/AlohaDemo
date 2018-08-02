package com.wf.aloha;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.wf.aloha.utils.LogUtils;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Tactivity extends AppCompatActivity {

    @BindView(R.id.tv_)
    TextView tvShow;
    @BindView(R.id.tv_clear)
    TextView tvClear;
    private File cacheDir;
    private long externalCache;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tactivity);
        ButterKnife.bind(this);
        
        
        cacheDir = this.getCacheDir();

        File file = new File(cacheDir, "hh.txt");
        try {
            file.createNewFile();
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write("hehehehehheeheehehehhehehehehhe");
            fileWriter.close();
            LogUtils.d("-----",file.getAbsolutePath()+"::"+file.length());
        } catch (IOException e) {
            e.printStackTrace();
        }
        LogUtils.d("-----", cacheDir.getPath());
        long fileSize = getFileSize(cacheDir);
        if(Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED && getExternalCacheDir()!=null){
            externalCache = getFileSize(getExternalCacheDir());
        }
        tvClear.setText((fileSize+externalCache) + "Mb");
    }

    //    删除目录下的所有文件
    private boolean delFile(File cacheDir) {
        if (cacheDir != null && cacheDir.isDirectory()) {
            String[] list = cacheDir.list();
            for (int i = 0; i < list.length; i++) {
                boolean sucdess = delFile(new File(cacheDir, list[i]));
                if (!sucdess) {
                    return false;
                }
            }
        }
        return cacheDir.delete();
    }

    //    获取目录总的大小
    private long getFileSize(File cacheDir) {
        long totalsize = 0;
        File[] files = cacheDir.listFiles();
        if (files == null) {
            return 0;
        }
        for (int i = 0; i < files.length; i++) {
            if (files[i].isDirectory()) {
                totalsize += getFileSize(files[i]);
            } else {
                totalsize += files[i].length();
            }
        }
        return totalsize;
    }

    @OnClick(R.id.tv_)
    public void onViewClicked() {
        delFile(cacheDir);
        long clsize = getFileSize(cacheDir);
        tvClear.setText(clsize + "Mb");
    }
}
