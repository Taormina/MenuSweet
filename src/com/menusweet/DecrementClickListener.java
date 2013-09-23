package com.menusweet;

import android.view.View;

import java.util.ArrayList;

public class DecrementClickListener implements View.OnClickListener{

    private ArrayList<View> views;

    public DecrementClickListener(ArrayList<View> views) {
        this.views = views;
    }

    public void onClick(View v) {
        MenuActivity activity = (MenuActivity) v.getContext();
        activity.userCart.decrementItem((Integer) v.getTag(R.id.TAG_INDEX), views);
        activity.setSubtotal(v.getRootView());
    }
}
