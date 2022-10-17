package com.example.appsuperseguro;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {
    public AdminSQLiteOpenHelper(Context context) {
        super(context, "taxi.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase BaseDeDatos) {
        BaseDeDatos.execSQL("create table usuarios(id int primary key, name text, email text, phone text, password text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop Table if exists usuarios");
    }

    public Boolean insertData(String name, String email, String phone, String password){
        SQLiteDatabase admin = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("name", name);
        contentValues.put("email", email);
        contentValues.put("phone", phone);
        contentValues.put("password", password);
        long result = admin.insert("usuarios", null, contentValues);
        if(result==-1) return false;
        else
            return true;
    }

    public Boolean checkEmail(String email) {
        SQLiteDatabase admin = this.getWritableDatabase();
        Cursor cursor = admin.rawQuery("select * from usuarios where email = ?", new String[]{email});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public Boolean checkUser(String email, String password){
        SQLiteDatabase admin = this.getWritableDatabase();
        Cursor cursor = admin.rawQuery("select * from usuarios where email = ? and password = ?", new String[] {email,password});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }
}
