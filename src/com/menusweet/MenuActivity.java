package com.menusweet;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import java.util.ArrayList;

public class MenuActivity extends FragmentActivity {

    ArrayList<Category> categories;
    Category suggestions;
    UserCart userCart;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        getSupportFragmentManager().beginTransaction().add(R.id.menu, new CartFragment()).commit();

        initialize();
    }

    private void initialize() {
        categories = new ArrayList<Category>();
        Category first = new Category("Food", false);
        Category second = new Category("Drink", false);
        first.addItem(new Item("Chicken Curry", "Its a curry. With chicken", 999));
        first.addItem(new Item("Beef Curry", "Its a curry. With beef", 999));
        first.addItem(new Item("Tofu Curry", "Its a curry. With tofu. For vegetarians I guess", 999));
        second.addItem(new Item("Tap Water", "Comes with free ice!", 0));
        second.addItem(new Item("Soft Drink", "Everything else", 199));

        double tax = 0.07;

        userCart = new UserCart(tax);
    }

    public boolean isCartEmpty() {
        return userCart.cartItems.isEmpty();
    }

    public void print() {

    }

    public void email() {

    }

    public void sendFeedback() {

    }
}