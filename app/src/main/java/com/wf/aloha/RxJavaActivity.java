package com.wf.aloha;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.wf.aloha.baseview.BaseRecyclerView;
import com.wf.aloha.bean.AppInfo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

public class RxJavaActivity extends AppCompatActivity {

    @BindView(R.id.recyclerview)
    BaseRecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java);
        ButterKnife.bind(this);

        Observable<List<AppInfo>> listObservable = getListByRxJava();
        
    }

    private Observable<List<AppInfo>> getListByRxJava() {
        Observable<List<AppInfo>> observable = Observable.create(new ObservableOnSubscribe<List<AppInfo>>() {
            @Override
            public void subscribe(ObservableEmitter<List<AppInfo>> e) throws Exception {
                e.onNext(getAppList());
                e.onComplete();
            }
        });
        return observable;
    }

    private ArrayList<AppInfo> getAppList() {
        ArrayList<AppInfo> appInfos = new ArrayList<>();
        List<PackageInfo> installedPackages = this.getPackageManager().getInstalledPackages(PackageManager.GET_ACTIVITIES);
        for (PackageInfo pkInfo :
                installedPackages) {
            AppInfo appInfo = new AppInfo();
            appInfo.setName(pkInfo.applicationInfo.loadLabel(this.getPackageManager()).toString());
            appInfo.setIcon(pkInfo.applicationInfo.loadIcon(this.getPackageManager()));
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String time = simpleDateFormat.format(new Date(pkInfo.firstInstallTime));
            appInfo.setInstallTime(time);
            appInfo.setVersionCode(pkInfo.versionCode);
            appInfo.setVersionName(pkInfo.versionName);
            appInfos.add(appInfo);
        }
        return appInfos;
    }
}
