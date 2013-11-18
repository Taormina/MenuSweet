package com.menusweet;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class ItemDetailsFragment extends DialogFragment{
	
	TextView name, description;
	Button addToCart;
	static View adapterView;
	public ItemDetailsFragment(){}
	
	  void passView(View v, String itemName, String itemDescrip) {
		 adapterView = v;
        
        Bundle args = new Bundle();
        args.putString("itemName", itemName);
        args.putString("itemDescrip", itemDescrip);
        this.setArguments(args);
    }
	
	
	
	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		
		Window window = getDialog().getWindow();
	    window.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		View v = inflater.inflate(R.layout.fragment_item_details,  container, false);
			addToCart=(Button) v.findViewById(R.id.bItemDetailAddToCart);
			name = (TextView) v.findViewById(R.id.tvItemDetailName);
			description = (TextView) v.findViewById(R.id.tvItemDetailDescrip);
			
			String asdf = getArguments().getString("itemName");
			name.setText(asdf);
			description.setText(getArguments().getString("itemDescrip"));
			
			addToCart.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View vi) {
					// TODO Auto-generated method stub
				 	MenuActivity activity = (MenuActivity) adapterView.getContext();
					Item item = (Item) adapterView.getTag(R.id.TAG_ITEM);
			        int index = activity.items.indexOf(item);
			        activity.userCart.addItem(index, 1, "");
			        activity.updateCartRow(index, activity.userCart.getItem(index));
			
			        
			        activity.setSubtotal(adapterView.getRootView());
			        View emptyCartMessage = adapterView.getRootView().findViewById(R.id.empty_cart_message);
			        if (emptyCartMessage.getVisibility() != View.GONE)
			            emptyCartMessage.setVisibility(View.GONE);
				getDialog().dismiss();
				}
			});
			
		getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		return v;

	}


	
}
