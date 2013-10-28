package com.menusweet;

import android.view.View;
import android.widget.TextView;

public class DecrementClickListener extends ClickListener {

    private TextView quantity;

    public DecrementClickListener(TextView quantity) {
        this.quantity = quantity;
    }

    public void doAction(View v) {
        activity.setIntText(quantity, activity.userCart.decrementItem((Integer) v.getTag(R.id.TAG_INDEX)));
    }
}
