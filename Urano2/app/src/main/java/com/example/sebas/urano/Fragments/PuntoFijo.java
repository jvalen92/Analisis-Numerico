package com.example.sebas.urano.Fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sebas.urano.Methods.UnaVariable;
import com.example.sebas.urano.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PuntoFijo extends Fragment {
    UnaVariable uv;
    Button btn;
    View view;
    TextView out;
    EditText in1;
    EditText in2;
    EditText in3;
    EditText in4;
    EditText in5;


    public PuntoFijo() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.punto_fijo, container, false);
        submit();
        return view;
    }

    public void submit(){
        btn= (Button) view.findViewById(R.id.p_submit);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context con = getContext();

                //Obtener datos de la intefaz
                in3 = (EditText) view.findViewById(R.id.p_tx1);
                in4 = (EditText) view.findViewById(R.id.p_tol);
                in5 = (EditText) view.findViewById(R.id.p_tniter);

                //parser para correr los metodos
                double x1 = Double.parseDouble(in3.getText().toString());
                int niter = Integer.parseInt(in5.getText().toString());
                double tol = Double.parseDouble(in4.getText().toString());

                //String txt = uv.puntoFijo(tol,x1,niter);
                //Toast.makeText(con,txt,Toast.LENGTH_LONG).show();

                /*
                //Parser para pasar como parametro al metodo solicitado

                //String fx = in1.getText().toString();
                //String fprima = in2.getText().toString();
                double x1 = Double.parseDouble(in3.getText().toString());
                int niter = Integer.parseInt(in5.getText().toString());
                double tol = Double.parseDouble(in5.getText().toString());

                //salida del metodo de punto fijo con los datos de la interfaz como parametros
                String txt = uv.puntoFijo(tol,x1,niter);
                String txt2 = uv.biseccion(x1,0,tol,niter);
                Toast.makeText(con,txt2,Toast.LENGTH_LONG).show();
                //out.setText(s_out);*/


            }
        });
    }

}
