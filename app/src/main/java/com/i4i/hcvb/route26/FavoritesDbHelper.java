package com.i4i.hcvb.route26;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class FavoritesDbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "favorites.db";
    public static final String TEXT_TYPE = " TEXT";
    public static final String DATETIME_TYPE = " DATETIME";
    public static final String COM_SEP = ", ";

    public static final String SQL_CREATE_FAVORITES = "CREATE TABLE " + FavoritesContract.FavoritesEntry.TABLE_NAME + " (" + FavoritesContract.FavoritesEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT" + COM_SEP + FavoritesContract.FavoritesEntry.COLUMN_EVENT_NAME + TEXT_TYPE + COM_SEP + FavoritesContract.FavoritesEntry.COLUMN_EVENT_DATE + DATETIME_TYPE + COM_SEP + FavoritesContract.FavoritesEntry.COLUMN_STREET_ADDRESS + TEXT_TYPE + COM_SEP + FavoritesContract.FavoritesEntry.COLUMN_EVENT_DESCRIP + TEXT_TYPE + " )";

    public static final String SQL_DELETE = "DROP TABLE IF EXISTS " + FavoritesContract.FavoritesEntry.TABLE_NAME;

    public FavoritesDbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context, name, factory, version);
    }

    public FavoritesDbHelper (Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(SQL_CREATE_FAVORITES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE);
        onCreate(db);
    }
}
