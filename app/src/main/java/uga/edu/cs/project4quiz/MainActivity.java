package uga.edu.cs.project4quiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
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
    1. DONE Get rid of button on quiz questions screen. Possibly try to add it back on last question.
    If not able to add it back, find a way to end the quiz with user interaction.
    2. Update amount answered every time a user clicks a radio button. Be able to access that
    number when the quiz ends.
    3. DONE Only make a new set of quiz questions when there is no quiz in progress (upon app launch and completed quiz).
    4. DONE Find a way to use the same Quiz object for all the classes.
    5. Save and restore: if the user clicks back or pauses the app, save all answer choices
    and pick up on the page they left.
    6. DONE Every time a quiz is finished (the calculate results button is clicked),
    the Quiz toString() method is called in the Past Quiz section and recorded in the database.
    7. DONE The Past Quizzes section is the only part of the quiz that keeps information always. It should connect
    to the database of previous quizzes.
    8. Implement amount answered correctly after figuring out how to view the results.
    10. Make the answer choices random. As of now, the first choice is always the correct one. You only have to
    make sure the correct answer is in a randomized position. Fill in the other incorrect choices wherever.
    11. Store Quiz object as a static variable and get/set methods to access with other classes.
    12. Implement onPause and onResume to restore data if the app is paused (go back to the page they were on).

 */
public class MainActivity extends AppCompatActivity {
    QuestionsHolder qh;
    static Quiz q;
    QuizDatabaseWriter writer;
    QuizDatabaseReader reader;
    int i; // used in setQuestions to populate the questions
    int amountCorrect; // used in correctAnswers to calculate total amount of correct questions

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
        try {
            CountryDatabaseHelper countryHelper = new CountryDatabaseHelper(this);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        i = 0;
        amountCorrect = 0;

        qh = new QuestionsHolder(this);
        qh.generateQuestions();
        Log.d("TEST", String.valueOf(qh.QuestionList.size()));
        for (int j = 0; j < qh.QuestionList.size(); j++) {
            Log.d("Questions Holder Test", qh.QuestionList.get(j) + qh.AnswerList.get(j));
        }


        q = new Quiz(0, 0, "");
        writer = new QuizDatabaseWriter(this);
        writer.execute(q);
        reader = new QuizDatabaseReader(this);

        /*
        try {
            Toast.makeText(this.getContext(), reader.execute().get().toString(),Toast.LENGTH_LONG).show();
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
         */
    }

    // Set the questions from the current quiz object into the quiz question fragment layout.
    public void setQuestions(TextView questions, RadioGroup answers) {
        questions.setText(qh.QuestionList.get(i));

        String strMain = qh.AnswerList.get(i);
        String[] arrSplit = strMain.split(",");
        for (int oCounter=0; oCounter < answers.getChildCount(); oCounter++) {
            ((RadioButton) answers.getChildAt(oCounter)).setText(arrSplit[oCounter]);
        }
        i++;
    }

    public int correctAnswers() {
        // If a radio button is clicked, we need to know which is clicked and if its text
        // matches the correct answer. If so, have a boolean list it as correct.
        // After the last question, have a boolean array with all the booleans from each question
        // and loop it to see how many correct answers there are.
        return amountCorrect;
    }

    // Used for PastQuizzes screen when trying to add quiz to database
    public ArrayList<Quiz> getAllQuizzes() {
        writer.writeQuiz(q);
        ArrayList<Quiz> quizzes = reader.readAllQuizzes();

        return quizzes;
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}