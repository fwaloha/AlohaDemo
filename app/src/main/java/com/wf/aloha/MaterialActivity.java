package com.wf.aloha;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.androidadvance.topsnackbar.TSnackbar;
import com.wf.aloha.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MaterialActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.rvToDoList)
    RecyclerView rvToDoList;
    @BindView(R.id.main_content)
    CoordinatorLayout mainContent;
    @BindView(R.id.floatingbt)
    FloatingActionButton floatingbt;
    @BindView(R.id.bt)
    Button bt;
    @BindView(R.id.nestsv)
    NestedScrollView nestsv;
    @BindView(R.id.tv)
    TextView tv;
    @BindView(R.id.tv_title)
    TextView tvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //AppCompatActivity 此属性不起作用，
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //AppCompatActivity隐藏actionbar
//        getSupportActionBar().hide();
        setContentView(R.layout.activity_material);
        ButterKnife.bind(this);

        toolbar.setTitle("Material");
//        toolbar.setSubtitle("second Titile");
        toolbar.setLogo(R.mipmap.ic_launcher);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.mipmap.ic_tre_line);

        //使用自带的返回键
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                ToastUtils.showInstance("点哪了？");
                return false;
            }
        });
//        自带返回键的点击监听 也可以在onOptionsItemSelected中设置R.id.home
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.action_search:
                        ToastUtils.showInstance("search le");
                        break;
                    case R.id.action_share:
                        ToastUtils.showInstance("share le");
                        break;
                    case R.id.action_settings:
                        ToastUtils.showInstance("setting le");
                        break;
                }
                return true;
            }
        });
    }

    /**
     * 加载toolbar上方右侧按钮
     *
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_material, menu);
        return true;
    }

    /**
     * toolbar item 点击事件 右侧添加的menu点击无效，左侧返回按钮没问题。
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            //返回键的监听
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }
//
    @OnClick({R.id.toolbar, R.id.floatingbt, R.id.bt,R.id.tv_title})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.toolbar:
//                finish();
                break;
            case R.id.bt:
                //snackbar 操作 改变背景颜色及文字颜色及简单点击事件
                Snackbar snackbar = Snackbar.make(mainContent, "zheshi snack bar", Snackbar.LENGTH_INDEFINITE);
                View view1 = snackbar.getView();
                if (view1 != null) {
                    view1.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    ((TextView) view1.findViewById(R.id.snackbar_text)).setTextColor(getResources().getColor(R.color.bg_cyan));
                }
                snackbar.setAction("this action", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ToastUtils.showInstance("哈哈哈");
                    }
                })
                        .setActionTextColor(getResources().getColor(R.color.bg_cyan))
                        .show();

                TSnackbar tSnackbar = TSnackbar.make(nestsv, "top snackbar", TSnackbar.LENGTH_LONG);
                tSnackbar.show();
                break;
            case R.id.tv_title:
                ToastUtils.showInstance("hahahha title");
                break;
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

}
