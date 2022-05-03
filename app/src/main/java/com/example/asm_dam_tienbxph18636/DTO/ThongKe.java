package com.example.asm_dam_tienbxph18636.DTO;

public class ThongKe {

//    SELECT tenSach, count (tenSach) as  soLuong FROM phieuMuon
//    GROUP BY tenSach ORDER BY
//    soLuong desc
//    LIMIT 10;
    String tenSach;
    int soLuong;

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
}
