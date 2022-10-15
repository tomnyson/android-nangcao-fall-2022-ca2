package com.example.androidnangcaoca2.provider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    static  final  String DB_NAME="QLSinhVien";
    static  final  String TB_NAME="sinhvien";
    static final int DATABASE_VERSION = 1;

    static final String CREATE_DB_TABLE =
            " CREATE TABLE " + TB_NAME +
                    "( massv TEXT PRIMARY KEY  NOT NULL," +
                    "ten TEXT NOT NULL, " +
                    "diemtb TEXT NOT NULL);";
    public DBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_DB_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table  IF EXISTS sinhvien");
        db.execSQL(CREATE_DB_TABLE);
    }
}
