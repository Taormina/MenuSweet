package com.menusweet;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class MenuActivity extends FragmentActivity {

    ArrayList<Category> categories;
    Category suggestions;
    UserCart userCart;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        getSupportFragmentManager().beginTransaction().add(R.id.main, new MenuFragment()).commit();

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
        categories.add(first);
        categories.add(second);


        double tax = 0.07;

        userCart = new UserCart(tax);
        userCart.testAddItem(first.items.get(0), 1);
        userCart.testAddItem(first.items.get(1), 2);
        userCart.testAddItem(second.items.get(0), 3);
    }

    public boolean isCartEmpty() {
        return userCart.isEmpty();
    }

    public void setSubtotal(View view) {
        StringBuilder subtotal = new StringBuilder();
        subtotal.append(userCart.totalPrice / 100);
        subtotal.append(".");
        subtotal.append(userCart.totalPrice % 100);
        ((TextView) view.findViewById(R.id.subtotal)).setText(subtotal.toString());
    }

    public void setIntText(TextView textView, int i) {
        textView.setText(Integer.toString(i));
    }

    public void print() {

    }

    public void email() {

    }

    public void sendFeedback() {

    }
}