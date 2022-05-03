package com.example.asm_dam_tienbxph18636.DTO;

public class ThanhVien {
    int id_tvien;
    String ten_tv;
    String sdt;

    public static final String TB_NAME = "thanhVien";
    public static final String COL_NAME = "ten_tv";
    public static final String COL_SDT = "sdt";

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

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }
}
