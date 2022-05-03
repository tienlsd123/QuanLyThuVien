package com.example.asm_dam_tienbxph18636;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.asm_dam_tienbxph18636.DAO.AdminDAO;
import com.example.asm_dam_tienbxph18636.DTO.Admin;

public class LoginActivity extends AppCompatActivity {
    Button btn_login;
    EditText ed_name, ed_pass;
    AdminDAO adminDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
        btn_login = findViewById(R.id.btn_login);
        ed_name = findViewById(R.id.ed_username);
        ed_pass = findViewById(R.id.ed_pass);
        adminDAO = new AdminDAO(getApplicationContext());
        adminDAO.open();
        Admin objAdmin = new Admin();
        objAdmin.setTaiKhoanAdmin("admin");
        objAdmin.setMatKhauAdmin("admin");
        adminDAO.insertAdmin(objAdmin);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Admin objAdmin2 = adminDAO.selectAd();
                String name = ed_name.getText().toString();
                String pass = ed_pass.getText().toString();

                if(name.length() == 0 || pass.length() == 0){
                    Toast.makeText(getApplicationContext(), "Tài khoản mật khẩu không được bỏ trống", Toast.LENGTH_SHORT).show();
                }
                else if(!name.equals(objAdmin2.getTaiKhoanAdmin()) || !pass.equals(objAdmin2.getMatKhauAdmin())){
                    Toast.makeText(getApplicationContext(), "Sai thông tin tài khoản hoặc mật khẩu", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getApplicationContext(), "Đăng nhập thành công với quyền ADMIN", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    intent.putExtra("quyen", "admin");
                    startActivity(intent);
                }
            }
        });



    }
}