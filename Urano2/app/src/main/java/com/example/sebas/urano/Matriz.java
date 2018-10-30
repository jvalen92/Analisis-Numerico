package com.example.sebas.urano;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import de.codecrafters.tableview.TableDataAdapter;
import de.codecrafters.tableview.TableView;

public class Matriz extends AppCompatActivity {

    private static final String[][] DATA_TO_SHOW = {{ "This", "is", "a", "test"},
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

    public void crearTabla(View view) {
        int N = Integer.parseInt(((EditText)findViewById(R.id.numero)).getText().toString());
        EditText[][] data = new EditText[N][N];
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                data[i][j] = new EditText(this);
                data[i][j].setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL);
            }
        }
        TableView<EditText[]> tableView = (TableView<EditText[]>) findViewById(R.id.miTabla);
        //tableView.setDataAdapter(new SimpleTableDataAdapter(this, DATA_TO_SHOW));
        tableView.setColumnCount(N);
        tableView.setDataAdapter(new EditTextTableDataAdapter(this, data));
    }

    public void submit(View view) {
        TableView<EditText[]> tableView = (TableView<EditText[]>) findViewById(R.id.miTabla);
        int N = tableView.getColumnCount();
        TableDataAdapter adapter = tableView.getDataAdapter();
        EditText[][] data = new EditText[N][N];
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                data[i][j] = adapter.getCellView(i, j, ???);
            }
        }
    }
}