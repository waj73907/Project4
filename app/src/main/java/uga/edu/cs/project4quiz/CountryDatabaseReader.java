package uga.edu.cs.project4quiz;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.List;

public class CountryDatabaseReader extends AsyncTask<Void, Void, ArrayList<Country>> {
    Context ct;
    CountryDatabaseHelper helper;

    public CountryDatabaseReader(Context c) {
        this.ct = c;
        this.helper = new CountryDatabaseHelper(this.ct);
    }


    @Override
    protected ArrayList<Country> doInBackground(Void... voids) {
        return readAllCountries();
    }

    public ArrayList<Country> readAllCountries() {
        ArrayList<Country> returnList = new ArrayList<>();

        String queryString = "SELECT * FROM " + helper.COUNTRY_TABLE;
        SQLiteDatabase db = this.helper.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
            do {
                int countryID = cursor.getInt(0);
                String countryName = cursor.getString(1);
                String countryContinent = cursor.getString(2);
                Country c = new Country(countryID, countryName, countryContinent);
                returnList.add(c);
            } while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return returnList;
    }
}
