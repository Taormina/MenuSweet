package com.menusweet;

import java.util.ArrayList;
import java.util.List;

public class UserCart {

    ArrayList<CartItem> cartItems;
    int totalPrice, finalTotal;
    double tax;
    String email, name;

    public UserCart(double tax, ArrayList<Item> items) {
        this.tax = tax;
        cartItems = new ArrayList<CartItem>();
        for (Item item : items)
            cartItems.add(new CartItem(item, 0, ""));
        reset();
    }

    public List getList(){
    	return (List) cartItems.clone();
    }

    public CartItem getItem(int index) {
        if (index < 0 || index >= cartItems.size())
            throw new IndexOutOfBoundsException();

        return cartItems.get(index);
    }
    	
    public boolean equals(Object other) {
        if (other == null) return false;
        if (other == this) return true;
        if (!(other instanceof UserCart))return false;
        UserCart otherMyClass = (UserCart)other;

        return totalPrice == otherMyClass.totalPrice &&
                tax == otherMyClass.tax &&
                finalTotal == otherMyClass.finalTotal &&
                cartItems.equals(otherMyClass.cartItems) &&
                email.equals(otherMyClass.email) &&
                name.equals(otherMyClass.name);
    }

    public int numberOf(int index) {
        if (index < 0 || index >= cartItems.size())
            throw new IndexOutOfBoundsException();

        return cartItems.get(index).quantity;
    }

    public boolean isEmpty() {
        int numOfItems = 0;

        for (CartItem item : cartItems)
            numOfItems += item.quantity;

        return numOfItems == 0 && totalPrice == 0;
    }

    void reset() {
        for (CartItem item : cartItems)
            item.clear();
        totalPrice = 0;
        finalTotal = 0;
        email = "";
        name = "";
    }

    public void changeQuantity(int index, int amount) {
        if (index < 0 || index >= cartItems.size())
            throw new IndexOutOfBoundsException();
        else if (amount < 0)
            throw new IllegalArgumentException("Items can't have a negative quantity.");
        else if (amount == 0)
            removeItem(index);
        else {
            CartItem item = cartItems.get(index);
            totalPrice += (amount - item.quantity) * item.baseItem.price;
            item.quantity = amount;
        }
    }

    public void testChangeQuantity(int index, int amount) {
        changeQuantity(index, amount);
    }

    public void addItem(int index, int quantity, String comments) {
        if (quantity < 0)
            throw new IllegalArgumentException("Items can't have a negative quantity while being added.");
        if (index < 0 || index >= cartItems.size())
            throw new IndexOutOfBoundsException(String.valueOf("Index: " + index));

        CartItem item = cartItems.get(index);
        item.quantity += quantity;
        item.comments = comments;

        totalPrice += item.baseItem.price * quantity;
    }

    public void testAddItem(int index, int quantity) {
        addItem(index, quantity, "");
    }

    public int incrementItem(int index) {
        if (index < 0 || index >= cartItems.size())
            throw new IndexOutOfBoundsException();

        CartItem item = cartItems.get(index);
        item.quantity++;
        totalPrice += item.baseItem.price;
        return item.quantity;
    }

    public int decrementItem(int index) {
        if (index < 0 || index >= cartItems.size())
            throw new IndexOutOfBoundsException();
        CartItem item = cartItems.get(index);

        if (item.quantity == 0)
            throw new IllegalArgumentException("You can't have negative items.");

        if (--item.quantity == 0)
            removeItem(index);

        if (item.quantity >= 0)
            totalPrice -= item.baseItem.price;

        return item.quantity;
    }

    public int testDecrementItem(int index) {
        return decrementItem(index);
    }

    public void removeItem(int index) {
        if (index < 0 || index >= cartItems.size())
            throw new IndexOutOfBoundsException();

        CartItem item = cartItems.get(index);
        totalPrice -= item.quantity * item.baseItem.price;
        item.quantity = 0;
    }

    public void testRemoveItem(int index) {
        removeItem(index);
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public int getSubtotal() {
        return (int) Math.ceil(totalPrice * (1 + tax));
    }

    public void emailReceipt() {

    }

    public void printReceipt() {

    }

    public String toString() {
        StringBuilder retVal = new StringBuilder();
        for (CartItem i : cartItems) {
            retVal.append(i.toString());
            retVal.append("\n");
        }
        retVal.append("================\nSubtotal: $");
        retVal.append(totalPrice / 100);
        retVal.append(".");
        retVal.append(totalPrice % 100);
        retVal.append("\nTax:\t $");
        retVal.append((int) (tax * totalPrice / 100));
        retVal.append(".");
        retVal.append((int) (tax * totalPrice % 100));
        retVal.append("\nTotal:\t $");
        retVal.append(getSubtotal() / 100);
        retVal.append(".");
        retVal.append(getSubtotal() % 100);
        retVal.append("\n");


        return retVal.toString();
    }

}
