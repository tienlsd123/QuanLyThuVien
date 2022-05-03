package com.example.asm_dam_tienbxph18636.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asm_dam_tienbxph18636.DAO.PhieuMuonDAO;
import com.example.asm_dam_tienbxph18636.DTO.ThongKe;
import com.example.asm_dam_tienbxph18636.R;

import java.util.ArrayList;

public class ThongKeAdapter extends RecyclerView.Adapter<ThongKeAdapter.ThongKeViewHolder> {
    ArrayList<ThongKe> arr = new ArrayList<>();
    PhieuMuonDAO phieuMuonDAO;

    public ThongKeAdapter(PhieuMuonDAO phieuMuonDAO) {
        this.arr = phieuMuonDAO.thongKeSach();
        this.phieuMuonDAO = phieuMuonDAO;
    }

    @NonNull
    @Override
    public ThongKeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.col_tk, parent, false);
        ThongKeViewHolder thongKeViewHolder = new ThongKeViewHolder(view);

        return thongKeViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ThongKeViewHolder holder, int position) {
        final ThongKe objThongKe = arr.get(position);
        final int index = position;
        int top = index+1;
        holder.tv_top.setText("Top "+top+":");
        holder.tv_name.setText(objThongKe.getTenSach());
        holder.tv_sl.setText("Số lượng: "+objThongKe.getSoLuong());
    }

    @Override
    public int getItemCount() {
        return arr.size();
    }


    public class ThongKeViewHolder extends RecyclerView.ViewHolder {
        TextView tv_name, tv_top, tv_sl;
        public ThongKeViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.tv_sach_tk);
            tv_top = itemView.findViewById(R.id.tv_top);
            tv_sl = itemView.findViewById(R.id.tv_soluong);
        }
    }
}
