package com.menusweet;

public class Item {

    String name, description;
    int price, pictureResId;

    public Item(String name, String description, int price, int resId) {
        this.name = name;
        this.description = description;
        this.price = price < 0 ? 0 : price;
        this.pictureResId = resId;
    }

    public Item(String name, String description, int price) {
        this(name, description, price, 0);
    }

    public int getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }
}
