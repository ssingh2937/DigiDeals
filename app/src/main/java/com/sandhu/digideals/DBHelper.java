package com.sandhu.digideals;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DBNAME = "DigiDeals.db";
    public static final String USERS = "users";

    public DBHelper(Context context) {
        super(context, DBNAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table users(username TEXT primary key, password TEXT, name TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists users");
    }

    public boolean registerUser(String name, String username, String password){
        SQLiteDatabase myDb = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("username", username);
        contentValues.put("password", password);
        long result = myDb.insert(USERS, null, contentValues);

        return result != -1;
    }

    public boolean checkUsername(String username){
        SQLiteDatabase myDb = this.getWritableDatabase();

        Cursor cursor = myDb.rawQuery("Select * from users where username = ?", new String[]{username});
        return cursor.getCount() > 0;
    }

    public boolean checkUsernamePassword(String username, String password){
        SQLiteDatabase myDb = this.getWritableDatabase();

        Cursor cursor = myDb.rawQuery("Select * from users where username = ? and password = ?",new String[]{username, password});
        return cursor.getCount() > 0;
    }

}
