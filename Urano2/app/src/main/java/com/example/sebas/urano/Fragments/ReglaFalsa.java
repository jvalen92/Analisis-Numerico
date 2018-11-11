package com.example.sebas.urano.Fragments;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.sebas.urano.Funciones;
import com.example.sebas.urano.Methods.UnaVariable;
import com.example.sebas.urano.R;

import java.util.ArrayList;

import de.codecrafters.tableview.TableView;
import de.codecrafters.tableview.model.TableColumnDpWidthModel;
import de.codecrafters.tableview.toolkit.SimpleTableDataAdapter;
import de.codecrafters.tableview.toolkit.SimpleTableHeaderAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class ReglaFalsa extends Fragment {


    int cont =0;
    final String funciones[] = {"x^3 + 4*(x^2) - 10","0"};
    final String x1[] = {"1","0"};
    final String x2[] = {"2","0"};
    final String tol[] = {"0.5e-6","0"};

    View vista;
    ImageButton btn;
    EditText in1;
    EditText in2;
    EditText in3;
    EditText in4;
    EditText in5;

    UnaVariable uv;

    public ReglaFalsa() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        vista = inflater.inflate(R.layout.regla_falsa, container, false);


        random();
        borrar();
        //Ejecutar metodo
        submit();
        graficar();
        return vista;
    }


    //apretar boton
    public void submit(){
        btn = (ImageButton ) vista.findViewById(R.id.rf_submit);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //obtener valores de la interfaz
                obtDatos();

                try {

                    //obtener funcion para el metodo
                    String fx = in5.getText().toString();

                    //Casteo de Double a String
                    double x1 = Double.parseDouble(in1.getText().toString());
                    double x2 = Double.parseDouble(in2.getText().toString());
                    double tol = Double.parseDouble(in3.getText().toString());

                    //pasar de String a entero
                    Integer niter = Integer.valueOf(in4.getText().toString());

                    //ejecutar el metodo
                    ArrayList<String[]> solucion =UnaVariable.reglaFalsa(fx,x1,x2,tol,niter);


                    //TableView
                    Context context = getContext();
                    TableView<String[]> tableView = (TableView<String[]>) vista.findViewById(R.id.tableView);

                    int n_columns=solucion.get(0).length;
                    tableView.setColumnCount(n_columns);
                    tableView.setHeaderAdapter(new SimpleTableHeaderAdapter(context,"i","xm","fx","Error Absoluto","Error Relativo"));

                    //Ajustar tamaño de las columnas
                    TableColumnDpWidthModel columnModel = new TableColumnDpWidthModel(context, n_columns, 125);
                    columnModel.setColumnWidth(0, 50);
                    columnModel.setColumnWidth(n_columns-2, 170);
                    columnModel.setColumnWidth(n_columns-1, 160);
                    tableView.setColumnModel(columnModel);
                    tableView.setDataAdapter(new SimpleTableDataAdapter(context, solucion));

                    //cambiar el color de la tabla para que se vea mas kawai
                    tableView.setHeaderBackground(R.color.colorPrimary);

                }catch (Exception e) {
                    Toast.makeText(getContext(),"Llene todos los campos y verifique lso datos",Toast.LENGTH_LONG).show();
                }


            }
        });
    }
    public void random(){

        ImageButton btn_ran = (ImageButton) vista.findViewById(R.id.rf_rand);
        btn_ran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //obtener datos de la interfaz
                obtDatos();
                //Cambiar llenar los campos con los valores de los arreglos
                in5.setText(funciones[cont]);
                in1.setText((x1[cont]));
                in2.setText((x2[cont]));
                in3.setText(tol[cont]);
                in4.setText("80");

                if (cont >= funciones.length -1){
                    cont=0;
                }else {
                    cont++;
                }

            }
        });
    }

    public void obtDatos(){
        //obtener valores de la interfaz
        in1 = (EditText) vista.findViewById(R.id.rf_x1); //x1
        in2 = (EditText) vista.findViewById(R.id.rf_x2); //x2
        in3 = (EditText) vista.findViewById(R.id.rf_tol); //tol
        in4 = (EditText) vista.findViewById(R.id.rf_niter); //niter
        in5 = (EditText) vista.findViewById(R.id.rf_fx); //fx
    }

    public void borrar(){
        //borrar los datos de los campos de texto
        ImageButton btn_clean = (ImageButton) vista.findViewById(R.id.rf_borrar);
        btn_clean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                obtDatos();
                in5.setText(null);
                in1.setText(null);
                in2.setText(null);
                in3.setText(null);
                in4.setText(null);
                cont=0;
            }
        });

    }

    public void graficar(){
        ImageButton btn_graf = (ImageButton) vista.findViewById(R.id.rf_graf);
        btn_graf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    if (in5.getText().toString() != null){
                        Intent fun = new Intent(getActivity(), Funciones.class);
                        fun.putExtra("funcion",in5.getText().toString());
                        startActivity(fun);
                    }else {
                        Toast.makeText(getContext(),"Ingresa la funcion a graficar",Toast.LENGTH_LONG).show();
                    }
                }catch (Exception e){
                    Toast.makeText(getContext(),"Llene todos los campos y verifique los datos",Toast.LENGTH_LONG).show();
                }

            }
        });
    }

}
