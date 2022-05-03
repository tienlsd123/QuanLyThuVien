package com.example.asm_dam_tienbxph18636;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.asm_dam_tienbxph18636.DAO.ThuThuDAO;
import com.example.asm_dam_tienbxph18636.DTO.ThuThu;

import java.util.ArrayList;

public class LoginThuThuActivity extends AppCompatActivity {
    Button btn_login;
    EditText ed_name, ed_pass;
    ThuThuDAO thuThuDAO;
    int index= 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_thu_thu);
        btn_login = findViewById(R.id.btn_login);
        ed_name = findViewById(R.id.ed_username);
        ed_pass = findViewById(R.id.ed_pass);

        thuThuDAO = new ThuThuDAO(getApplicationContext());
        thuThuDAO.open();

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tk = ed_name.getText().toString();
                String pass = ed_pass.getText().toString();

                if(tk.length() == 0 || pass.length()==0){
                    Toast.makeText(getApplicationContext(), "Tài khoản mật khẩu không được bỏ trống", Toast.LENGTH_SHORT).show();
                    return;
                }
                ArrayList<ThuThu> arr = thuThuDAO.selectAll();
                int count = 0;
                for(int i=0; i<arr.size(); i++){
                    final ThuThu objThuThu = arr.get(i);
                    if(tk.equals(objThuThu.getTaiKhoanThuThu()) && pass.equals(objThuThu.getMatKhauThuThu())){
                        index = i;
                        count++;
                    }
                }
                if(count>0){
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    intent.putExtra("quyen", "thuthu");
                    intent.putExtra("idtt", index);
                    Toast.makeText(getApplicationContext(), "Đăng nhập thành công với quyền Thủ Thư", Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(), "Sai tên đăng nhập hoặc mật khẩu", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}