package uga.edu.cs.project4quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

/*
    How the program works:

    The program starts with the StartQuizFragment showing the splash page, where you
    have 2 options: start quiz or view past quizzes.

    If you select the View Past Quizzes button, it goes to the PastQuizFragment
    where it shows results from past quizzes.

    If you select the Start Quiz button, it goes to the ViewPagerFragment, which works alongside
    ViewPagerAdapter to create 6 QuizQuestionFragments. Every time you proceed to the next question,
    it provides a different country and different answer choices.

    Question 6 is the last question, and after swiping one last time, it will
    go to QuizResultsFragment, which shows how many you got right. It then stores your grade
    in the PastQuizFragment section.
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

        // Initialization of variables
        i = 0;
        amountCorrect = 0;

        // Create list of 6 questions
        qh = new QuestionsHolder(this);
        qh.generateQuestions();
        for (int j = 0; j < qh.QuestionList.size(); j++) {
            Log.d("Questions Holder Test", qh.QuestionList.get(j) + qh.AnswerList.get(j));
        }

        // Create quiz object
        q = new Quiz(0, 0, "");
        writer = new QuizDatabaseWriter(this);
        reader = new QuizDatabaseReader(this);
    }

    // Set the questions from the current quiz object into the quiz question fragment layout.
    public void setQuestions(TextView questions, RadioGroup answers) {
        questions.setText(qh.QuestionList.get(i)); // question
        String strMain = qh.AnswerList.get(i); // 3 answers per question
        String[] arrSplit = strMain.split(","); // turns answers into array of 3

        // Pick a random number between 0-2 and set it as the spot where the correct answer goes.
        // Both of the incorrect answers will go in the other spots (1+2, 0+2, or 0+1)
        Random random = new Random();
        int randomNumber = 0;
        randomNumber = random.nextInt(3);

        ((RadioButton) answers.getChildAt(randomNumber)).setText(arrSplit[0]); // correct
        // incorrect
        if (randomNumber == 0) {
            ((RadioButton) answers.getChildAt(1)).setText(arrSplit[1]);
            ((RadioButton) answers.getChildAt(2)).setText(arrSplit[2]);
         } else if (randomNumber == 1) {
            ((RadioButton) answers.getChildAt(0)).setText(arrSplit[1]);
            ((RadioButton) answers.getChildAt(2)).setText(arrSplit[2]);
        }  else {
            ((RadioButton) answers.getChildAt(0)).setText(arrSplit[1]);
            ((RadioButton) answers.getChildAt(1)).setText(arrSplit[2]);
        }
        i++;
    }

    // Used for PastQuizzes screen when trying to add quiz to database
    public ArrayList<Quiz> getAllQuizzes() {
        return reader.readAllQuizzes();
    }

    // Button event handler for RadioButtons (answer choices)
    public void onClicked(View view) {
        String strMain = qh.AnswerList.get(i-1);
        String[] arrSplit = strMain.split(",");
        if (((RadioButton)view).getText().equals(arrSplit[0])) { // if clicked answer is correct
            Log.d("onClicked Called:", "Is correct");
            amountCorrect++; // increment
        }
        else { // does nothing
            Log.d("onClicked Called:", "Not correct");
            Log.d("Value:", "" + ((RadioButton)view).getText());
            Log.d("Correct:", "" + arrSplit[0]);
        }
    }

    // returns amountCorrect variable, used for quiz results screen
    public int getCorrectAmount() {
        q.setAmountCorrect(amountCorrect); // updates correct amount to quiz database
        return amountCorrect;
    }

    // Prints all past quizzes to Past Quizzes screen without writing the current quiz
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

    // writes the current quiz to database, only called after quiz has ended
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