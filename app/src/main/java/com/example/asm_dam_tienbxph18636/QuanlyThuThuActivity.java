package com.example.asm_dam_tienbxph18636;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.asm_dam_tienbxph18636.Adapter.ThuThuAdapter;
import com.example.asm_dam_tienbxph18636.DAO.ThuThuDAO;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class QuanlyThuThuActivity extends AppCompatActivity {
    RecyclerView rv;
    FloatingActionButton btn_add;
    ThuThuDAO thuThuDAO;
    ThuThuAdapter thuThuAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quanly_thu_thu);

        rv = findViewById(R.id.rv_qltt);
        btn_add = findViewById(R.id.btn_add_tt);

        thuThuDAO = new ThuThuDAO(getApplicationContext());
        thuThuDAO.open();
        thuThuAdapter = new ThuThuAdapter(thuThuDAO);
        rv.setAdapter(thuThuAdapter);

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                thuThuAdapter.showDialogAdd(QuanlyThuThuActivity.this);
            }
        });

    }
}