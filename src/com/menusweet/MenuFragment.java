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
    LinearLayout cart;
    int i = 0;


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
        categoryList.setOnItemClickListener(new CategoryClickListener(activity.categories));

        // Items
        ListView itemList = (ListView) activity.findViewById(R.id.items);
        ArrayList<Item> categoryItems = new ArrayList<Item>();
        categoryItems.addAll(activity.categories.get(0).items);
        itemList.setAdapter(new ItemArrayAdapter(activity, categoryItems));
        itemList.setOnItemClickListener(new AddClickListener());

        // Cart
        cart = (LinearLayout) getActivity().findViewById(R.id.cart);

        if (!activity.isCartEmpty()) {
            view.findViewById(R.id.empty_cart_message).setVisibility(View.GONE);
            for (Item item : activity.items) {
                addCartRow(item);
                activity.userCart.addItem(i++, 0, "");
            }
        }

        i = 0;
        for (CartItem item : activity.userCart.cartItems)
            activity.updateCartRow(i++, item);

        activity.setSubtotal(view);
    }

    public void addCartRow(Item item) {
        MenuActivity activity = (MenuActivity) getActivity();

        LinearLayout current = new LinearLayout(activity);
        TextView textView, itemAmount;
        ImageView incrementButton, removeButton, decrementButton;

        textView = new TextView(activity);
        textView.setText(item.name);
        textView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));

        itemAmount = new TextView(activity);
        activity.setIntText(itemAmount, 0);
        itemAmount.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));

        incrementButton = new ImageView(activity);
        incrementButton.setImageResource(R.drawable.add_button);
        incrementButton.setTag(R.id.TAG_INDEX, i);
        incrementButton.setOnClickListener(new IncrementClickListener(itemAmount));
        incrementButton.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));

        decrementButton = new ImageView(activity);
        decrementButton.setImageResource(R.drawable.decrement_button);
        decrementButton.setTag(R.id.TAG_INDEX, i);
        decrementButton.setOnClickListener(new DecrementClickListener(itemAmount));
        decrementButton.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));

        removeButton = new ImageView(activity);
        removeButton.setImageResource(R.drawable.remove_button);
        removeButton.setTag(R.id.TAG_INDEX, i);
        removeButton.setOnClickListener(new RemoveClickListener());
        removeButton.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));

        current.addView(textView);
        current.addView(itemAmount);
        current.addView(incrementButton);
        current.addView(decrementButton);
        current.addView(removeButton);

        current.setVisibility(View.GONE);

        i++;
    }

}
