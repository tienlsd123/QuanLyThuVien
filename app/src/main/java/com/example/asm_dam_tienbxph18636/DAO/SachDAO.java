package com.example.asm_dam_tienbxph18636.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.asm_dam_tienbxph18636.DTO.Sach;
import com.example.asm_dam_tienbxph18636.DTO.ThanhVien;
import com.example.asm_dam_tienbxph18636.Database.Dbhelper;

import java.util.ArrayList;

public class SachDAO {
    SQLiteDatabase db;
    Dbhelper dbhelper;

    public SachDAO(Context context){
        dbhelper = new Dbhelper(context);
    }

    public void open(){
        db = dbhelper.getWritableDatabase();
    }

    public void close(){
        dbhelper.close();
    }

    public long insertSach(Sach objSach){
        ContentValues val = new ContentValues();
        val.put(Sach.COL_ID_TL, objSach.getId_theLoai());
        val.put(Sach.COL_TENSACH, objSach.getTenSach());
        val.put(Sach.COL_TENTHELOAI, objSach.getTenTheLoai());
        val.put(Sach.COL_GIASACH, objSach.getGiaSach());

        long res = db.insert(Sach.TB_NAME, null, val);
        return res;
    }

    public int deleteSach(Sach objSach){
        String[] arg = new String[]{objSach.getId_sach()+""};

        int res = db.delete(Sach.TB_NAME, "id_sach = ?", arg);
        return res;
    }

    public int updateSach(Sach objSach){
        ContentValues val = new ContentValues();
        val.put(Sach.COL_ID_TL, objSach.getId_theLoai());
        val.put(Sach.COL_TENSACH, objSach.getTenSach());
        val.put(Sach.COL_TENTHELOAI, objSach.getTenTheLoai());
        val.put(Sach.COL_GIASACH, objSach.getGiaSach());

        String[] arg = new String[]{objSach.getId_sach()+""};

        return db.update(Sach.TB_NAME, val, "id_sach = ?", arg);
    }


    public ArrayList<Sach> selectAll(){
        ArrayList<Sach> arr = new ArrayList<>();

        Cursor cursor = db.query(Sach.TB_NAME, null, null,null,null,null,null);
        if(cursor.moveToFirst()){
            while (!cursor.isAfterLast()){
                Sach objSach = new Sach();

                objSach.setId_sach(cursor.getInt(0));
                objSach.setId_theLoai(cursor.getInt(1));
                objSach.setTenSach(cursor.getString(2));
                objSach.setTenTheLoai(cursor.getString(3));
                objSach.setGiaSach(cursor.getInt(4));
                arr.add(objSach);

                cursor.moveToNext();
            }
        }
        return arr;
    }
}
