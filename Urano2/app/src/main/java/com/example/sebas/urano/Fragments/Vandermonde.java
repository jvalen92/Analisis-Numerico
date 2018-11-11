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
public class Vandermonde extends Fragment {


    public Vandermonde() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_vandermonde, container, false);
    }

}
