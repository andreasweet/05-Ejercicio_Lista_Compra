package com.example.a05_ejercicio_lista_compra;

import android.content.DialogInterface;
import android.os.Bundle;

import com.example.a05_ejercicio_lista_compra.Modelos.Producto;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;


import com.example.a05_ejercicio_lista_compra.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private ActivityMainBinding binding;
    private ArrayList<Producto> productosList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);
        productosList = new ArrayList<>();

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                creteProductos().show();
            }
        });
    }

    private AlertDialog creteProductos(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("aGREGAR producto");
        builder.setCancelable(false);

        View productoAlertView = LayoutInflater.from(this).inflate(R.layout.producto_model_alert,null);
        builder.setView(productoAlertView);
        EditText txtNombre = productoAlertView.findViewById(R.id.txtNombreProductoAlert);
        EditText txtCantidad = productoAlertView.findViewById(R.id.txtCantidadProductoAlert);
        EditText txtPrecio = productoAlertView.findViewById(R.id.txtPrecioaProductoAlert);
        TextView lblTotal = productoAlertView.findViewById(R.id.lblProductoAlert);


        TextWatcher textWatcher = new TextWatcher() {

            //NO HACE FALTA UTILIZAR LAS TRES
            /**
             * al MODIFICAR UN CUADRO DE TEXTO
             * @param charSequence --> envia el contenido que habia antes del cambio
             * @param i
             * @param i1
             * @param i2
             */

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            /**
             * al modificar un cuadro de texto
             * @param charSequence --> envia el texto actual despues de la modificacion
             * @param i
             * @param i1
             * @param i2
             */

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            /**
             * se dispara al terminar la modificacion
             * @param editable --> envia el contenido final del cuadro de texto
             *
             */
            @Override
            public void afterTextChanged(Editable editable) {
                try{
                    int cantidad = Integer.parseInt(txtCantidad.getText().toString());
                    float precio = Float.parseFloat(txtPrecio.getText().toString());
                    NumberFormat numberFormat = NumberFormat.getCurrencyInstance();
                    lblTotal.setText(numberFormat.format(cantidad*precio));
                }catch (NumberFormatException ex){

                }

            }
        };

        txtCantidad.addTextChangedListener(textWatcher);
        txtPrecio.addTextChangedListener(textWatcher);

        builder.setNegativeButton("CANCELAR", null);
        builder.setPositiveButton("AGREGAR", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(!txtNombre.getText().toString().isEmpty()
                    && !txtCantidad.getText().toString().isEmpty()
                        && !txtPrecio.getText().toString().isEmpty()){
                    Producto producto = new Producto( txtNombre.getText().toString(), Integer.parseInt(txtCantidad.getText().toString()), Float.parseFloat(txtPrecio.getText().toString()));
                productosList.add(producto);
                }else{
                    Toast.makeText(MainActivity.this, "FALTAN DATOS", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return builder.create();
    }
}