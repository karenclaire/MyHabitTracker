package com.example.android.myhabittracker.data;

        import android.provider.BaseColumns;

/**
 * Created by karenulmer on 7/6/2017.
 */

public final class MyHabitTrackerContract {

    private MyHabitTrackerContract (){}


    public static final class MyHabitTrackerEntry implements BaseColumns {

        public static final String TABLE_NAME = "habit";
        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_DATE = "date";
        public static final String COLUMN_ACTIVITY = "activity";
        public static final String COLUMN_DURATION = "duration";
        public static final String COLUMN_FREQUENCY = "frequency";

        // Duration Very Short = 14 minutes or less
        // Duration Short = 16 to 30 minutes
        // Duration Medium 31 to 45 minutes
        // Duration Long 46 to 75 minutes
        // Duration Very Long = more than 75 minutes
        public static final int DURATION_VERY_SHORT = 0;
        public static final int DURATION_SHORT = 1;
        public static final int DURATION_MEDIUM = 2;
        public static final int DURATION_LONG = 3;
        public static final int DURATION_VERY_LONG = 4;

    }
}
