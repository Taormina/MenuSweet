package com.menusweet;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: GrumpyOwl
 * Date: 9/6/13
 * Time: 2:10 PM
 * To change this template use File | Settings | File Templates.
 */
public class Category {

    String name;
    ArrayList<Item> items;
    Boolean requires21;

    public Category(String name, Boolean requires21) {
        this.name = name;
        this.requires21 = requires21;
        items = new ArrayList<Item>();
    }

    void addItem(Item newItem) {
        items.add(newItem);
    }

}
