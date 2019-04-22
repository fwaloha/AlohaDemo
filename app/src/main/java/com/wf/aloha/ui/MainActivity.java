package com.wf.aloha.ui;

import android.app.ActivityManager;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Process;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.tencent.bugly.crashreport.CrashReport;
import com.wf.aloha.R;
import com.wf.aloha.utils.LogUtils;

import java.io.Serializable;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


public class MainActivity extends AppCompatActivity implements Serializable {


    @BindView(R.id.tv_upload)
    TextView mTvPdf;
    @BindView(R.id.tv_get)
    TextView tvGet;
    @BindView(R.id.tv_draw_layout)
    TextView tvDrawLayout;
    @BindView(R.id.tv_test)
    TextView tvTest;
    @BindView(R.id.tv_viewstub)
    TextView tvViewstub;
    @BindView(R.id.tv_nav)
    TextView tvNav;
    @BindView(R.id.tv_camera)
    TextView tvCamera;
    @BindView(R.id.tv_super_swipe)
    TextView tvSuperSwipe;
    @BindView(R.id.tv_material)
    TextView tvMaterial;
    @BindView(R.id.tv_popup)
    TextView tvPopup;
    @BindView(R.id.tv_download)
    TextView tvDownload;
    @BindView(R.id.tv_rxjava)
    TextView tvRxjava;
    @BindView(R.id.tv_post)
    TextView tvPost;
    @BindView(R.id.tv_drawable)
    TextView tvDrawable;
    @BindView(R.id.tv_finger)
    TextView tvFinger;
    @BindView(R.id.tv_kotlin)
    TextView tvKotlin;
    @BindView(R.id.tv_catch)
    TextView tvCatch;
    @BindView(R.id.tv_dialog)
    TextView tvDialog;
    @BindView(R.id.tv_attr)
    TextView tvAttr;
    @BindView(R.id.tv_audio)
    TextView tvAudio;
    @BindView(R.id.kotlin_recyclerview)
    TextView kotlinRecyclerview;
    @BindView(R.id.tv_permission)
    TextView tvPermission;
    @BindView(R.id.main_layout)
    ConstraintLayout mainLayout;
    @BindView(R.id.tv_content_resolver)
    TextView tvContentResolver;
    @BindView(R.id.tv_notification)
    TextView tvNotification;
    @BindView(R.id.tv_capture)
    TextView tvCapture;
    @BindView(R.id.tv_video)
    TextView tvVideo;
    @BindView(R.id.tv_net)
    TextView tvNet;
    @BindView(R.id.thread)
    TextView thread;
    @BindView(R.id.tv_service)
    TextView tvService;
    @BindView(R.id.tv_download_service)
    TextView tvDownloadService;
    @BindView(R.id.tv_toolbar)
    TextView tvToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        LogUtils.d("-----mem 应用获取的内存大小", Runtime.getRuntime().maxMemory() + "M");

