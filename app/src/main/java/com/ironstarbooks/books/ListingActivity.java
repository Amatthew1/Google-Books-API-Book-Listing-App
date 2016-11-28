package com.ironstarbooks.books;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.LoaderManager.LoaderCallbacks;
import android.app.LoaderManager;
import android.content.Loader;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ListingActivity extends AppCompatActivity implements LoaderCallbacks<List<Book>> {


    private static final int BOOK_LOADER_ID = 1;


    private BookAdapter mAdapter;
    private TextView mEmptyStateView;
    private String GBOOKS_REQUEST_URL = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);
        ListView bookListView = (ListView) findViewById(R.id.list);
        mEmptyStateView = (TextView) findViewById(R.id.empty_view);
        bookListView.setEmptyView(mEmptyStateView);

        Bundle extras = getIntent().getExtras();

        ///////////////////Janky code to follow////////////////////////////
        String mQuery = null;
        if (extras != null) {
            mQuery = extras.getString("KEY");
        }
        GBOOKS_REQUEST_URL = mQuery;
        //////////////////////End janky code////////////////////////////////////
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            LoaderManager loaderManager = getLoaderManager();
            loaderManager.initLoader(BOOK_LOADER_ID, null, this);
        } else {
            View loadingIndicator = (View) findViewById(R.id.load_indicator);
            loadingIndicator.setVisibility(View.GONE);
            mEmptyStateView.setText(R.string.no_internet);
        }
        mAdapter = new BookAdapter(this, new ArrayList<Book>());
        bookListView.setAdapter(mAdapter);


    }

    @Override
    public Loader<List<Book>> onCreateLoader(int i, Bundle bundle) {
        return new BookLoader(this, GBOOKS_REQUEST_URL);
    }

    @Override
    public void onLoadFinished(Loader<List<Book>> loader, List<Book> books) {
        View loadingIndicator = (View) findViewById(R.id.load_indicator);
        loadingIndicator.setVisibility(View.GONE);

        mEmptyStateView.setText(R.string.no_books);

        mAdapter.clear();

        if (books != null && !books.isEmpty()) {
            mAdapter.addAll(books);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<Book>> loader) {
        mAdapter.clear();

    }
}

