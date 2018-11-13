package com.example.sebas.urano;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.sebas.urano.Adapters.InterporlacionAdapter;
import com.example.sebas.urano.Adapters.P_Adapter;
import com.example.sebas.urano.Methods.SistemaDeEcuaciones;

public class InterpolacionFinal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interpolacion_final);

        //definir el toolbar
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        tabs();
    }

    public void tabs(){
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.addTab(tabLayout.newTab().setText("Vandermonde"));
        tabLayout.addTab(tabLayout.newTab().setText("Diferencias Divididas"));
        tabLayout.addTab(tabLayout.newTab().setText("Lagrange"));
        tabLayout.addTab(tabLayout.newTab().setText("Trazadores lineales"));
        tabLayout.addTab(tabLayout.newTab().setText("Trazadores cubicos"));
        tabLayout.addTab(tabLayout.newTab().setText("Trazadores Cuadraticos"));


        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        //Bloque de codigo para la gestion de los tabs ¡¡NO TOCAR!!
        final ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        //Clase auxiliar para la gestion de os tabs
        InterporlacionAdapter adapter = new InterporlacionAdapter(getSupportFragmentManager(),tabLayout.getTabCount());

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

            case R.id.sis_ecuaciones:
                Intent sis_ec = new Intent(this,SistemaDeEcuaciones.class);
                startActivity(sis_ec);
                return true;

            case  R.id.sis_unaVariable:
                Intent sis_uv = new Intent(this,MainActivity.class);
                startActivity(sis_uv);
                return true;

            case R.id.home:
                Intent home = new Intent(this,MainActivity.class);
                startActivity(home);
                return true;
        }


        return super.onOptionsItemSelected(item);
    }


}
