package com.ironstarbooks.books;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.content.Intent;

/**
 * Created by Admin on 11/28/2016.
 */

public class MainActivity extends AppCompatActivity {

    public static final String LOG_TAG = MainActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void getBooks(View view) {
        EditText queryText = (EditText) findViewById(R.id.edit_query);
        String query = queryText.getText().toString();
        StringBuilder queryStringBuilder = new StringBuilder();

        queryStringBuilder.append("https://www.googleapis.com/books/v1/volumes?q=");
        queryStringBuilder.append(query);
        queryStringBuilder.append("&maxResults=25");
        String mQuery = queryStringBuilder.toString();
        mQuery = mQuery.replaceAll(" ", ",");
        Log.d(LOG_TAG, mQuery);
        Intent listingIntent = new Intent(MainActivity.this, ListingActivity.class);
        listingIntent.putExtra("KEY", mQuery);
        startActivity(listingIntent);
    }

}
