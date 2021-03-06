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

import com.example.asm_dam_tienbxph18636.DAO.ThuThuDAO;
import com.example.asm_dam_tienbxph18636.DTO.ThuThu;
import com.example.asm_dam_tienbxph18636.R;

import java.util.ArrayList;

public class ThuThuAdapter extends RecyclerView.Adapter<ThuThuAdapter.ThuThuViewHolder> {
    ArrayList<ThuThu> arr = new ArrayList<>();
    ThuThuDAO thuThuDAO;

    public ThuThuAdapter(ThuThuDAO thuThuDAO){
        this.arr = thuThuDAO.selectAll();
        this.thuThuDAO = thuThuDAO;
    }

    @NonNull
    @Override
    public ThuThuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.col_thuthu, parent, false);
        ThuThuViewHolder thuThuViewHolder = new ThuThuViewHolder(view);

        return thuThuViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ThuThuViewHolder holder, int position) {
        final ThuThu objThuThu = arr.get(position);
        final int index = position;

        holder.tv_un.setText(objThuThu.getTaiKhoanThuThu());
        holder.tv_pass.setText(objThuThu.getMatKhauThuThu());
        holder.img_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    showDialogEdit(view.getContext(), objThuThu, index);
            }
        });

        holder.img_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setTitle("X??a th??? th?? ?");
                builder.setMessage("B???n c?? ch???c mu???n x??a th??? th?? n??y?");
                builder.setPositiveButton("X??c nh???n", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        int res = thuThuDAO.deleteThuThu(objThuThu);
                        if(res>0){
                            arr.remove(index);
                            notifyDataSetChanged();
                            Toast.makeText(view.getContext(), "X??a th??nh c??ng", Toast.LENGTH_SHORT).show();
                            dialogInterface.dismiss();
                        }else {
                            Toast.makeText(view.getContext(), "X??a th???t b???i", Toast.LENGTH_SHORT).show();
                            dialogInterface.dismiss();
                        }
                    }
                });
                builder.setNegativeButton("H???y", new DialogInterface.OnClickListener() {
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

    public class ThuThuViewHolder extends RecyclerView.ViewHolder{
        TextView tv_un, tv_pass;
        ImageView img_delete, img_edit;

        public ThuThuViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_pass = itemView.findViewById(R.id.tv_sdt);
            tv_un = itemView.findViewById(R.id.tv_name);
            img_delete = itemView.findViewById(R.id.img_delete);
            img_edit = itemView.findViewById(R.id.img_edit);

        }
    }

    public void showDialogAdd(Context context){
        Dialog dialog = new Dialog(context, R.style.Theme_AppCompat_Light_Dialog_Alert);
        dialog.setContentView(R.layout.dialogadd_thuthu);
        EditText ed_tk = dialog.findViewById(R.id.ed_name_sach);
        EditText ed_mk = dialog.findViewById(R.id.ed_giaSach);
        Button btn_add = dialog.findViewById(R.id.btn_add);

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tk = ed_tk.getText().toString();
                String mk = ed_mk.getText().toString();
                if(tk.length()==0||mk.length()==0){
                    Toast.makeText(context, "D??? li???u kh??ng ???????c b??? tr???ng", Toast.LENGTH_SHORT).show();
                }else{
                    ThuThu objThuThu = new ThuThu();
                    objThuThu.setTaiKhoanThuThu(tk);
                    objThuThu.setMatKhauThuThu(mk);
                    long res = thuThuDAO.insertThuThu(objThuThu);

                    if(res>0){
                        arr.clear();
                        arr.addAll(thuThuDAO.selectAll());
                        notifyDataSetChanged();
                        Toast.makeText(context, "Th??m m???i th??nh c??ng", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }else{
                        Toast.makeText(context, "Th??m m???i th???t b???i", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                }
            }
        });
        dialog.show();
    }

    public void showDialogEdit(Context context, ThuThu objThuThu, int index){
        Dialog dialog = new Dialog(context, R.style.Theme_AppCompat_Light_Dialog_Alert);
        dialog.setContentView(R.layout.dialogedit_thuthu);
        EditText ed_tk = dialog.findViewById(R.id.ed_name_sach);
        EditText ed_mk = dialog.findViewById(R.id.ed_giaSach);
        Button btn_add = dialog.findViewById(R.id.btn_add);

        ed_mk.setText(objThuThu.getMatKhauThuThu());
        ed_tk.setText(objThuThu.getTaiKhoanThuThu());

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tk = ed_tk.getText().toString();
                String mk = ed_mk.getText().toString();

                if(tk.length()==0||mk.length()==0){
                    Toast.makeText(context, "D??? li???u kh??ng ???????c b??? tr???ng", Toast.LENGTH_SHORT).show();
                }else{
                    objThuThu.setMatKhauThuThu(mk);
                    objThuThu.setTaiKhoanThuThu(tk);

                    int res = thuThuDAO.updateThuThu(objThuThu);
                    if(res>0){
                        arr.set(index, objThuThu);
                        notifyDataSetChanged();
                        Toast.makeText(context, "S???a th??ng tin th??nh c??ng", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }else {
                        Toast.makeText(context, "S???a th??ng tin th???t b???i", Toast.LENGTH_SHORT).show();

                    }
                }
            }
        });
        dialog.show();
    }
}
