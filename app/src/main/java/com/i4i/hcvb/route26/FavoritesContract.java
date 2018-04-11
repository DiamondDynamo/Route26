package com.i4i.hcvb.route26;


import android.provider.BaseColumns;

public class FavoritesContract {

    public FavoritesContract(){

    }

    public static abstract class FavoritesEntry implements BaseColumns {
        public static final String TABLE_NAME = "favorites";
        public static final String COLUMN_EVENT_NAME = "event_name";
        public static final String COLUMN_EVENT_DATE = "event_date";
        public static final String COLUMN_STREET_ADDRESS = "street_address";
        public static final String COLUMN_EVENT_DESCRIP = "event_description";
    }
}
