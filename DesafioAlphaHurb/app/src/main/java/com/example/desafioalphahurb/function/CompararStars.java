package com.example.desafioalphahurb.function;

import com.example.desafioalphahurb.model.Hotel;

import java.util.Comparator;

//MÉTODO PARA ORDENAR OS HOTEIS PELA QUANTIDADE DE ESTRELAS
public class CompararStars implements Comparator<Hotel> {
    public int compare(Hotel stars1, Hotel stars2) {
        if (stars1.stars < stars2.stars) return +1;
        else if (stars1.stars > stars2.stars) return -1;
        else return 0;
    }
}