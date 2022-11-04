package com.example.a05_ejercicio_lista_compra.Adapters;

import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;

import com.example.a05_ejercicio_lista_compra.Modelos.Producto;

import java.util.List;

public class ProductosAdapter extends RecyclerView.Adapter{

    private List<Producto> objects;
    private int recource;
    private Context contect;


    public ProductosAdapter(List<Producto> objects, int recource, Context contect) {
        this.objects = objects;
        this.recource = recource;
        this.contect = contect;
    }


    
}
