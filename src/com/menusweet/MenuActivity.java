package com.menusweet;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: GrumpyOwl
 * Date: 9/6/13
 * Time: 9:45 AM
 * To change this template use File | Settings | File Templates.
 */
public class MenuActivity extends FragmentActivity {

    ArrayList<Category> categories;
    Category suggestions;
    UserCart userCart;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        getSupportFragmentManager().beginTransaction().add(R.id.menu, new CategoryFragment()).commit();

        initialize();
    }

    private void initialize() {
        categories = new ArrayList<Category>();
        Category first = new Category("Food", false);
        Category second = new Category("Drink", false);
        first.addItem(new Item("Chicken Curry", "Its a curry. With chicken", 9.99));
        first.addItem(new Item("Beef Curry", "Its a curry. With beef", 9.99));
        first.addItem(new Item("Tofu Curry", "Its a curry. With tofu. For vegetarians I guess", 9.99));
        second.addItem(new Item("Tap Water", "Comes with free ice!", 0.00));
        second.addItem(new Item("Soft Drink", "Everything else", 1.99));
    }

    public void print() {

    }

    public void email() {

    }

    public void sendFeedback() {

    }
}