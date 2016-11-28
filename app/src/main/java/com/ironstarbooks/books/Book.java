package com.ironstarbooks.books;

import java.util.List;

/**
 * Created by Admin on 10/27/2016.
 */

public class Book {
    private String mTitle;
    private List<String> mAuthors;


    public Book(String title, List<String> authors) {

        this.mTitle = title;
        this.mAuthors = authors;

    }

    public String getTitle() {
        return mTitle;
    }

    public List<String> getAuthors() {
        return mAuthors;
    }


}