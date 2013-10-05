package com.menusweet;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class MenuActivity extends FragmentActivity {

    ArrayList<Category> categories;
    Category suggestions;
    UserCart userCart;
    private int i = 0; // next open index in the Cart

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
        //userCart.testAddItem(first.items.get(0), 1);
        //userCart.testAddItem(first.items.get(1), 2);
        //userCart.testAddItem(second.items.get(0), 3);
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

    public void addGraphicallyToCart(CartItem item) {
        LinearLayout layout = (LinearLayout) findViewById(R.id.cart);
        TextView textView, itemAmount;
        ImageView incrementButton, removeButton, decrementButton;
        ArrayList<View> views;

        textView = new TextView(this);
        textView.setText(item.baseItem.name);
        textView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));

        layout.addView(textView);

        itemAmount = new TextView(this);
        setIntText(itemAmount, item.quantity);
        itemAmount.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));

        layout.addView(itemAmount);

        incrementButton = new ImageView(this);
        incrementButton.setImageResource(R.drawable.add_button);
        incrementButton.setTag(R.id.TAG_INDEX, i);
        incrementButton.setOnClickListener(new IncrementClickListener(itemAmount));
        incrementButton.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));

        layout.addView(incrementButton);

        decrementButton = new ImageView(this);
        decrementButton.setImageResource(R.drawable.decrement_button);
        decrementButton.setTag(R.id.TAG_INDEX, i);
        decrementButton.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));

        removeButton = new ImageView(this);
        removeButton.setImageResource(R.drawable.remove_button);
        removeButton.setTag(R.id.TAG_INDEX, i);
        removeButton.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));

        views = new ArrayList<View>();
        views.add(textView);
        views.add(itemAmount);
        views.add(incrementButton);
        views.add(decrementButton);
        views.add(removeButton);

        decrementButton.setOnClickListener(new DecrementClickListener(views, itemAmount));
        removeButton.setOnClickListener(new RemoveClickListener(views));

        layout.addView(decrementButton);
        layout.addView(removeButton);

        i++;

    }

    public void print() {

    }

    public void email() {

    }

    public void sendFeedback() {

    }
}