package com.example.asm_dam_tienbxph18636;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class MainFragment extends Fragment {
    // Main thủ thư
    Button btn_tv, btn_sach, btn_tl, btn_pm, btn_dt, btn_tk;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btn_tv = view.findViewById(R.id.btn_tv);
        btn_sach = view.findViewById(R.id.btn_sach);
        btn_tk = view.findViewById(R.id.btn_tk);
        btn_tl = view.findViewById(R.id.btn_tl);
        btn_pm = view.findViewById(R.id.btn_pm);
        btn_dt = view.findViewById(R.id.btn_tkdt);

        btn_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), QuanlyThanhVienActivity.class);
                startActivity(intent);
            }
        });

        btn_tl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), QuanLyTheLoaiActivity.class);
                startActivity(intent);
            }
        });

        btn_sach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), QuanLySachActivity.class);
                startActivity(intent);
            }
        });

        btn_pm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), QuanLyPhieuMuonActivity.class);
                startActivity(intent);
            }
        });

        btn_tk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ThongKeActivity.class);
                startActivity(intent);
            }
        });

        btn_dt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), DoanhThuActivity.class);
                startActivity(intent);
            }
        });

    }
}