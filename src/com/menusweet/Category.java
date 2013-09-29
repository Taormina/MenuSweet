package com.menusweet;

import java.util.ArrayList;

public class Category {

    String name;
    ArrayList<Item> items;
    Boolean requires21;

    public Category(String name, Boolean requires21) {
        this.name = name;
        this.requires21 = requires21;
        items = new ArrayList<Item>();
    }

    public void addItem(Item newItem) {
        items.add(newItem);
    }

    @Override
    public String toString() {
        return name;
    }

}
