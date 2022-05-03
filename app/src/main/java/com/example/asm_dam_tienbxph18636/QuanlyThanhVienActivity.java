package com.example.asm_dam_tienbxph18636;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.asm_dam_tienbxph18636.Adapter.ThanhVienAdapter;
import com.example.asm_dam_tienbxph18636.DAO.ThanhVienDAO;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class QuanlyThanhVienActivity extends AppCompatActivity {
    RecyclerView rv;
    FloatingActionButton btn_add;

    ThanhVienAdapter thanhVienAdapter;
    ThanhVienDAO thanhVienDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quanly_thanh_vien);

        rv = findViewById(R.id.rv_qltv);
        btn_add = findViewById(R.id.btn_add_tv);

        thanhVienDAO = new ThanhVienDAO(getApplicationContext());
        thanhVienDAO.open();
        thanhVienAdapter = new ThanhVienAdapter(thanhVienDAO);
        rv.setAdapter(thanhVienAdapter);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                thanhVienAdapter.showDialogAdd(QuanlyThanhVienActivity.this);
            }
        });
    }
}