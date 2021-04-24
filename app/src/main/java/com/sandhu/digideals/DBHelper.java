package com.sandhu.digideals;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public static final int version = 3;
    public static final String DBNAME = "DigiDeals.db";
    public static final String USERS = "users";

    public static final String itemTableName = "Items";
    public static final String cartTableName = "cart";

    //create a string variable to store create table query
    private static final String createTableITEMS = "CREATE TABLE Items (itemId INTEGER PRIMARY KEY AUTOINCREMENT, itemImage BLOB, itemName TEXT, itemDesc TEXT, itemPrice FLOAT);";

    //crete a string to store drop table command
    private static final String dropTableITEMS = " DROP TABLE IF EXISTS " + itemTableName;
    private static final String dropTableCART = " DROP TABLE IF EXISTS " + cartTableName;


    public DBHelper(Context context) {
        super(context, DBNAME, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table users(username TEXT primary key, password TEXT, name TEXT)");
        db.execSQL(createTableITEMS);
        db.execSQL("create table cart(cartid INTEGER PRIMARY KEY AUTOINCREMENT,itemName TEXT,itemQty TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists users");
        db.execSQL(dropTableITEMS);
        db.execSQL(dropTableCART);
        onCreate(db);
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

    //Insert Items description to table
    public boolean InsertItems(byte[] itemImage, String itemName,String itemDesc, Float itemPrice){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues cvObj = new ContentValues();
        cvObj.put("itemImage",itemImage);
        cvObj.put("itemName",itemName);
        cvObj.put("itemDesc",itemDesc);
        cvObj.put("itemPrice",itemPrice);

        long result = sqLiteDatabase.insert(itemTableName,null,cvObj);
        if(result == -1)
            return false;
        else
            return true;
    }

    //Insert into cart table
    public boolean InsertIntoCart(String name,String productqty){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues cvObj = new ContentValues();
        cvObj.put("itemName",name);
        cvObj.put("itemQty",productqty);

        long result = sqLiteDatabase.insert(cartTableName,null,cvObj);
        if(result == -1)
            return false;
        else
            return true;
    }


    public Cursor getData(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String select = "SELECT * FROM Items";
        Cursor cursorObj = sqLiteDatabase.rawQuery(select,null);
        if(cursorObj != null)
            cursorObj.moveToFirst();
        return cursorObj;
    }

    public Cursor getItemData(String name){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String select = "";
        Cursor cursorObj = sqLiteDatabase.rawQuery("SELECT * FROM Items WHERE itemName = ?",new String[]{name});
        if(cursorObj != null)
            cursorObj.moveToFirst();
        return cursorObj;
    }

}
