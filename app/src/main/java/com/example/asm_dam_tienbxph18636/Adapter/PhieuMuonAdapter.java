package com.example.asm_dam_tienbxph18636.Adapter;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asm_dam_tienbxph18636.DAO.PhieuMuonDAO;
import com.example.asm_dam_tienbxph18636.DAO.SachDAO;
import com.example.asm_dam_tienbxph18636.DAO.ThanhVienDAO;
import com.example.asm_dam_tienbxph18636.DTO.PhieuMuon;
import com.example.asm_dam_tienbxph18636.DTO.Sach;
import com.example.asm_dam_tienbxph18636.DTO.ThanhVien;
import com.example.asm_dam_tienbxph18636.DTO.TheLoai;
import com.example.asm_dam_tienbxph18636.R;

import java.util.ArrayList;
import java.util.Calendar;

public class PhieuMuonAdapter extends RecyclerView.Adapter<PhieuMuonAdapter.PhieuMuonViewHolder> {
    ArrayList<PhieuMuon> arr;
    PhieuMuonDAO phieuMuonDAO;

    public PhieuMuonAdapter(PhieuMuonDAO phieuMuonDAO){
        this.phieuMuonDAO = phieuMuonDAO;
        this.arr = phieuMuonDAO.selectAll();
    }

