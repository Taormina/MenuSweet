package com.menusweet;

import android.view.View;

public class AddClickListener implements View.OnClickListener {



    public void onClick(View v) {
        MenuActivity activity = (MenuActivity) v.getContext();
        activity.userCart.addItem((Item) v.getTag(R.id.TAG_ITEM), (Integer) v.getTag(R.id.TAG_QUANTITY));
    }
}
