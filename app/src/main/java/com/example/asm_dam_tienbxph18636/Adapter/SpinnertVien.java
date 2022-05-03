package com.example.asm_dam_tienbxph18636.Adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.asm_dam_tienbxph18636.DTO.Sach;
import com.example.asm_dam_tienbxph18636.DTO.ThanhVien;
import com.example.asm_dam_tienbxph18636.R;

import java.util.ArrayList;

public class SpinnertVien extends BaseAdapter {
    ArrayList<ThanhVien> arr;

    public SpinnertVien(ArrayList<ThanhVien> arr) {
        this.arr = arr;
    }

    @Override
    public int getCount() {
        return this.arr.size();
    }

    @Override
    public Object getItem(int i) {
        return this.arr.get(i);
    }

    @Override
    public long getItemId(int i) {
        return arr.get(i).getId_tvien();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View itemView;
        if(view == null){
            itemView = View.inflate(viewGroup.getContext() , R.layout.row_spntl , null);
        }else {
            itemView = view;
        }

        TextView item = itemView.findViewById(R.id.tv_sp_tl);

        ThanhVien objThanhVien = arr.get(i);
        item.setText(objThanhVien.getTen_tv());

        return itemView;
    }
}
