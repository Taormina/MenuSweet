package com.menusweet;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class CategoryArrayAdapter extends ArrayAdapter<Category> {

    MenuActivity activity;
    List<Category> categoryList;

    public CategoryArrayAdapter(MenuActivity activity, List<Category> objects) {
        super(activity, R.layout.category_row, objects);
        this.activity = activity;
        this.categoryList = objects;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        //ViewHolder holder = null;
        Category rowItem = getItem(position);

        LayoutInflater mInflater = (LayoutInflater) activity.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.category_row, null);
            ((TextView) convertView.findViewById(R.id.category_name)).setText(rowItem.toString());

        }

        AbsListView.LayoutParams params = new AbsListView.LayoutParams(AbsListView.LayoutParams.WRAP_CONTENT, AbsListView.LayoutParams.WRAP_CONTENT);
        convertView.setLayoutParams(params);

        return convertView;

    }

}