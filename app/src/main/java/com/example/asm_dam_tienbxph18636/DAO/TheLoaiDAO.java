package com.example.asm_dam_tienbxph18636.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.asm_dam_tienbxph18636.DTO.ThanhVien;
import com.example.asm_dam_tienbxph18636.DTO.TheLoai;
import com.example.asm_dam_tienbxph18636.Database.Dbhelper;

import java.util.ArrayList;

public class TheLoaiDAO {
    SQLiteDatabase db;
    Dbhelper dbhelper;

    public TheLoaiDAO(Context context){
        dbhelper = new Dbhelper(context);
    }

    public void open(){
        db = dbhelper.getWritableDatabase();
    }

    public void close(){
        dbhelper.close();
    }

    public long insertTL(TheLoai objTheLoai){
        ContentValues val = new ContentValues();
        val.put(TheLoai.COL_NAME, objTheLoai.getTenTheLoai());

        long res = db.insert(TheLoai.TB_NAME, null, val );
        return res;
    }

    public int deleteTL(TheLoai objTheLoai){
        String[] arg = new String[]{objTheLoai.getId_theLoai()+""};

        int res = db.delete(TheLoai.TB_NAME, "id_theLoai = ?", arg);
        return res;
    }

    public int updateTL(TheLoai objTheLoai){
        ContentValues contentValues = new ContentValues();
        contentValues.put(TheLoai.COL_NAME, objTheLoai.getTenTheLoai());
        String[] val = new String[]{objTheLoai.getId_theLoai()+""};

        return db.update(TheLoai.TB_NAME, contentValues, "id_theLoai = ?", val);
    }


    public ArrayList<TheLoai> selectAll(){
        ArrayList<TheLoai> arr = new ArrayList<>();

        Cursor cursor = db.query(TheLoai.TB_NAME, null, null,null,null,null,null);
        if(cursor.moveToFirst()){
            while (!cursor.isAfterLast()){
                TheLoai objTheLoai = new TheLoai();
                objTheLoai.setId_theLoai(cursor.getInt(0));
                objTheLoai.setTenTheLoai(cursor.getString(1));
                arr.add(objTheLoai);

                cursor.moveToNext();
            }
        }
        return arr;
    }
}
