package com.menusweet;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
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

    /*private view holder class*/
    private class ViewHolder {
        ImageView imageView;
        TextView txtMenuName;
        TextView txtMenuDesc;
        TextView txtPrice;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        Item rowItem = getItem(position);

        LayoutInflater mInflater = (LayoutInflater) activity.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_row, null);


            holder = new ViewHolder();
            holder.txtMenuName = (TextView) convertView.findViewById(R.id.menu_name);
            holder.txtMenuDesc = (TextView) convertView.findViewById(R.id.description);
            holder.txtPrice = (TextView) convertView.findViewById(R.id.price);
            holder.imageView = (ImageView) convertView.findViewById(R.id.list_image);
            convertView.setTag(holder);
        }  else
            holder = (ViewHolder) convertView.getTag();

        holder.txtMenuDesc.setText(rowItem.description);
        holder.txtMenuName.setText(rowItem.name);
        holder.txtPrice.setText(String.valueOf("$" + rowItem.price / 100 + "." + rowItem.price % 100));
        //holder.imageView.setImageResource(rowItem.getImageId());

        convertView.setOnClickListener(new AddClickListener(rowItem, 1));

        AbsListView.LayoutParams params = new AbsListView.LayoutParams(AbsListView.LayoutParams.WRAP_CONTENT, AbsListView.LayoutParams.WRAP_CONTENT);
        convertView.setLayoutParams(params);

        return convertView;

    }

}