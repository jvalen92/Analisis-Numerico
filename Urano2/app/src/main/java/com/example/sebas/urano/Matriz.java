package com.example.sebas.urano;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

import com.example.sebas.urano.Methods.SistemaDeEcuaciones;

public class Matriz extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matriz);
    }

    /**
        Captura los datos ingresados en la matriz
        Y ejecuta el metodo
     */
    public void submit(View view) {
        try {
            int n = Integer.parseInt(((EditText) findViewById(R.id.numero)).getText().toString());
            double A[][] = new double[n][n];
             TableLayout tb = (TableLayout) findViewById(R.id.b);
            double b[] = new double[n];
            for (int i = 0; i < n; i++) {
                EditText ed1 = (EditText) tb.findViewById(i);
                b[i] = Double.parseDouble(ed1.getText().toString());
                TableRow row = (TableRow) this.findViewById(i);
                for (int j = 0; j < n; j++) {
                    EditText ed = (EditText)(row.findViewById(n + j));
                    A[i][j] = Double.parseDouble(ed.getText().toString());
                }
            }
            double solucion [] = SistemaDeEcuaciones.eliminacionGaussianaParcial(A, b);
        } catch (Exception e) {
            Toast.makeText(Matriz.this, e.getMessage(),
                    Toast.LENGTH_LONG).show();
        }
    }

    /*
        Este metodo se encarga de obtener la tabla de la vista y reconstruirla segun los parametros
        definidos por el usuario
    */
    public void createTable(View view) {
        try {
            int n = Integer.parseInt(((EditText) findViewById(R.id.numero)).getText().toString());
            TableLayout A = (TableLayout) findViewById(R.id.A);
            TableLayout b = crearB(view, n);
            A.removeAllViews();
            for (int i = 0; i < n; i++) {
                TableRow row = new TableRow(this);
                row.setId(i);
                for (int j = 0; j < n; j++) {
                    EditText editText = new EditText(this);
                    editText.setId(n + j);
                    editText.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL);
                    editText.setText("0");
                    row.addView(editText);
                }
                A.addView(row);
            }
        } catch (Exception e) {
            Toast.makeText(Matriz.this, "Por favor ingresa un numero",
                    Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Crea la matriz b y la muestra al usuario
     * */
    TableLayout crearB(View view, int n) {
        TableLayout b = (TableLayout) findViewById(R.id.b);
        b.removeAllViews();
        for (int i = 0; i < n; i++) {
            EditText ed = new EditText(this);
            ed.setId(i);
            ed.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL);
            ed.setText("0");
            b.addView(ed);
        }
        return b;
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