package com.example.sebas.urano.Fragments;


import android.content.Context;
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

import java.util.ArrayList;

import de.codecrafters.tableview.TableView;
import de.codecrafters.tableview.model.TableColumnDpWidthModel;
import de.codecrafters.tableview.toolkit.SimpleTableDataAdapter;
import de.codecrafters.tableview.toolkit.SimpleTableHeaderAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class Jacobi extends Fragment {

    View inflaterView;

    public Jacobi() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        inflaterView = inflater.inflate(R.layout.fragment_jacobi,
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
                    inflaterView.findViewById(R.id.j_numero)).getText().toString());
            TableLayout ta = (TableLayout) inflaterView.findViewById(R.id.j_matrizA);
            double A[][] = new double[n][n];
            TableLayout tb = (TableLayout) inflaterView.findViewById(R.id.j_vectorB);
            double b[] = new double[n];
            double x0[] = new double[n];
            TableLayout x = (TableLayout) inflaterView.findViewById(R.id.j_vectorX);
            for (int i = 0; i < n; i++) {
                EditText ed1 = (EditText) tb.findViewById(i);
                b[i] = Double.parseDouble(ed1.getText().toString());

                //EditText ed2 = (EditText) x.findViewById(i * 10);
                EditText ed2 = (EditText) x.findViewById(i);
                x0[i] = Double.parseDouble(ed2.getText().toString());


                TableRow row = (TableRow) ta.findViewById(i);
                for (int j = 0; j < n; j++) {
                    EditText ed = (EditText)(row.findViewById(n + j));
                    A[i][j] = Double.parseDouble(ed.getText().toString());
                }
            }
            double tol = Double.parseDouble(inflaterView.findViewById(R.id.j_tol).toString());
            int niter = Integer.parseInt(inflaterView.findViewById(R.id.j_niter).toString());

            //Ejecutar Metodo


            ArrayList <String[]> solucion = SistemaDeEcuaciones.jacobi(A, b, tol, x0, niter);

            //Mostrar Solucion

            //TableView
            Context context = getContext();
            TableView<String[]> tableView = (TableView<String[]>) inflaterView.findViewById(R.id.tableView);

            //Lenar tabla
            int n_columns=solucion.get(0).length;
            tableView.setColumnCount(n_columns);
            //tableView.setHeaderAdapter(new SimpleTableHeaderAdapter(context,"i","xi","xs","xm","fx","Error Absoluto","Error Relativo"));

            //Ajustar tamaño de las columnas
            TableColumnDpWidthModel columnModel = new TableColumnDpWidthModel(context, n_columns, 125);
            columnModel.setColumnWidth(0, 50);
            columnModel.setColumnWidth(n_columns-2, 170);
            columnModel.setColumnWidth(n_columns-1, 160);
            tableView.setColumnModel(columnModel);
            tableView.setDataAdapter(new SimpleTableDataAdapter(context, solucion));

            //cambiar el color de la tabla para que se vea mas kawai
            tableView.setHeaderBackground(R.color.colorPrimary);



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
            int n = Integer.parseInt(((EditText) inflaterView.findViewById(R.id.j_numero)).getText().toString());
            TableLayout A = (TableLayout) inflaterView.findViewById(R.id.j_matrizA);
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
            Toast.makeText(this.getContext(), e.getMessage(),
                    Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Crea la matriz b y la muestra al usuario
     */
    TableLayout crearB(int n) {
        TableLayout x = (TableLayout) inflaterView.findViewById(R.id.j_vectorX);
        x.removeAllViews();
        TableLayout B = (TableLayout) inflaterView.findViewById(R.id.j_vectorB);
        B.removeAllViews();
        for (int i = 0; i < n; i++) {
            EditText edb = new EditText(this.getContext());
            edb.setId(i);
            edb.setInputType(InputType.TYPE_NUMBER_FLAG_SIGNED
                    | InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_CLASS_NUMBER);
            edb.setGravity(Gravity.CENTER_HORIZONTAL);
            edb.setHint("  0  ");
            B.addView(edb);

            EditText edx = new EditText(this.getContext());
            //edb.setId(i*10);
            edb.setId(i);
            edb.setInputType(InputType.TYPE_NUMBER_FLAG_SIGNED
                    | InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_CLASS_NUMBER);
            edb.setGravity(Gravity.CENTER_HORIZONTAL);
            edb.setHint("  0  ");
            x.addView(edx);
        }
        return B;
    }
}