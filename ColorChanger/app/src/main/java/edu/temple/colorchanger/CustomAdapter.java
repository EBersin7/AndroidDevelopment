package edu.temple.colorchanger;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class CustomAdapter extends ArrayAdapter {

    final String[] my_colors = {"Blue", "Green", "Purple", "Red", "Yellow"};

    public CustomAdapter(Context context, int resource, Object[] objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = super.getView(position, convertView, parent);
        v.setBackgroundColor(Color.parseColor(my_colors[position]));
        return v;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        View v = super.getView(position, convertView, parent);
        v.setBackgroundColor(Color.parseColor(my_colors[position]));
        return v;
    }
}