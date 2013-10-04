package com.menusweet;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

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
                textView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));

                layout.addView(textView);

                itemAmount = new TextView(activity);
                activity.setIntText(itemAmount, item.quantity);
                itemAmount.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));

                layout.addView(itemAmount);

                incrementButton = new ImageView(activity);
                incrementButton.setImageResource(R.drawable.add_button);
                incrementButton.setTag(R.id.TAG_INDEX, i);
                incrementButton.setOnClickListener(new IncrementClickListener(itemAmount));
                incrementButton.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));

                layout.addView(incrementButton);

                decrementButton = new ImageView(activity);
                decrementButton.setImageResource(R.drawable.decrement_button);
                decrementButton.setTag(R.id.TAG_INDEX, i);
                decrementButton.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));

                removeButton = new ImageView(activity);
                removeButton.setImageResource(R.drawable.remove_button);
                removeButton.setTag(R.id.TAG_INDEX, i);
                removeButton.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));

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
    }
}
