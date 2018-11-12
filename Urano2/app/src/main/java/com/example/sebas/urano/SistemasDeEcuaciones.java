package com.example.sebas.urano;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.sebas.urano.Adapters.SistemasDeEcuacionesAdapter;

public class SistemasDeEcuaciones extends AppCompatActivity {
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private SistemasDeEcuacionesAdapter adapter;
    private ViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sistemas_de_ecuaciones);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        pager = (ViewPager) findViewById(R.id.pager);

        // Agregar tabs al ViewPager
        tabLayout.addTab(tabLayout.newTab().setText("EG Parcial"));
        tabLayout.addTab(tabLayout.newTab().setText("EG Total"));
        tabLayout.addTab(tabLayout.newTab().setText("LU Simple"));
        tabLayout.addTab(tabLayout.newTab().setText("LU Parcial"));
        tabLayout.addTab(tabLayout.newTab().setText("Choletsky"));
        tabLayout.addTab(tabLayout.newTab().setText("Doolittle"));
        tabLayout.addTab(tabLayout.newTab().setText("Crout"));
        tabLayout.addTab(tabLayout.newTab().setText("Jacobi"));
        tabLayout.addTab(tabLayout.newTab().setText("SOR"));
        tabLayout.addTab(tabLayout.newTab().setText("Gauss-Seidel"));
        tabLayout.addTab(tabLayout.newTab().setText("Vandermonde"));
        tabLayout.addTab(tabLayout.newTab().setText("Diferencias Divididas"));
        tabLayout.addTab(tabLayout.newTab().setText("Lagrange"));
        tabLayout.addTab(tabLayout.newTab().setText("Trazadores Lineales"));
        tabLayout.addTab(tabLayout.newTab().setText("Trazadores Cuadraticos"));
        tabLayout.addTab(tabLayout.newTab().setText("Trazadores Cubicos"));

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        adapter = new SistemasDeEcuacionesAdapter(getSupportFragmentManager(),
                tabLayout.getTabCount());

        pager.setAdapter(adapter);
        pager.addOnPageChangeListener(
                new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            // Ubicar los tabs
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int pos = tab.getPosition();
                pager.setCurrentItem(pos);
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

            case R.id.interpolacion:
                //Añadir salto a interpolacion

            case  R.id.sis_unaVariable:
                Intent sis_uv = new Intent(this,MainActivity.class);
                startActivity(sis_uv);

            case R.id.home:
                Intent home = new Intent(this,MainActivity.class);
                startActivity(home);
                return true;
        }


        return super.onOptionsItemSelected(item);
    }
}