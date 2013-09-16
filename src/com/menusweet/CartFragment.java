package com.menusweet;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created with IntelliJ IDEA.
 * User: GrumpyOwl
 * Date: 9/9/13
 * Time: 6:23 PM
 * To change this template use File | Settings | File Templates.
 */
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

        if (!activity.isCartEmpty())
            view.findViewById(R.id.empty_cart_message).setVisibility(View.GONE);


        return view;
    }

}
