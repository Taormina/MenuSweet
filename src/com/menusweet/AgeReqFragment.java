package com.menusweet;

import java.util.Calendar;
import java.util.Date;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.Toast;

public class AgeReqFragment extends Fragment {

	public static View view; 
	private DatePicker dp;
	private Button done;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {	
		
		if (view != null) {
            ViewGroup parent = (ViewGroup) view.getParent();
            
            if (parent != null)
                parent.removeView(view);
        }
        try {
            view = inflater.inflate(R.layout.fragment_agereq, container, false);
        } catch (InflateException e) {
            /* Map is already there, just return view as it is */
        }	
		
        dp = (DatePicker) view.findViewById(R.id.datePicker1);
		done = (Button) view.findViewById(R.id.bAgeDone);
		
        done.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(isLegalAge(dp)){
				Toast.makeText(getActivity(), "Legal", Toast.LENGTH_SHORT).show();
				
				}else{
					Toast.makeText(getActivity(), "Not Legal", Toast.LENGTH_SHORT).show();	
				}
			}
		});		
		
		return view;
	}

	private boolean isLegalAge(DatePicker datePick) {
		boolean is21;

		Calendar cal = Calendar.getInstance();

		int day = datePick.getDayOfMonth();
		int month = datePick.getMonth();
		int year = datePick.getYear()-1900;

		
		Date now = cal.getTime();
		
		if (now.getYear() - year >= 21 && now.getMonth()-month >= 0 && now.getDate()-day>=0)
			is21 = true;
		else
			is21 = false;
		return is21;
	}

}
