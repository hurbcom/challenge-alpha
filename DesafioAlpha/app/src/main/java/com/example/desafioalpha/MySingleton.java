package com.example.desafioalpha;

import java.util.List;

public final class MySingleton {
    private static MySingleton instance;

    public static MySingleton getInstance() {
        if (instance == null)
            instance = new MySingleton();
        return instance;
    }

    private MySingleton() {}

    private static List<Hotels> ListaHoteis;

    public static List<Hotels> getHotels() {
        return ListaHoteis;
    }
    public void setListaHoteis(List<Hotels> ListaHoteis) {
        this.ListaHoteis = ListaHoteis;
    }
}
