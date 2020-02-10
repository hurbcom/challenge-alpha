package com.example.desafioalpha;

import java.util.List;

public class Hotels {

    public String nome;
    public String preco;
    public String cidade;
    public String estado;
    public String[] amenidadeName;
    public String[] amenidadeCategoria;
    public Integer stars;
    public String foto;
    public boolean mIsSeparator;

    public Hotels(String nome, String preco, String cidade, String estado, String[] amenidadeName,
                  String[] amenidadeCategoria,
                  Integer stars,
                  String foto,
                  boolean mIsSeparator) {
        this.nome = nome;
        this.preco = preco;
        this.cidade = cidade;
        this.estado = estado;
        this.amenidadeName = amenidadeName;
        this.amenidadeCategoria = amenidadeCategoria;
        this.stars = stars;
        this.foto = foto;
        this.mIsSeparator= mIsSeparator;
    }


    //Gets e sets
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getNome() {
        return nome;
    }

    public void setPreco(String preco) {
        this.preco = preco;
    }
    public String getPreco() {
        return preco;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }
    public String getCidade() {
        return cidade;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    public String getEstado() {
        return estado;
    }

    public void setAmenidadeName(String[] amenidadeName) {
        this.amenidadeName = amenidadeName;
    }
    public String[] getAmenidadeName() {
        return amenidadeName;
    }

    public void setAmenidadeCategoria(String[] amenidadeCategoria) {
        this.amenidadeCategoria = amenidadeCategoria;
    }
    public String[] getAmenidadeCategoria() {
        return amenidadeCategoria;
    }

    public void setStars(Integer stars) {
        this.stars = stars;
    }
    public Integer getStars() {
        return stars;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
    public String getFoto() {
        return foto;
    }

    public void setIsSection(boolean isSection) {
        mIsSeparator = isSection;
    }

    public void setmIsSeparator(boolean mIsSeparator) {this.mIsSeparator = mIsSeparator; }
}
