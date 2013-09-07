package com.menusweet;

/**
 * Created with IntelliJ IDEA.
 * User: GrumpyOwl
 * Date: 9/6/13
 * Time: 2:19 PM
 * To change this template use File | Settings | File Templates.
 */
public class CartItem {

    Item baseItem;
    int quantity;
    String comments;

    public CartItem(Item baseItem, int quantity, String comments) {
        this.baseItem = baseItem;
        this.quantity = quantity;
        this.comments = comments;
    }
}
