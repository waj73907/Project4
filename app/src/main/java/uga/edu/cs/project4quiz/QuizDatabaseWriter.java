package uga.edu.cs.project4quiz;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;

import java.nio.channels.AsynchronousChannelGroup;
import java.time.LocalDate;
/*
This class represents the object that will write a quiz to the quiz database.
In order to write a quiz to the database, simply create a new QuizDatabaseWriter instance and
call it's execute function with a quiz or list of quizzes as a parameter. This will write the
given quiz asynchronously.
 */
public class QuizDatabaseWriter extends AsyncTask<Quiz, Void, Boolean> {
    Context ct;
    QuizDatabaseHelper helper;
    public QuizDatabaseWriter(Context c) {
        this.ct = c;
        this.helper = new QuizDatabaseHelper(this.ct);
    }


    public boolean writeQuiz(Quiz q) {
        LocalDate date = LocalDate.now();
        q.setDateCompleted(date.toString());
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(helper.COLUMN_AMOUNT_CORRECT, q.getAmountCorrect());
        cv.put(helper.COLUMN_DATE_COMPLETED, q.getDateCompleted());

        long insert = db.insert(helper.QUIZ_TABLE, null, cv);

        if (insert == 1) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    protected Boolean doInBackground(Quiz... quizzes) {
        boolean success = false;
        for (int i = 0; i <quizzes.length; i++) {
            success = writeQuiz(quizzes[i]);
        }
        return success;
    }
}
