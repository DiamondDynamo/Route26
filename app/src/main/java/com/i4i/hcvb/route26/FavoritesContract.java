/*
 Name: FavoritesContract.java
 Written by: Charles Bein
 Description: Database Contract for user favorites to be stored locally
 NOTE: Not up to date (should have start and end dates), or implemented
 */

package com.i4i.hcvb.route26;


import android.provider.BaseColumns;

public class FavoritesContract {

    public FavoritesContract() {

    }

    public static abstract class FavoritesEntry implements BaseColumns {
        public static final String TABLE_NAME = "favorites";
        public static final String COLUMN_EVENT_NAME = "event_name";
        public static final String COLUMN_EVENT_DATE = "event_date";
        public static final String COLUMN_STREET_ADDRESS = "street_address";
        public static final String COLUMN_EVENT_DESCRIP = "event_description";
    }
}
