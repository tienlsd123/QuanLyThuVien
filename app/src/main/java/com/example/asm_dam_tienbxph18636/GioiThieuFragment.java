package com.example.asm_dam_tienbxph18636;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;


public class GioiThieuFragment extends Fragment {

    public GioiThieuFragment() {

    }

    public static GioiThieuFragment newInstance() {
        GioiThieuFragment fragment = new GioiThieuFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_gioi_thieu, container, false);
    }
}