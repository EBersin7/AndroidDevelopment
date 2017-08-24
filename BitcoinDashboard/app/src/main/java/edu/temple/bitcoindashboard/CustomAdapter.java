package edu.temple.bitcoindashboard;

//necessary imports

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

//custom adapter to work with the spinner
public class CustomAdapter extends ArrayAdapter {

    //default constructor
    public CustomAdapter(Context context, int resource, Object[] objects) {
        super(context, resource, objects);
    }

    //returns the view from super call
    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = super.getView(position, convertView, parent);
        return v;
    }

    //returns the view from super call
    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        View v = super.getDropDownView(position, convertView, parent);
        return v;
    }
}