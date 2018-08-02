package com.wf.aloha.ui;

import android.app.SearchManager;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.telecom.TelecomManager;

import com.wf.aloha.R;
import com.wf.aloha.utils.LogUtils;
import com.wf.aloha.utils.NougatTools;

import java.io.File;
import java.util.List;

public class AttrDefineActivity extends AppCompatActivity {

    public AttrDefineActivity() {
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attr_define);

//        Intent intent = new Intent();
//        intent.setAction(Intent.ACTION_DIAL);
//        intent.setData(Uri.parse("tel:911"));
//        intent.setDataAndType(Uri.parse("uri"),"application/pdf");
//        Intent intent1 = new Intent(Intent.ACTION_CALL, Uri.parse("tel:10200"));

        //更新app，浏览器下载，某些浏览器自动打开安装
//        Intent intent = new Intent(Intent.ACTION_VIEW);
//        intent.setData(Uri.parse("http://app.daokoudai.com/daokoudai_v1.4.5_baidu.apk"));
//
//        Intent intent1 = new Intent(Intent.ACTION_WEB_SEARCH);
//        intent1.putExtra(SearchManager.QUERY,"hongmao");
//        startActivity(intent1);
        
        
//        List<PackageInfo> list = this.getPackageManager().getInstalledPackages(0);
//        String packageName = list.get(0).packageName;
//        PackageInfo packageInfo = list.get(0);
//        int icon = packageInfo.applicationInfo.icon;

//        File filsdir = this.getExternalFilesDir(Environment.DIRECTORY_MUSIC);
//        Uri parse = Uri.parse(filsdir.getPath());
//        Intent intent2 = new Intent(Intent.ACTION_VIEW);
//        intent2.setDataAndType(parse,"audio/mp3");
//        startActivity(intent2);

        //启动第三方app
//        startActivity(this.getPackageManager().getLaunchIntentForPackage("com.tencent.mm"));
//        try {
//        跳转应用商店相应app页面
//            PackageInfo packageInfo = this.getPackageManager().getPackageInfo("com.app.one", 0);
//            LogUtils.d("-----", packageInfo.packageName);
//            Uri uri = Uri.parse("market://details?id=" + packageInfo.packageName);
//            Intent intent2 = new Intent(Intent.ACTION_VIEW, uri);
//            startActivity(intent2);
//
//        } catch (PackageManager.NameNotFoundException e) {
//            e.printStackTrace();
//        }
        
        File musicFile = new File(getExternalFilesDir(null), "xzq.mp3");
        LogUtils.d("-----",musicFile.getPath());

        if(musicFile.exists()){
            LogUtils.d("-----","yes,...");
        }
        Intent intent3 = new Intent(Intent.ACTION_VIEW);
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.N){
            intent3.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            Uri uri = NougatTools.formatFileProviderUri(this, musicFile);
            intent3.setDataAndType(uri,"audio/mp3");
            startActivity(intent3);
        }else {
            LogUtils.d("-----",musicFile.getPath());
            Uri parse1 = Uri.parse("file://" + musicFile.getAbsolutePath());
            intent3.setDataAndType(parse1,"autio/mp3");
            startActivity(intent3);
        }
    }

}
