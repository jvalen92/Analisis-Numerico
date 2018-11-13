package com.example.sebas.urano.Fragments;


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

import com.example.sebas.urano.Methods.Interpolacion;
import com.example.sebas.urano.R;

import io.github.kexanie.library.MathView;

/**
 * A simple {@link Fragment} subclass.
 */
public class Lagrange extends Fragment {

    View inflaterView;

    public Lagrange() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        inflaterView = inflater.inflate(R.layout.fragment_lagrange,
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
            TableLayout A = (TableLayout) inflaterView.findViewById(R.id.matrizA);

            double x[] = new double[n];
            double y[] = new double[n];
            TableRow X = (TableRow) A.findViewById(300);
            TableRow Y = (TableRow) A.findViewById(301);

            for (int i = 0; i < n; i++) {
                EditText xi = (EditText) X.findViewById(n + i);
                x[i] = Double.parseDouble(xi.getText().toString());

                EditText yi = (EditText) Y.findViewById(n + i * 10);
                y[i] = Double.parseDouble(yi.getText().toString());
            }

            //Ejecutar Metodo
            String solucion = Interpolacion.lagrange(x, y);

            //Mostrar Solucion
            MathView mv = (MathView) inflaterView.findViewById(R.id.poly);
            mv.setText(solucion);

        } catch (Exception e) {
            Toast.makeText(this.getContext(), "Ingresa datos validos. (?)",
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
            TableRow row0 = new TableRow(this.getContext());
            row0.setId(300 - 1 + 1);
            TableRow row1 = new TableRow(this.getContext());
            row1.setId(301 - 1 + 1);

            for (int i = 0; i < n; i++) {
                EditText editText = new EditText(this.getContext());
                editText.setId(n + i);
                editText.setInputType(InputType.TYPE_NUMBER_FLAG_SIGNED
                        | InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_CLASS_NUMBER);
                editText.setGravity(Gravity.CENTER_HORIZONTAL);
                editText.setHint("  0  ");
                row0.addView(editText);

                EditText edit = new EditText(this.getContext());
                edit.setId(n + i * 10);
                edit.setInputType(InputType.TYPE_NUMBER_FLAG_SIGNED
                        | InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_CLASS_NUMBER);
                edit.setGravity(Gravity.CENTER_HORIZONTAL);
                edit.setHint("  0  ");
                row1.addView(edit);
            }
            A.addView(row0);
            A.addView(row1);
        } catch (Exception e) {
            Toast.makeText(this.getContext(), "Por favor ingresa un numero",
                    Toast.LENGTH_LONG).show();
        }
    }

}
