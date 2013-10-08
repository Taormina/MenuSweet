package com.menusweet;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MenuActivity extends FragmentActivity {

    ArrayList<Category> categories;
    Category suggestions;
    UserCart userCart;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        getSupportFragmentManager().beginTransaction().add(R.id.menu, new IntroScreenFragment()).commit();

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
        userCart.testAddItem(first.items.get(0), 1);
        userCart.testAddItem(first.items.get(1), 2);
        userCart.testAddItem(second.items.get(0), 3);
        userCart.name = "Anthony";
        userCart.email = "gaeljudicium@aol.com";

        email();
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

    public void email() {  // Only call this when we have an email and name
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("message/rfc822");
        i.putExtra(Intent.EXTRA_EMAIL  , new String[]{userCart.email});
        i.putExtra(Intent.EXTRA_SUBJECT, "Lucky Buddha Receipt");
        i.putExtra(Intent.EXTRA_TEXT   , "Hey" + userCart.name + ",\n" + userCart);
        try {
            startActivity(Intent.createChooser(i, "Send mail..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
        }
    }

    public void sendFeedback() {

    }
}