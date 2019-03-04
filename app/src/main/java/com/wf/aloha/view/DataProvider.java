package com.wf.aloha.view;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import com.wf.aloha.database.MyDatabaseHelper;

/**
 * 内容提供器 让其他app访问其数据
 */
public class DataProvider extends ContentProvider {

    private static final int BOOK_DIR = 0;
    private static final int BOOK_ITEM = 1;
    private static final int CATEGORY_DIR = 3;
    private static final int CATEGORY_ITEM = 4;
    private final UriMatcher uriMatcher;
    private final String AUTHORITY;
    private MyDatabaseHelper dbHelper;

    public DataProvider() {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        AUTHORITY = "com.wf.aloha.provider";
        uriMatcher.addURI(AUTHORITY, "Book", BOOK_DIR);
        uriMatcher.addURI(AUTHORITY, "Book/#", BOOK_ITEM);
        uriMatcher.addURI(AUTHORITY, "category", CATEGORY_DIR);
        uriMatcher.addURI(AUTHORITY, "category/#", CATEGORY_ITEM);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int deleteRows = 0;
        switch (uriMatcher.match(uri)) {
            case BOOK_DIR:
                deleteRows = db.delete("Book", selection, selectionArgs);
                break;
            case BOOK_ITEM:
                String id = uri.getPathSegments().get(1);
                deleteRows = db.delete("Book", "id=?", new String[]{id});
                break;
            case CATEGORY_DIR:
                deleteRows = db.delete("Category", selection, selectionArgs);
                break;
            case CATEGORY_ITEM:
                String id2 = uri.getPathSegments().get(1);
                deleteRows = db.delete("Category", "id=?", new String[]{id2});
                break;
        }
        return deleteRows;
    }

    @Override
    public String getType(Uri uri) {
        switch (uriMatcher.match(uri)) {
            case BOOK_DIR:
                return "vnd.android.cursor.dir/vnd.com.wf.aloha.provider.book";
            case BOOK_ITEM:
                return "vnd.android.cursor.item/vnd.com.wf.aloha.provider.book";
            case CATEGORY_DIR:
                return "vnd.android.cursor.dir/vnd.com.wf.aloha.provider.category";
            case CATEGORY_ITEM:
                return "vnd.android.cursor.item/vnd.com.wf.aloha.provider.category";
            default:
                return "";
        }
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
//        throw new UnsupportedOperationException("Not yet implemented");
        Uri returnUri = null;
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        switch (uriMatcher.match(uri)) {
            case BOOK_DIR:
            case BOOK_ITEM:
                long book = db.insert("Book", null, values);
                returnUri = Uri.parse("content://" + AUTHORITY + "/Book/" + book);
                break;
            case CATEGORY_DIR:
            case CATEGORY_ITEM:
                long category = db.insert("category", null, values);
                returnUri = Uri.parse("content://" + AUTHORITY + "/category/" + category);
                break;
        }
        return returnUri;
    }

    @Override
    public boolean onCreate() {
        dbHelper = new MyDatabaseHelper(getContext(), "TestBook.db", null, 1);
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
//        throw new UnsupportedOperationException("Not yet implemented");
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = null;
        switch (uriMatcher.match(uri)) {
            case BOOK_DIR:
                cursor = db.query("Book", projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case BOOK_ITEM:
                String bookId = uri.getPathSegments().get(1);
                cursor = db.query("Book", projection, "id=?", new String[]{bookId}, null, null, sortOrder);
                break;
            case CATEGORY_DIR:
                cursor = db.query("Category", projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case CATEGORY_ITEM:
                String categoryId = uri.getPathSegments().get(1);
                cursor = db.query("Category", projection, "id=?", new String[]{categoryId}, null, null, sortOrder);
                break;
        }
        return cursor;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int updateRows = 0;
        switch (uriMatcher.match(uri)) {
            case BOOK_DIR:
                updateRows = db.update("Book", values, selection, selectionArgs);
                break;
            case BOOK_ITEM:
                String s = uri.getPathSegments().get(1);
                updateRows = db.update("Book", values, "id=?", new String[]{s});
                break;
            case CATEGORY_DIR:
                updateRows = db.update("Category", values, selection, selectionArgs);
                break;
            case CATEGORY_ITEM:
                String s1 = uri.getPathSegments().get(1);
                updateRows = db.update("Category", values, "id=?", new String[]{s1});
                break;
        }
        return updateRows;
    }
}
