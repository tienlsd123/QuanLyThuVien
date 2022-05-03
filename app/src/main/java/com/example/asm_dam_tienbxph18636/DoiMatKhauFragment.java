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
import android.widget.EditText;
import android.widget.Toast;

import com.example.asm_dam_tienbxph18636.DAO.AdminDAO;
import com.example.asm_dam_tienbxph18636.DAO.ThuThuDAO;
import com.example.asm_dam_tienbxph18636.DTO.Admin;
import com.example.asm_dam_tienbxph18636.DTO.ThuThu;

import java.util.ArrayList;

public class DoiMatKhauFragment extends Fragment {
    MainActivity mainActivity;
    Button btn_change;
    EditText ed_mkcu, ed_mkmoi, ed_xnmk;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_doi_mat_khau, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btn_change = view.findViewById(R.id.btn_changepass);
        ed_mkcu = view.findViewById(R.id.ed_passcu);
        ed_mkmoi = view.findViewById(R.id.ed_passmoi);
        ed_xnmk = view.findViewById(R.id.ed_xnpass);

        mainActivity = (MainActivity) getActivity();
        String quyen = mainActivity.getQuyen();
        if(quyen.equals("admin")){
            Toast.makeText(getContext(), "Đổi mật khẩu ADMIN", Toast.LENGTH_SHORT).show();
            AdminDAO adminDAO = new AdminDAO(getContext());
            adminDAO.open();

            btn_change.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String mkcu = ed_mkcu.getText().toString();
                    String mkm = ed_mkmoi.getText().toString();
                    String xnmk = ed_xnmk.getText().toString();
                    Admin objAdmin = adminDAO.selectAd();

                    if(mkcu.length() == 0 || mkm.length()==0|| xnmk.length()==0){
                        Toast.makeText(getContext(), "Dữ liệu không được bỏ trống", Toast.LENGTH_SHORT).show();
                    }else if(!mkcu.equals(objAdmin.getMatKhauAdmin())){
                        Toast.makeText(getContext(), "Mật khẩu cũ không đúng", Toast.LENGTH_SHORT).show();
                    }
                    else if(mkcu.equals(mkm)){
                        Toast.makeText(getContext(), "Mật khẩu mới không hợp lệ", Toast.LENGTH_SHORT).show();
                    }else if(!mkm.equals(xnmk)){
                        Toast.makeText(getContext(), "Xác nhận mật khẩu không trùng khớp", Toast.LENGTH_SHORT).show();
                    }else{

                        objAdmin.setMatKhauAdmin(mkm);
                        int res = adminDAO.updateAdmin(objAdmin);
                        if(res > 0){
                            Toast.makeText(getContext(), "Đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getContext(), MainActivity.class);
                            startActivity(intent);

                        }else{
                            Toast.makeText(getContext(), "Đổi mật khẩu thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });

        }else if(quyen.equals("thuthu")){
            ThuThuDAO thuThuDAO = new ThuThuDAO(getContext());
            thuThuDAO.open();

            ArrayList<ThuThu> arr = thuThuDAO.selectAll();
            ThuThu objThuThu = arr.get(mainActivity.getIndex());

            btn_change.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String mkcu = ed_mkcu.getText().toString();
                    String mkm = ed_mkmoi.getText().toString();
                    String xnmk = ed_xnmk.getText().toString();

                    if(mkcu.length() == 0 || mkm.length()==0|| xnmk.length()==0){
                        Toast.makeText(getContext(), "Dữ liệu không được bỏ trống", Toast.LENGTH_SHORT).show();
                    }else if(!mkcu.equals(objThuThu.getMatKhauThuThu())){
                        Toast.makeText(getContext(), "Mật khẩu cũ không đúng", Toast.LENGTH_SHORT).show();
                    }
                    else if(mkcu.equals(mkm)){
                        Toast.makeText(getContext(), "Mật khẩu mới không hợp lệ", Toast.LENGTH_SHORT).show();
                    }else if(!mkm.equals(xnmk)){
                        Toast.makeText(getContext(), "Xác nhận mật khẩu không trùng khớp", Toast.LENGTH_SHORT).show();
                    }else{

                        objThuThu.setMatKhauThuThu(mkm);
                        int res = thuThuDAO.updateThuThu(objThuThu);
                        if(res > 0){
                            Toast.makeText(getContext(), "Đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getContext(), MainActivity.class);
                            startActivity(intent);
                        }else{
                            Toast.makeText(getContext(), "Đổi mật khẩu thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }

                }
            });
            Toast.makeText(getContext(), "Đổi mật khẩu Thủ Thư", Toast.LENGTH_SHORT).show();

        }

    }
}