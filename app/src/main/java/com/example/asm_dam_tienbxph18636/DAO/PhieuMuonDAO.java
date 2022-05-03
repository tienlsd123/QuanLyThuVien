package com.example.asm_dam_tienbxph18636.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.asm_dam_tienbxph18636.DTO.PhieuMuon;
import com.example.asm_dam_tienbxph18636.DTO.Sach;
import com.example.asm_dam_tienbxph18636.DTO.ThongKe;
import com.example.asm_dam_tienbxph18636.Database.Dbhelper;

import java.util.ArrayList;
import java.util.Date;

public class PhieuMuonDAO {
    Dbhelper dbhelper;
    SQLiteDatabase db;

    public PhieuMuonDAO(Context context) {
        dbhelper = new Dbhelper(context);
    }

    public void open(){
        db = dbhelper.getWritableDatabase();
    }

    public void close(){
        dbhelper.close();
    }

    public long insertPm(PhieuMuon objPhieuMuon){
        ContentValues val = new ContentValues();
        val.put(PhieuMuon.COL_ID_SACH, objPhieuMuon.getId_sach());
        val.put(PhieuMuon.COL_TEN_SACH, objPhieuMuon.getTenSach());
        val.put(PhieuMuon.COL_NGAYMUON, objPhieuMuon.getNgayMuon());
        val.put(PhieuMuon.COL_GIAPHIEU, objPhieuMuon.getGiaPhieu());
        val.put(PhieuMuon.COL_ID_TV, objPhieuMuon.getId_tvien());
        val.put(PhieuMuon.COL_TEN_TV, objPhieuMuon.getTen_tv());

        long res = db.insert(PhieuMuon.TB_NAME, null, val);
        return res;
    }

    public int deletePm(PhieuMuon objPhieuMuon){
        String[] arg = new String[]{objPhieuMuon.getId_phieuMuon()+""};

        int res = db.delete(PhieuMuon.TB_NAME, "id_phieuMuon = ?", arg);
        return res;
    }

    public int updateSach(PhieuMuon objPhieuMuon){
        ContentValues val = new ContentValues();
        val.put(PhieuMuon.COL_ID_SACH, objPhieuMuon.getId_sach());
        val.put(PhieuMuon.COL_TEN_SACH, objPhieuMuon.getTenSach());
        val.put(PhieuMuon.COL_NGAYMUON, objPhieuMuon.getNgayMuon());
        val.put(PhieuMuon.COL_GIAPHIEU, objPhieuMuon.getGiaPhieu());
        val.put(PhieuMuon.COL_ID_TV, objPhieuMuon.getId_tvien());
        val.put(PhieuMuon.COL_TEN_TV, objPhieuMuon.getTen_tv());


        String[] arg = new String[]{objPhieuMuon.getId_phieuMuon()+""};

        return db.update(PhieuMuon.TB_NAME, val, "id_phieuMuon = ?", arg);
    }


    public ArrayList<PhieuMuon> selectAll(){
        ArrayList<PhieuMuon> arr = new ArrayList<>();

        Cursor cursor = db.query(PhieuMuon.TB_NAME, null, null,null,null,null,null);
        if(cursor.moveToFirst()){
            while (!cursor.isAfterLast()){
                PhieuMuon objPhieuMuon = new PhieuMuon();

                objPhieuMuon.setId_phieuMuon(cursor.getInt(0));
                objPhieuMuon.setId_sach(cursor.getInt(1));
                objPhieuMuon.setId_tvien(cursor.getInt(2));
                objPhieuMuon.setTenSach(cursor.getString(3));
                objPhieuMuon.setTen_tv(cursor.getString(4));
                objPhieuMuon.setNgayMuon(cursor.getString(5));
                objPhieuMuon.setGiaPhieu(cursor.getInt(6));
                arr.add(objPhieuMuon);

                cursor.moveToNext();
            }
        }
        return arr;
    }

    public ArrayList<ThongKe> thongKeSach(){
        ArrayList<ThongKe> arr = new ArrayList<>();

        Cursor cursor = db.rawQuery(" SELECT tenSach, count (tenSach) as  soLuong FROM phieuMuon GROUP BY tenSach ORDER BY soLuong desc LIMIT 10",null);
        if(cursor.moveToFirst()){
            while (!cursor.isAfterLast()){
                ThongKe objThongKe = new ThongKe();
                objThongKe.setTenSach(cursor.getString(0));
                objThongKe.setSoLuong(cursor.getInt(1));
                arr.add(objThongKe);

                cursor.moveToNext();
            }
        }

        return arr;
    }

    public int doanhThu(String tungay, String denngay){
        ArrayList<Integer> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(" SELECT sum(giaPhieu) as doanhThu FROM PhieuMuon where ngayMuon BETWEEN ? AND ? ", new String[]{tungay, denngay});
        while (cursor.moveToNext()){
            try{
                list.add(cursor.getInt(0));
            }catch (Exception e){
                list.add(0);
            }
        }
        return list.get(0);
    }

}
