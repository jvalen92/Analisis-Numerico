package com.example.sebas.urano.Fragments;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.sebas.urano.Ayudas.AyudaSecante;
import com.example.sebas.urano.CuadroDialogo;
import com.example.sebas.urano.Funciones;
import com.example.sebas.urano.Methods.SingletonMensaje;
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
public class Secante extends Fragment {

    View vista;

    int cont =0;
    final String funciones[] = {"x^3 + 4*(x^2) - 10","0"};
    final String x1[] = {"1.3","0"};
    final String x2[] = {"1.4","0"};
    final String tol[] = {"0.5e-10","0"};

    ImageButton btn;
    EditText in1;
    EditText in2;
    EditText in3;
    EditText in4;
    EditText in5;

    SingletonMensaje singletonMensaje = SingletonMensaje.getInstance();

    public Secante() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        vista=inflater.inflate(R.layout.fragment_secante, container, false);
        random();
        borrar();
        spinner();
        submit();
        graficar();
        help();
        return vista;
    }


    //apretar boton
    public void submit(){
        btn = (ImageButton) vista.findViewById(R.id.s_submit);
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
                    ArrayList<String[]> solucion = UnaVariable.secante(fx,tol,x1,x2,niter);

                    if(singletonMensaje.getError()) {
                        Toast.makeText(getContext(), singletonMensaje.getMensajeActual(), Toast.LENGTH_LONG).show();
                        String err = singletonMensaje.getMensajeActual();
                        openDialog("Error",err);
                    } else {
                        //TableView
                        Context context = getContext();
                        TableView<String[]> tableView = (TableView<String[]>) vista.findViewById(R.id.tableView);
                        tableView.setColumnCount(solucion.get(0).length);

                        //Lenar tabla
                        int n_columns=solucion.get(0).length;
                        tableView.setColumnCount(n_columns);
                        String headers[]={"i","xm","fx","Error Absoluto", "Error Relativo"};
                        tableView.setHeaderAdapter(new SimpleTableHeaderAdapter(context,headers));


                        //tableView.setDataAdapter(new SimpleTableDataAdapter(context, DATA_TO_SHOW));

                        //Ajustar tamaño de las columnas
                        TableColumnDpWidthModel columnModel = new TableColumnDpWidthModel(context, n_columns, 125);
                        columnModel.setColumnWidth(0, 50);
                        columnModel.setColumnWidth(n_columns-2, 170);
                        columnModel.setColumnWidth(n_columns-1, 160);
                        tableView.setColumnModel(columnModel);

                        tableView.setDataAdapter(new SimpleTableDataAdapter(context, solucion));

                        //cambiar el color de la tabla para que se vea mas kawai
                        tableView.setHeaderBackground(R.color.colorPrimary);
                        Toast.makeText(getContext(), singletonMensaje.getMensajeActual(), Toast.LENGTH_LONG).show();
                        String err = singletonMensaje.getMensajeActual();
                        openDialog("solucion",err);
                    }
                }catch (Exception e) {
                    Toast.makeText(getContext(),"Llene todos los campos y verifique lso datos",Toast.LENGTH_LONG).show();
                }


            }
        });
    }

    public void openDialog(String tittle,String msg){
        CuadroDialogo dialogo = new CuadroDialogo();
        dialogo.setText(msg);
        dialogo.setTittle(tittle);
        dialogo.show(getFragmentManager(),"Biseccion");
    }


    public void random(){

        ImageButton btn_ran = (ImageButton) vista.findViewById(R.id.s_rand);
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
        in1 = (EditText) vista.findViewById(R.id.s_x1); //x1
        in2 = (EditText) vista.findViewById(R.id.s_x2); //x2
        in3 = (EditText) vista.findViewById(R.id.s_tol); //tol
        in4 = (EditText) vista.findViewById(R.id.s_niter); //niter
        in5 = (EditText) vista.findViewById(R.id.s_fx); //fx
    }

    public void borrar(){
        //borrar los datos de los campos de texto
        ImageButton btn_clean = (ImageButton) vista.findViewById(R.id.s_borrar);
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
        ImageButton btn_graf = (ImageButton) vista.findViewById(R.id.s_graf);
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

    //Metodo para ejecutar las acciones necesarias para mostrar las ayudas
    public void help(){
        ImageButton btn= (ImageButton) vista.findViewById(R.id.s_help);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Aqui haces lo que quieras para mostrar las ayudas
                //Toast.makeText(getContext(),"Ayudas",Toast.LENGTH_LONG).show();
                startActivity(new Intent(getActivity(), AyudaSecante.class));

            }
        });
    }

    // Lista despleglable para escoger la tolerancia del metodo
    public void spinner(){
        Spinner spinner;
        final String datos[]={"10e-7","0.5e-6","1e-5","0.5e-8"};

        spinner = (Spinner) vista.findViewById(R.id.s_spinner);
        Context context=getContext();
        ArrayAdapter<String> adapter =new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item,datos);
        spinner.setAdapter(adapter);

        //seleccionar un elemento de la lista y escribir en el campo de tol
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                EditText tol =(EditText) vista.findViewById(R.id.s_tol);
                switch (position){

                    case 0:
                        tol.setText("");
                        tol.setText(datos[position]);
                        //Toast.makeText(getContext(),datos[position],Toast.LENGTH_LONG).show();

                    case 1:
                        tol.setText("");
                        tol.setText(datos[position]);
                        //Toast.makeText(getContext(),datos[position],Toast.LENGTH_LONG).show();

                    case 2:
                        borrar();
                        tol.setText("");
                        tol.setText(datos[position]);
                        //Toast.makeText(getContext(),datos[position],Toast.LENGTH_LONG).show();
                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

}
