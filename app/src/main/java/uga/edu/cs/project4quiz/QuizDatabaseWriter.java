package uga.edu.cs.project4quiz;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;

import java.nio.channels.AsynchronousChannelGroup;
import java.time.LocalDate;
/*

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
