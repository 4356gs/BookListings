package com.saldana.booklisting;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Gelo Saldana on 4/2/2017.
 */

public class BookAdapter extends ArrayAdapter<BookListing> {

    public BookAdapter(Activity context, ArrayList<BookListing> BookListing) {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context, 0, BookListing);
    }

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.book_list_item, parent, false);
        }

        // Get the {@link AndroidFlavor} object located at this position in the list
        BookListing currentBook = getItem(position);

        // Find the TextView in the list_item.xml layout with the ID version_name
        TextView titleTextView = (TextView) listItemView.findViewById(R.id.title);
        // Get the version name from the current AndroidFlavor object and
        // set this text on the name TextView
        titleTextView.setText(currentBook.getTitle());

        // Find the TextView in the list_item.xml layout with the ID version_number
        TextView authorTextView = (TextView) listItemView.findViewById(R.id.author);
        // Get the version number from the current AndroidFlavor object and
        // set this text on the number TextView
        authorTextView.setText(currentBook.getAuthor());

        // Find the TextView in the list_item.xml layout with the ID version_number
        TextView dateTextView = (TextView) listItemView.findViewById(R.id.publishedDate);
        // Get the version number from the current AndroidFlavor object and
        // set this text on the number TextView
        dateTextView.setText(currentBook.getPublishedDate());




/*        ImageView iconView = (ImageView) listItemView.findViewById(R.id.image);

        if (currentBook.hasImage()) {
            iconView.setImageResource(currentBook.getAttractionId());
            iconView.setVisibility(View.VISIBLE);
        } else {
            iconView.setVisibility(View.GONE);
        }*/

        // Return the whole list item layout (containing 2 TextViews and an ImageView)
        // so that it can be shown in the ListView
        return listItemView;
    }
}
