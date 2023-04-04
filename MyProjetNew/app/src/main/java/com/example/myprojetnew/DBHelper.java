package com.example.myprojetnew;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context){
        super(context,"main.db",null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("create Table users(id INTEGER primary key AUTOINCREMENT, mail TEXT, password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop Table if exists users");
    }

    public Boolean insertData(String mail, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("mail",mail);
        contentValues.put("password",password);
        long result = MyDB.insert("users",null,contentValues);
        if(result == -1) return false;
        else return true;
    }

    public Boolean checkUser(String mail, String password){
        SQLiteDatabase MyDB  = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("SELECT * FROM users WHERE mail = ? AND password = ?",new String[] {mail,password});
        if(cursor.getCount()>0){
            return true;
        }else return false;
    }

    public Boolean checkMail(String mail){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("SELECT * FROM users WHERE mail = ?",new String[] {mail});
        if(cursor.getCount()>0){
            return true;
        }else return false;
    }
}
