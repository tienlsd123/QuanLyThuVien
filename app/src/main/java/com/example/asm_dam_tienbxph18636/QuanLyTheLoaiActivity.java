package com.example.asm_dam_tienbxph18636;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.asm_dam_tienbxph18636.Adapter.TheLoaiAdapter;
import com.example.asm_dam_tienbxph18636.DAO.TheLoaiDAO;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class QuanLyTheLoaiActivity extends AppCompatActivity {
    RecyclerView rv;
    FloatingActionButton btn_add;

    TheLoaiDAO theLoaiDAO;
    TheLoaiAdapter theLoaiAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly_the_loai);

        rv = findViewById(R.id.rv_qltl);
        btn_add = findViewById(R.id.btn_add_tl);

        theLoaiDAO = new TheLoaiDAO(QuanLyTheLoaiActivity.this);
        theLoaiDAO.open();
        theLoaiAdapter = new TheLoaiAdapter(theLoaiDAO);
        rv.setAdapter(theLoaiAdapter);

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                theLoaiAdapter.showDialogAdd(QuanLyTheLoaiActivity.this);
            }
        });
    }
}