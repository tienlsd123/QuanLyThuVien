package com.example.asm_dam_tienbxph18636.DTO;

public class TheLoai {
    int id_theLoai;
    String tenTheLoai;

    public static final String TB_NAME ="theLoai";
    public static final String COL_NAME ="tenTheLoai";


    public int getId_theLoai() {
        return id_theLoai;
    }

    public void setId_theLoai(int id_theLoai) {
        this.id_theLoai = id_theLoai;
    }

    public String getTenTheLoai() {
        return tenTheLoai;
    }

    public void setTenTheLoai(String tenTheLoai) {
        this.tenTheLoai = tenTheLoai;
    }
}
