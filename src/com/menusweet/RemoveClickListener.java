package com.menusweet;

import android.view.View;

public class RemoveClickListener implements View.OnClickListener{

    public void onClick(View v) {
        MenuActivity activity = (MenuActivity) v.getContext();
        activity.userCart.removeItem((Integer) v.getTag(R.id.TAG_INDEX));
        System.out.println(activity.userCart);
    }
}
