package com.wf.aloha.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.wf.aloha.CheckAdapter;
import com.wf.aloha.CheckBean;
import com.wf.aloha.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TestActivity extends AppCompatActivity implements CheckAdapter.CheckItemListener {

    //适配器
    private CheckAdapter mCheckAdapter;
    //列表
    private RecyclerView check_rcy;
    //全选操作
    private CheckBox check_all_cb;
    //列表数据
    private List<CheckBean> dataArray;
    //选中后的数据
    private List<CheckBean> checkedList;
    private boolean isSelectAll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        checkedList = new ArrayList<>();
        initDatas();
        initViews();
    }

    private void initViews() {
        check_rcy = (RecyclerView) findViewById(R.id.check_rcy);
        check_all_cb = (CheckBox) findViewById(R.id.check_all_cb);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        check_rcy.setLayoutManager(linearLayoutManager);
        mCheckAdapter = new CheckAdapter(this, dataArray, this);
        check_rcy.setAdapter(mCheckAdapter);
        //如果使用CheckBox的OnCheckedChangeListener事件，则选中事件会有一些意想不到的结果，欢迎体验
        //在列表Item中的CheckBox也一样的效果
        check_all_cb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isSelectAll = !isSelectAll;
                checkedList.clear();
                if (isSelectAll) {//全选处理
                    checkedList.addAll(dataArray);
                }
                for (CheckBean checkBean : dataArray) {
                    checkBean.setChecked(isSelectAll);
                }
                mCheckAdapter.notifyDataSetChanged();
            }
        });
//        check_all_cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                    checkedList.clear();
//                    checkedList.addAll(dataArray);
//                    for (CheckBean checkBean : dataArray) {
//                        checkBean.setChecked(isChecked);
//                    }
//                    mCheckAdapter.notifyDataSetChanged();
//            }
//        });
    }

    private void initDatas() {
        dataArray = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            CheckBean bean = new CheckBean();
            bean.setOrder(String.valueOf(i + 1));
            bean.setName("名称_" + i);
            bean.setContent("第" + i + "条内容");
            bean.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            dataArray.add(bean);
        }
    }

    @Override
    public void itemChecked(CheckBean checkBean, boolean isChecked) {
        //处理Item点击选中回调事件
        if (isChecked) {
            //选中处理
            if (!checkedList.contains(checkBean)) {
                checkedList.add(checkBean);
            }
        } else {
            //未选中处理
            if (checkedList.contains(checkBean)) {
                checkedList.remove(checkBean);
            }
        }
        //判断列表数据是否全部选中
        if (checkedList.size() == dataArray.size()) {
            check_all_cb.setChecked(true);
        } else {
            check_all_cb.setChecked(false);
        }
    }
}
