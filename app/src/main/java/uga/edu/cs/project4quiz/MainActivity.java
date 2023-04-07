package uga.edu.cs.project4quiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
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
    2. DONE Update amount answered every time a user clicks a radio button. Be able to access that
    number when the quiz ends.
    3. DONE Only make a new set of quiz questions when there is no quiz in progress (upon app launch and completed quiz).
    4. DONE Find a way to use the same Quiz object for all the classes.
    5. DONE Save and restore: if the user clicks back or pauses the app, save all answer choices
    and pick up on the page they left.
    6. DONE Every time a quiz is finished (the calculate results button is clicked),
    the Quiz toString() method is called in the Past Quiz section and recorded in the database.
    7. DONE The Past Quizzes section is the only part of the quiz that keeps information always. It should connect
    to the database of previous quizzes.
    8. DONE Implement amount answered correctly after figuring out how to view the results.
    10. Make the answer choices random. As of now, the first choice is always the correct one. You only have to
    make sure the correct answer is in a randomized position. Fill in the other incorrect choices wherever.
    11. DONE Store Quiz object as a static variable and get/set methods to access with other classes.
    12. DONE Make sure the quiz only adds to the database after the quiz results fragment is shown.

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

    // Used for PastQuizzes screen when trying to add quiz to database
    public ArrayList<Quiz> getAllQuizzes() {
        ArrayList<Quiz> quizzes = reader.readAllQuizzes();
        return quizzes;
    }

    public void onClicked(View view) {
        String strMain = qh.AnswerList.get(i-1);
        String[] arrSplit = strMain.split(",");
        if (((RadioButton)view).getText().equals(arrSplit[0])) {
            Log.d("onClicked Called:", "Is correct");
            amountCorrect++;
        }
        else {
            Log.d("onClicked Called:", "Not correct");
            Log.d("Value:", "" + ((RadioButton)view).getText());
            Log.d("Correct:", "" + arrSplit[0]);
        }

    }

    public int getCorrectAmount() {
        q.setAmountCorrect(amountCorrect); // updates correct amount to quiz database
        return amountCorrect;
    }

    public void addToDatabase(View view) {
        LinearLayout layout;
        layout = view.findViewById(R.id.linear);

        ArrayList<Quiz> quizzes = getAllQuizzes();
        for (int i = 0; i < quizzes.size(); i++) {
            TextView textView = new TextView(this);
            textView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            textView.setText(quizzes.get(i).toString());
            layout.addView(textView);
        }
    }

    public void writeToDatabase() {
        writer.writeQuiz(q); // adds current quiz to this list
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