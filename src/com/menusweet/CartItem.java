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
        this.quantity = quantity < 0 ? 0 : quantity;
        this.comments = comments;
    }

    public String toString() {
        StringBuilder retVal = new StringBuilder();

        retVal.append(baseItem.name);
        retVal.append("\t\t\t");
        retVal.append(quantity);
        retVal.append(" x $");
        retVal.append(baseItem.price / 100);
        retVal.append(".");
        retVal.append(baseItem.price % 100);
        retVal.append(" = $");
        retVal.append(baseItem.price * quantity / 100);
        retVal.append(".");
        retVal.append(baseItem.price * quantity % 100);
        retVal.append("\n");

        return retVal.toString();
    }
}
