package com.menusweet.test;

import com.menusweet.Item;
import com.menusweet.UserCart;

import junit.framework.*;

public class UserCartTest extends TestCase {

    Item chickenCurry = new Item("Chicken Curry", "Its a curry. With chicken", 999);
    Item beefCurry = new Item("Beef Curry", "Its a curry. With beef", 999);
    Item tofuCurry = new Item("Tofu Curry", "Its a curry. With tofu. For vegetarians I guess", 999);
    Item softDrink = new Item("Soft Drink", "Everything else", 199);
    Item water = new Item("Tap Water", "Comes with free ice!", 0);

    public UserCartTest(String name) {
        super(name);
    }

    public void testAddItem() {

        int cost;
        boolean caughtException;
        UserCart cart = new UserCart(0.07);
        assertTrue("The cart is not properly initialized.", cart.isEmpty());

        cart.testAddItem(chickenCurry, 1);
        cost = chickenCurry.getPrice();
        assertFalse("The cart is empty after an item has been added", cart.isEmpty());
        assertEquals("The subtotal was not updated correctly when 1 new item was added.", cart.getTotalPrice(), cost);

        cart.testAddItem(beefCurry, 2);
        cost += 2 * beefCurry.getPrice();
        assertEquals("The subtotal was not updated correctly when 2 new items were added.", cart.getTotalPrice(), cost);

        cart.testAddItem(softDrink, 3);
        cost += 3 * softDrink.getPrice();
        assertEquals("The subtotal was not updated correctly when 3 new items were added.", cart.getTotalPrice(), cost);

        caughtException = false;
        try {
            cart.testAddItem(null, 3);
        }  catch (NullPointerException e) {
            caughtException = true;
        }
        assertTrue("Did not throw the correct exception when given a null value.", caughtException);
        assertEquals("The subtotal was updated when a null parameter is passed to testAddItem.", cart.getTotalPrice(), cost);

        caughtException = false;
        try {
            cart.testAddItem(softDrink, -3);
        }  catch (IllegalArgumentException e) {
            caughtException = true;
        }
        assertTrue("Did not throw the correct exception when given a null value.", caughtException);
        assertEquals("The subtotal was updated when an invalid parameter is passed to testAddItem.", cart.getTotalPrice(), cost);

        caughtException = false;
        try {
            cart.testAddItem(null, -3);
        }  catch (IllegalArgumentException e) {
            caughtException = true;
        }
        assertTrue("Did not throw the correct exception when multiple invalid parameters are passed to testAddItem.", caughtException);
        assertEquals("The subtotal was updated when multiple invalid parameters are passed to testAddItem.", cart.getTotalPrice(), cost);

    }

    public void testChangeQuantity() {

        int cost;
        boolean caughtException;
        UserCart cart = new UserCart(0.07);
        assertTrue("The cart is not properly initialized.", cart.isEmpty());

        cart.testAddItem(chickenCurry, 1);
        cart.testAddItem(beefCurry, 2);
        cart.testAddItem(softDrink, 3);
        cost = chickenCurry.getPrice() + 2 * beefCurry.getPrice() + 3 * softDrink.getPrice();
        assertEquals("The cart was not properly prepped for testing.", cart.getTotalPrice(), cost);

        cart.testChangeQuantity(0, 3);  // chickenCurry
        cost += 2 * chickenCurry.getPrice();
        assertEquals("The subtotal was not updated correctly when 2 chicken curries were added.", cart.getTotalPrice(), cost);

        cart.testChangeQuantity(0, 1);  // beefCurry
        cost -= 2 * chickenCurry.getPrice();
        assertEquals("The subtotal was not updated correctly when 2 chicken curries were removed.", cart.getTotalPrice(), cost);
        assertFalse("The cart is empty when it shouldn't be.", cart.isEmpty());

        caughtException = false;
        try {
            cart.testChangeQuantity(-1, -1);
        }  catch (IndexOutOfBoundsException e) {
            caughtException = true;
        }
        assertTrue("Did not throw the correct exception when given negative parameters.", caughtException);
        assertEquals("Nothing should change on negative parameters given to testChangeQuantity.", cart.getTotalPrice(), cost);

        caughtException = false;
        try {
            cart.testChangeQuantity(-1, 1);
        }  catch (IndexOutOfBoundsException e) {
            caughtException = true;
        }
        assertTrue("Did not throw the correct exception when given a negative index.", caughtException);
        assertEquals("Nothing should change on a negative index given to testChangeQuantity.", cart.getTotalPrice(), cost);

        caughtException = false;
        try {
            cart.testChangeQuantity(1, -1);
        }  catch (IllegalArgumentException e) {
            caughtException = true;
        }
        assertTrue("Did not throw the correct exception when given a negative amount.", caughtException);
        assertEquals("Nothing should change on a negative amount given to testChangeQuantity.", cart.getTotalPrice(), cost);

        caughtException = false;
        try {
            cart.testChangeQuantity(5, 17);
        }  catch (IndexOutOfBoundsException e) {
            caughtException = true;
        }
        assertTrue("Did not throw the correct exception when given too large of an index.", caughtException);
        assertEquals("Nothing should change on indexOutOfBounds with testChangeQuantity.", cart.getTotalPrice(), cost);

    }

