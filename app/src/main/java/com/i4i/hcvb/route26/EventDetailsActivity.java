package com.i4i.hcvb.route26;

import android.content.Intent;
import android.location.Address;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.net.URI;
import java.util.Calendar;

public class EventDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        String name = getIntent().getStringExtra("EventName");

        setTitle(name);

        Address address = getIntent().getParcelableExtra("EventAddr");
        String description = getIntent().getStringExtra("EventDesc");

        TextView descView = findViewById(R.id.details_desc_text);
        TextView locView = findViewById(R.id.details_loc);
        TextView timeView = findViewById(R.id.details_date_time);


        //TODO: Start/End DateTime

//        Calendar startTime = getIntent().getParcelableExtra("EventStart");
//        String endTime = getIntent().getStringExtra("EventEnd");
//        timeView.setText(startTime.toString());

        String addrName = address.getAddressLine(0);
        String addrStreet = address.getAddressLine(1);
        String addrCity = address.getAddressLine(2);
        String addrState = address.getAddressLine(3);
        String addrZip = address.getPostalCode();

        String[] addrArray = { address.getAddressLine(1), address.getAddressLine(2), address.getAddressLine(3), address.getPostalCode()};
        String addr = addrArray[0] + "\n" + addrArray[1] + ", " + addrArray[2] + "\n" + addrArray[3];
        descView.setText(description);
        locView.setText(addr);

        String outAddrString = "geo:0,0?q=" + addrStreet + "+" + addrCity + ",+" + addrState + "+" + addrZip;

        outAddrString = outAddrString.replaceAll(" ", "+");

        final Uri outAddr = Uri.parse(outAddrString);

        locView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMap(outAddr);
            }
        });

    }

    public void showMap(Uri geoLoc){
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(geoLoc);
        if(intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

}
