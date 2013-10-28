package com.menusweet;

import android.view.View;
import android.widget.AdapterView;

public class AddClickListener implements AdapterView.OnItemClickListener {

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        MenuActivity activity = (MenuActivity) view.getContext();
        Item item = (Item) view.getTag(R.id.TAG_ITEM);
        int index = activity.items.indexOf(item);
        activity.userCart.addItem(index, 1, "");
        activity.updateCartRow(index, activity.userCart.getItem(index));

        activity.setSubtotal(view.getRootView());
        View emptyCartMessage = view.getRootView().findViewById(R.id.empty_cart_message);
        if (emptyCartMessage.getVisibility() != View.GONE)
            emptyCartMessage.setVisibility(View.GONE);
    }
}
