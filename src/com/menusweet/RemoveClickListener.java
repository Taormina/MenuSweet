package com.menusweet;

import android.view.View;

public class RemoveClickListener extends ClickListener {

    @Override
    public void doAction(View v) {
        activity.userCart.removeItem((Integer) v.getTag(R.id.TAG_INDEX));
    }
}
