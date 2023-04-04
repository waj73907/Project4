package uga.edu.cs.project4quiz;

import android.content.Context;
import android.os.AsyncTask;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;

public class Question {
    Country questionCountry;
    String correctContinent;
    ArrayList<String> incorrectContinents = new ArrayList<>();

    ArrayList<String> continents = new ArrayList<>();

    @Override
    public String toString() {
        return "What is the continent of " + this.questionCountry.getCountryName() + "? " + this.correctContinent + " " + this.incorrectContinents.get(0) + " " + this.incorrectContinents.get(1);
    }

    Context context;
    CountryDatabaseReader countryReader;



    public Question(Context c) {
        this.context = c;
        this.countryReader = new CountryDatabaseReader(this.context);
        this.continents.add("Asia");
        this.continents.add("North America");
        this.continents.add("Europe");
        this.continents.add("South America");
        this.continents.add("Oceania");
        this.continents.add("Africa");
        this.makeQuestion();
    }


    public void makeQuestion() {
        Random randomInt = new Random();
        CountryDatabaseReader reader = new CountryDatabaseReader(this.context);
        AsyncTask<Void, Void, ArrayList<Country>> countryList = reader.execute();

        try {
            this.questionCountry = countryList.get().get(randomInt.nextInt(196));
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        this.correctContinent = this.questionCountry.getCountryContinent();
        this.continents.remove(this.correctContinent);

        this.incorrectContinents.add(this.continents.get(randomInt.nextInt(5)));
        this.continents.remove(this.incorrectContinents.get(0));
        this.incorrectContinents.add(this.continents.get(randomInt.nextInt(4)));



    }
}
