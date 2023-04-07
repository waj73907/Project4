package uga.edu.cs.project4quiz;

import android.content.Context;
import android.os.AsyncTask;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;

/*
This class represents the question objects. Each instance of this class contains
the country that the question is being asked about, the correct continent in String
form, a list of the two incorrect answer choices inside the incorrect continents list,
and an array listing all of the possible continents.
 */
public class Question {
    Country questionCountry;
    String correctContinent;
    ArrayList<String> incorrectContinents = new ArrayList<>();

    ArrayList<String> continents = new ArrayList<>();

    Context context;
    CountryDatabaseReader countryReader;

    @Override
    public String toString() {
        return "What is the continent of " + this.questionCountry.getCountryName() + "? " + this.correctContinent + "," + this.incorrectContinents.get(0) + "," + this.incorrectContinents.get(1);
    }
    public String questionToString() {
        return "What is the continent of " + this.questionCountry.getCountryName() + "? ";
    }
    public String answersToString() {
        return this.correctContinent + "," + this.incorrectContinents.get(0) + "," + this.incorrectContinents.get(1);
    }
    /*
    This constructor takes in a context and fills the continents ArrayList
    with all of the possible continent answer choices.
     */

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

    /*
    This method actually generates the question.
    1. First it picks a country at random from the countries database.
    2. Then, the method assigns the continent of the randomly chosen country that the question will be asked about
        to the correctContinent variable (String type).
    3. Then, the method takes two randomly selected other continents and adds them into the incorrect answer
       list.
     */
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
