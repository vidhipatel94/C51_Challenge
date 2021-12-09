package com.c51.c51challenge.model;

public class Offer {
    String name;
    String image_url;
    float cash_back;

    public Offer() {
    }

    public Offer(String name, String image_url, int cash_back) {
        this.name = name;
        this.image_url = image_url;
        this.cash_back = cash_back;
    }

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return image_url;
    }

    public float getCashBack() {
        return cash_back;
    }
}
