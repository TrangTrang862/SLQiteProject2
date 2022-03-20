package com.example.sqliteproject2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, "Hello.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table Information(id TEXT primary key, name TEXT, age TEXT, phone TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("drop Table if exists Information");
    }

    public Boolean insertuserdata(String id, String name, String age, String phone) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", id);
        contentValues.put("name", name);
        contentValues.put("age", age);
        contentValues.put("phone", phone);
        long result = DB.insert("Information", null, contentValues);
        if(result==-1) {
            return false;
        } else {
            return true;
        }
    }

    public Boolean updateuserdata(String id, String name, String age, String phone) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("age", age);
        contentValues.put("phone", phone);
        Cursor cursor = DB.rawQuery("Select * from Information where id = ?", new String[]{id});
        if(cursor.getCount()>0) {
            long result = DB.update("Information", contentValues, "id=?", new String[]{id});
            if(result==-1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    public Boolean deletedata(String id) {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Information where id = ?", new String[]{id});
        if(cursor.getCount()>0) {


            long result = DB.delete("Information", "id=?", new String[]{id});
            if(result==-1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    public Cursor getdata() {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Information", null);
        return cursor;
    }
}
