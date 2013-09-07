package com.menusweet;

/**
 * Created with IntelliJ IDEA.
 * User: GrumpyOwl
 * Date: 9/6/13
 * Time: 2:13 PM
 * To change this template use File | Settings | File Templates.
 */
public class Item {

    String name, description;
    float price;
    int pictureResId;

    public Item(String name, String description, Float price, int resId) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.pictureResId = resId;
    }

    public Item(String name, String description, Float price) {
        this(name, description, price, 0);
    }
}
