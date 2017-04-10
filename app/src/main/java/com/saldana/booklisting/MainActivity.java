package com.saldana.booklisting;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public static final String REQUEST_URL = "https://www.googleapis.com/books/v1/volumes?q=";

    public static String modifiedUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, BookListingActivity.class);
                EditText seachEditText = (EditText) findViewById(R.id.editText);
                String userTypedQuery = seachEditText.getText().toString();
                userTypedQuery = userTypedQuery.trim();
                userTypedQuery = userTypedQuery.replace(" ", "+");

                //Add the typed query by the user to the request url
                modifiedUrl = REQUEST_URL + userTypedQuery + "&maxResults=15";

                i.putExtra(modifiedUrl, REQUEST_URL + userTypedQuery);

                startActivity(i);
            }
        });
    }}
