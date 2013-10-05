package com.menusweet;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class MenuFragment extends Fragment {

    public static View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view != null) {
            ViewGroup parent = (ViewGroup) view.getParent();
            if (parent != null)
                parent.removeView(view);
        }
        try {
            view = inflater.inflate(R.layout.fragment_menu, container, false);
        } catch (InflateException e) {
                /* It is already there, just return view as it is */
        }
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        MenuActivity activity = (MenuActivity) getActivity();

        // Category
        ListView categoryList = (ListView) activity.findViewById(R.id.categories);
        categoryList.setAdapter(new CategoryArrayAdapter(activity, activity.categories));

        // Items
        ListView itemList = (ListView) activity.findViewById(R.id.items);
        itemList.setAdapter(new ItemArrayAdapter(activity, activity.categories.get(0).items));

        if (!activity.isCartEmpty()) {
            view.findViewById(R.id.empty_cart_message).setVisibility(View.GONE);
            for (CartItem item : activity.userCart.cartItems)
                activity.addGraphicallyToCart(item);
        }

        activity.setSubtotal(view);
    }
}
