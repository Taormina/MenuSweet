package com.menusweet;

import android.view.View;

import java.util.ArrayList;

public class RemoveClickListener implements View.OnClickListener{

    private ArrayList<View> views;

    public RemoveClickListener(ArrayList<View> views) {
        this.views = views;
    }

    public void onClick(View v) {
        MenuActivity activity = (MenuActivity) v.getContext();
        activity.userCart.removeItem((Integer) v.getTag(R.id.TAG_INDEX), views);
        activity.setSubtotal(v.getRootView());
    }
}