package com.menusweet.com.menusweet.test;

import com.menusweet.CartItem;
import com.menusweet.Item;
import com.menusweet.UserCart;

import junit.framework.*;

import java.util.ArrayList;

public class UserCartTest extends TestCase {

    public UserCartTest(String name) {
        super(name);
    }

    public void testCart() {

        Item chickenCurry = new Item("Chicken Curry", "Its a curry. With chicken", 999);
        Item beefCurry = new Item("Beef Curry", "Its a curry. With beef", 999);
        Item tofuCurry = new Item("Tofu Curry", "Its a curry. With tofu. For vegetarians I guess", 999);
        Item softDrink = new Item("Soft Drink", "Everything else", 199);
        Item water = new Item("Tap Water", "Comes with free ice!", 0);
        int cost = 0;

        UserCart cart = new UserCart(0.07);
        assertTrue("The cart is not properly initialized.", cart.isEmpty());

        cart.addItem(new CartItem(chickenCurry, 1, ""));
        cost += chickenCurry.getPrice();
        assertFalse("The cart is empty after an item has been added", cart.isEmpty());
        assertEquals("The subtotal was not updated correctly when 1 new item was added.", cart.getTotalPrice(), cost);

        cart.addItem(new CartItem(beefCurry, 2, ""));
        cost += 2 * beefCurry.getPrice();
        assertEquals("The subtotal was not updated correctly when 2 new items were added.", cart.getTotalPrice(), cost);

        cart.addItem(new CartItem(softDrink, 3, ""));
        cost += 3 * softDrink.getPrice();
        assertEquals("The subtotal was not updated correctly when 3 new items were added.", cart.getTotalPrice(), cost);

        cart.changeQuantity(0, 3);
        cost += 2 * chickenCurry.getPrice();
        assertEquals("The subtotal was not updated correctly when 2 chicken curries were added.", cart.getTotalPrice(), cost);

        cart.removeItem(2);
        cart.addItem(new CartItem(water, 3, ""));
        cost = cost - (3 * softDrink.getPrice()) + (3 * water.getPrice());
        assertEquals("The subtotal was not updated correctly when swapping the soft drinks for water.", cart.getTotalPrice(), cost);

        cart.changeQuantity(0, 1);
        cost -= 2 * chickenCurry.getPrice();
        assertEquals("The subtotal was not updated correctly when 2 chicken curries were removed.", cart.getTotalPrice(), cost);
        assertFalse("The cart is empty when it shouldn't be.", cart.isEmpty());

        cart.removeItem(-1);
        assertEquals("Nothing should change on a negative index given to removeItem.", cart.getTotalPrice(), cost);

        cart.changeQuantity(-1, -1);
        assertEquals("Nothing should change on negative parameters given to changeQuantity.", cart.getTotalPrice(), cost);

        cart.changeQuantity(-1, 1);
        assertEquals("Nothing should change on a negative index given to changeQuantity.", cart.getTotalPrice(), cost);

        cart.changeQuantity(1, -1);
        assertEquals("Nothing should change on a negative amount given to changeQuantity.", cart.getTotalPrice(), cost);

        cart.removeItem(5);
        assertEquals("Nothing should change on indexOutOfBounds with removeItem.", cart.getTotalPrice(), cost);

        cart.changeQuantity(5, 17);
        assertEquals("Nothing should change on indexOutOfBounds with changeQuantity.", cart.getTotalPrice(), cost);

        cart.removeItem(0);
        cost -= chickenCurry.getPrice();
        assertEquals("The subtotal was not updated correctly when the chicken curry was removed.", cart.getTotalPrice(), cost);

        cart.removeItem(0);
        cost = 0;
        assertEquals("The subtotal was not updated correctly when the beef curry was removed.", cart.getTotalPrice(), cost);
        assertFalse("The cart is empty when there are free items still in the cart.", cart.isEmpty());

        cart.removeItem(0);
        assertEquals("The cost should still be zero.", cart.getTotalPrice(), cost);
        assertTrue("The cart should be empty.", cart.isEmpty());
    }
}
