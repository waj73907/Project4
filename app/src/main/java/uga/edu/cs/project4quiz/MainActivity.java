package uga.edu.cs.project4quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Quiz q = new Quiz(0, 5, "03-29-2023");
        CountryDatabaseHelper countryHelper = new CountryDatabaseHelper(MainActivity.this);
        try {
            countryHelper.copyDataBase(MainActivity.this);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        CountryDatabaseReader countryReader = new CountryDatabaseReader(MainActivity.this);
        try {
            ArrayList<Country> countryList = countryReader.execute().get();
            String test = "not empty";
            if (countryList.isEmpty()) {
                test = "empty";
            }
            Toast.makeText(MainActivity.this,test, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Log.d("Error", e.getMessage());
        }
        QuizDatabaseHelper db = new QuizDatabaseHelper(MainActivity.this);
        QuizDatabaseWriter writer = new QuizDatabaseWriter(MainActivity.this);
        writer.execute(q);
        QuizDatabaseReader reader = new QuizDatabaseReader(MainActivity.this);
        try {
            ArrayList<Quiz> quizList = reader.execute().get();
        } catch (Exception e ) {
            Log.d("ERROR READING LIST: ", e.getMessage());
        }
    }


}