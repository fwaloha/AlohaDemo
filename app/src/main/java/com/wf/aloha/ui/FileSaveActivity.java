package com.wf.aloha.ui;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.wf.aloha.R;
import com.wf.aloha.database.MyDatabaseHelper;
import com.wf.aloha.utils.ToastUtils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FileSaveActivity extends AppCompatActivity {

    @BindView(R.id.et_input)
    EditText etInput;
    @BindView(R.id.bt_save)
    Button btSave;
    @BindView(R.id.bt_sqlite)
    Button btSqlite;
    @BindView(R.id.bt_add_sql)
    Button btAddSql;
    @BindView(R.id.bt_update)
    Button btUpdate;
    @BindView(R.id.bt_query)
    Button btQuery;
    private MyDatabaseHelper testdb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_save);
        ButterKnife.bind(this);
        readFile();
        shared();
    }

    private void shared() {
        SharedPreferences preferences = this.getPreferences(MODE_PRIVATE);
        SharedPreferences shardname = getApplicationContext().getSharedPreferences("shardname", MODE_PRIVATE);
        SharedPreferences.Editor edit = preferences.edit();
        edit.putBoolean("kkk", false);
        edit.remove("");
        edit.commit();
        edit.apply();
    }

    private void readFile() {
        try {
            FileInputStream fis = openFileInput("testData");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fis));
            StringBuilder stringBuilder = new StringBuilder();
            try {
                String lins;
                while ((lins = bufferedReader.readLine()) != null) {
                    stringBuilder.append(lins);
                }
                etInput.setText(stringBuilder.toString());
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    bufferedReader.close();
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @OnClick({R.id.bt_save, R.id.bt_sqlite, R.id.bt_add_sql, R.id.bt_update, R.id.bt_query})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_sqlite:
                createData();
                break;
            case R.id.bt_add_sql:
                addSql();
                break;
            case R.id.bt_update:
                updateSql();
                break;
            case R.id.bt_query:
                querySql();
                break;
        }
    }

    private void querySql() {
        SQLiteDatabase database = testdb.getWritableDatabase();
        Cursor cursor = database.query("Book", null, null, null, null, null, null);
        if (cursor.moveToFirst()) {

            do {
                String name = cursor.getString(cursor.getColumnIndex("name"));
                double pages = cursor.getInt(cursor.getColumnIndex("pages"));
                double price = cursor.getDouble(cursor.getColumnIndex("price"));
                String author = cursor.getString(cursor.getColumnIndex("author"));
                Log.d("sql", name + ":" + pages + ":" + price + ":" + author);
            } while (cursor.moveToNext());
        }
        cursor.close();
    }

    private void updateSql() {
        SQLiteDatabase db = testdb.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("price", 999);
        db.update("Book", contentValues, "name =?", new String[]{"kkk"});
    }

    //add value to sql db book
    private void addSql() {
        SQLiteDatabase database = testdb.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("author", "itheme");
        values.put("price", 12.11);
        values.put("pages", 232);
        values.put("name", "kkk");

        long isOk = database.insert("Book", null, values);
        ToastUtils.showInstance("insert " + isOk);

        values.clear();
        values.put("name", "kdkk");
        values.put("author", "ithademe");
        values.put("pages", 1232);
        values.put("price", 192.11);
        long issk = database.insert("Book", null, values);
        ToastUtils.showInstance("add ok" + issk);
    }

    private void createData() {
        testdb = new MyDatabaseHelper(this, "Testdb.db", null, 1);
//        testdb.getWritableDatabase();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        saveText();
    }

    private void saveText() {
        String inputStr = etInput.getText().toString().trim();
        try {
            FileOutputStream fos = openFileOutput("testData", Context.MODE_PRIVATE);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos));
            try {
                writer.write(inputStr);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    writer.close();
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
