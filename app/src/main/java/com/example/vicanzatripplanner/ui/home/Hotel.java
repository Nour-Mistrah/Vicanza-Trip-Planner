package com.example.vicanzatripplanner.ui.home;


public class Hotel {
    private int id;
    private String name;
    private String location;
    private double price;
    private String image_url;

    private boolean isSelected;

    public Hotel(int id, String name, String location, double price, String imageUrl) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.price = price;
        this.image_url = imageUrl;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected (boolean selected) {
        isSelected = selected;
    }

    public int getId() { return id; }

    public String getName() { return name; }
    public String getLocation() { return location; }
    public String getimage_url() { return image_url; }

    public double getPrice() {return price;}
}
