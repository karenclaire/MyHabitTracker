package com.example.android.myhabittracker.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import com.example.android.myhabittracker.data.MyHabitTrackerContract.MyHabitTrackerEntry;

/**
 * Created by karenulmer on 7/6/2017.
 */

public class MyHabitTrackerDbHelper extends SQLiteOpenHelper {

    public static final String LOG_TAG = MyHabitTrackerDbHelper.class.getSimpleName();

    /** Name of the database file */
    private static final String DATABASE_NAME = "habit.db";
    /**
     * Database version. If you change the database schema, you must increment the database version.
     */
    private static final int DATABASE_VERSION = 1;

    /** Database helper that will provide us access to the database */
    private MyHabitTrackerDbHelper mDbHelper;

    // Constructs a new instance of {@link MyHabitTrackerDbHelper}.
    // @param context of the app
    public MyHabitTrackerDbHelper (Context context){
        super (context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate (SQLiteDatabase db){

        //Create a String that contains the SQL statement to create the habit table
        //CREATE_TABLE habit (_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT NOT NULL, breed TEXT,
        //gender INTEGER NOT NULL, weight INTEGER NOT NULL DEFAULT 0);
        String SQL_CREATE_HABIT_TABLE =  "CREATE TABLE " + MyHabitTrackerEntry.TABLE_NAME + " ("
                + MyHabitTrackerEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + MyHabitTrackerEntry.COLUMN_DATE + " TEXT NOT NULL, "
                + MyHabitTrackerEntry.COLUMN_ACTIVITY + " TEXT NOT NULL, "
                + MyHabitTrackerEntry.COLUMN_DURATION + " INTEGER NOT NULL, "
                + MyHabitTrackerEntry.COLUMN_FREQUENCY + " INTEGER NOT NULL DEFAULT 1)";
        // Execute the SQL statement
        db.execSQL(SQL_CREATE_HABIT_TABLE);
        Log.v(LOG_TAG,SQL_CREATE_HABIT_TABLE);
    }

    private void insertHabit(String date, String activity, int duration, int frequency){

        // TO update database
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        // / Create a ContentValues object where column names are the keys,

        ContentValues habitValues = new ContentValues();
        habitValues.put(MyHabitTrackerEntry.COLUMN_DATE, date);
        habitValues.put(MyHabitTrackerEntry.COLUMN_ACTIVITY, activity);
        habitValues.put(MyHabitTrackerEntry.COLUMN_DURATION, duration);
        habitValues.put(MyHabitTrackerEntry.COLUMN_FREQUENCY, frequency);
        db.insert(MyHabitTrackerEntry.TABLE_NAME, null, habitValues);


        // Insert a new row for pet in the database, returning the ID of that new row.
        long newRowId = db.insert(MyHabitTrackerEntry.TABLE_NAME, null, habitValues);



    }

    // This method is called when the dbase is updated
    @Override
    public void onUpgrade (SQLiteDatabase db, int oldVersion, int newVersion){

    }
}