    @NonNull
    @Override
    public PhieuMuonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.col_phieumuon, parent, false);
        PhieuMuonViewHolder phieuMuonAdapter = new PhieuMuonViewHolder(view);

        return phieuMuonAdapter;
    }

    @Override
    public void onBindViewHolder(@NonNull PhieuMuonViewHolder holder, int position) {
        final PhieuMuon objPhieuMuon = arr.get(position);
        final int index = position;

        holder.tv_tentv.setText(objPhieuMuon.getTen_tv());
        holder.tv_ngay.setText(objPhieuMuon.getNgayMuon());
        holder.tv_tensach.setText(objPhieuMuon.getTenSach());
        holder.tv_gia.setText("Giá: "+objPhieuMuon.getGiaPhieu());
        holder.img_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogEdit(view.getContext(), objPhieuMuon,index);
            }
        });

        holder.img_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setTitle("Xóa Phiếu Mượn ?");
                builder.setMessage("Bạn có chắc muốn xóa phiếu mượn này?");
                builder.setPositiveButton("Xác nhận", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        int res = phieuMuonDAO.deletePm(objPhieuMuon);
                        if(res>0){
                            arr.remove(index);
                            notifyDataSetChanged();
                            Toast.makeText(view.getContext(), "Xóa thành công", Toast.LENGTH_SHORT).show();
                            dialogInterface.dismiss();
                        }else {
                            Toast.makeText(view.getContext(), "Xóa thất bại", Toast.LENGTH_SHORT).show();
                            dialogInterface.dismiss();
                        }
                    }
                });
                builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                Dialog dialog = builder.create();
                dialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return arr.size();
    }


    public class PhieuMuonViewHolder extends RecyclerView.ViewHolder {
        TextView tv_tensach, tv_gia, tv_tentv, tv_ngay;
        ImageView img_delete, img_edit;
        public PhieuMuonViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_gia = itemView.findViewById(R.id.tv_giaPhieu);
            tv_ngay = itemView.findViewById(R.id.tv_ngayMuon);
            tv_tensach = itemView.findViewById(R.id.tv_ten_sach);
            tv_tentv = itemView.findViewById(R.id.tv_ten_tv);
            img_delete = itemView.findViewById(R.id.img_delete);
            img_edit = itemView.findViewById(R.id.img_edit);
        }
    }

    public void showDialogAdd(Context context){
        Dialog dialog = new Dialog(context, R.style.Theme_AppCompat_Light_Dialog_Alert);
        dialog.setContentView(R.layout.dialogadd_phieumuon);
        EditText ed_ngaymuon = dialog.findViewById(R.id.ed_ngayMuon) ;
        Spinner sp_tvien = dialog.findViewById(R.id.sp_tvien);
        Spinner sp_sach = dialog.findViewById(R.id.sp_sach);
        Button btn_add = dialog.findViewById(R.id.btn_add);
        SachDAO sachDAO = new SachDAO(context);
        sachDAO.open();
        ThanhVienDAO thanhVienDAO = new ThanhVienDAO(context);
        thanhVienDAO.open();

        SpinnerSach spinnerSach = new SpinnerSach(sachDAO.selectAll());
        sp_sach.setAdapter(spinnerSach);
        SpinnertVien spinnertVien = new SpinnertVien(thanhVienDAO.selectAll());
        sp_tvien.setAdapter(spinnertVien);

        Calendar calendar = Calendar.getInstance();

        ed_ngaymuon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int y = calendar.get(Calendar.YEAR);
                int m = calendar.get(Calendar.MONTH);
                int d = calendar.get(Calendar.DATE);

                DatePickerDialog datePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
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
                        ed_ngaymuon.setText(i + "-" +month + "-" + day);
                    }
                }, y,m,d);
                datePickerDialog.show();
            }
        });

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PhieuMuon objPhieuMuon = new PhieuMuon();
                ThanhVien objThanhVien = (ThanhVien) sp_tvien.getSelectedItem();
                Sach objSach = (Sach) sp_sach.getSelectedItem();
                objPhieuMuon.setTen_tv(objThanhVien.getTen_tv());
                objPhieuMuon.setTenSach(objSach.getTenSach());
                objPhieuMuon.setGiaPhieu(objSach.getGiaSach());
                objPhieuMuon.setId_tvien(objThanhVien.getId_tvien());
                objPhieuMuon.setId_sach(objSach.getId_sach());
                objPhieuMuon.setNgayMuon(ed_ngaymuon.getText().toString());

                long res = phieuMuonDAO.insertPm(objPhieuMuon);
                if(res>0){
                    arr.clear();
                    arr.addAll(phieuMuonDAO.selectAll());
                    notifyDataSetChanged();
                    Toast.makeText(context, "Thêm mới thành công", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }else{
                    Toast.makeText(context, "Thêm mới thất bại", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
            }
        });

        dialog.show();
    }


    public void showDialogEdit(Context context, PhieuMuon objPhieuMuon, int index){
        Dialog dialog = new Dialog(context, R.style.Theme_AppCompat_Light_Dialog_Alert);
        dialog.setContentView(R.layout.dialogedit_phieumuon);
        EditText ed_ngaymuon = dialog.findViewById(R.id.ed_ngayMuon) ;
        Spinner sp_tvien = dialog.findViewById(R.id.sp_tvien);
        Spinner sp_sach = dialog.findViewById(R.id.sp_sach);
        Button btn_add = dialog.findViewById(R.id.btn_add);
        SachDAO sachDAO = new SachDAO(context);
        sachDAO.open();
        ThanhVienDAO thanhVienDAO = new ThanhVienDAO(context);
        thanhVienDAO.open();

        ArrayList<Sach> listSach = sachDAO.selectAll();
        ArrayList<ThanhVien> listTv = thanhVienDAO.selectAll();

        SpinnerSach spinnerSach = new SpinnerSach(listSach);
        sp_sach.setAdapter(spinnerSach);
        SpinnertVien spinnertVien = new SpinnertVien(listTv);
        sp_tvien.setAdapter(spinnertVien);

        Calendar calendar = Calendar.getInstance();

        for(int j = 0; j <listTv.size(); j++){
            ThanhVien tmp = listTv.get(j);
            if(tmp.getId_tvien() == objPhieuMuon.getId_tvien()){
                sp_tvien.setSelection(j);
                sp_tvien.setSelected(true);
                break;
            }
        }

        for(int j = 0; j <listSach.size(); j++){
            Sach tmp = listSach.get(j);
            if(tmp.getId_sach() == objPhieuMuon.getId_sach()){
                sp_sach.setSelection(j);
                sp_sach.setSelected(true);
                break;
            }
        }
        ed_ngaymuon.setText(objPhieuMuon.getNgayMuon());

        ed_ngaymuon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int y = calendar.get(Calendar.YEAR);
                int m = calendar.get(Calendar.MONTH);
                int d = calendar.get(Calendar.DATE);

                DatePickerDialog datePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
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
                        ed_ngaymuon.setText(i + "-" +month + "-" + day);
                    }
                }, y,m,d);
                datePickerDialog.show();
            }
        });

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ThanhVien objThanhVien = (ThanhVien) sp_tvien.getSelectedItem();
                Sach objSach = (Sach) sp_sach.getSelectedItem();
                objPhieuMuon.setTen_tv(objThanhVien.getTen_tv());
                objPhieuMuon.setTenSach(objSach.getTenSach());
                objPhieuMuon.setGiaPhieu(objSach.getGiaSach());
                objPhieuMuon.setId_tvien(objThanhVien.getId_tvien());
                objPhieuMuon.setId_sach(objSach.getId_sach());
                objPhieuMuon.setNgayMuon(ed_ngaymuon.getText().toString());

                int res = phieuMuonDAO.updateSach(objPhieuMuon);
                if(res>0){
                    arr.set(index, objPhieuMuon);
                    notifyDataSetChanged();
                    Toast.makeText(context, "Sửa mới thành công", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }else{
                    Toast.makeText(context, "Sửa mới thất bại", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
            }
        });

        dialog.show();
    }

}
