package uga.edu.cs.project4quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            CountryDatabaseHelper countryHelper = new CountryDatabaseHelper(MainActivity.this);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        QuestionsHolder qh = new QuestionsHolder(MainActivity.this);
        qh.generateQuestions();
        Log.d("TEST", String.valueOf(qh.QuestionList.size()));
        for (int i = 0; i < qh.QuestionList.size(); i++) {
            Log.d("Question Holder Test", qh.QuestionList.get(i).toString());
        }

        Quiz q = new Quiz(0, 5, "");
        QuizDatabaseWriter writer = new QuizDatabaseWriter(MainActivity.this);
        writer.execute(q);
        QuizDatabaseReader reader = new QuizDatabaseReader(MainActivity.this);
        try {
            Toast.makeText(MainActivity.this, reader.execute().get().toString(),Toast.LENGTH_LONG).show();
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


    }


}