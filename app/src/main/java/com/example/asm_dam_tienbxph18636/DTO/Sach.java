package com.example.asm_dam_tienbxph18636.DTO;

public class Sach {
    int id_sach;
    int id_theLoai;
    String tenSach;
    String tenTheLoai;
    int giaSach;

    public static final String TB_NAME = "sach";
    public static final String COL_ID_TL = "id_theLoai";
    public static final String COL_TENSACH = "tenSach";
    public static final String COL_TENTHELOAI = "tenTheLoai";
    public static final String COL_GIASACH = "giaSach";

    public int getId_sach() {
        return id_sach;
    }

    public void setId_sach(int id_sach) {
        this.id_sach = id_sach;
    }

    public int getId_theLoai() {
        return id_theLoai;
    }

    public void setId_theLoai(int id_theLoai) {
        this.id_theLoai = id_theLoai;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public String getTenTheLoai() {
        return tenTheLoai;
    }

    public void setTenTheLoai(String tenTheLoai) {
        this.tenTheLoai = tenTheLoai;
    }

    public int getGiaSach() {
        return giaSach;
    }

    public void setGiaSach(int giaSach) {
        this.giaSach = giaSach;
    }
}
