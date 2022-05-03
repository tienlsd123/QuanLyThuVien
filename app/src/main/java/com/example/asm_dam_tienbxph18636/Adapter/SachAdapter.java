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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asm_dam_tienbxph18636.DAO.SachDAO;
import com.example.asm_dam_tienbxph18636.DAO.TheLoaiDAO;
import com.example.asm_dam_tienbxph18636.DTO.Sach;
import com.example.asm_dam_tienbxph18636.DTO.TheLoai;
import com.example.asm_dam_tienbxph18636.R;

import java.util.ArrayList;

public class SachAdapter extends RecyclerView.Adapter<SachAdapter.SachViewHolder> {
    ArrayList<Sach> arr = new ArrayList<>();
    SachDAO sachDAO;

    public SachAdapter(SachDAO sachDAO){
        this.sachDAO = sachDAO;
        this.arr = sachDAO.selectAll();
    }

    @NonNull
    @Override
    public SachViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.col_sach, parent, false);
        SachViewHolder sachViewHolder = new SachViewHolder(view);

        return sachViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SachViewHolder holder, int position) {
        final Sach objSach = arr.get(position);
        final int index = position;

        holder.tv_name_sach.setText(objSach.getTenSach());
        holder.tv_theLoai.setText(objSach.getTenTheLoai());
        holder.tv_giaSach.setText("Giá: "+objSach.getGiaSach());

        holder.img_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setTitle("Xóa sách ?");
                builder.setMessage("Bạn có chắc muốn xóa sách này?");
                builder.setPositiveButton("Xác nhận", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        int res = sachDAO.deleteSach(objSach);
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
                showDialogEdit(view.getContext(),objSach,index);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arr.size();
    }


    public class SachViewHolder extends RecyclerView.ViewHolder {
        TextView tv_name_sach, tv_theLoai, tv_giaSach;
        ImageView img_delete, img_edit;
        public SachViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_name_sach = itemView.findViewById(R.id.tv_ten_tv);
            tv_theLoai = itemView.findViewById(R.id.tv_ten_sach);
            tv_giaSach = itemView.findViewById(R.id.tv_giaPhieu);
            img_delete = itemView.findViewById(R.id.img_delete);
            img_edit = itemView.findViewById(R.id.img_edit);
        }
    }

    public void showDialogAdd(Context context){
        Dialog dialog = new Dialog(context, R.style.Theme_AppCompat_Light_Dialog_Alert);
        dialog.setContentView(R.layout.dialogadd_sach);
        EditText ed_name = dialog.findViewById(R.id.ed_name_sach);
        EditText ed_gia = dialog.findViewById(R.id.ed_giaSach);
        Spinner sp_tl = dialog.findViewById(R.id.sp_tls);
        Button btn_add = dialog.findViewById(R.id.btn_add);

        TheLoaiDAO theLoaiDAO = new TheLoaiDAO(context);
        theLoaiDAO.open();
        SpinnerTheLoai spinnerTheLoai = new SpinnerTheLoai(theLoaiDAO.selectAll());

        sp_tl.setAdapter(spinnerTheLoai);


        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TheLoai objTheLoai = (TheLoai) sp_tl.getSelectedItem();
                try {
                    int gia = Integer.parseInt(ed_gia.getText().toString());
                } catch (NumberFormatException ex) {
                    System.out.println("Giá sách phải là số");
                    return;
                }


                if(ed_name.getText().toString().length()==0 || ed_gia.getText().toString().length()==0){
                    Toast.makeText(context, "Dữ liệu không được bỏ trống ", Toast.LENGTH_SHORT).show();
                    return;
                }else{
                    Sach objSach = new Sach();
                    objSach.setTenSach(ed_name.getText().toString());
                    objSach.setGiaSach(Integer.parseInt(ed_gia.getText().toString()));
                    objSach.setId_theLoai(objTheLoai.getId_theLoai());
                    objSach.setTenTheLoai(objTheLoai.getTenTheLoai());

                    long res = sachDAO.insertSach(objSach);

                    if(res>0){
                        arr.clear();
                        arr.addAll(sachDAO.selectAll());
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


    public void showDialogEdit(Context context, Sach objSach, int index){
        Dialog dialog = new Dialog(context, R.style.Theme_AppCompat_Light_Dialog_Alert);
        dialog.setContentView(R.layout.dialogadd_sach);
        EditText ed_name = dialog.findViewById(R.id.ed_name_sach);
        EditText ed_gia = dialog.findViewById(R.id.ed_giaSach);
        Spinner sp_tl = dialog.findViewById(R.id.sp_tls);
        Button btn_add = dialog.findViewById(R.id.btn_add);

        ed_name.setText(objSach.getTenSach());
        ed_gia.setText(objSach.getGiaSach()+"");

        TheLoaiDAO theLoaiDAO = new TheLoaiDAO(context);
        theLoaiDAO.open();
        ArrayList<TheLoai> arrTl = theLoaiDAO.selectAll();
        SpinnerTheLoai spinnerTheLoai = new SpinnerTheLoai(arrTl);



        sp_tl.setAdapter(spinnerTheLoai);

        for(int j = 0; j <arrTl.size(); j++){
            TheLoai tmp = arrTl.get(j);
            if(tmp.getId_theLoai() == objSach.getId_theLoai()){
                sp_tl.setSelection(j);
                sp_tl.setSelected(true);
                break;
            }
        }


        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ten = ed_name.getText().toString();
                TheLoai objTheLoai = (TheLoai) sp_tl.getSelectedItem();
                try {
                    int gia = Integer.parseInt(ed_gia.getText().toString());
                } catch (NumberFormatException ex) {
                    System.out.println("Giá sách phải là số");
                    return;
                }


                if(ten.length()==0 || ed_gia.getText().toString().length()==0){
                    Toast.makeText(context, "Dữ liệu không được bỏ trống ", Toast.LENGTH_SHORT).show();
                    return;
                }else{
                    objSach.setTenSach(ten);
                    objSach.setGiaSach(Integer.parseInt(ed_gia.getText().toString()));
                    objSach.setId_theLoai(objTheLoai.getId_theLoai());
                    objSach.setTenTheLoai(objTheLoai.getTenTheLoai());

                    int res = sachDAO.updateSach(objSach);

                    if(res>0){
                        arr.set(index, objSach);
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
