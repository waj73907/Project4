package uga.edu.cs.project4quiz;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.Random;

public class QuestionsHolder {
    CountryDatabaseReader countryReader;
    ArrayList<Country> countries;
    Context context;

    public QuestionsHolder(Context ct) {
        this.context = ct;
        this.countryReader = new CountryDatabaseReader(this.context);
    }

    public void makeQuestions() {
        Random randInt = new Random();
            countries = countryReader.readAllCountries();

        for (int i = 0; i < 5; i++) {
            Log.d("Question", "What is the continent of: " + countries.get(randInt.nextInt(196)).getCountryName() + "?");
        }
    }
}
