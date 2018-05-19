package com.i4i.hcvb.route26;

import android.app.DatePickerDialog;
import android.app.LoaderManager;
import android.app.TimePickerDialog;
import android.content.AsyncTaskLoader;
import android.content.Context;
import android.content.Loader;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import org.threeten.bp.LocalDate;
import org.threeten.bp.LocalTime;
import org.threeten.bp.OffsetDateTime;
import org.threeten.bp.ZoneOffset;

import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import io.swagger.client.ApiClient;
import io.swagger.client.ApiException;
import io.swagger.client.Configuration;
import io.swagger.client.api.EventApi;
import io.swagger.client.auth.HttpBasicAuth;
import io.swagger.client.model.Address;
import io.swagger.client.model.Event;
import io.swagger.client.model.Location;

public class CreateEventActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Event> {

    String mUsername;
    String mPassword;

    private int mStartYear, mStartMonth, mStartDay, mStartHour, mStartMinute;
    private int mEndYear, mEndMonth, mEndDay, mEndHour, mEndMinute;
    DatePickerDialog datePicker;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        SharedPreferences preferences = getSharedPreferences("loginCredentials", MODE_PRIVATE);

        mUsername = preferences.getString("username", null);
        mPassword = preferences.getString("password", null);

        final EditText nameField = findViewById(R.id.create_name);

        final EditText streetField = findViewById(R.id.create_street);
        final EditText cityField = findViewById(R.id.create_city);
        final EditText stateField = findViewById(R.id.create_state);
        final EditText zipField = findViewById(R.id.create_zip);

        Button startDateButton = findViewById(R.id.create_start_date_button);
        final TextView startDateDisp = findViewById(R.id.create_start_date_disp);


        startDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar c = Calendar.getInstance(TimeZone.getDefault());

