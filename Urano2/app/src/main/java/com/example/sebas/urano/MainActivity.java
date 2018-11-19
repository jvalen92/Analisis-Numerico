package com.example.sebas.urano;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


import com.example.sebas.urano.Adapters.P_Adapter;

import de.codecrafters.tableview.TableView;
import de.codecrafters.tableview.toolkit.SimpleTableDataAdapter;
import de.codecrafters.tableview.toolkit.SimpleTableHeaderAdapter;

public class MainActivity extends AppCompatActivity{



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //definir el toolbar
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        //Agregar tabs al ViewPager
        tabs();

    }


    //Habilitar el menú en la parte supeior derecha
    /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }*/


    public void tabs(){
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.addTab(tabLayout.newTab().setText("Biseccion"));
        tabLayout.addTab(tabLayout.newTab().setText("Regla Falsa"));
        tabLayout.addTab(tabLayout.newTab().setText("Punto fijo"));
        tabLayout.addTab(tabLayout.newTab().setText("Secante"));
        tabLayout.addTab(tabLayout.newTab().setText("Newton"));
        tabLayout.addTab(tabLayout.newTab().setText("Raices multiples"));


        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        //Bloque de codigo para la gestion de los tabs ¡¡NO TOCAR!!
        final ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        //Clase auxiliar para la gestion de os tabs
        P_Adapter adapter = new P_Adapter(getSupportFragmentManager(),tabLayout.getTabCount());

        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            //Ubicar los tabs
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int pos = tab.getPosition();
                viewPager.setCurrentItem(pos);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id){

            case R.id.graficar:
                //Abrir las funcionalidades del gráfico
                Intent grafico = new Intent(MainActivity.this,Funciones.class);
                grafico.putExtra("funcion","x^2+5x-10");
                startActivity(grafico);
                return true;

            case R.id.interpolacion:
                //salto a Interpolacion
                Intent inter = new Intent(MainActivity.this,InterpolacionFinal.class);
                startActivity(inter);
                return true;


            case R.id.sis_ecuaciones:
                //Toast.makeText(this,"me tocaste wey",Toast.LENGTH_LONG).show();
                Intent irSistemaEq = new Intent(MainActivity.this, SistemasDeEcuaciones.class);
                startActivity(irSistemaEq);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void abrirFuncionesGrafico(View view) {
        Intent nuevaActividad = new Intent(MainActivity.this, Grafico.class);
        startActivity(nuevaActividad);
    }

    public void irMatriz(View view) {
        Intent irAMatriz = new Intent(MainActivity.this, Matriz.class);
        startActivity(irAMatriz);
    }

    public void irSistemaEcuaciones(View view) {
        Intent irSistemaEq = new Intent(MainActivity.this, SistemasDeEcuaciones.class);
        startActivity(irSistemaEq);
    }
}
