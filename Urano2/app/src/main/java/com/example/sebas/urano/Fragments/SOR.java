package com.example.sebas.urano.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.sebas.urano.Ayudas.AyudaSOR;
import com.example.sebas.urano.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SOR extends Fragment {

    private View inflaterView;
    public SOR() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //inflaterView = inflater.inflate(R.layout.fragment_sor,
        //        container, false);
        //help();
        return inflater.inflate(R.layout.fragment_sor, container, false);
    }
    public void help(){
        ImageButton btn= (ImageButton) inflaterView.findViewById(R.id.b_help);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Aqui haces lo que quieras para mostrar las ayudas
                //Toast.makeText(getContext(),"Ayudas",Toast.LENGTH_LONG).show();
                startActivity(new Intent(getActivity(), AyudaSOR.class));
            }
        });
    }

}
