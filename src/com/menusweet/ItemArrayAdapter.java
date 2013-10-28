package com.menusweet;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;


public class ItemArrayAdapter extends ArrayAdapter<Item> {

    MenuActivity activity;
    List<Item> itemList;

    public ItemArrayAdapter(MenuActivity activity, List<Item> objects) {
        super(activity, R.layout.category_row, objects);
        this.activity = activity;
        this.itemList = objects;
    }

    public void update(List<Item> objects) {
        itemList.clear();
        itemList.addAll(objects);
        notifyDataSetChanged();
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        Item rowItem = getItem(position);
        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater) activity.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.item_row, null);
        }

        TextView txtMenuName = (TextView) convertView.findViewById(R.id.menu_name);
        TextView txtMenuDesc = (TextView) convertView.findViewById(R.id.description);
        TextView txtPrice = (TextView) convertView.findViewById(R.id.price);
        //ImageView imageView = (ImageView) convertView.findViewById(R.id.list_image);
        txtMenuDesc.setText(rowItem.description);
        txtMenuName.setText(rowItem.name);
        txtPrice.setText(String.valueOf("$" + rowItem.price / 100 + "." + rowItem.price % 100));
        //imageView.setImageResource(rowItem.pictureResId);

        convertView.setTag(R.id.TAG_ITEM, rowItem);

        AbsListView.LayoutParams params = new AbsListView.LayoutParams(AbsListView.LayoutParams.WRAP_CONTENT, AbsListView.LayoutParams.WRAP_CONTENT);
        convertView.setLayoutParams(params);

        return convertView;

    }

}