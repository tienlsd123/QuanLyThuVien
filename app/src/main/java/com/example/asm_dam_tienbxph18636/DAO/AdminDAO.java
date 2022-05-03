package com.example.asm_dam_tienbxph18636.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.asm_dam_tienbxph18636.DTO.Admin;
import com.example.asm_dam_tienbxph18636.Database.Dbhelper;

import java.util.ArrayList;

public class AdminDAO {
    SQLiteDatabase db;
    Dbhelper dbhelper;

    public AdminDAO(Context context) {
        dbhelper = new Dbhelper(context);
    }

    public void open(){
        db = dbhelper.getWritableDatabase();
    }

    public void close(){
        db.close();
    }

    public long insertAdmin(Admin objAdmin){
        ContentValues val = new ContentValues();
        val.put(Admin.COL_TK, objAdmin.getTaiKhoanAdmin());
        val.put(Admin.COL_MK, objAdmin.getMatKhauAdmin());

        long res = db.insert(Admin.TB_NAME, null, val );
        return res;
    }

    public int updateAdmin(Admin objAdmin){
        ContentValues contentValues = new ContentValues();
        contentValues.put(Admin.COL_MK, objAdmin.getMatKhauAdmin());
        String[] val = new String[]{objAdmin.getId_admin()+""};

        return db.update(Admin.TB_NAME, contentValues, "id_admin = ?", val);
    }

    public Admin selectAd(){
        Admin objAdmin = new Admin();
        Cursor cursor = db.query(Admin.TB_NAME, null, null,null,null,null,null);
        if(cursor.moveToFirst()){
            while (!cursor.isAfterLast()){
                objAdmin.setId_admin(cursor.getInt(0));
                objAdmin.setTaiKhoanAdmin(cursor.getString(1));
                objAdmin.setMatKhauAdmin(cursor.getString(2));
                cursor.moveToNext();
            }
        }
        return objAdmin;
    }
}
