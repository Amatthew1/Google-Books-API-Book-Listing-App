package com.ironstarbooks.books;

/**
 * Created by Admin on 10/27/2016.
 */

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;


public class BookAdapter extends ArrayAdapter<Book> {

    public BookAdapter(Activity context, ArrayList<Book> books) {
        super(context, 0, books);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;

        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate((R.layout.activity_main), parent, false);
        }

        final Book currentBook = getItem(position);

        TextView titleTextView = (TextView) listItemView.findViewById(R.id.title);
        String titleText = currentBook.getTitle();
        titleTextView.setText("Title:" + " " + titleText);


        TextView authorTextView = (TextView) listItemView.findViewById(R.id.authors);
        StringBuilder authorStringBuilder = new StringBuilder();
        List<String> authorText = currentBook.getAuthors();
        for (int k = 0; k < authorText.size(); k++) {
            authorStringBuilder.append(authorText.get(k));
            if (k < (authorText.size()) - 1) authorStringBuilder.append(",");
        }
        authorTextView.setText("Author(s): " + " " + authorStringBuilder);


        return listItemView;
    }
}
