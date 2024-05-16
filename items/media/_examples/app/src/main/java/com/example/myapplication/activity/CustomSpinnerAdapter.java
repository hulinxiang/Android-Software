package com.example.myapplication.activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


/**
 * @author Wenhui Shi
 */
public class CustomSpinnerAdapter extends ArrayAdapter<String> {

    /**
     * Constructor for the CustomSpinnerAdapter.
     * @param context The context in which the adapter is being used.
     * @param resource The resource ID for a layout file containing a TextView to use when instantiating views.
     * @param objects The objects to represent in the dropdown.
     */
    public CustomSpinnerAdapter(Context context, int resource, String[] objects) {
        super(context, resource, objects);
    }

    /**
     * Returns a dropdown view for the spinner at the specified position.
     * @param position The position of the item within the adapter's data set.
     * @param convertView The old view to reuse, if possible.
     * @param parent The parent that this view will eventually be attached to.
     * @return The dropdown view corresponding to the specified position.
     */
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

    /**
     * Returns whether the item at the specified position is enabled.
     * @param position The position of the item within the adapter's data set.
     * @return True if the item is enabled, false otherwise.
     */
    @Override
    public boolean isEnabled(int position) {
        // Disable the first item (title) from being selected
        return position != 0;
    }
}