package com.wf.aloha.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.wf.aloha.R;
import com.wf.aloha.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DrawerActivity extends AppCompatActivity {

    
    
    
    @BindView(R.id.nav_view)
    NavigationView navView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);
        ButterKnife.bind(this);


        //item 图标显示自己本身的颜色，否则全是灰色
        navView.setItemIconTintList(null);
        //左侧布局的点击事件
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.favorite:
                        ToastUtils.showInstance("favorite");
                        break;
                }
                return false;
            }
        });
        //隐藏某个menu item
        navView.getMenu().findItem(R.id.favorite).setVisible(true);
        
        //获取左侧布局头部
        View headerView = navView.getHeaderView(0);
        ImageView ivHeader = (ImageView) headerView.findViewById(R.id.iv_head);
        ivHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.showInstance("touch a iv");
            }
        });
    }
}
