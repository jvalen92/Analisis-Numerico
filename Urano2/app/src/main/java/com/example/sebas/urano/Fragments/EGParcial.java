package com.example.sebas.urano.Fragments;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sebas.urano.Matriz;
import com.example.sebas.urano.Methods.SistemaDeEcuaciones;
import com.example.sebas.urano.R;
import com.google.common.collect.Table;

/**
 * A simple {@link Fragment} subclass.
 */
public class EGParcial extends Fragment {

    public EGParcial() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_egparcial, container, false);
    }

    /**
     * Captura los datos ingresados en la matriz
     * y ejecuta el metodo
     */
    public void submit(View view) {
        try {
            int n = Integer.parseInt(((EditText) view.findViewById(R.id.numero)).getText().toString());
            double A[][] = new double[n][n];
            TableLayout tb = (TableLayout) view.findViewById(R.id.vectorB);
            double b[] = new double[n];
            for (int i = 0; i < n; i++) {
                EditText ed1 = (EditText) tb.findViewById(i);
                b[i] = Double.parseDouble(ed1.getText().toString());
                TableRow row = (TableRow) view.findViewById(i);
                for (int j = 0; j < n; j++) {
                    EditText ed = (EditText)(row.findViewById(n + j));
                    A[i][j] = Double.parseDouble(ed.getText().toString());
                }
            }
            double solucion [] = SistemaDeEcuaciones.eliminacionGaussianaParcial(A, b);
        } catch (Exception e) {
            Toast.makeText(this.getContext(), e.getMessage(),
                    Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Este metodo se encarga de obtener la tabla de la vista y reconstruirla segun los parametros
     * definidos por el usuario
    */
    public void createTable(View view) {
        try {
            int n = Integer.parseInt(((EditText) view.findViewById(R.id.numero)).getText().toString());
            TableLayout A = (TableLayout) view.findViewById(R.id.matrizA);
            A.removeAllViews();
            TableLayout B = crearB(view, n);
            for (int i = 0; i < n; i++) {
                TableRow row = new TableRow(this.getContext());
                row.setId(i);
                for (int j = 0; j < n; j++) {
                    EditText editText = new EditText(this.getContext());
                    editText.setId(n + j);
                    editText.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL);
                    editText.setText("0");
                    row.addView(editText);
                }
                A.addView(row);
            }
        } catch (Exception e) {
            Toast.makeText(this.getContext(), "Por favor ingresa un numero",
                    Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Crea la matriz b y la muestra al usuario
     */
    TableLayout crearB(View view, int n) {
        TableLayout B = (TableLayout) view.findViewById(R.id.vectorB);
        B.removeAllViews();
        for (int i = 0; i < n; i++) {
            EditText ed = new EditText(this.getContext());
            ed.setId(i);
            ed.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL);
            ed.setText("0");
            B.addView(ed);
        }
        return B;
    }
}
