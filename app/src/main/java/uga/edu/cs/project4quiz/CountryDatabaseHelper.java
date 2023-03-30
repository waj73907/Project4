package uga.edu.cs.project4quiz;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class CountryDatabaseHelper extends SQLiteOpenHelper {

    public static final String COUNTRY_TABLE = "COUNTRY_TABLE";
    public static final String COLUMN_ID = "COLUMN_ID";
    public static final String COLUMN_COUNTRY_NAME = "COLUMN_COUNTRY_NAME";
    public static final String COLUMN_CONTINENT_NAME = "COLUMN_CONTINENT_NAME";

    public CountryDatabaseHelper(@Nullable Context context) {
        super(context, "country_continent.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    public void onUpgrade(SQLiteDatabase db, int newVersion, int oldVersion) {

    }
}
