package com.saldana.booklisting;

/**
 * Created by Gelo Saldana on 4/4/2017.
 * An Object contains information related to a single book
 */

public class BookListing {
    // Title of the book
    private String mTitle;
    // Author of the book
    private String mAuthor;
    // Date the book was published
    private String mPublishedDate;

    public BookListing(String title, String author, String publishedDate) {
        mTitle = title;
        mAuthor = author;
        mPublishedDate = publishedDate;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getAuthor() {
        return mAuthor;
    }

    public String getPublishedDate() {
        return mPublishedDate;
    }
}
