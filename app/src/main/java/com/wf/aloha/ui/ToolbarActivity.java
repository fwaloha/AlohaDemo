package com.wf.aloha.ui;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.method.MovementMethod;
import android.text.method.ScrollingMovementMethod;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Scroller;
import android.widget.TextView;

import com.wf.aloha.R;
import com.wf.aloha.service.AlermService;
import com.wf.aloha.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ToolbarActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.floating)
    FloatingActionButton floating;
    private TextView tvDrawer;
    private DrawerLayout drawer;
    private NavigationView navView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toolbar);
        ButterKnife.bind(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
//            actionBar.setHomeAsUpIndicator(R.mipmap.menu);
        }
//        if(Build.VERSION.SDK_INT>Build.VERSION_CODES.M){
//            View decorView = getWindow().getDecorView();
//            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
//            getWindow().setstat
//        }
        Intent intent = new Intent(this, AlermService.class);
        startService(intent);
        drawer = findViewById(R.id.drawer);
        navView = findViewById(R.id.nav);
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.it1:
                        ToastUtils.showInstance("it1");
                        break;
                    case R.id.it2:
                        ToastUtils.showInstance("it2");
                        break;

                    case R.id.it3:
                        ToastUtils.showInstance("it3");
                        break;

                    case R.id.it4:
                        ToastUtils.showInstance("it4");
                        break;

                    case R.id.it5:
                        ToastUtils.showInstance("it5");
                        break;

                }
                drawer.closeDrawer(Gravity.LEFT);
                return true;
            }
        });
        
        floating.setOnClickListener(this);
        TextView tv = findViewById(R.id.tv);
        tv.setMovementMethod(ScrollingMovementMethod.getInstance());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawer.openDrawer(Gravity.LEFT);
                break;
            case R.id.back:
                ToastUtils.showInstance("back");
                break;
            case R.id.delete:
                ToastUtils.showInstance("delete");

                break;
            case R.id.setting:
                ToastUtils.showInstance("setting");

                break;
        }
        return true;
    }

    @OnClick(R.id.floating)
    public void onViewClicked() {
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.floating:
                Snackbar.make(v,"delete le", Snackbar.LENGTH_LONG)
                        .setAction("undo", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                           ToastUtils.showInstance("hahah");     
                            }
                        }).show();
                ToastUtils.showInstance("floating");
                break;
        }
    }
}
