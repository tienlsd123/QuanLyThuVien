package com.example.asm_dam_tienbxph18636.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Dbhelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "sqltv";
    public static final int DB_VERSION = 1;

    public Dbhelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String sql = "CREATE TABLE thanhVien ( id_tvien INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT , ten_tv TEXT NOT NULL, sdt TEXT NOT NULL UNIQUE )";
        sqLiteDatabase.execSQL(sql);

        sql = "CREATE TABLE admin ( id_admin INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, taiKhoanAdmin TEXT NOT NULL, matKhauAdmin TEXT);";
        sqLiteDatabase.execSQL(sql);

        sql = "CREATE TABLE thuThu ( id_thuThu INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, taiKhoanThuThu TEXT NOT NULL, matKhauThuThu TEXT);";
        sqLiteDatabase.execSQL(sql);

        sql = "CREATE TABLE theLoai ( id_theLoai INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, tenTheLoai TEXT NOT NULL)";
        sqLiteDatabase.execSQL(sql);

        sql = "CREATE TABLE sach (id_sach INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, id_theLoai INTEGER NOT NULL, tenSach TEXT NOT NULL, tenTheLoai TEXT NOT NULL, giaSach INTEGER NOT NULL)";
        sqLiteDatabase.execSQL(sql);

        sql = "CREATE TABLE phieuMuon (id_phieuMuon INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT , id_sach INTEGER NOT NULL, id_tvien INTEGER NOT NULL, tenSach TEXT NOT NULL, ten_tv TEXT NOT NULL, ngayMuon DATE NOT NULL, giaPhieu INTEGER NOT NULL )";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
