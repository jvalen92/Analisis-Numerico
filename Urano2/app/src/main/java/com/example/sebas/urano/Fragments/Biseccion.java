package com.example.sebas.urano.Fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sebas.urano.R;
import com.example.sebas.urano.metodos.UnaVariable;

/**
 * A simple {@link Fragment} subclass.
 */
public class Biseccion extends Fragment {
    View vista;
    Button btn;
    EditText in1;
    EditText in2;
    EditText in3;
    EditText in4;

    UnaVariable uv = new UnaVariable();



    public Biseccion() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        vista = inflater.inflate(R.layout.biseccion, container, false);
        //Leer datos de la intefaz





        //Ejecutar metodo
        submit();
        return vista;
    }

    //apretar boton
    public void submit(){
        btn = (Button) vista.findViewById(R.id.b_submit);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context con = getContext();

                //obtener valores de la interfaz
                in1 = (EditText) vista.findViewById(R.id.bt1);
                in2 =(EditText) vista.findViewById(R.id.bt2);
                in3 = (EditText) vista.findViewById(R.id.bt3);
                in4 = (EditText) vista.findViewById(R.id.bt4);

                //pasar de String a Double
                double x1 = Double.parseDouble(in1.getText().toString());
                double x2 = Double.parseDouble(in2.getText().toString());
                double tol = Double.parseDouble(in3.getText().toString());

                //pasar de String a entero
                Integer niter = Integer.valueOf(in4.getText().toString());
                String txt = uv.biseccion(x1,x2,tol,niter);
                Toast.makeText(con,txt,Toast.LENGTH_LONG).show();
            }
        });
    }
    public static void test(){

    }
}
