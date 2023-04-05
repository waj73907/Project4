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
        QuestionsHolder qh = new QuestionsHolder(MainActivity.this);
        qh.generateQuestions();
        Log.d("TEST", String.valueOf(qh.QuestionList.size()));
        for (int i = 0; i < qh.QuestionList.size(); i++) {
            Log.d("Question Holder Test", qh.QuestionList.get(i).toString());
        }


    }


}