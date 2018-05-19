package com.i4i.hcvb.route26;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import io.swagger.client.model.Event;

public class EventAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private List<Event> events;
    EventAdapter(Context context, List<Event> events) {
        this.events = events;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        Event event = (Event) getItem(position);
        if(view == null) {
            view = inflater.inflate(R.layout.browse_item, null);
        }
        TextView eventName = (TextView) view.findViewById(R.id.browse_name);
        eventName.setText(event.getName());

        return view;
    }

    @Override
    public Object getItem(int position) {
        return events.get(position);
    }

    @Override
    public long getItemId(int position){
        return position;
    }

    @Override
    public int getCount(){
        return events.size();
    }

    public void setEvents(List<Event> data) {
        events.addAll(data);
        notifyDataSetChanged();
    }
}
