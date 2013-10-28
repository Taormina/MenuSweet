package com.menusweet;

import android.app.Activity;
import android.os.AsyncTask;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

public class CategoryClickListener implements AdapterView.OnItemClickListener {

    private List<Category> categories;

    public CategoryClickListener(List<Category> categories) {
        super();
        this.categories = categories;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Activity activity = (Activity) view.getContext();
        ListView listView = (ListView) activity.findViewById(R.id.items);
        ItemArrayAdapter itemArrayAdapter = (ItemArrayAdapter) listView.getAdapter();
        itemArrayAdapter.update(categories.get(i).items);
    }
}

