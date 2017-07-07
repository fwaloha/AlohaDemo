package com.wf.aloha;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.umeng.message.PushAgent;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


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

        PushAgent.getInstance(this).onAppStart();
    }

    @OnClick({R.id.tv_get, R.id.tv_upload, R.id.tv_post, R.id.tv_rxjava, R.id.tv_download, R.id.tv_popup, R.id.tv_material, R.id.tv_super_swipe,R.id.tv_draw_layout,R.id.tv_camera})
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
        }
    }
}
