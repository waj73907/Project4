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

    If you select the Start Quiz button, it goes to the ViewPagerFragment, which works alongside
    ViewPagerAdapter to create 6 QuizQuestionFragments. Every time you proceed to the next question,
    it provides a different country and different answer choices.

    Question 6 is the last question, and after clicking Calculate Results button it will
    go to QuizResultsFragment, which shows how many you got right. It then stores your grade
    in the PastQuizFragment section.
 */

/*
    Still left to implement:
    1. Get rid of button on quiz questions screen. Possibly try to add it back on last question.
    If not able to add it back, find a way to end the quiz with user interaction.
    2. Update amount answered on screen every time a user clicks a radio button. Be able to access that
    number when the quiz ends.
    3. Only make a new set of quiz questions when there is no quiz in progress (upon app launch and completed quiz).
    4. Find a way to use the same Quiz object for all the classes.
    5. Save and restore: if the user clicks back or pauses the app, save all answer choices
    and pick up on the page they left.
    6. Every time a quiz is finished (the calculate results button is clicked),
    the Quiz toString() method is called in the Past Quiz section and recorded in the database.
    7. The Past Quizzes section is the only part of the quiz that keeps information always. It should connect
    to the database of previous quizzes.
    8. Implement amount answered correctly after figuring out how to view the results.
    9. When the back button is pressed, do NOT make a new batch of questions! Ever!
    10. Make the answer choices random. As of now, the first choice is always the correct one.

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