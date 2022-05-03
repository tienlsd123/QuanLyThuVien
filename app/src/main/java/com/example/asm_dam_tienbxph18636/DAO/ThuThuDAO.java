package com.example.asm_dam_tienbxph18636.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.asm_dam_tienbxph18636.DTO.Admin;
import com.example.asm_dam_tienbxph18636.DTO.ThuThu;
import com.example.asm_dam_tienbxph18636.Database.Dbhelper;

import java.util.ArrayList;

public class ThuThuDAO {
    SQLiteDatabase db;
    Dbhelper dbhelper;

    public ThuThuDAO(Context context){
        dbhelper = new Dbhelper(context);
    }

    public void open(){
        db = dbhelper.getWritableDatabase();
    }

    public void close(){
        dbhelper.close();
    }

    public long insertThuThu(ThuThu objThuThu){
        ContentValues val = new ContentValues();
        val.put(ThuThu.COL_TK, objThuThu.getTaiKhoanThuThu());
        val.put(ThuThu.COL_MK, objThuThu.getMatKhauThuThu());

        long res = db.insert(ThuThu.TB_NAME, null, val );
        return res;
    }

    public int deleteThuThu(ThuThu objThuThu){
        String[] arg = new String[]{objThuThu.getId_thuThu()+""};

        int res = db.delete(ThuThu.TB_NAME, "id_thuThu = ?", arg);
        return res;
    }

    public int updateThuThu(ThuThu objThuThu){
        ContentValues contentValues = new ContentValues();
        contentValues.put(ThuThu.COL_TK, objThuThu.getTaiKhoanThuThu());
        contentValues.put(ThuThu.COL_MK, objThuThu.getMatKhauThuThu());
        String[] val = new String[]{objThuThu.getId_thuThu()+""};

        return db.update(ThuThu.TB_NAME, contentValues, "id_thuThu = ?", val);
    }


    public ArrayList<ThuThu> selectAll(){
        ArrayList<ThuThu> arr = new ArrayList<>();

        Cursor cursor = db.query(ThuThu.TB_NAME, null, null,null,null,null,null);
        if(cursor.moveToFirst()){
            while (!cursor.isAfterLast()){
                ThuThu objThuThu = new ThuThu();
                objThuThu.setId_thuThu(cursor.getInt(0));
                objThuThu.setTaiKhoanThuThu(cursor.getString(1));
                objThuThu.setMatKhauThuThu(cursor.getString(2));
                arr.add(objThuThu);

                cursor.moveToNext();
            }
        }
        return arr;
    }
}
