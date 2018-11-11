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
import android.widget.TextView;
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
public class PuntoFijo extends Fragment {
    int cont =0;
    final String funciones[] = {"x^3 + 4*(x^2) - 10","0"};
    final String gx[] = {"(10/(4+x))^0.5","0"};
    final String x1[] = {"1.5","0"};
    final String tol[] = {"0.5e-6","0"};

    UnaVariable uv;
    //Button btn;
    View vista;
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
        vista = inflater.inflate(R.layout.punto_fijo, container, false);
        random();
        borrar();
        submit();
        graficar();
        return vista;
    }

    public void submit(){
        ImageButton btn= (ImageButton) vista.findViewById(R.id.p_submit);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    //Obtener datos de la intefaz
                    obtDatos();

                    //parser para correr los metodos
                    String fx = in1.getText().toString();
                    String gx = in2.getText().toString();
                    double x1 = Double.parseDouble(in3.getText().toString());
                    int niter = Integer.parseInt(in5.getText().toString());
                    double tol = Double.parseDouble(in4.getText().toString());
                    //ejecutar el metodo
                    ArrayList<String[]> solucion =UnaVariable.puntoFijo(fx,gx,tol,x1,niter);

                    //TableView
                    Context context = getContext();
                    TableView<String[]> tableView = (TableView<String[]>) vista.findViewById(R.id.tableView);

                    //Lenar tabla
                    int n_columns=solucion.get(0).length;
                    tableView.setColumnCount(n_columns);
                    String headers[]={"i","xi","fx","Error Absoluto", "Error Relativo"};
                    tableView.setHeaderAdapter(new SimpleTableHeaderAdapter(context,headers));

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

        ImageButton btn_ran = (ImageButton) vista.findViewById(R.id.p_rand);
        btn_ran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //obtener datos de la interfaz
                obtDatos();
                //Cambiar llenar los campos con los valores de los arreglos
                in1.setText(funciones[cont]);
                in2.setText(gx[cont]);
                in3.setText((x1[cont]));
                in4.setText(tol[cont]);
                in5.setText("80");

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
        in1 = (EditText) vista.findViewById(R.id.p_tfx); //fx
        in2 = (EditText) vista.findViewById(R.id.p_gx); //gx
        in3 = (EditText) vista.findViewById(R.id.p_tx1); //xi
        in4 = (EditText) vista.findViewById(R.id.p_tol); //tol
        in5 = (EditText) vista.findViewById(R.id.p_tniter); //niter


    }

    public void borrar(){
        //borrar los datos de los campos de texto
        ImageButton btn_clean = (ImageButton) vista.findViewById(R.id.p_borrar);
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
        ImageButton btn_graf = (ImageButton) vista.findViewById(R.id.p_graf);
        btn_graf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    if (in5.getText().toString() != null){
                        Intent fun = new Intent(getActivity(), Funciones.class);
                        fun.putExtra("funcion",in1.getText().toString());
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

    public void setTable(ArrayList<String> solucion,String [] header){



    }


}
