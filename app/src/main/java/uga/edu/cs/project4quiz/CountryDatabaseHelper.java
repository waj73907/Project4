package uga.edu.cs.project4quiz;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class CountryDatabaseHelper extends SQLiteOpenHelper {

    public static final String COUNTRY_TABLE = "countries";
    public static final String COLUMN_ID = "COLUMN_ID";
    public static final String COLUMN_COUNTRY_NAME = "COLUMN_COUNTRY_NAME";
    public static final String COLUMN_CONTINENT_NAME = "COLUMN_CONTINENT_NAME";

    public CountryDatabaseHelper(@Nullable Context context) throws IOException {
        super(context, "country_continent.db", null, 1);
        this.copyDataBase(context);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    public void onUpgrade(SQLiteDatabase db, int newVersion, int oldVersion) {

    }

    public void copyDataBase(Context context)  {
        String DB_NAME = "country_continent.db";
        String DB_PATH = "/data/data/uga.edu.cs.project4quiz/databases/";
        //Open your local db as the input stream
        InputStream myInput = null;
        try {
            myInput = context.getAssets().open(DB_NAME);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Path to the just created empty db
        String outFileName = DB_PATH + DB_NAME;

        //Open the empty db as the output stream
        OutputStream myOutput = null;
        try {
            myOutput = new FileOutputStream(outFileName);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        //transfer bytes from the inputfile to the outputfile
        try {
            byte[] buffer = new byte[1024];
            int length;
            while ((length = myInput.read(buffer)) > 0) {
                myOutput.write(buffer, 0, length);
            }

            //Close the streams
            myOutput.flush();
            myOutput.close();
            myInput.close();
        } catch (IOException ioe) {
            throw new RuntimeException(ioe);
        }
    }
}
