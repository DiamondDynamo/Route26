package com.i4i.hcvb.route26;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import java.util.ArrayList;

import io.swagger.client.ApiException;
import io.swagger.client.api.EventApi;
import io.swagger.client.model.Event;

public class ResultsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        handleIntent(getIntent());

        EventApi eventApi = new EventApi();
        try {
            eventApi.getEventList(0, 10, 11);
        } catch (ApiException e){
            System.out.println(e.getMessage());
        }

        final ListView listView = (ListView) findViewById(R.id.browse_list);

        ArrayList<String> list = new ArrayList<>();
        list.add(Event.class.getName());

//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.browse_item, R.id.browse_name, list);

    }

    @Override
    protected void onNewIntent(Intent intent){
        handleIntent(intent);
    }


    private void handleIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);

        }
    }

}
