package com.menusweet;

import android.view.View;
import android.widget.TextView;

public abstract class ClickListener implements View.OnClickListener {

    MenuActivity activity;

    public void onClick(View v) {
        activity = (MenuActivity) v.getContext();
        doAction(v);
        activity.setSubtotal(v.getRootView());
        View emptyCartMessage = v.getRootView().findViewById(R.id.empty_cart_message);
        if (activity.isCartEmpty())
            emptyCartMessage.setVisibility(View.VISIBLE);
    }

    public abstract void doAction(View v);
}
