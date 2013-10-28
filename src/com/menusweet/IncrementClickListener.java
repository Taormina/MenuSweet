package com.menusweet;

import android.view.View;
import android.widget.TextView;

public class IncrementClickListener extends ClickListener {

    private TextView quantity;

    public IncrementClickListener(TextView quantity) {
      this.quantity = quantity;
    }

    public void doAction(View v) {
        activity.setIntText(quantity, activity.userCart.incrementItem((Integer) v.getTag(R.id.TAG_INDEX)));
    }
}
