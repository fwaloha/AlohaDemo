package com.wf.aloha.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import com.wf.aloha.ui.DbHelper;

public class DataProvider extends ContentProvider {


    private static final String AUTHORITY = "com.wf.aloha.provider";

    private static final int BOOK_DIR = 0;
    private static UriMatcher uriMatcher;

    private static final int BOOK_ITEM = 1;

    private static final int CATEGORY_DIR = 2;

    private static final int CATEGORY_ITEM = 3;
    private final Context mContext;


    private DbHelper dbHelper;

    public DataProvider() {
        this.mContext = getContext();
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY, "book", BOOK_DIR);
        uriMatcher.addURI(AUTHORITY, "book", BOOK_ITEM);
        uriMatcher.addURI(AUTHORITY, "category", CATEGORY_DIR);
        uriMatcher.addURI(AUTHORITY, "category", CATEGORY_ITEM);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public String getType(Uri uri) {
        switch (uriMatcher.match(uri)){
            case BOOK_DIR:
                return "vnd.com.wf.aloha.cursor.dir/com.wf.aloha.provider.book";
            case BOOK_ITEM:
                return "vnd.com.wf.aloha.cursor.item/com.wf.aloha.provider.book";
            case CATEGORY_DIR:
                return "vnd.com.wf.aloha.cursor.dir/com.wf.aloha.provider.category";
            case CATEGORY_ITEM:
                return "vnd.com.wf.aloha.cursor.item/com.wf.aloha.provider.category";
        }
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public boolean onCreate() {
        dbHelper = new DbHelper(mContext, "BookStore.db", null, 2);
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        SQLiteDatabase database = dbHelper.getReadableDatabase();

//        throw new UnsupportedOperationException("Not yet implemented");
        switch (uriMatcher.match(uri)){
            case BOOK_DIR:
                Cursor cursor = database.query("book", projection, selection, selectionArgs, null, null, sortOrder);
                return cursor;
            case BOOK_ITEM:
                String id = uri.getPathSegments().get(1);
                database.query("book",projection,"id = ?",new String[]{id},null,null,sortOrder);
                break;
            case CATEGORY_DIR:
                break;
            case CATEGORY_ITEM:
                break;
        }
        return null;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
