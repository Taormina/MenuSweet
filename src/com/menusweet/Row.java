package com.menusweet;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Row {

    LinearLayout root;
    TextView itemAmount;
    Item item;
    MenuActivity activity;

    public Row(MenuActivity activity, Item item, int i) {
        this.item = item;
        this.activity = activity;

        root = new LinearLayout(activity);
        TextView textView;
        ImageView incrementButton, removeButton, decrementButton;

        textView = new TextView(activity);
        textView.setText(item.name);
        textView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));

        root.addView(textView);

        itemAmount = new TextView(activity);
        activity.setIntText(itemAmount, 0);
        itemAmount.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));

        root.addView(itemAmount);

        incrementButton = new ImageView(activity);
        incrementButton.setImageResource(R.drawable.add_button);
        incrementButton.setTag(R.id.TAG_INDEX, i);
        incrementButton.setOnClickListener(new IncrementClickListener(itemAmount));
        incrementButton.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));

        root.addView(incrementButton);

        decrementButton = new ImageView(activity);
        decrementButton.setImageResource(R.drawable.decrement_button);
        decrementButton.setTag(R.id.TAG_INDEX, i);
        decrementButton.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));

        removeButton = new ImageView(activity);
        removeButton.setImageResource(R.drawable.remove_button);
        removeButton.setTag(R.id.TAG_INDEX, i);
        removeButton.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));


        decrementButton.setOnClickListener(new DecrementClickListener(itemAmount));
        removeButton.setOnClickListener(new RemoveClickListener());

        root.addView(decrementButton);
        root.addView(removeButton);

        root.setVisibility(View.GONE);
    }

    public void setQuantity(int i) {
        activity.setIntText(itemAmount, i);
    }

    public void show() {
        root.setVisibility(View.VISIBLE);
    }

    public void hide() {
        root.setVisibility(View.GONE);
    }

    public LinearLayout getView() {
        return root;
    }
}
