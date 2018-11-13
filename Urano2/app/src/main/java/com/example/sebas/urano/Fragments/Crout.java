package com.example.sebas.urano.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sebas.urano.Ayudas.AyudaCrout;
import com.example.sebas.urano.Ayudas.AyudaEGParcial;
import com.example.sebas.urano.Methods.SistemaDeEcuaciones;
import com.example.sebas.urano.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Crout extends Fragment {
    private View inflaterView;
    private double[][] L, U;
    private boolean l = true;

    public Crout() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        inflaterView = inflater.inflate(R.layout.fragment_crout,
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

        /*Boton para cambiar LU*/
        Button luBtn = (Button) inflaterView.findViewById(R.id.luBtn);
        luBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarLU();
            }
        });

        help();
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
            Object retVal[] = SistemaDeEcuaciones.croutSolver(A, b);
            L = (double[][]) retVal[0];
            U = (double[][]) retVal[1];
            double solucion[] = (double[]) retVal[2];

            //Mostrar Solucion
            inflaterView.findViewById(R.id.textViewLU).setVisibility(View.VISIBLE);
            inflaterView.findViewById(R.id.luBtn).setVisibility(View.VISIBLE);
            inflaterView.findViewById(R.id.LU).setVisibility(View.VISIBLE);
            mostrarLU();
            TableLayout x = (TableLayout) inflaterView.findViewById(R.id.vectorX);
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
            }
        } catch (Exception e) {
            Toast.makeText(this.getContext(), "Por favor ingresa datos validos. (?)",
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
            inflaterView.findViewById(R.id.textViewLU).setVisibility(View.INVISIBLE);
            inflaterView.findViewById(R.id.luBtn).setVisibility(View.INVISIBLE);
            inflaterView.findViewById(R.id.LU).setVisibility(View.INVISIBLE);
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

    /**
     * Intercambia LU de la vista
     * */
    public void mostrarLU() {
        Button luBtn = (Button) inflaterView.findViewById(R.id.luBtn);
        TableLayout LU = (TableLayout) inflaterView.findViewById(R.id.LU);
        LU.removeAllViews();
        l = !l;
        int n = L.length;
        double mat[][] = l ? L : U;

        for(int i = 0; i < n; i++) {
            TableRow tr = new TableRow(this.getContext());
            for (int j = 0; j < n; j++) {
                EditText tv = new EditText(this.getContext());
                tv.setText(String.format("%.2f", mat[i][j]));
                tv.setGravity(Gravity.CENTER_HORIZONTAL);
                tv.setEnabled(false);
                tr.addView(tv, j);
            }
            LU.addView(tr, i);
        }
        TextView tv = (TextView) inflaterView.findViewById(R.id.textViewLU);
        if(l) {
            tv.setText("Matriz L");
            luBtn.setText("Ver U");
        } else {
            tv.setText("Matriz U");
            luBtn.setText("Ver L");
        }
        tv.setVisibility(View.VISIBLE);
        luBtn.setVisibility(View.VISIBLE);
    }

    public void help(){
        ImageButton btn= (ImageButton) inflaterView.findViewById(R.id.b_help);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Aqui haces lo que quieras para mostrar las ayudas
                //Toast.makeText(getContext(),"Ayudas",Toast.LENGTH_LONG).show();
                startActivity(new Intent(getActivity(), AyudaCrout.class));
            }
        });
    }

}
