package com.example.sebas.urano.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sebas.urano.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ReglaFalsa extends Fragment {


    public ReglaFalsa() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.regla_falsa, container, false);
        return view;
    }

}
