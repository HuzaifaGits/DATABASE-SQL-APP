package com.example.databaseapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper
{



    public DBHelper(Context context)
    {
        super(context, "Student.DB", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table Userdetails (regid integer primary key AUTOINCREMENT ,name TEXT , contact integer,semester integer,degree TEXT,dob integer,contact integer,cnic integer)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int ii) {
        DB.execSQL("drop table if exists Userdetails");
    }
    public Boolean insertuserdata(String name, String regid, String semester,String cnic,String dob,String contact,String degree)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("regid", regid);
        contentValues.put("semester", semester);
        contentValues.put("cnic", cnic);
        contentValues.put("dob", dob);
        contentValues.put("contact", contact);
        contentValues.put("degree", degree);


        long result=DB.insert("Student", null, contentValues);
        if(result==-1){
            return false;
        }else{
            return true;
        }
    }

    public Cursor getdata ()
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Userdetails", null);
        return cursor;
    }
}
