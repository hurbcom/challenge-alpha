package com.example.desafioalphahurb.model;

public class Hotel {

    public Integer index;
    public String name;
    public String city;
    public String state;
    public String price;
    public String description;
    public Integer stars;
    public String image;
    public String amenities;

    public String getHotelNames() {
        return this.name;
    }

    public Hotel(String name) {
        this.name = name;
    }

    public Hotel(Integer index, Integer stars) {
        this.index = index;
        this.stars = stars;
    }

    public Hotel(Integer index, String name, Integer stars) {
        this.index = index;
        this.name = name;
        this.stars = stars;
    }

    public Hotel(String name, String city, String state, String price, String description, Integer stars, String image, String amenities) {
        this.name = name;
        this.city = city;
        this.state = state;
        this.price = price;
        this.description = description;
        this.stars = stars;
        this.image = image;
        this.amenities = amenities;
    }

    public Hotel(Integer index, String name, String city, String state, String price, Integer stars, String image, String amenities) {
        this.index = index;
        this.name = name;
        this.city = city;
        this.state = state;
        this.price = price;
        this.stars = stars;
        this.image = image;
        this.amenities = amenities;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStars() {
        return stars;
    }

    public void setStars(Integer stars) {
        this.stars = stars;
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