    public void testRemoveItem() {

        int cost;
        boolean caughtException;
        UserCart cart = new UserCart(0.07);
        assertTrue("The cart is not properly initialized.", cart.isEmpty());

        cart.testAddItem(chickenCurry, 1);  // 0
        cart.testAddItem(beefCurry, 2);     // 1
        cart.testAddItem(softDrink, 3);     // 2
        cost = chickenCurry.getPrice() + 2 * beefCurry.getPrice() + 3 * softDrink.getPrice();
        assertEquals("The cart was not properly prepped for testing.", cart.getTotalPrice(), cost);

        cart.testRemoveItem(2); // softDrink
        cart.testAddItem(water, 3);         // 3
        cost = cost - (3 * softDrink.getPrice()) + (3 * water.getPrice());
        assertEquals("The subtotal was not updated correctly when swapping the soft drinks for water.", cart.getTotalPrice(), cost);

        caughtException = false;
        try {
            cart.testRemoveItem(-1);
        }  catch (IndexOutOfBoundsException e) {
            caughtException = true;
        }
        assertTrue("Did not throw the correct exception when given a negative index.", caughtException);
        assertEquals("Nothing should change on a negative index given to testRemoveItem.", cart.getTotalPrice(), cost);

        caughtException = false;
        try {
            cart.testRemoveItem(5);
        }  catch (IndexOutOfBoundsException e) {
            caughtException = true;
        }
        assertTrue("Did not throw the correct exception when given too large of an index.", caughtException);
        assertEquals("Nothing should change on indexOutOfBounds with testRemoveItem.", cart.getTotalPrice(), cost);

        cart.testRemoveItem(0); // chickenCurry
        cost -= chickenCurry.getPrice();
        assertEquals("The subtotal was not updated correctly when the chicken curry was removed.", cart.getTotalPrice(), cost);

        cart.testRemoveItem(1); // beefCurry
        cost = 0;
        assertEquals("The subtotal was not updated correctly when the beef curry was removed.", cart.getTotalPrice(), cost);

        cart.testRemoveItem(1); // beefCurry
        assertEquals("The subtotal was changed after the same item was removed consecutively.", cart.getTotalPrice(), cost);
        assertFalse("The cart is empty when there are free items still in the cart.", cart.isEmpty());

        cart.testRemoveItem(3); // water, the softDrink is still at 2
        assertTrue("The cart should be empty.", cart.isEmpty());

    }

