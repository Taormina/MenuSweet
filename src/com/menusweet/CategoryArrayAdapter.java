package com.menusweet;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class CategoryArrayAdapter<Category> extends ArrayAdapter<Category> {

    MenuActivity activity;

    public CategoryArrayAdapter(Context context, int textViewResourceId, List<Category> objects) {
        super(context, textViewResourceId, objects);
        this.activity = (MenuActivity) context;
    }

    /*private view holder class*/
   /*private class ViewHolder {
        ImageView imageView;
        TextView txtMenuName;
        TextView txtMenuDesc;
        TextView txtPrice;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
       // Menu rowItem = getItem(position);

        LayoutInflater mInflater = (LayoutInflater) activity.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.list_row, null);
            holder = new ViewHolder();
            holder.txtMenuName = (TextView) convertView.findViewById(R.id.menu_name);
            holder.txtMenuDesc = (TextView) convertView.findViewById(R.id.description);
            holder.txtPrice = (TextView) convertView.findViewById(R.id.price);
            holder.imageView = (ImageView) convertView.findViewById(R.id.list_image);
            convertView.setTag(holder);
        }  else
            holder = (ViewHolder) convertView.getTag();

        holder.txtMenuDesc.setText(rowItem.getDescription());
        holder.txtMenuName.setText(rowItem.getName());
        holder.txtPrice.setText(String.valueOf(rowItem.getPrice()) + " TL");
        //holder.imageView.setImageResource(rowItem.getImageId());

        return convertView;
    }   */

}