        ActivityManager activityManager = (ActivityManager) this.getSystemService(ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo info : activityManager.getRunningAppProcesses()) {
            if (info.pid == Process.myPid()) {
                LogUtils.d("-----processName", info.processName);
            }
        }
//        PushAgent.getInstance(this).onAppStart();

//        动态更换icon图标
//        PackageManager mPM = getPackageManager();
//        ComponentName defaultComp = getComponentName();
//        ComponentName newCompt = new ComponentName(getApplicationContext(), "com.wf.aloha.icon1");
//        ComponentName oldCompt = new ComponentName(getApplicationContext(), "com.wf.aloha.iconold");
////        mPM.setComponentEnabledSetting(defaultComp,PackageManager.COMPONENT_ENABLED_STATE_DISABLED,PackageManager.DONT_KILL_APP);
//        mPM.setComponentEnabledSetting(oldCompt,PackageManager.COMPONENT_ENABLED_STATE_ENABLED,PackageManager.DONT_KILL_APP);
//        mPM.setComponentEnabledSetting(newCompt,PackageManager.COMPONENT_ENABLED_STATE_DISABLED,PackageManager.DONT_KILL_APP);
//        
        Observable.create(new ObservableOnSubscribe<Drawable>() {
            @Override
            public void subscribe(ObservableEmitter<Drawable> e) throws Exception {
                Drawable drawable = getResources().getDrawable(R.mipmap.ic_girl);
                e.onNext(drawable);
            }
        }).subscribe(new Observer<Drawable>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Drawable drawable) {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    @OnClick({R.id.tv_get, R.id.tv_upload, R.id.tv_post, R.id.tv_rxjava, R.id.tv_download, R.id.tv_popup, R.id.tv_material, R.id.tv_super_swipe, R.id.tv_draw_layout, R.id.tv_camera
            , R.id.tv_nav, R.id.tv_viewstub, R.id.tv_test, R.id.tv_drawable, R.id.tv_finger, R.id.tv_kotlin, R.id.tv_catch, R.id.tv_dialog, R.id.tv_attr, R.id.tv_audio
            , R.id.kotlin_recyclerview, R.id.tv_permission, R.id.tv_content_resolver, R.id.tv_notification, R.id.tv_capture, R.id.tv_video, R.id.tv_net, R.id.thread
            , R.id.tv_service, R.id.tv_download_service,R.id.tv_toolbar})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_get:
                Intent intent1 = new Intent(this, RetrofitGetActivity.class);
                startActivity(intent1);
                break;
            case R.id.tv_upload:
                Intent intent = new Intent(this, UploadActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_post:
                Intent intent2 = new Intent(this, RetrofitPostActivity.class);
                startActivity(intent2);
                break;
            case R.id.tv_rxjava:
                Intent intent3 = new Intent(this, RxJavaActivity.class);
                startActivity(intent3);
                break;
            case R.id.tv_download:
                Intent intent4 = new Intent(this, DownLoadActivity.class);
                startActivity(intent4);
                break;
            case R.id.tv_popup:
                View rootView = View.inflate(this, R.layout.tv_popupwindow, null);
                PopupWindow popupWindow = new PopupWindow(rootView, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);
                popupWindow.setAnimationStyle(R.style.MyPopup_anim_style);
                popupWindow.showAtLocation(findViewById(R.id.main_layout), Gravity.CENTER, 0, 0);
                break;
            case R.id.tv_material:
                Intent intent5 = new Intent(this, MaterialActivity.class);
                startActivity(intent5);
                break;
            case R.id.tv_super_swipe:
                Intent intent6 = new Intent(this, SuperSwipeActivity.class);
                startActivity(intent6);
                break;
            case R.id.tv_draw_layout:
                Intent intent7 = new Intent(this, DrawerActivity.class);
                startActivity(intent7);
                break;
            case R.id.tv_camera:
                Intent intent8 = new Intent(this, CameraActivity.class);
                startActivity(intent8);
                break;
            case R.id.tv_nav:
                Intent intent9 = new Intent(this, LoginActivity.class);
                startActivity(intent9);
                break;
            case R.id.tv_viewstub:
                Intent intent10 = new Intent(this, ViewStubActivity.class);
                startActivity(intent10);
                break;
            case R.id.tv_test:
//                Intent intent11 = new Intent(this, TestActivity.class);
                Intent intent11 = new Intent(this, Test2Activity.class);
                startActivity(intent11);
                break;
            case R.id.tv_drawable:
                Intent intent12 = new Intent(this, CanvasDrawActivity.class);
                startActivity(intent12);
                break;
            case R.id.tv_finger:
                Intent intent13 = new Intent(this, FingerActivity.class);
                startActivity(intent13);
                break;
            case R.id.tv_kotlin:
                Intent intent14 = new Intent(this, KotlinActivity.class);
                startActivity(intent14);
                break;
            case R.id.tv_catch:
                Intent intent15 = new Intent(this, TempActivity.class);
                startActivity(intent15);
                break;
            case R.id.tv_dialog:
                Intent intent16 = new Intent(this, DialogFrgmtActivity.class);
                startActivity(intent16);
                break;
            case R.id.tv_attr:
                Intent intent17 = new Intent(this, AttrDefineActivity.class);
                startActivity(intent17);
                break;
            case R.id.tv_audio:
//                Intent intent18 = new Intent(this, RadioActivity.class);
//                Intent intent18 = new Intent(this, WebviewDemoActivity.class);
//                Intent intent18 = new Intent(this, ViewActivity.class);
                CrashReport.testJavaCrash();
                Intent intent18 = new Intent(this, ComListActivity.class);
                startActivity(intent18);
                break;
            case R.id.kotlin_recyclerview:
                Intent intent19 = new Intent(this, KotlinRecyActivity.class);
                startActivity(intent19);
                break;
            case R.id.tv_permission:
                Intent intent20 = new Intent(this, PermissionActivity.class);
                startActivity(intent20);
                break;
            case R.id.tv_content_resolver:
                Intent intent21 = new Intent(this, ResolverActivity.class);
                startActivity(intent21);
                break;
            case R.id.tv_notification:
                Intent intent22 = new Intent(this, NotifiActivity.class);
                startActivity(intent22);
                break;
            case R.id.tv_capture:
                Intent intent23 = new Intent(this, CaptureActivity.class);
                startActivity(intent23);
                break;
            case R.id.tv_video:
                Intent intent24 = new Intent(this, VideoActivity.class);
                startActivity(intent24);
                break;
            case R.id.tv_net:
                Intent intent25 = new Intent(this, NetActivity.class);
                startActivity(intent25);
                break;
            case R.id.thread:
                Intent intent26 = new Intent(this, ThreadUIActivity.class);
                startActivity(intent26);
                break;
            case R.id.tv_service:
                Intent intent27 = new Intent(this, ServiceActivity.class);
                startActivity(intent27);
                break;
            case R.id.tv_download_service:
                Intent intent28 = new Intent(this, DownloadServiceActivity.class);
                startActivity(intent28);
                break;
            case R.id.tv_toolbar:
                Intent intent29 = new Intent(this, ToolbarActivity.class);
                startActivity(intent29);
                break;
        }
    }

}
