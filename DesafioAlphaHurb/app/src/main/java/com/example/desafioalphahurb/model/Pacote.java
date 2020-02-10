package com.example.desafioalphahurb.model;

public class Pacote {

    public String name;
    public String city;
    public String state;
    public String price;
    public String image;
    public String amenities;

    public Pacote(String name, String city, String state, String price, String image, String amenities) {
        this.name = name;
        this.city = city;
        this.state = state;
        this.price = price;
        this.image = image;
        this.amenities = amenities;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAmenities() {
        return amenities;
    }

    public void setAmenities(String amenities) {
        this.amenities = amenities;
    }
}
