package com.menusweet;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

public class FeedbackFragment extends Fragment {

    public static View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        if (view != null) {
            ViewGroup parent = (ViewGroup) view.getParent();
            if (parent != null)
                parent.removeView(view);
        }
        try {
            view = inflater.inflate(R.layout.fragment_feedback, container, false);
        } catch (InflateException e) {
            /* Map is already there, just return view as it is */
        }

        Button button = (Button) view.findViewById(R.id.send_button);
        button.setOnClickListener(new SendClickListener());

        return view;
    }
}
