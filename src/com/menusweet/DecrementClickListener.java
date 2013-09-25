package com.menusweet;

import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class DecrementClickListener extends ClickListener {

    private ArrayList<View> views;
    private TextView quantity;

    public DecrementClickListener(ArrayList<View> views, TextView quantity) {
        this.views = views;
        this.quantity = quantity;
    }

    public void doAction(View v) {
        activity.setIntText(quantity, activity.userCart.decrementItem((Integer) v.getTag(R.id.TAG_INDEX), views));
    }
}
