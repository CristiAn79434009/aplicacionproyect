package com.example.jose.myaplicacion.ListDataSourse;

import android.print.PrinterId;

public class ItemList {
    private  String tipo;
    private String precio;
    private String poster;
    public ItemList(String tipo, String precio, String poster){
        this.tipo = tipo;
        this.precio = precio;
        this.poster = poster;
    }

    public String getTipo() {
        return this.tipo;
    }

    public String getPrecio() {
        return this.precio;
    }

    public String getPoster() {
        return this.poster;
    }
}
