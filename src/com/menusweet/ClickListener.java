package com.menusweet;

import android.view.View;

public abstract class ClickListener implements View.OnClickListener {

    MenuActivity activity;

    public void onClick(View v) {
        activity = (MenuActivity) v.getContext();
        doAction(v);
        int index = (Integer) v.getTag(R.id.TAG_INDEX);
        activity.updateCartRow(index, activity.userCart.getItem(index));
        activity.setSubtotal(v.getRootView());
        View emptyCartMessage = v.getRootView().findViewById(R.id.empty_cart_message);
        if (activity.isCartEmpty())
            emptyCartMessage.setVisibility(View.VISIBLE);
    }

    public abstract void doAction(View v);
}
