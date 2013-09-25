package com.menusweet;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import java.util.ArrayList;

public class CartFragment extends Fragment {

    public static View view;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if (view != null) {
            ViewGroup parent = (ViewGroup) view.getParent();
            if (parent != null)
                parent.removeView(view);
        }
        try {
            view = inflater.inflate(R.layout.fragment_cart, container, false);
        } catch (InflateException e) {
            /* Map is already there, just return view as it is */
        }

        MenuActivity activity = (MenuActivity) this.getActivity();

        if (!activity.isCartEmpty()) {
            ((TextView) view.findViewById(R.id.empty_cart_message)).setVisibility(View.GONE);

            UserCart cart = activity.userCart;
            LinearLayout layout = (LinearLayout) view.findViewById(R.id.cart);
            TextView textView, itemAmount;
            ImageView incrementButton, removeButton, decrementButton;
            ArrayList<View> views;
            int i = 0;

            for (CartItem item : cart.cartItems) {
                textView = new TextView(activity);
                textView.setText(item.baseItem.name);
                textView.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));

                layout.addView(textView);

                itemAmount = new TextView(activity);
                activity.setIntText(itemAmount, item.quantity);
                itemAmount.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));

                layout.addView(itemAmount);

                incrementButton = new ImageView(activity);
                incrementButton.setImageResource(R.drawable.add_button);
                incrementButton.setTag(R.id.TAG_INDEX, i);
                incrementButton.setOnClickListener(new AddClickListener(itemAmount));
                incrementButton.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));

                layout.addView(incrementButton);

                decrementButton = new ImageView(activity);
                decrementButton.setImageResource(R.drawable.decrement_button);
                decrementButton.setTag(R.id.TAG_INDEX, i);
                decrementButton.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));

                removeButton = new ImageView(activity);
                removeButton.setImageResource(R.drawable.remove_button);
                removeButton.setTag(R.id.TAG_INDEX, i);
                removeButton.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));

                views = new ArrayList<View>();
                views.add(textView);
                views.add(itemAmount);
                views.add(incrementButton);
                views.add(decrementButton);
                views.add(removeButton);

                decrementButton.setOnClickListener(new DecrementClickListener(views, itemAmount));
                removeButton.setOnClickListener(new RemoveClickListener(views));

                layout.addView(decrementButton);
                layout.addView(removeButton);

                i++;
            }
        }

        activity.setSubtotal(view);

        return view;
    }

}
