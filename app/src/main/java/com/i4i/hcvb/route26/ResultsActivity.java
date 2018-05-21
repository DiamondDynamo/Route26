package com.i4i.hcvb.route26;

import android.content.ContentValues;
import android.database.Cursor;
import android.support.v4.app.LoaderManager;
import android.app.SearchManager;
import android.content.Intent;
import android.support.v4.content.Loader;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import io.swagger.client.model.Address;
import io.swagger.client.model.Event;
import io.swagger.client.model.Location;

public class ResultsActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Event>> {

    EventAdapter eventAdapter;
    String query = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        eventAdapter = new EventAdapter(this, new ArrayList<Event>());



        handleIntent(getIntent());


        ListView listView = (ListView) findViewById(R.id.browse_list);
        final TextView noEvent = (TextView) findViewById(R.id.no_events);

        listView.setAdapter(eventAdapter);
        listView.setEmptyView(noEvent);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Event selEvent = (Event) parent.getItemAtPosition(position);
                String name = selEvent.getName();
                Location srcLocation = selEvent.getLocation();
                Address srcAddress = srcLocation.getAddress();
                android.location.Address address = new android.location.Address(Locale.US);

                address.setPostalCode(srcAddress.getZip());
                if(srcAddress.getName() != null) {
                    address.setAddressLine(0, srcAddress.getName());
                }
                    address.setAddressLine(1, srcAddress.getStreet());
                    address.setAddressLine(2, srcAddress.getCity());
                    address.setAddressLine(3, srcAddress.getState());
                    address.setPostalCode(srcAddress.getZip());


                org.threeten.bp.OffsetDateTime srcStartDateTime = selEvent.getStartDatetime();
                org.threeten.bp.OffsetDateTime srcEndDateTime = selEvent.getEndDatetime();
                Calendar startDate = Calendar.getInstance();
                Calendar endDate = Calendar.getInstance();

//                startDate.set(srcStartDateTime.getYear(), srcStartDateTime.getMonthValue(), srcStartDateTime.getDayOfMonth(), srcStartDateTime.getHour(), srcStartDateTime.getMinute());
//                endDate.set(srcEndDateTime.getYear(), srcEndDateTime.getMonthValue(), srcEndDateTime.getDayOfMonth(), srcEndDateTime.getHour(), srcEndDateTime.getMinute());


                String description = selEvent.getDescription();
                Intent intent = new Intent(getApplicationContext(), EventDetailsActivity.class);
                intent.putExtra("EventName", name);
                intent.putExtra("EventAddr", address);
                intent.putExtra("EventDesc", description);
//                intent.putExtra("EventStart", startDate);
//                intent.putExtra("EventEnd", endTime);

                setResult(RESULT_OK, intent);
                startActivity(intent);
            }
        });

        getSupportLoaderManager().initLoader(1, null, this).forceLoad();
    }

    @Override
    public Loader<List<Event>> onCreateLoader(int id, Bundle args) {
        return new EventLoader(ResultsActivity.this);
    }

    @Override
    public void onLoadFinished(Loader<List<Event>> loader, List<Event> data) {
        eventAdapter.setEvents(data);
    }

    @Override
    public void onLoaderReset(Loader<List<Event>> loader) {
        eventAdapter.setEvents(new ArrayList<Event>());
    }



    @Override
    protected void onNewIntent(Intent intent){
        handleIntent(intent);
    }


    private void handleIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            query = intent.getStringExtra(SearchManager.QUERY);

        }
    }

}
