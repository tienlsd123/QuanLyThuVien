package com.example.asm_dam_tienbxph18636;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.asm_dam_tienbxph18636.Adapter.PhieuMuonAdapter;
import com.example.asm_dam_tienbxph18636.DAO.PhieuMuonDAO;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class QuanLyPhieuMuonActivity extends AppCompatActivity {
    RecyclerView rv;
    FloatingActionButton btn_add;

    PhieuMuonDAO phieuMuonDAO;
    PhieuMuonAdapter phieuMuonAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly_phieu_muon);

        rv = findViewById(R.id.rv_qlpm);
        btn_add = findViewById(R.id.btn_add_pm);

        phieuMuonDAO = new PhieuMuonDAO(QuanLyPhieuMuonActivity.this);
        phieuMuonDAO.open();
        phieuMuonAdapter = new PhieuMuonAdapter(phieuMuonDAO);

        rv.setAdapter(phieuMuonAdapter);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                phieuMuonAdapter.showDialogAdd(QuanLyPhieuMuonActivity.this);
            }
        });
    }
}