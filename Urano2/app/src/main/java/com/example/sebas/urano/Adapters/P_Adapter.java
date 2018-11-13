package com.example.sebas.urano.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.sebas.urano.Fragments.Biseccion;
import com.example.sebas.urano.Fragments.EGParcial;
import com.example.sebas.urano.Fragments.Newton;
import com.example.sebas.urano.Fragments.RaicesMultiples;
import com.example.sebas.urano.Fragments.ReglaFalsa;
import com.example.sebas.urano.Fragments.PuntoFijo;
import com.example.sebas.urano.Fragments.Secante;

public class P_Adapter extends FragmentStatePagerAdapter{

    private int n_tabs;

    public P_Adapter(FragmentManager fm, int n_tabs) {
        super(fm);
        this.n_tabs=n_tabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position){


            case 0:
                return new Biseccion();
            case 1:
                return new ReglaFalsa();
            case 2:
                return new PuntoFijo();
            case 3:
                return new Secante();
            case 4:
                return new Newton();
            case 5:
                return new RaicesMultiples();

                default:
                    return null;

        }
    }

    @Override
    //dice cuantos tabs tengo
    public int getCount() {
        return n_tabs;
    }
}
