package com.wf.aloha.ui;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

import com.wf.aloha.utils.ToastUtils;

public class DbHelper extends SQLiteOpenHelper {
    private static final String CREAT_SQL = "create table Book (" +
            "id integer primary key autoincrement," +
            "price real," +
            "author text," +
            "pages integer," +
            "name text)";

    public DbHelper( @Nullable Context context,  @Nullable String name,  @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREAT_SQL);
        ToastUtils.showInstance("db created!");
    }  
        


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }
}
