package com.example.android.myhabittracker;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.android.myhabittracker.data.MyHabitTrackerContract;
import com.example.android.myhabittracker.data.MyHabitTrackerContract.MyHabitTrackerEntry;
import com.example.android.myhabittracker.data.MyHabitTrackerDbHelper;

import static android.R.attr.description;
import static android.R.attr.duration;
import static android.R.attr.name;
import static com.example.android.myhabittracker.data.MyHabitTrackerDbHelper.LOG_TAG;


public class MainActivity extends AppCompatActivity {

    /** Database helper that will provide us access to the database */
    private MyHabitTrackerDbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private void insertHabit(){

        // Updates the database by writing
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        // Insert the new row, returning the primary key value of the new row
        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(MyHabitTrackerEntry.COLUMN_DATE, "6 July 2017");
        values.put(MyHabitTrackerEntry.COLUMN_ACTIVITY, "Boxing");
        values.put(MyHabitTrackerEntry.COLUMN_DURATION, MyHabitTrackerEntry.DURATION_LONG);
        values.put(MyHabitTrackerEntry.COLUMN_FREQUENCY, 2);
        db.insert(MyHabitTrackerEntry.TABLE_NAME, null, values);

        values.put(MyHabitTrackerEntry.COLUMN_DATE, "6 July 2017");
        values.put(MyHabitTrackerEntry.COLUMN_ACTIVITY, "Studying");
        values.put(MyHabitTrackerEntry.COLUMN_DURATION, MyHabitTrackerEntry.DURATION_VERY_LONG);
        values.put(MyHabitTrackerEntry.COLUMN_FREQUENCY, 1);
        db.insert(MyHabitTrackerEntry.TABLE_NAME, null, values);

        values.put(MyHabitTrackerEntry.COLUMN_DATE, "6 July 2017");
        values.put(MyHabitTrackerEntry.COLUMN_ACTIVITY, "Eating");
        values.put(MyHabitTrackerEntry.COLUMN_DURATION, MyHabitTrackerEntry.DURATION_VERY_SHORT);
        values.put(MyHabitTrackerEntry.COLUMN_FREQUENCY, 5);
        db.insert(MyHabitTrackerEntry.TABLE_NAME, null, values);

        values.put(MyHabitTrackerEntry.COLUMN_DATE, "6 July 2017");
        values.put(MyHabitTrackerEntry.COLUMN_ACTIVITY, "Showering");
        values.put(MyHabitTrackerEntry.COLUMN_DURATION, MyHabitTrackerEntry.DURATION_SHORT);
        values.put(MyHabitTrackerEntry.COLUMN_FREQUENCY, 2);
        db.insert(MyHabitTrackerEntry.TABLE_NAME, null, values);

        values.put(MyHabitTrackerEntry.COLUMN_DATE, "6 July 2017");
        values.put(MyHabitTrackerEntry.COLUMN_ACTIVITY, "Walking");
        values.put(MyHabitTrackerEntry.COLUMN_DURATION, MyHabitTrackerEntry.DURATION_MEDIUM);
        values.put(MyHabitTrackerEntry.COLUMN_FREQUENCY, 2);
        db.insert(MyHabitTrackerEntry.TABLE_NAME, null, values);

        //Insert a new rows for new activities in the database, returning the ID of that new row.
        // The first argument for db.insert() is the pets table name.
        db.insert(MyHabitTrackerEntry.TABLE_NAME, null, values);
    }


    @Override
    public void onStart(){
        super.onStart();
        displayDatabaseInfo();
    }

    /**
     * Temporary helper method to display information in the onscreen TextView about the state of
     * the pets database.
     */
    public Cursor displayDatabaseInfo() {

        //Create Datbase Helper
        mDbHelper = new MyHabitTrackerDbHelper(this);

        // Create and/or open a database to read from it
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        // Perform this raw SQL query "SELECT * FROM habit"
        // to get a Cursor that contains all rows from the pets table.
        String[] projection = {MyHabitTrackerEntry._ID,
                MyHabitTrackerEntry.COLUMN_DATE,
                MyHabitTrackerEntry.COLUMN_ACTIVITY,
                MyHabitTrackerEntry.COLUMN_DURATION,
                MyHabitTrackerEntry.COLUMN_FREQUENCY
        };

        Cursor cursor = db.query(MyHabitTrackerEntry.TABLE_NAME, projection, null, null, null, null, null);

        return (cursor);
    }

    public void readCursorInfo(Cursor cursor){

        try {

            // Figure out the index of each column
            int idColumnIndex = cursor.getColumnIndex(MyHabitTrackerEntry._ID);
            int dateColumnIndex = cursor.getColumnIndex(MyHabitTrackerEntry.COLUMN_DATE);
            int activityColumnIndex = cursor.getColumnIndex(MyHabitTrackerEntry.COLUMN_ACTIVITY);
            int durationColumnIndex = cursor.getColumnIndex(MyHabitTrackerEntry.COLUMN_DURATION);
            int frequencyColumnIndex = cursor.getColumnIndex(MyHabitTrackerEntry.COLUMN_FREQUENCY);

            // Iterate through all the returned rows in the cursor
            while (cursor.moveToNext()) {
                int currentID = cursor.getInt(idColumnIndex);
                String currentDate = cursor.getString(dateColumnIndex);
                String currentActivity = cursor.getString(activityColumnIndex);
                int currentDuration = cursor.getInt(durationColumnIndex);
                int currentFrequency = cursor.getInt(frequencyColumnIndex);
                // Display the values from each column of the current row in the cursor
                //db.query(MyHabitTrackerEntry.TABLE_NAME, projection, null, null, null, null, null);

                Log.i(LOG_TAG, (("\n" + currentID + " - " +
                       currentDate + " - " +
                       currentActivity + " - " +
                       currentDuration + " - " +
                       currentFrequency)));


            }
            cursor = displayDatabaseInfo();
            readCursorInfo(cursor);

        } finally {
            //  close the cursor and releases all its resources to make it invalid.
            cursor.close();
        }
    }



}