                int y = c.get(Calendar.YEAR);
                int m = c.get(Calendar.MONTH);
                int d = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        mStartYear = year;
                        String outYear = String.valueOf(year);
                        mStartMonth = month;
                        String outMonth = String.valueOf(month + 1);
                        mStartDay = dayOfMonth;
                        String outDay = String.valueOf(dayOfMonth);
                        String outDate = outMonth + "/" + outDay + "/" + outYear;
                        startDateDisp.setText(outDate);
                    }
                };

                DatePickerDialog datePicker = new DatePickerDialog(v.getContext(), datePickerListener, y, m, d);
                datePicker.setCancelable(false);
                datePicker.setTitle("Select Date");
                datePicker.show();


            }
        });



        Button startTimeButton = findViewById(R.id.create_start_time_button);
        final TextView startTimeDisp = findViewById(R.id.create_start_time_disp);

        startTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int h = 0;
                int m = 0;
                TimePickerDialog.OnTimeSetListener timePickerListener = new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        mStartHour = hourOfDay;
                        String outHour = String.valueOf(hourOfDay);
                        mStartMinute = minute;
                        String outMinute = String.valueOf(minute);
                        String outTime = outHour + ":" + outMinute;
                        startTimeDisp.setText(outTime);
                    }
                };

                TimePickerDialog timePicker = new TimePickerDialog(v.getContext(), timePickerListener, h, m, false);
                timePicker.setCancelable(false);
                timePicker.setTitle("Select Time");
                timePicker.show();
            }
        });

        Button endDateButton = findViewById(R.id.create_end_date_button);
        final TextView endDateDisp = findViewById(R.id.create_end_date_disp);


        endDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar c = Calendar.getInstance(TimeZone.getDefault());

                int y = c.get(Calendar.YEAR);
                int m = c.get(Calendar.MONTH);
                int d = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        mEndYear = year;
                        String outYear = String.valueOf(year);
                        mEndMonth = month;
                        String outMonth = String.valueOf(month + 1);
                        mEndDay = dayOfMonth;
                        String outDay = String.valueOf(dayOfMonth);
                        String outDate = outMonth + "/" + outDay + "/" + outYear;
                        endDateDisp.setText(outDate);
                    }
                };

                DatePickerDialog datePicker = new DatePickerDialog(v.getContext(), datePickerListener, y, m, d);
                datePicker.setCancelable(false);
                datePicker.setTitle("Select Date");
                datePicker.show();


            }
        });



        Button endTimeButton = findViewById(R.id.create_end_time_button);
        final TextView endTimeDisp = findViewById(R.id.create_end_time_disp);

        endTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int h = 0;
                int m = 0;
                TimePickerDialog.OnTimeSetListener timePickerListener = new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        mEndHour = hourOfDay;
                        String outHour = String.valueOf(hourOfDay);
                        mEndMinute = minute;
                        String outMinute = String.valueOf(minute);
                        String outTime = outHour + ":" + outMinute;
                        endTimeDisp.setText(outTime);
                    }
                };

                TimePickerDialog timePicker = new TimePickerDialog(v.getContext(), timePickerListener, h, m, false);
                timePicker.setCancelable(false);
                timePicker.setTitle("Select Time");
                timePicker.show();
            }
        });

        final EditText descField = findViewById(R.id.create_desc);

        Button submitButton = findViewById(R.id.create_submit);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Event newEvent = new Event();
                Location outLoc = new Location();
                Address outAddr = new Address();
                newEvent.setName(nameField.getText().toString());


                outAddr.setStreet(streetField.getText().toString());
                outAddr.setCity(cityField.getText().toString());
                outAddr.setState(stateField.getText().toString());
                outAddr.setZip(zipField.getText().toString());
                outLoc.setAddress(outAddr);
                newEvent.setLocation(outLoc);

                LocalDate startDate = LocalDate.of(mStartYear, mStartMonth, mStartDay);
                LocalTime startTime = LocalTime.of(mStartHour, mStartMinute);

                OffsetDateTime startDateTime = OffsetDateTime.of(startDate, startTime, ZoneOffset.ofHours(-4));
                newEvent.setStartDatetime(startDateTime);


                LocalDate endDate = LocalDate.of(mEndYear, mEndMonth, mEndDay);
                LocalTime endTime = LocalTime.of(mEndHour, mEndMinute);

                OffsetDateTime endDateTime = OffsetDateTime.of(endDate, endTime, ZoneOffset.ofHours(-4));
                newEvent.setEndDatetime(endDateTime);

                newEvent.setDescription(descField.getText().toString());

                Bundle args = new Bundle();
                args.putSerializable("event", newEvent);
                args.putString("username", mUsername);
                args.putString("password", mPassword);
                if(!newEvent.equals(args.getSerializable("event"))){
                    System.err.println("event not serialized correctly");
                }
                startLoader(args);
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void startLoader(Bundle args){
        getLoaderManager().initLoader(2, args, this).forceLoad();
    }

    @Override
    public Loader<Event> onCreateLoader(int id, Bundle args){
        return new PostAsyncTask(CreateEventActivity.this, (Event) args.get("event"), args.getString("username"), args.getString("password"));
    }

    @Override
    public void onLoadFinished(Loader<Event> loader, Event data){
        if(data.getName() != null){
            Toast.makeText(getApplicationContext(), "Event successfully posted", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onLoaderReset(Loader<Event> loader){
        Toast.makeText(getApplicationContext(), "Loader reset", Toast.LENGTH_SHORT).show();
    }

    private static class PostAsyncTask extends AsyncTaskLoader<Event>{
        Event mEvent;
        String mUsername;
        String mPassword;
        PostAsyncTask(Context context, Object inEvent, String username, String password){
            super(context);
            mEvent = (Event) inEvent;
            mUsername = username;
            mPassword = password;
        }

        @Override
        public Event loadInBackground(){
            ApiClient defaultClient = Configuration.getDefaultApiClient();
            HttpBasicAuth basicAuth = (HttpBasicAuth) defaultClient.getAuthentication("basicAuth");
            basicAuth.setUsername(mUsername);
            basicAuth.setPassword(mPassword);

            EventApi apiInstance = new EventApi();

            try{
                apiInstance.createEvent(mEvent);
            } catch (ApiException e){
                System.err.println("Exception when calling EventApi.createEvent");
                System.err.println(e.getMessage());
                e.printStackTrace();
            }

            return mEvent;
        }

    }

}
