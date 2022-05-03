package com.example.asm_dam_tienbxph18636.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.asm_dam_tienbxph18636.DTO.ThanhVien;
import com.example.asm_dam_tienbxph18636.DTO.ThuThu;
import com.example.asm_dam_tienbxph18636.Database.Dbhelper;

import java.util.ArrayList;

public class ThanhVienDAO {
    SQLiteDatabase db;
    Dbhelper dbhelper;

    public ThanhVienDAO(Context context){
        dbhelper = new Dbhelper(context);
    }

    public void open(){
        db = dbhelper.getWritableDatabase();
    }

    public void close(){
        dbhelper.close();
    }

    public long insertTV(ThanhVien objThanhVien){
        ContentValues val = new ContentValues();
        val.put(ThanhVien.COL_NAME, objThanhVien.getTen_tv());
        val.put(ThanhVien.COL_SDT, objThanhVien.getSdt());

        long res = db.insert(ThanhVien.TB_NAME, null, val );
        return res;
    }

    public int deleteTV(ThanhVien objThanhVien){
        String[] arg = new String[]{objThanhVien.getId_tvien()+""};

        int res = db.delete(ThanhVien.TB_NAME, "id_tvien = ?", arg);
        return res;
    }

    public int updateTV(ThanhVien objThanhVien){
        ContentValues contentValues = new ContentValues();
        contentValues.put(ThanhVien.COL_NAME, objThanhVien.getTen_tv());
        contentValues.put(ThanhVien.COL_SDT, objThanhVien.getSdt());
        String[] val = new String[]{objThanhVien.getId_tvien()+""};

        return db.update(ThanhVien.TB_NAME, contentValues, "id_tvien = ?", val);
    }


    public ArrayList<ThanhVien> selectAll(){
        ArrayList<ThanhVien> arr = new ArrayList<>();

        Cursor cursor = db.query(ThanhVien.TB_NAME, null, null,null,null,null,null);
        if(cursor.moveToFirst()){
            while (!cursor.isAfterLast()){
                ThanhVien objThanhVien = new ThanhVien();
                objThanhVien.setId_tvien(cursor.getInt(0));
                objThanhVien.setTen_tv(cursor.getString(1));
                objThanhVien.setSdt(cursor.getString(2));
                arr.add(objThanhVien);

                cursor.moveToNext();
            }
        }
        return arr;
    }
}
