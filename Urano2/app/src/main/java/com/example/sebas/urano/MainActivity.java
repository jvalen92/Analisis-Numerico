package com.example.sebas.urano;

import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sebas.urano.Adapters.P_Adapter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //definir el toolbar
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        //Agregar tabs al ViewPager
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.addTab(tabLayout.newTab().setText("Biseccion"));
        tabLayout.addTab(tabLayout.newTab().setText("Regla Falsa"));
        tabLayout.addTab(tabLayout.newTab().setText("Punto fijo"));
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
    //Habilitar el menú en la parte supeior derecha
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }
}
