/*
 Name: EventLoader.java
 Written by: Charles Bein
 Description: AsyncTaskLoader for browsing events
 */

package com.i4i.hcvb.route26;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import java.util.ArrayList;
import java.util.List;

import io.swagger.client.ApiException;
import io.swagger.client.api.EventApi;
import io.swagger.client.model.Event;

public class EventLoader extends AsyncTaskLoader<List<Event>> {

    private List<Event> eventList = new ArrayList<Event>();

    EventLoader(Context context) {
        super(context);
    }

    @Override
    public List<Event> loadInBackground() {

        EventApi eventApi = new EventApi();
        //TODO: Develop permanent solution to displaying all events on server
        //At present, there is no way to get the count of events from the server, so only the first 100 are fetched
        try {
            eventApi.getEventList(null, null, null);
            for (int i = 1; i < 100; i++) {

                Event event = new Event();
                event = eventApi.getEventByID(i);
                eventList.add(event);
            }
        } catch (ApiException e) {
            System.out.println(e.getMessage());
        }
        return eventList;
    }
}
