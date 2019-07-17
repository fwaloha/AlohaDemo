package com.wf.aloha.ui;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.wf.aloha.R;
import com.wf.aloha.utils.LogUtils;
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

public class Test3Activity extends AppCompatActivity {

    @BindView(R.id.et_input)
    EditText mEtInput;
    @BindView(R.id.bt_create)
    Button btCreate;
    @BindView(R.id.bt_retrive)
    Button btRetrive;
    @BindView(R.id.bt_update)
    Button btUpdate;
    @BindView(R.id.bt_delete)
    Button btDelete;
    @BindView(R.id.bt_start)
    Button btStart;
    private DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test3);
        ButterKnife.bind(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        read();
    }

    private void read() {
        StringBuilder sb = null;
        FileInputStream fis = null;
        BufferedReader reader = null;
        try {
            fis = openFileInput("data");
            reader = new BufferedReader(new InputStreamReader(fis));
            sb = new StringBuilder();
            String in;
            while ((in = reader.readLine()) != null) {
                sb.append(in);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (!TextUtils.isEmpty(sb)) {
                mEtInput.setText(sb.toString());
            }
            try {

                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        save();
    }

    private void save() {
        String inputStr = mEtInput.getText().toString();
        FileOutputStream fos = null;
        BufferedWriter bufferedWriter = null;
        try {
            fos = openFileOutput("data", MODE_PRIVATE);
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(fos));
            bufferedWriter.write(inputStr);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {

                if (bufferedWriter != null) {
                    bufferedWriter.close();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @OnClick({R.id.bt_create, R.id.bt_retrive, R.id.bt_update, R.id.bt_delete, R.id.bt_start})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_start:
                dbHelper = new DbHelper(this, "BookStore.db", null, 1);
                dbHelper.getWritableDatabase();
                break;
            case R.id.bt_create:
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues contentValues = new ContentValues();
                contentValues.put("price", 12.1);
                contentValues.put("author", "hahha");
                contentValues.put("pages", 100);
                contentValues.put("name", "wangchongyang");
                db.insert("Book", null, contentValues);
                contentValues.clear();
                contentValues.put("price", 40.1);
                contentValues.put("author", "lklklj");
                contentValues.put("pages", 20);
                contentValues.put("name", "qqqq");
                db.insert("Book", null, contentValues);
//                db.execSQL("insert into Book (price, name, pages, name) values(?,?,?,?)",new String[]{"11.2","dsd","33","dlask"});
//                db.execSQL("update Book set price = ? where name = ?",new String[]{"23","lksdl"});
//                db.execSQL("delete from Book where pages >?",new String[]{"20"});
//                db.rawQuery("select * from Book",null);
                break;
            case R.id.bt_retrive:
                SQLiteDatabase database1 = dbHelper.getWritableDatabase();
                Cursor cursor = database1.query("Book", null, null, null, null, null, null);
                if (cursor.moveToFirst()) {

                    do {
                        double price = cursor.getDouble(cursor.getColumnIndex("price"));
                        String author = cursor.getString(cursor.getColumnIndex("author"));
                        int page = cursor.getInt(cursor.getColumnIndex("pages"));
                        String name = cursor.getString(cursor.getColumnIndex("name"));
                        LogUtils.d("-----", price + "  " + author + "  " + page + "  " + name);
                    } while (cursor.moveToNext());
                }
                cursor.close();
                break;
            case R.id.bt_update:
                SQLiteDatabase db2 = dbHelper.getWritableDatabase();
                ContentValues contentValues1 = new ContentValues();
                contentValues1.put("price", 99);
                int book = db2.update("Book", contentValues1, "price < ?", new String[]{"13"});
                if (book > 0) {
                    ToastUtils.showInstance("success");
                }
                break;
            case R.id.bt_delete:
                SQLiteDatabase db3 = dbHelper.getWritableDatabase();
                int book1 = db3.delete("Book", "price < ?", new String[]{"13"});
                ToastUtils.showInstance("deleted line" + book1);
                break;
        }
    }
}
