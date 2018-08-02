package com.wf.aloha.ui;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.wf.aloha.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


public class MainActivity extends AppCompatActivity {


    @BindView(R.id.tv_upload)
    TextView mTvPdf;
    @BindView(R.id.tv_get)
    TextView tvGet;
    @BindView(R.id.tv_draw_layout)
    TextView tvDrawLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

//        PushAgent.getInstance(this).onAppStart();

//        动态更换icon图标
//        PackageManager mPM = getPackageManager();
//        ComponentName newCompt = new ComponentName(getApplicationContext(), "com.wf.aloha.icon1");
//        ComponentName oldCompt = new ComponentName(getApplicationContext(), "com.wf.aloha.iconold");
//        mPM.setComponentEnabledSetting(oldCompt,PackageManager.COMPONENT_ENABLED_STATE_DISABLED,PackageManager.DONT_KILL_APP);
//        mPM.setComponentEnabledSetting(newCompt,PackageManager.COMPONENT_ENABLED_STATE_ENABLED,PackageManager.DONT_KILL_APP);
        
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
            , R.id.tv_nav, R.id.tv_viewstub, R.id.tv_test, R.id.tv_drawable, R.id.tv_finger, R.id.tv_kotlin, R.id.tv_catch, R.id.tv_dialog, R.id.tv_attr, R.id.tv_audio})
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
                Intent intent11 = new Intent(this, TestActivity.class);
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
                Intent intent18 = new Intent(this, RadioActivity.class);
                startActivity(intent18);
                break;
        }
    }
}
