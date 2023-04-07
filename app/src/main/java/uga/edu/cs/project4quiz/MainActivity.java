package uga.edu.cs.project4quiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutionException;

/*
    How the program works:

    The program starts with the StartQuizFragment showing the splash page, where you
    have 2 options: start quiz or view past quizzes.

    If you select the View Past Quizzes button, it goes to the PastQuizFragment
    where it shows results from past quizzes.

    If you select the Start Quiz button, it goes to the QuizQuestionFragment, which is
    a fragment that holds two fragments (CountryNameFragment and AnswerChoiceFragment).
    Every time you proceed to the next question, it provides a different country and
    different answer choices by accessing those two fragments.

    Question 6 is the last question, and after clicking Calculate Results button it will
    go to QuizResultsFragment, which shows how many you got right. It then stores your grade
    in the PastQuizFragment section.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onBackPressed() {
        // transition to the new fragment
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContainerView, StartQuizFragment.newInstance());
        fragmentTransaction.commit();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }


}