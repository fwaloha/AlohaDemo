package com.wf.aloha.ui;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Looper;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.androidadvance.topsnackbar.TSnackbar;
import com.wf.aloha.R;
import com.wf.aloha.baseview.BaseRecyclerView;
import com.wf.aloha.bean.AppInfo;
import com.wf.aloha.utils.LogUtils;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;

public class RxJavaActivity extends AppCompatActivity {

    @BindView(R.id.recyclerview)
    BaseRecyclerView mRecyclerView;
    @BindView(R.id.co_main)
    CoordinatorLayout coMain;
    @BindView(R.id.bt)
    Button bt;
    @BindView(R.id.ll)
    LinearLayout ll;
    private Subscription mSubscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java);
        ButterKnife.bind(this);

        rxjavaMethod();
    }

    private void rxjavaMethod() {
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                e.onNext("a");
//                Thread.sleep(1000);
                e.onNext("b");
//                Thread.sleep(1000);
                e.onNext("c");
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        LogUtils.d("-----rx", "begin....");
                    }

                    @Override
                    public void onNext(String s) {
                        LogUtils.d("-----rx", s + ":\n");
                        Thread mainthread = Looper.getMainLooper().getThread();
                        Thread nowThread = Thread.currentThread();
                        LogUtils.d("-----rx", android.os.Process.myTid() + "thread id" + nowThread + "::main:" + mainthread);
//                        try {
////                            Thread.sleep(3000);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

        //2种，背压式，
        Flowable.create(new FlowableOnSubscribe<String>() {
            @Override
            public void subscribe(FlowableEmitter<String> e) throws Exception {

                e.onNext("d");
                e.onNext("e");
                e.onNext("f");
                e.onNext("g");
            }
        }, BackpressureStrategy.ERROR)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onSubscribe(Subscription s) {
//                        s.request(2);
                        mSubscription = s;
                    }

                    @Override
                    public void onNext(String s) {
                        LogUtils.d("-----rx", "jieshoudao:" + s);
                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    private void showTopSnackbar() {
        TSnackbar tSnackbar = TSnackbar.make(ll, "hahahah top", TSnackbar.LENGTH_LONG);
        tSnackbar.show();
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

    @OnClick({R.id.bt,R.id.bt_rx})
    public void onViewClicked(View view) {
        switch (view.getId()){
            case R.id.bt:
                showTopSnackbar();
                break;
            case R.id.bt_rx://处理事件，点一次处理一个的request 
                mSubscription.request(1);
                break;
        }
    }
}
