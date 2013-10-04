package com.menusweet;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class AddClickListener extends ClickListener {

    private Item item;
    private int quantity;

    public AddClickListener(Item item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    public void doAction(View v) {
        activity.userCart.addItem(item, quantity, "");
        LinearLayout layout = (LinearLayout) v.getRootView().findViewById(R.id.cart);
    }


}
