package com.example.asm_dam_tienbxph18636;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.example.asm_dam_tienbxph18636.DAO.PhieuMuonDAO;

import java.util.Calendar;

public class DoanhThuActivity extends AppCompatActivity {
    PhieuMuonDAO phieuMuonDAO;
    TextView tv_dt;
    EditText ed_tu, ed_den;
    Button btn_tinh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doanh_thu);
        tv_dt = findViewById(R.id.tv_tong_dt);
        ed_den = findViewById(R.id.ed_den);
        ed_tu = findViewById(R.id.ed_tu);
        btn_tinh = findViewById(R.id.btn_tinhdt);

        phieuMuonDAO = new PhieuMuonDAO(DoanhThuActivity.this);
        phieuMuonDAO.open();

        Calendar calendar = Calendar.getInstance();


        ed_tu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int y = calendar.get(Calendar.YEAR);
                int m = calendar.get(Calendar.MONTH);
                int d = calendar.get(Calendar.DATE);

                DatePickerDialog datePickerDialog = new DatePickerDialog(DoanhThuActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        String month = String.valueOf(i1);
                        String day = String.valueOf(i2);
                        if(String.valueOf(i1).length() == 1){
                            month = "0"+i1;
                        }
                        if(String.valueOf(i2).length() == 1){
                            day = "0"+i2;
                        }
                        ed_tu.setText(i + "-" +month + "-" + day);
                    }
                }, y,m,d);
                datePickerDialog.show();
            }
        });

        ed_den.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int y = calendar.get(Calendar.YEAR);
                int m = calendar.get(Calendar.MONTH);
                int d = calendar.get(Calendar.DATE);

                DatePickerDialog datePickerDialog = new DatePickerDialog(DoanhThuActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        String month = String.valueOf(i1);
                        String day = String.valueOf(i2);
                        if(String.valueOf(i1).length() == 1){
                            month = "0"+i1;
                        }
                        if(String.valueOf(i2).length() == 1){
                            day = "0"+i2;
                        }
                        ed_den.setText(i + "-" +month + "-" + day);
                    }
                }, y,m,d);
                datePickerDialog.show();
            }
        });

        btn_tinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tungay = ed_tu.getText().toString();
                String denngay = ed_den.getText().toString();

                int a = phieuMuonDAO.doanhThu(tungay,denngay);
                tv_dt.setText("Tổng doanh thu: "+ a);
            }
        });

    }
}