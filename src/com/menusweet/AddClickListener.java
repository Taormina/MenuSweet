package com.menusweet;

import android.view.View;

public class AddClickListener implements View.OnClickListener {

    public void onClick(View v) {
        MenuActivity activity = (MenuActivity) v.getContext();
        activity.userCart.incrementItem((Integer) v.getTag(R.id.TAG_INDEX));
        activity.setSubtotal(v.getRootView());
    }
}
