package com.example.sebas.urano.Fragments;

import android.app.Activity;
import android.os.Bundle;
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
import android.widget.Toast;
import com.example.sebas.urano.Methods.SistemaDeEcuaciones;
import com.example.sebas.urano.R;

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
            TableLayout ultimaEtapa = (TableLayout) inflaterView.findViewById(R.id.vectorEtapa);
            TableLayout x = (TableLayout) inflaterView.findViewById(R.id.vectorX);
            ultimaEtapa.removeAllViews();
            x.removeAllViews();
            for(int i = 0; i < n; i++) {
                TableRow sol = new TableRow(this.getContext());
                EditText xi = new EditText(this.getContext());
                xi.setText(String.format("X%d  ", i));
                xi.setEnabled(false);
                xi.setGravity(Gravity.CENTER_HORIZONTAL);
                sol.addView(xi, 0);

                EditText number = new EditText(this.getContext());
                number.setText(String.format("%.2f", solucion[i]));
                number.setGravity(Gravity.CENTER_HORIZONTAL);
                number.setEnabled(false);
                sol.addView(number, 1);
                x.addView(sol, i);

                TableRow tr = new TableRow(this.getContext());
                for(int j = 0; j < n; j++) {
                    EditText tv = new EditText(this.getContext());
                    tv.setText(String.format("%.2f", etapa[i][j]));
                    tv.setGravity(Gravity.CENTER_HORIZONTAL);
                    tv.setEnabled(false);
                    tr.addView(tv, j);
                }
                ultimaEtapa.addView(tr, i);
                inflaterView.findViewById(R.id.textView).setVisibility(View.VISIBLE);
            }
        } catch (Exception e) {
            Toast.makeText(this.getContext(), "Por favor ingresa datos validos (?)",
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
            TableLayout x = (TableLayout) inflaterView.findViewById(R.id.vectorX);
            x.removeAllViews();
            A.removeAllViews();
            TableLayout B = crearB(n);
            for (int i = 0; i < n; i++) {
                TableRow row = new TableRow(this.getContext());
                row.setId(i);
                for (int j = 0; j < n; j++) {
                    EditText editText = new EditText(this.getContext());
                    editText.setId(n + j);
                    editText.setInputType(InputType.TYPE_NUMBER_FLAG_SIGNED
                            | InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_CLASS_NUMBER);
                    editText.setGravity(Gravity.CENTER_HORIZONTAL);
                    editText.setHint("  0  ");
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
            ed.setInputType(InputType.TYPE_NUMBER_FLAG_SIGNED
                    | InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_CLASS_NUMBER);
            ed.setGravity(Gravity.CENTER_HORIZONTAL);
            ed.setHint("  0  ");
            B.addView(ed);
        }
        return B;
    }
}
