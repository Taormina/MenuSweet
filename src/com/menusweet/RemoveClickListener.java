package com.menusweet;

import android.view.View;

import java.util.ArrayList;

public class RemoveClickListener extends ClickListener {

    private ArrayList<View> views;

    public RemoveClickListener(ArrayList<View> views) {
        this.views = views;
    }

    @Override
    public void doAction(View v) {
        activity.userCart.removeItem((Integer) v.getTag(R.id.TAG_INDEX), views);
    }
}
