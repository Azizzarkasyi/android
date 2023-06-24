package com.aziz.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.BoringLayout;
import android.util.Log;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

public class database  extends SQLiteOpenHelper {

    public static final String databasename ="jajal.db";

    public database(@Nullable Context context) {
        super(context, databasename, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase myDatabase) {
        myDatabase.execSQL("create Table coba (username TEXT primary key,email TEXT ,password TEXT )");
        myDatabase.execSQL("create Table mahasiswa(nama TEXT primary key,npm TEXT ,tanggal TEXT)");


    }

    @Override
    public void onUpgrade(SQLiteDatabase myDatabase, int i, int i1) {
        myDatabase.execSQL("drop Table if exists coba");
        myDatabase.execSQL("drop Table if exists mahasiswa");


    }
    public Boolean  insertdata(String email, String username,String password){
        SQLiteDatabase myDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email",email);
        contentValues.put("username",username);
        contentValues.put("password",password);
        long result = myDatabase.insert("coba",null,contentValues);

        if (result == -1){
            return false;
        }else {
            return true;
        }
    }
    public  Boolean checkEmail(String email){
        SQLiteDatabase myDatabase = this.getWritableDatabase();
        Cursor cursor = myDatabase.rawQuery("Select * from coba where email = ?",new String[]{email});

        if (cursor.getCount() > 0){
            return true;
        }else {
            return false;
        }
    }
    public Boolean checkEmailPassword(String username, String password){
        SQLiteDatabase myDatabase = this.getWritableDatabase();
        Cursor cursor = myDatabase.rawQuery("Select * from coba where username =? and password =?",new String[]{username,password});

        if (cursor.getCount()> 0){
            return true;
        }else {
            return false;
        }

    }

    public Boolean insertdatamahasiswa(String nama, String npm,String tanggal){
        SQLiteDatabase myDatabase= this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("nama",nama);
        contentValues.put("npm",npm);
        contentValues.put("tanggal",tanggal);
        long result = myDatabase.insert("mahasiswa",null,contentValues);

        if (result == -1){
            return true;
        }else {
            return false;
        }

    }
    public Boolean editdatamahasiswa(String nama, String npm,String tanggal){
        SQLiteDatabase myDatabase= this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("npm",npm);
        contentValues.put("tanggal",tanggal);
        Cursor cursor = myDatabase.rawQuery("select * from mahasiswa where nama=?",new String[]{nama});
        if (cursor.getCount()>0){
        long result = myDatabase.update("mahasiswa",contentValues,"nama=?",new String[]{nama});

        if (result == -1){
            return false;
        }else {
            return true;
        }
        }else {
            return false;
        }


    }
}
