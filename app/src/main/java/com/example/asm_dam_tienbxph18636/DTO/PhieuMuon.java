package com.example.asm_dam_tienbxph18636.DTO;

public class PhieuMuon {
    int id_phieuMuon;
    int id_sach;
    int id_tvien;
    String tenSach;
    String ten_tv;
    String ngayMuon;
    int giaPhieu;

    public static final String TB_NAME = "phieuMuon";
    public static final String COL_ID_PM = "id_phieuMuon";
    public static final String COL_ID_TV = "id_tvien";
    public static final String COL_TEN_TV = "ten_tv";
    public static final String COL_ID_SACH = "id_sach";
    public static final String COL_TEN_SACH = "tenSach";
    public static final String COL_NGAYMUON = "ngayMuon";
    public static final String COL_GIAPHIEU = "giaPhieu";

    public int getId_tvien() {
        return id_tvien;
    }

    public void setId_tvien(int id_tvien) {
        this.id_tvien = id_tvien;
    }

    public String getTen_tv() {
        return ten_tv;
    }

    public void setTen_tv(String ten_tv) {
        this.ten_tv = ten_tv;
    }

    public int getId_phieuMuon() {
        return id_phieuMuon;
    }

    public void setId_phieuMuon(int id_phieuMuon) {
        this.id_phieuMuon = id_phieuMuon;
    }

    public int getId_sach() {
        return id_sach;
    }

    public void setId_sach(int id_sach) {
        this.id_sach = id_sach;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public String getNgayMuon() {
        return ngayMuon;
    }

    public void setNgayMuon(String ngayMuon) {
        this.ngayMuon = ngayMuon;
    }

    public int getGiaPhieu() {
        return giaPhieu;
    }

    public void setGiaPhieu(int giaPhieu) {
        this.giaPhieu = giaPhieu;
    }
}
