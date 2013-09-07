package com.menusweet;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: GrumpyOwl
 * Date: 9/6/13
 * Time: 2:20 PM
 * To change this template use File | Settings | File Templates.
 */
public class UserCart {

    ArrayList<CartItem> cartItems;
    float totalPrice, tax, finalTotal;
    String email, name;

    public UserCart(float tax) {
        this.tax = 0;
        cartItems = new ArrayList<CartItem>();
        reset();
    }

    void reset() {
        cartItems.clear();
        totalPrice = 0;
        finalTotal = 0;
        email = "";
        name = "";
    }
}
