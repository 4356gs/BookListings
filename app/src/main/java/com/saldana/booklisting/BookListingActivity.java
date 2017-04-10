/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.saldana.booklisting;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import static com.saldana.booklisting.MainActivity.modifiedUrl;

public class BookListingActivity extends AppCompatActivity {

    public static final String LOG_TAG = BookListingActivity.class.getName();

    /**
     * Sample JSON response for a USGS query
     */
   // private static final String SAMPLE_JSON_RESPONSE = "https://www.googleapis.com/books/v1/volumes?q=android&maxResults=15";

    /** Adapter for the list of earthquakes */
    private BookAdapter mAdapter;

    private String queryUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.booklisting_activity);

        // Find a reference to the {@link ListView} in the layout
        ListView earthquakeListView = (ListView) findViewById(R.id.list);

        // Create a new adapter that takes an empty list of earthquakes as input
        mAdapter = new BookAdapter(this, new ArrayList<BookListing>());

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        earthquakeListView.setAdapter(mAdapter);


        Intent intent = getIntent();
        queryUrl = intent.getStringExtra(modifiedUrl);
        Log.v(LOG_TAG, "url is: " + queryUrl);



        // Start the AsyncTask to fetch the earthquake data
        BookAsyncTask task = new BookAsyncTask();
        task.execute(modifiedUrl);
    }

    private class BookAsyncTask extends AsyncTask<String, Void, List<BookListing>> {

        @Override
        protected List<BookListing> doInBackground(String... urls) {
            // Don't perform the request if there are no URLs, or the first URL is null.
            if (urls.length < 1 || urls[0] == null) {
                return null;
            }

            List<BookListing> result = QueryUtils.fetchEarthquakeData(urls[0]);
            return result;

        }

        @Override
        protected void onPostExecute(List<BookListing> data) {
            // Clear the adapter of previous earthquake data
            mAdapter.clear();

            // If there is a valid list of {@link Earthquake}s, then add them to the adapter's
            // data set. This will trigger the ListView to update.
            if (data != null && !data.isEmpty()) {
                mAdapter.addAll(data);
            }
        }
        }
    }

