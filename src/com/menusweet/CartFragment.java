package com.menusweet;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;

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
        /* map is already there, just return view as it is */
        }

        MenuActivity activity = (MenuActivity) this.getActivity();

        if (!activity.isCartEmpty()) {
            ((TextView) view.findViewById(R.id.empty_cart_message)).setVisibility(View.GONE);

            UserCart cart = activity.userCart;
            LinearLayout layout = (LinearLayout) view.findViewById(R.id.cart);
            TextView textView;

            for (CartItem item : cart.cartItems) {
                textView = new TextView(activity);
                textView.setText(item.toString());
                //textView.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));

                layout.addView(textView);
            }

            StringBuilder subtotal = new StringBuilder();
            subtotal.append(cart.totalPrice / 100);
            subtotal.append(".");
            subtotal.append(cart.totalPrice % 100);
            ((TextView) view.findViewById(R.id.subtotal)).setText(subtotal.toString());
        }

        return view;
    }

}
