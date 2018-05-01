package com.i4i.hcvb.route26;

import android.content.pm.PackageManager;
import android.support.v4.content.AsyncTaskLoader;
import android.content.Context;

import org.threeten.bp.OffsetDateTime;

import java.util.ArrayList;
import java.util.List;

import io.swagger.client.ApiException;
import io.swagger.client.api.EventApi;
import io.swagger.client.model.Address;
import io.swagger.client.model.Event;
import io.swagger.client.model.Location;

public class EventLoader extends AsyncTaskLoader<List<Event>> {

    EventLoader(Context context){
        super(context);
    }

    private List<Event> eventList = new ArrayList<Event>();

    boolean DEBUG = true;

    @Override
    public List<Event> loadInBackground() {

        EventApi eventApi = new EventApi();
        try {
            for (int i = 1; i < 11; i++) {

                Event event = new Event();
                //TODO: Remove debug tools
                if(DEBUG) {
                    Location location = new Location();
                    Address address = new Address();
//                    OffsetDateTime startDateTime = new OffsetDateTime();

                    address.setCity("Littlestown");
                    address.setName("home");
                    address.setState("PA");
                    address.setZip("17340");
                    address.setStreet("545 Mud College Rd");
                    location.setAddress(address);
                    event.setName("Test " + i);
                    event.setLocation(location);
                    event.setDescription("This is a test");
                }else {
                    event = eventApi.getEventByID(i);
                }
                eventList.add(event);
            }
        } catch (ApiException e) {
            System.out.println(e.getMessage());
        }
        return eventList;
    }

//    @Override
//    public void deliverResult(List<Event> events) {
//        if(isReset()) {
//            if (events != null) {
//                onReleaseResources(events);
//            }
//        }
//        List<Event> oldEvents = eventList;
//        eventList = events;
//
//        if (isStarted()) {
//            super.deliverResult(events);
//        }
//
//        if (oldEvents != null) {
//            onReleaseResources(oldEvents);
//        }
//    }

//    @Override
//    protected void onStartLoading() {
//        if(eventList != null) {
//            deliverResult(eventList);
//        }
//
//        if(takeContentChanged()){
//            forceLoad();
//        }
//    }
//
//    @Override
//    protected void onStopLoading() {
//        cancelLoad();
//    }
//
//    @Override
//    public void onCanceled(List<Event> events) {
//        super.onCanceled(events);
//
//        onReleaseResources(events);
//    }
//
//    @Override
//    protected void onReset() {
//        super.onReset();
//
//        onStopLoading();
//
//        if (eventList != null){
//            onReleaseResources(eventList);
//            eventList = null;
//        }
//    }
//
//    private void onReleaseResources(List<Event> events) {
//    }
}
