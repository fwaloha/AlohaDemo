package com.wf.aloha.ui;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.wf.aloha.R;
import com.wf.aloha.adapter.TestListAdapter;
import com.wf.aloha.utils.ToastUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HideActivity extends AppCompatActivity {

    @BindView(R.id.listview)
    ListView listview;
    @BindView(R.id.rec_view)
    RecyclerView recView;

    private String[] data = {
            "Apple", "Banana", "Orange", "Watermelon",
            "Pear", "Grape", "Pineapple", "Strawberry", "Cherry", "Mango",
            "Apple", "Banana", "Orange", "Watermelon", "Pear", "Grape",
            "Pineapple", "Strawberry", "Cherry", "Mango"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hide);
        ButterKnife.bind(this);

//        showAlertdialog();
//        showProgressDialog();
//        initListview();
        initRecView();
    }

    private void initRecView() {
        final ArrayList<Fruit> fruits = new ArrayList<Fruit>();
        for (String item : data) {
            Fruit fruit = new Fruit(item, R.mipmap.apple_pic);
            fruits.add(fruit);
        }
        TestRecAdapter testRecAdapter = new TestRecAdapter(fruits);
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
//        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        recView.setLayoutManager(staggeredGridLayoutManager);
        recView.setAdapter(testRecAdapter);
//        testRecAdapter.add(fruits);
        testRecAdapter.setOnItemClickListener(new TestRecAdapter.OnItemClickListener(){

            @Override
            public void onItemClick(View v) {
                Fruit item = (Fruit) v.getTag();
                ToastUtils.showInstance(item.name);
            }
        });
    }

    private void initListview() {
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,data);
        final ArrayList<Fruit> fruits = new ArrayList<Fruit>();
        for (String item : data) {
            Fruit fruit = new Fruit(item, R.mipmap.apple_pic);
            fruits.add(fruit);
        }
        TestListAdapter adapter = new TestListAdapter(this, R.layout.test_adapter_layout, fruits);

//        adapter.addAll(fruits);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Fruit fruit = fruits.get(position);
                ToastUtils.showInstance(fruit.name);
            }
        });

    }

    private void showProgressDialog() {
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("this is progress dialog");
        progressDialog.setMessage("loading...");
        progressDialog.setCancelable(true);
        progressDialog.show();
    }

    private void showAlertdialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("This is Dialog");
        builder.setMessage("Something important.");
        builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ToastUtils.showInstance("ok");
            }
        });
        builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ToastUtils.showInstance("no");
            }
        });
        builder.create().show();
    }
}
