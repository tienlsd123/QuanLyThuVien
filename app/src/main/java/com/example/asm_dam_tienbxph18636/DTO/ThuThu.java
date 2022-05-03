package com.example.asm_dam_tienbxph18636.DTO;

public class ThuThu {
    public int id_thuThu;
    public String taiKhoanThuThu;
    public String matKhauThuThu;

    public static final String TB_NAME = "thuThu";
    public static final String COL_TK = "taiKhoanThuThu";
    public static final String COL_MK = "matKhauThuThu";


    public int getId_thuThu() {
        return id_thuThu;
    }

    public void setId_thuThu(int id_thuThu) {
        this.id_thuThu = id_thuThu;
    }

    public String getTaiKhoanThuThu() {
        return taiKhoanThuThu;
    }

    public void setTaiKhoanThuThu(String taiKhoanThuThu) {
        this.taiKhoanThuThu = taiKhoanThuThu;
    }

    public String getMatKhauThuThu() {
        return matKhauThuThu;
    }

    public void setMatKhauThuThu(String matKhauThuThu) {
        this.matKhauThuThu = matKhauThuThu;
    }
}
