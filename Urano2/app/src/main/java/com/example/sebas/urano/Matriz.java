package com.example.sebas.urano;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;
import java.util.List;
import de.codecrafters.tableview.TableView;

public class Matriz extends AppCompatActivity {
    private static final String[][] DATA_TO_SHOW = {{"This", "is", "a", "test"},
            {"and", "a", "second", "test"}};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matriz);
    }

    public void holi(View view) {
        EditText ed = (EditText) findViewById(R.id.numero);
        Toast.makeText(Matriz.this, ed.getText().toString(), Toast.LENGTH_LONG).show();
    }

    //Este metodo se encarga de capturar los datos ingresados en la matriz
    public void submit(View view) {
        try {
            int n = Integer.parseInt(((EditText) findViewById(R.id.numero)).getText().toString());
            TableLayout table = (TableLayout) findViewById(R.id.tableLayout);
            String data[][] = new String[n][n];
            for (int j = 0; j < n; j++) {
                TableRow row = (TableRow) this.findViewById(j);
                for (int i = 0; i < n; i++) {
                    EditText ed = (EditText)(row.findViewById(i + j));
                    data[i][j] = ed.getText().toString();
                }
            }
            Toast.makeText(Matriz.this, "Exit Success ", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(Matriz.this, "Por favor ingresa una matriz valida ",
                    Toast.LENGTH_LONG).show();
        }
    }

    //Este metodo se encarga de obtener la tabla de la vista y reconstruirla segun los parametros
    //definidos por el usuario
    public void createTable(View view) {
        try {
            int n = Integer.parseInt(((EditText) findViewById(R.id.numero)).getText().toString());
            TableLayout table = (TableLayout) findViewById(R.id.tableLayout);
            for (int j = 0; j < n; j++) {
                TableRow row = new TableRow(this); //Este puede ser el bug
                row.setId(j);
                for (int i = 0; i < n; i++) {
                    EditText editText = new EditText(this); //Este puede ser el bug
                    editText.setId(i + j);
                    editText.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL);
                    editText.setText("0");
                    row.addView(editText);

                }
                table.addView(row);
            }
        } catch (Exception e) {
            Toast.makeText(Matriz.this, "Por favor ingresa un numero",
                    Toast.LENGTH_LONG).show();
        }
    }
}