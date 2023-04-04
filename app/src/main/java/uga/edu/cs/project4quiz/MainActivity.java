package uga.edu.cs.project4quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Question exampleQuestion = new Question(MainActivity.this);
        Log.d("QUESTION TEST", exampleQuestion.toString());
        Random randomInt = new Random();
        Quiz q = new Quiz(0, 5, "03-29-2023");
        CountryDatabaseHelper countryHelper = new CountryDatabaseHelper(MainActivity.this);
        try {
            countryHelper.copyDataBase(MainActivity.this);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        CountryDatabaseReader countryReader = new CountryDatabaseReader(MainActivity.this);
        Toast.makeText(MainActivity.this, countryReader.readAllCountries().get(randomInt.nextInt(196)).getCountryName(), Toast.LENGTH_LONG).show();
        try {
            ArrayList<Country> countryList = countryReader.execute().get();
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