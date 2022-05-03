package com.example.asm_dam_tienbxph18636;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.asm_dam_tienbxph18636.Adapter.SachAdapter;
import com.example.asm_dam_tienbxph18636.DAO.SachDAO;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class QuanLySachActivity extends AppCompatActivity {
    RecyclerView rv;
    FloatingActionButton btn_add;

    SachAdapter sachAdapter;
    SachDAO sachDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly_sach);

        rv = findViewById(R.id.rv_qlsach);
        btn_add = findViewById(R.id.btn_add_sach);

        sachDAO = new SachDAO(QuanLySachActivity.this);
        sachDAO.open();
        sachAdapter = new SachAdapter(sachDAO);
        rv.setAdapter(sachAdapter);

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sachAdapter.showDialogAdd(QuanLySachActivity.this);
            }
        });
    }
}