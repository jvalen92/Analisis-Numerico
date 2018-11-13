package com.example.sebas.urano.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.sebas.urano.Fragments.*;

public class SistemasDeEcuacionesAdapter extends FragmentStatePagerAdapter{

    private int n_tabs;

    public SistemasDeEcuacionesAdapter (FragmentManager fm, int n_tabs) {
        super(fm);
        this.n_tabs = n_tabs;

    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new EGParcial();
            case 1:
                return new EGTotal();
            case 2:
                return new LUSimple();
            case 3:
                return new LUParcial();
            case 4:
                return new Choletsky();
            case 5:
                return new Doolittle();
            case 6:
                return new Crout();
            case 7:
                return new Jacobi();
            case 8:
                return new SOR();
            case 9:
                return new GaussSeidel();

            default:
                return null;
        }
    }

    @Override
    /**
     * Retorna la cantidad de Fragments en el PagerAdapter
     * */
    public int getCount() {
        return n_tabs;
    }
}
