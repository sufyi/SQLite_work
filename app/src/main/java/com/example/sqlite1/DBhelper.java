package com.example.sqlite1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBhelper extends SQLiteOpenHelper {
    public DBhelper(Context context) {
        super(context, "userdata.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create Table userdetails(name Text primary key,contact Text,dob Text);");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("Drop Table if exists userdetails");

    }

    public Boolean insertuserdata(String name, String contact, String dob) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentvalues = new ContentValues();
        contentvalues.put("name", name);
        contentvalues.put("contact", contact);
        contentvalues.put("dob", dob);
        Long result = db.insert("userDetails", null, contentvalues);
        if (result == -1) {
            return false;
        } else
            return true;
    }

    public Boolean updateuserdata(String name, String contact, String dob) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentvalues = new ContentValues();
        contentvalues.put("contact", contact);
        contentvalues.put("dob", dob);
        Cursor cursor = db.rawQuery("select * from userdetails where name = ?", new String[]{name});
        if (cursor.getCount() > 0) {
            long result = db.update("userdetails", contentvalues, "name=?", new String[]{name});
            if (result == -1) {
                return false;
            } else {
                return true;
            }}else {
            return true;
        }
    }
    public Boolean deletedata(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from userdetails where name = ?", new String[]{name});
        if (cursor.getCount() > 0) {
            long result = db.delete("userdetails", "name=?", new String[]{name});
            if (result == -1) {
                return false;
            } else {
                return true;
            }}else {
            return true;
        }
    }
    public Cursor getdata(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from userdetails",null);
        return cursor;
    }
}

