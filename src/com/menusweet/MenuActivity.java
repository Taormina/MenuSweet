package com.menusweet;

import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.menusweet.mailsender.GMailSender;

import java.util.ArrayList;

public class MenuActivity extends FragmentActivity {

    ArrayList<Category> categories;
    ArrayList<Row> rows;
    Category suggestions;
    UserCart userCart;
    ArrayList<Item> items;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);


        getSupportFragmentManager().beginTransaction().add(R.id.main, new IntroScreenFragment()).commit();


        initialize();
    }

    private void initialize() {
        categories = new ArrayList<Category>();
        Category first = new Category("Food", false);
        Category second = new Category("Drink", false);
        suggestions = new Category("Suggestions", false);
        first.addItem(new Item("Chicken Curry", "Its a curry. With chicken", 999));
        first.addItem(new Item("Beef Curry", "Its a curry. With beef", 999));
        first.addItem(new Item("Tofu Curry", "Its a curry. With tofu. For vegetarians I guess", 999));
        second.addItem(new Item("Tap Water", "Comes with free ice!", 0));
        second.addItem(new Item("Soft Drink", "Everything else", 199));
        suggestions.addItem(new Item("Chicken Curry", "Its a curry. With chicken", 999));
        suggestions.addItem(new Item("Tap Water", "Comes with free ice!", 0));
        suggestions.addItem(new Item("Soft Drink", "Everything else", 199));
        categories.add(first);
        categories.add(second);
        categories.add(suggestions);

        double tax = 0.07;

        rows = new ArrayList<Row>();
        items = new ArrayList<Item>();
        // TODO don't you fucking dare leave this in production, do this properly later
        for (Category c : categories)
            for (Item i : c.items)
                if (!items.contains(i))
                    items.add(i);

        userCart = new UserCart(tax, items);
        userCart.name = "Anthony";
        userCart.email = "gaeljudicium@aol.com";

        //email();

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

    public void updateCartRow(int i, CartItem item) {
        Row row;
        if (rows.size() > i) {
            row = rows.get(i);
        } else if (rows.size() == i) {
            row = new Row(this, item.baseItem, i);
            rows.add(row);
            LinearLayout cart = (LinearLayout) findViewById(R.id.cart);
            cart.addView(row.getView());
        } else {
            throw new IllegalStateException(String.valueOf(i) + "," + String.valueOf(rows.size()));
        }

        row.setQuantity(item.quantity);
        if (item.quantity == 0)
            row.hide();
        else
            row.show();
    }

    public void print() {

    }

    public void email(String email, String subject, String message) {

            new EmailOperation().execute(email, subject, message);
    }

    private class EmailOperation extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {

            String email = params[0], subject = params[1], message = params[2];
            Resources res = getResources();
            String ourEmail = res.getString(R.string.our_email);
            GMailSender sender = new GMailSender(ourEmail, res.getString(R.string.password));
            try {
                sender.sendMail(subject,
                    message,
                    ourEmail,
                    email);
            } catch (Exception e) {
                Log.e("SendMail", e.getMessage(), e);
            }
            return null;
        }
    }

}