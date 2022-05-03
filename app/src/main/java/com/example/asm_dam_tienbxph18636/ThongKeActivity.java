package com.example.asm_dam_tienbxph18636;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ListView;

import com.example.asm_dam_tienbxph18636.Adapter.ThongKeAdapter;
import com.example.asm_dam_tienbxph18636.DAO.PhieuMuonDAO;

public class ThongKeActivity extends AppCompatActivity {
    RecyclerView rv;

    PhieuMuonDAO phieuMuonDAO;
    ThongKeAdapter thongKeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_ke);
        rv = findViewById(R.id.rv_thongke);

        phieuMuonDAO = new PhieuMuonDAO(ThongKeActivity.this);
        phieuMuonDAO.open();
        thongKeAdapter = new ThongKeAdapter(phieuMuonDAO);
        rv.setAdapter(thongKeAdapter);
    }
}