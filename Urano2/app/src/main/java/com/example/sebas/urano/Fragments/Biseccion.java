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
public class Biseccion extends Fragment {


    public Biseccion() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.biseccion, container, false);
        return view;
    }

}
