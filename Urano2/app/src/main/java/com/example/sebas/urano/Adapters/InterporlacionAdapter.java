package com.example.sebas.urano.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.sebas.urano.Fragments.DiferenciasDivididas;
import com.example.sebas.urano.Fragments.Lagrange;
import com.example.sebas.urano.Fragments.TrazadoresCuadraticos;
import com.example.sebas.urano.Fragments.TrazadoresCubicos;
import com.example.sebas.urano.Fragments.TrazadoresLineales;
import com.example.sebas.urano.Fragments.Vandermonde;

public class InterporlacionAdapter extends FragmentStatePagerAdapter {

    private int n_tabs;

    public InterporlacionAdapter(FragmentManager fm , int n_tabs) {
        super(fm);
        this.n_tabs =n_tabs;
    }


    @Override
    public Fragment getItem(int position) {

        switch (position){

            case 0:
                return new Vandermonde();

            case 1:
                return new DiferenciasDivididas();

            case 2:
                return new Lagrange();

            case 3:
                return new TrazadoresLineales();

            case 4:
                return new TrazadoresCubicos();

            case 5:
                return new TrazadoresCuadraticos();


            default:
                return null;
        }


    }

    @Override
    public int getCount() {
        return n_tabs;
    }
}
