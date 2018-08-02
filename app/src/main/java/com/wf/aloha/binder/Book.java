package com.wf.aloha.binder;

import android.os.Parcel;
import android.os.Parcelable;

import kotlin.collections.BooleanIterator;

public class Book implements Parcelable {
    public int bookid;
    public String name;

    protected Book(Parcel in) {
        this.bookid = in.readInt();
        this.name = in.readString();
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(bookid);
        dest.writeString(name);
    }
}
