package com.example.sebas.urano.Fragments;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabItem;
import android.support.v4.app.Fragment;
import android.support.v7.app.WindowDecorActionBar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.example.sebas.urano.MainActivity;
import com.example.sebas.urano.Methods.UnaVariable;
import com.example.sebas.urano.R;

import java.util.ArrayList;

import de.codecrafters.tableview.TableView;
import de.codecrafters.tableview.toolkit.SimpleTableDataAdapter;
import de.codecrafters.tableview.toolkit.SimpleTableHeaderAdapter;


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
    EditText in5;

    UnaVariable uv;



    public Biseccion() {
        // Required empty public constructor
    }

    String[][] DATA_TO_SHOW = { { "This", "is", "a", "test" },
            { "and", "a", "second", "test" },{ "This", "is", "a", "test" },
            { "and", "a", "second", "test" } ,{ "This", "is", "a", "test" },
            { "and", "a", "second", "test" } ,{ "This", "is", "a", "test" },
            { "and", "a", "second", "test" } ,{ "This", "is", "a", "test" },
            { "and", "a", "second", "test" }};
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

                //obtener valores de la interfaz
                in1 = (EditText) vista.findViewById(R.id.bt1);
                in2 = (EditText) vista.findViewById(R.id.bt2);
                in3 = (EditText) vista.findViewById(R.id.bt3);
                in4 = (EditText) vista.findViewById(R.id.bt4);
                in5 = (EditText) vista.findViewById(R.id.bt5);


                //obtener funcion para el metodo
                String fx = in5.getText().toString();

                double x1 = Double.parseDouble(in1.getText().toString());
                double x2 = Double.parseDouble(in2.getText().toString());
                double tol = Double.parseDouble(in3.getText().toString());

                //pasar de String a entero
                Integer niter = Integer.valueOf(in4.getText().toString());

                //ejecutar el metodo
                ArrayList<String[]> solucion =UnaVariable.biseccion(fx,x1,x2,tol,niter);


                //pasar de String a Double




                Activity activity = getActivity();
                Context context = getContext();
                String txt=in1.getText().toString();
                Toast.makeText(context,txt,Toast.LENGTH_LONG).show();
                TableView<String[]> tableView = (TableView<String[]>) vista.findViewById(R.id.tableView);
                tableView.setColumnCount(solucion.get(0).length);
                //tableView.setHeaderAdapter(new SimpleTableHeaderAdapter(context,"i","x1","fx","error"));
                //tableView.setDataAdapter(new SimpleTableDataAdapter(context, DATA_TO_SHOW));
                tableView.setDataAdapter(new SimpleTableDataAdapter(context, solucion));

                tableView.setHeaderBackground(R.color.colorAccent);



                //String txt = uv.biseccion(x1,x2,tol,niter);
                //Toast.makeText(con,txt,Toast.LENGTH_LONG).show();
            }
        });
    }
    public static void test(){

    }
}
