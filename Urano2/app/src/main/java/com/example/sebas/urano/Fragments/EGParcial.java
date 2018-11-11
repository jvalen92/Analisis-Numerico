package com.example.sebas.urano.Fragments;


import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
    View inflaterView;

    public EGParcial() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Activity a = getActivity();
        // Inflate the layout for this fragment
        inflaterView = inflater.inflate(R.layout.fragment_egparcial,
                container, false);
        /*Boton Crear Matriz*/
        Button matrizBtn = (Button) inflaterView.findViewById(R.id.CrearMatriz);
        matrizBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createTable();
            }
        });
        /*Boton ejecutar metodo*/
        Button calcBtn = (Button) inflaterView.findViewById(R.id.ejecutar);
        calcBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit();
            }
        });

        return inflaterView;
    }

    /**
     * Captura los datos ingresados en la matriz
     * y ejecuta el metodo
     */
    public void submit() {
        try {
            //Obtener Datos
            int n = Integer.parseInt(((EditText)
                    inflaterView.findViewById(R.id.numero)).getText().toString());
            double A[][] = new double[n][n];
            TableLayout tb = (TableLayout) inflaterView.findViewById(R.id.vectorB);
            double b[] = new double[n];
            for (int i = 0; i < n; i++) {
                EditText ed1 = (EditText) tb.findViewById(i);
                b[i] = Double.parseDouble(ed1.getText().toString());
                TableRow row = (TableRow) inflaterView.findViewById(i);
                for (int j = 0; j < n; j++) {
                    EditText ed = (EditText)(row.findViewById(n + j));
                    A[i][j] = Double.parseDouble(ed.getText().toString());
                }
            }

            //Ejecutar Metodo
            Object retVal[] = SistemaDeEcuaciones.eliminacionGaussianaParcial(A, b);
            double etapa[][] = (double[][]) retVal[0];
            double solucion[] = (double[]) retVal[1];

            //Mostrar Solucion
            TableLayout x = (TableLayout) inflaterView.findViewById(R.id.vectorX);
            x.removeAllViews();
            for(int i = 0; i < n; i++) {
                TableRow sol = new TableRow(this.getContext());
                TextView xi = new TextView(this.getContext());
                xi.setText(String.format("X%d    ", i));
                sol.addView(xi, 0);

                TextView number = new TextView(this.getContext());
                number.setText(String.format("%f", solucion[i]));
                number.setGravity(Gravity.CENTER_HORIZONTAL);
                sol.addView(number, 1);
                x.addView(sol, i);
            }
        } catch (Exception e) {
            Toast.makeText(this.getContext(), e.getMessage(),
                    Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Este metodo se encarga de obtener la tabla de la vista y reconstruirla segun los parametros
     * definidos por el usuario
    */

    public void createTable() {
        try {
            int n = Integer.parseInt(((EditText) inflaterView.findViewById(R.id.numero)).getText().toString());
            TableLayout A = (TableLayout) inflaterView.findViewById(R.id.matrizA);
            A.removeAllViews();
            TableLayout B = crearB(n);
            for (int i = 0; i < n; i++) {
                TableRow row = new TableRow(this.getContext());
                row.setId(i);
                for (int j = 0; j < n; j++) {
                    EditText editText = new EditText(this.getContext());
                    editText.setId(n + j);
                    editText.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
                    editText.setGravity(Gravity.CENTER_HORIZONTAL);
                    editText.setText("  0  ");
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
    TableLayout crearB(int n) {
        TableLayout B = (TableLayout) inflaterView.findViewById(R.id.vectorB);
        B.removeAllViews();
        for (int i = 0; i < n; i++) {
            EditText ed = new EditText(this.getContext());
            ed.setId(i);
            ed.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
            ed.setGravity(Gravity.CENTER_HORIZONTAL);
            ed.setText("  0  ");
            B.addView(ed);
        }
        return B;
    }

    /** Solo para efectos de desarrollo y testing de la aplicacion. No debe ser usado
     en produccion.
     */
    void imprimirMatriz(double [][] A, int n) {
        System.out.println("Matriz Ingresada fue: ");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(A[i][j] + " ");
            }
            System.out.println();
        }
    }
}
