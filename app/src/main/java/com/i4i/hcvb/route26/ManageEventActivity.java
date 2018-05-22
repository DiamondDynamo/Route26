/*
 Name: ManageEventActivity.java
 Written by: Charles Bein
 Description: Allows logged in users to modify or delete events that they own
 NOTE: Because it is unusable and untestable, this activity is currently empty
 */

package com.i4i.hcvb.route26;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

public class ManageEventActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_event);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
