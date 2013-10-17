package com.menusweet;

import android.content.res.Resources;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

public class SendClickListener implements View.OnClickListener {

    @Override
    public void onClick(View view) {

        MenuActivity activity = (MenuActivity) view.getContext();
        Resources res = activity.getResources();
        String feedback = ((TextView) activity.findViewById(R.id.feedback_content)).getText().toString();

        if (((RadioButton) activity.findViewById(R.id.radio_store)).isChecked())
            activity.email(res.getString(R.string.store_email), res.getString(R.string.feedback_subject), feedback);
        else
            activity.email(res.getString(R.string.our_email), res.getString(R.string.feedback_subject), feedback);
    }
}
