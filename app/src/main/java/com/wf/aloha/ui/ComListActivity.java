package com.wf.aloha.ui;

import android.app.ListActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.wf.aloha.R;

import java.util.ArrayList;

/**
 * ListActivity 是一个自己有列表的Activity，还有可以设置空视图，列表为空自动显示空视图。此list只能用listview，
 * 布局里有两个固定id，一个是 android:id="@id/android:list"，android:id="@id/android:empty"
 */
public class ComListActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_com_list);
        TextView tvTest = (TextView) findViewById(R.id.tv_test);
        tvTest.setText("dklsdfjlsdjlfskdjflsdjlfdk\nfdf\nsfn\nsdg\nsdfasd\nadfsdfsdf");
//        textview 设置滚动效果
        tvTest.setMovementMethod(ScrollingMovementMethod.getInstance());
        initList();
    }

    private void initList() {

        ArrayList<String> list = new ArrayList<>();
//        list.add("monday");
//        list.add("Tuesday");
//        list.add("winsday");
//        list.add("thurday");
//        list.add("friday");
//        list.add("sateday");
//        list.add("sunday");
//        list.add("monday");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
        setListAdapter(arrayAdapter);
    }
}
