package com.example.myapplication.activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class CustomSpinnerAdapter extends ArrayAdapter<String> {

    public CustomSpinnerAdapter(Context context, int resource, String[] objects) {
        super(context, resource, objects);
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        View view = super.getDropDownView(position, convertView, parent);
        TextView textView = (TextView) view;
        if (position == 0) {
            // Set the hint text color for the first item (title)
            textView.setTextColor(getContext().getResources().getColor(android.R.color.darker_gray));
        } else {
            textView.setTextColor(getContext().getResources().getColor(android.R.color.black));
        }
        return view;
    }

    @Override
    public boolean isEnabled(int position) {
        // Disable the first item (title) from being selected
        return position != 0;
    }
}
