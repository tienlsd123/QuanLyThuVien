package com.example.asm_dam_tienbxph18636.DTO;

public class Admin {
    public int id_admin;
    public String taiKhoanAdmin;
    public String matKhauAdmin;

    public static final String TB_NAME = "admin";
    public static final String COL_TK = "taiKhoanAdmin";
    public static final String COL_MK = "matKhauAdmin";


    public int getId_admin() {
        return id_admin;
    }

    public void setId_admin(int id_admin) {
        this.id_admin = id_admin;
    }

    public String getTaiKhoanAdmin() {
        return taiKhoanAdmin;
    }

    public void setTaiKhoanAdmin(String taiKhoanAdmin) {
        this.taiKhoanAdmin = taiKhoanAdmin;
    }

    public String getMatKhauAdmin() {
        return matKhauAdmin;
    }

    public void setMatKhauAdmin(String matKhauAdmin) {
        this.matKhauAdmin = matKhauAdmin;
    }
}