    public void testIncrementItem() {

        int cost;
        boolean caughtException;
        UserCart cart = new UserCart(0.07);
        assertTrue("The cart is not properly initialized.", cart.isEmpty());

        cart.testAddItem(chickenCurry, 1);
        cost = chickenCurry.getPrice();

        assertEquals("The quantity was not updated correctly when item was first incremented.", cart.incrementItem(0), 2);
        cost += chickenCurry.getPrice();
        assertEquals("The subtotal was not updated correctly when item was first incremented.", cart.getTotalPrice(), cost);

        assertEquals("The quantity was not updated correctly when item was incremented again.", cart.incrementItem(0), 3);
        cost += chickenCurry.getPrice();
        assertEquals("The subtotal was not updated correctly when item was incremented again.", cart.getTotalPrice(), cost);

        caughtException = false;
        try {
            cart.incrementItem(3);
        }  catch (IndexOutOfBoundsException e) {
            caughtException = true;
        }

        assertTrue("An exception was not thrown when too large of a index was passed in.", caughtException);
        assertEquals("The subtotal was updated when too large of a index was passed in.", cart.getTotalPrice(), cost);

        caughtException = false;
        try {
            cart.incrementItem(-1);
        }  catch (IndexOutOfBoundsException e) {
            caughtException = true;
        }
        assertTrue("An exception was not thrown when a negative index was passed in.", caughtException);
        assertEquals("The subtotal was updated when a negative index was passed in.", cart.getTotalPrice(), cost);
    }

    public void testDecrementItem() {

        int cost;
        boolean caughtException;
        UserCart cart = new UserCart(0.07);
        assertTrue("The cart is not properly initialized.", cart.isEmpty());

        cart.testAddItem(chickenCurry, 2);
        cost = chickenCurry.getPrice() * 2;

        assertEquals("The quantity was not updated correctly when item was first decremented.", cart.testDecrementItem(0), 1);
        cost -= chickenCurry.getPrice();
        assertEquals("The subtotal was not updated correctly when item was first decremented.", cart.getTotalPrice(), cost);

        assertEquals("The quantity was not updated correctly when item was decremented again.", cart.testDecrementItem(0), 0);
        cost -= chickenCurry.getPrice();
        assertEquals("The subtotal was not updated correctly when item was decremented again.", cart.getTotalPrice(), cost);
        assertTrue("The cart should be empty.", cart.isEmpty());

        caughtException = false;
        try {
            cart.testDecrementItem(3);
        }  catch (IndexOutOfBoundsException e) {
            caughtException = true;
        }
        assertTrue("An exception was not thrown when too large of a index was passed in.", caughtException);
        assertEquals("The subtotal was updated when too large of a index was passed in.", cart.getTotalPrice(), cost);

        caughtException = false;
        try {
            cart.testDecrementItem(0);
        }  catch (IllegalArgumentException e) {
            caughtException = true;
        }
        assertTrue("An exception was not thrown when a removed item is decremented.", caughtException);
        assertEquals("The quantity was updated when there was a item at that index with 0 quantity.", cart.numberOf(0), 0);
        assertEquals("The subtotal was updated when there was a item at that index with 0 quantity.", cart.getTotalPrice(), cost);

        caughtException = false;
        try {
            cart.testDecrementItem(-1);
        }  catch (IndexOutOfBoundsException e) {
            caughtException = true;
        }
        assertTrue("An exception was not thrown when a negative index was passed in.", caughtException);
        assertEquals("The subtotal was updated when a negative index was passed in.", cart.getTotalPrice(), cost);
    }

    public void testNumberOf() {

        boolean caughtException;
        UserCart cart = new UserCart(0.07);
        assertTrue("The cart is not properly initialized.", cart.isEmpty());

        cart.testAddItem(chickenCurry, 2);
        assertEquals("The quantity was not updated correctly when item was first added.", cart.numberOf(0), 2);


        caughtException = false;
        try {
            cart.numberOf(3);
        }  catch (IndexOutOfBoundsException e) {
            caughtException = true;
        }
        assertTrue("The quantity did not throw an exception when too large of an index is given.", caughtException);

        caughtException = false;
        try {
            cart.numberOf(-1);
        }  catch (IndexOutOfBoundsException e) {
            caughtException = true;
        }
        assertTrue("The quantity did not throw an exception when a negative index is given.", caughtException);


    }
}
