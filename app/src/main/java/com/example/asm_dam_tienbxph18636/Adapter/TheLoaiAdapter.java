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

import com.example.asm_dam_tienbxph18636.DAO.TheLoaiDAO;
import com.example.asm_dam_tienbxph18636.DTO.TheLoai;
import com.example.asm_dam_tienbxph18636.R;

import java.util.ArrayList;

public class TheLoaiAdapter extends RecyclerView.Adapter<TheLoaiAdapter.TheLoaiViewHolder> {
    TheLoaiDAO theLoaiDAO;
    ArrayList<TheLoai> arr;

    public TheLoaiAdapter(TheLoaiDAO theLoaiDAO){
        this.theLoaiDAO = theLoaiDAO;
        this.arr = theLoaiDAO.selectAll();
    }

    @NonNull
    @Override
    public TheLoaiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.col_tl, parent, false);
        TheLoaiViewHolder theLoaiViewHolder = new TheLoaiViewHolder(view);

        return theLoaiViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TheLoaiViewHolder holder, int position) {
        final TheLoai objTheLoai = arr.get(position);
        final int index = position;
        holder.tv_un.setText(objTheLoai.getTenTheLoai());

        holder.img_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setTitle("Xóa thể loại ?");
                builder.setMessage("Bạn có chắc muốn xóa thể loại này?");
                builder.setPositiveButton("Xác nhận", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        int res = theLoaiDAO.deleteTL(objTheLoai);
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
                showDialogEdit(view.getContext(), objTheLoai, index);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arr.size();
    }

    public class TheLoaiViewHolder extends RecyclerView.ViewHolder{
        TextView tv_un;
        ImageView img_delete, img_edit;

        public TheLoaiViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_un = itemView.findViewById(R.id.tv_name);
            img_delete = itemView.findViewById(R.id.img_delete);
            img_edit = itemView.findViewById(R.id.img_edit);

        }
    }

    public void showDialogAdd(Context context){
        Dialog dialog = new Dialog(context, R.style.Theme_AppCompat_Light_Dialog_Alert);
        dialog.setContentView(R.layout.dialogadd_tl);
        EditText ed_name = dialog.findViewById(R.id.ed_name_sach);
        Button btn_add = dialog.findViewById(R.id.btn_add);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ten = ed_name.getText().toString();
                if(ten.length()==0){
                    Toast.makeText(context, "Dữ liệu không được bỏ trống ", Toast.LENGTH_SHORT).show();
                }else{
                    TheLoai objTheLoai = new TheLoai();
                    objTheLoai.setTenTheLoai(ten);
                    long res = theLoaiDAO.insertTL(objTheLoai);

                    if(res>0){
                        arr.clear();
                        arr.addAll(theLoaiDAO.selectAll());
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


    public void showDialogEdit(Context context, TheLoai objTheLoai, int index){
        Dialog dialog = new Dialog(context, R.style.Theme_AppCompat_Light_Dialog_Alert);
        dialog.setContentView(R.layout.dialogedit_tl);
        EditText ed_name = dialog.findViewById(R.id.ed_name_sach);
        Button btn_add = dialog.findViewById(R.id.btn_add);

        ed_name.setText(objTheLoai.getTenTheLoai());

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ten = ed_name.getText().toString();
                if(ten.length()==0){
                    Toast.makeText(context, "Dữ liệu không được bỏ trống ", Toast.LENGTH_SHORT).show();
                }else{
                    objTheLoai.setTenTheLoai(ten);
                    long res = theLoaiDAO.updateTL(objTheLoai);

                    if(res>0){
                        arr.set(index,objTheLoai);
                        notifyDataSetChanged();
                        Toast.makeText(context, "Sửa thành công", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }else{
                        Toast.makeText(context, "Sửa thất bại", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                }
            }
        });

        dialog.show();
    }


}
