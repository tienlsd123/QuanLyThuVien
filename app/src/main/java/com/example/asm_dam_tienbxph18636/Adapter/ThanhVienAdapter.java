package com.example.asm_dam_tienbxph18636.Adapter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asm_dam_tienbxph18636.DAO.ThanhVienDAO;
import com.example.asm_dam_tienbxph18636.DTO.ThanhVien;
import com.example.asm_dam_tienbxph18636.R;

import java.util.ArrayList;

public class ThanhVienAdapter extends RecyclerView.Adapter<ThanhVienAdapter.ThanhVienViewHolder> {
    ArrayList<ThanhVien> arr = new ArrayList<>();
    ThanhVienDAO thanhVienDAO;

    public ThanhVienAdapter(ThanhVienDAO thanhVienDAO){
        this.thanhVienDAO = thanhVienDAO;
        this.arr = thanhVienDAO.selectAll();
    }


    @NonNull
    @Override
    public ThanhVienViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.col_tv, parent, false);
        ThanhVienViewHolder thanhVienViewHolder = new ThanhVienViewHolder(view);

        return thanhVienViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ThanhVienViewHolder holder, int position) {
        final ThanhVien objThanhVien = arr.get(position);
        final int index = position;
        holder.tv_un.setText(objThanhVien.getTen_tv());
        holder.tv_sdt.setText("SĐT: "+objThanhVien.getSdt());
        holder.img_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setTitle("Xóa thành viên ?");
                builder.setMessage("Bạn có chắc muốn xóa thành viên này?");
                builder.setPositiveButton("Xác nhận", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        int res = thanhVienDAO.deleteTV(objThanhVien);
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
        holder.img_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogEdit(view.getContext(), objThanhVien, index);
            }
        });

    }

    @Override
    public int getItemCount() {
        return arr.size();
    }

    public class ThanhVienViewHolder extends RecyclerView.ViewHolder {
        TextView tv_un, tv_sdt;
        ImageView img_delete, img_edit;
        public ThanhVienViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_sdt = itemView.findViewById(R.id.tv_sdt);
            tv_un = itemView.findViewById(R.id.tv_name);
            img_delete = itemView.findViewById(R.id.img_delete);
            img_edit = itemView.findViewById(R.id.img_edit);
        }
    }

    public void showDialogAdd(Context context){
        Dialog dialog = new Dialog(context, R.style.Theme_AppCompat_Light_Dialog_Alert);
        dialog.setContentView(R.layout.dialogadd_tv);
        EditText ed_name = dialog.findViewById(R.id.ed_name_sach);
        EditText ed_sdt = dialog.findViewById(R.id.ed_giaSach);
        Button btn_add = dialog.findViewById(R.id.btn_add);

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ten = ed_name.getText().toString();
                String sdt = ed_sdt.getText().toString();
                if(ten.length()==0 || sdt.length()==0){
                    Toast.makeText(context, "Dữ liệu không được bỏ trống ", Toast.LENGTH_SHORT).show();
                }else{
                    ThanhVien objThanhVien = new ThanhVien();
                    objThanhVien.setTen_tv(ten);
                    objThanhVien.setSdt(sdt);
                    long res = thanhVienDAO.insertTV(objThanhVien);

                    if(res>0){
                        arr.clear();
                        arr.addAll(thanhVienDAO.selectAll());
                        notifyDataSetChanged();
                        Toast.makeText(context, "Thêm mới thành công", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }else{
                        Toast.makeText(context, "Thêm mới thất bại", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                }
            }
        });

        dialog.show();
    }

    public void showDialogEdit(Context context, ThanhVien objThanhVien, int index){
        Dialog dialog = new Dialog(context, R.style.Theme_AppCompat_Light_Dialog_Alert);
        dialog.setContentView(R.layout.dialogedit_tv);
        EditText ed_name = dialog.findViewById(R.id.ed_name_sach);
        EditText ed_sdt = dialog.findViewById(R.id.ed_giaSach);
        Button btn_add = dialog.findViewById(R.id.btn_add);

        ed_name.setText(objThanhVien.getTen_tv());
        ed_sdt.setText(objThanhVien.getSdt());

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ten = ed_name.getText().toString();
                String sdt = ed_sdt.getText().toString();
                if(ten.length()==0|| sdt.length()==0){
                    Toast.makeText(context, "Dữ liệu không được bỏ trống ", Toast.LENGTH_SHORT).show();
                }else{
                    objThanhVien.setTen_tv(ten);
                    objThanhVien.setSdt(sdt);
                    long res = thanhVienDAO.updateTV(objThanhVien);

                    if(res>0){
                        arr.set(index, objThanhVien);
                        notifyDataSetChanged();
                        Toast.makeText(context, "Thêm mới thành công", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }else{
                        Toast.makeText(context, "Thêm mới thất bại", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                }
            }
        });

        dialog.show();

    }
}
